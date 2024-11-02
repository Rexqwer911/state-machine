package com.rexqwer.statemachine.persist;

import org.springframework.statemachine.StateMachineContext;
import org.springframework.statemachine.StateMachinePersist;
import org.springframework.statemachine.persist.AbstractStateMachinePersister;

import java.util.concurrent.ConcurrentMap;

public class InMemoryStateMachinePersister<S, E, T> extends AbstractStateMachinePersister<S, E, T> {

    private final ConcurrentMap<Object, StateMachineContext<S, E>> memory;

    public InMemoryStateMachinePersister(
            ConcurrentMap<Object, StateMachineContext<S, E>> memory) {
        super(new StateMachinePersist<>() {
            @Override
            public void write(StateMachineContext<S, E> context, T contextObj) {
                memory.put(contextObj, context);
            }

            @Override
            public StateMachineContext<S, E> read(T contextObj) {
                return memory.get(contextObj);
            }
        });
        this.memory = memory;
    }

    public boolean containsMachine(String orderId) {
        return this.memory.containsKey(orderId);
    }
}
