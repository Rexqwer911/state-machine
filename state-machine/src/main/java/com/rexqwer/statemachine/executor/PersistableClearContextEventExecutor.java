package com.rexqwer.statemachine.executor;

import com.rexqwer.statemachine.exception.EventNotExecutedException;
import com.rexqwer.statemachine.keys.DefaultContextKeys;
import com.rexqwer.statemachine.manager.PersistableStateMachineManager;
import com.rexqwer.statemachine.util.ContextUtils;
import lombok.SneakyThrows;
import org.springframework.statemachine.StateMachine;

import java.util.HashMap;
import java.util.Map;

public class PersistableClearContextEventExecutor<S, E, K> implements EventExecutor<E, K> {

    private final PersistableStateMachineManager<S, E, K> persistableStateMachineManager;

    public PersistableClearContextEventExecutor(PersistableStateMachineManager<S, E, K> persistableStateMachineManager) {
        this.persistableStateMachineManager = persistableStateMachineManager;
    }

    @Override
    public <V> V executeEvent(K key, E event, Object valueKey, Class<V> clazz) {
        Map<Object, Object> variables = process(key, event);
        checkException(variables);
        return ContextUtils.getFor(variables, valueKey, clazz);
    }

    @Override
    public void executeEvent(K key, E event) {
        Map<Object, Object> variables = process(key, event);
        checkException(variables);
    }

    private Map<Object, Object> process(K key, E event) {

        StateMachine<S, E> stateMachine = persistableStateMachineManager.provideStateMachine(key);

        boolean eventExecuted = stateMachine.sendEvent(event);

        Map<Object, Object> variables = new HashMap<>(stateMachine.getExtendedState().getVariables());

        stateMachine.getExtendedState().getVariables().clear();

        if (eventExecuted) {
            persistableStateMachineManager.persist(stateMachine, key);
        } else {
            variables.put(DefaultContextKeys.EXCEPTION, new EventNotExecutedException("Event %s not executed".formatted(event)));
        }

        persistableStateMachineManager.stop(stateMachine);

        return variables;
    }

    @SneakyThrows
    private void checkException(Map<Object, Object> variables) {
        if (variables.containsKey(DefaultContextKeys.EXCEPTION)) {
            throw ContextUtils.getFor(variables, DefaultContextKeys.EXCEPTION, Exception.class);
        }
    }
}
