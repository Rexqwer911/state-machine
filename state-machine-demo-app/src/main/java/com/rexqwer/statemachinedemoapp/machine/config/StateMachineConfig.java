package com.rexqwer.statemachinedemoapp.machine.config;

import com.rexqwer.statemachinedemoapp.machine.action.*;
import com.rexqwer.statemachinedemoapp.machine.enums.OrderEvent;
import com.rexqwer.statemachinedemoapp.machine.enums.OrderState;
import com.rexqwer.statemachinedemoapp.machine.guard.StateGuard;
import com.rexqwer.statemachinedemoapp.machine.manager.OrderStateMachineManager;
import com.rexqwer.statemachine.executor.EventExecutor;
import com.rexqwer.statemachine.executor.PersistableClearContextEventExecutor;
import com.rexqwer.statemachine.persist.InMemoryStateMachinePersister;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.statemachine.action.Action;
import org.springframework.statemachine.config.EnableStateMachineFactory;
import org.springframework.statemachine.config.EnumStateMachineConfigurerAdapter;
import org.springframework.statemachine.config.builders.StateMachineStateConfigurer;
import org.springframework.statemachine.config.builders.StateMachineTransitionConfigurer;
import org.springframework.statemachine.guard.Guard;

import java.util.concurrent.ConcurrentHashMap;

import static com.rexqwer.statemachinedemoapp.machine.enums.OrderEvent.*;
import static com.rexqwer.statemachinedemoapp.machine.enums.OrderState.*;

@Configuration
@EnableStateMachineFactory
public class StateMachineConfig extends EnumStateMachineConfigurerAdapter<OrderState, OrderEvent> {

    @Override
    public void configure(final StateMachineStateConfigurer<OrderState, OrderEvent> states) throws Exception {
        states
                .withStates()
                .initial(NEW)
                .state(START)
                .state(MIDDLE)
                .fork(FORK)
                .join(JOIN)
                .state(PRE_FINISH)
                .end(FINISH)

                .and()
                    .withStates()
                        .parent(FORK)
                        .initial(FORK_FIRST_START)
                        .end(FORK_FIRST_FINISH)
                .and()
                    .withStates()
                        .parent(FORK)
                        .initial(FORK_SECOND_START)
                        .end(FORK_SECOND_FINISH)
        ;
    }

    @Override
    public void configure(final StateMachineTransitionConfigurer<OrderState, OrderEvent> transitions) throws Exception {
        transitions
                .withExternal()
                .source(NEW)
                .target(START)
                .event(START_EVENT)
                .guard(stateGuard())
                .action(new StartAction(), errorAction())

                .and()
                .withExternal()
                .source(START)
                .target(MIDDLE)
                .event(MIDDLE_EVENT)
                .guard(stateGuard())
                .action(new MiddleAction(), errorAction())

                .and()
                .withExternal()
                .source(MIDDLE)
                .target(FORK)
                .event(FORK_EVENT)
                .guard(stateGuard())
                .action(new ForkAction(), errorAction())

                .and()
                .withFork()
                .source(FORK)
                .target(FORK_FIRST_START)
                .target(FORK_SECOND_START)

                .and()
                .withExternal()
                .source(FORK_FIRST_START)
                .target(FORK_FIRST_FINISH)
                .event(FORK_FIRST_EVENT)
                .guard(stateGuard())
                .action(new ForkFirstAction(), errorAction())

                .and()
                .withExternal()
                .source(FORK_SECOND_START)
                .target(FORK_SECOND_FINISH)
                .event(FORK_SECOND_EVENT)
                .guard(stateGuard())
                .action(new ForkSecondAction(), errorAction())

                .and()
                .withJoin()
                .source(FORK_FIRST_FINISH)
                .source(FORK_SECOND_FINISH)
                .target(JOIN)

                .and()
                .withExternal()
                .source(JOIN)
                .target(PRE_FINISH)

                .and()
                .withExternal()
                .source(PRE_FINISH)
                .target(FINISH)
                .event(FINISH_EVENT)
                .guard(stateGuard())
                .action(new FinishAction(), errorAction())
        ;

    }

    @Bean
    public Action<OrderState, OrderEvent> errorAction() {
        return new ErrorAction();
    }

    @Bean
    public Guard<OrderState, OrderEvent> stateGuard() {
        return new StateGuard();
    }

    @Bean
    public EventExecutor<OrderEvent, String> eventExecutor(OrderStateMachineManager machineManager) {
        return new PersistableClearContextEventExecutor<>(machineManager);
    }

    @Bean
    public InMemoryStateMachinePersister<OrderState, OrderEvent, String> inMemoryStateMachinePersister() {
        return new InMemoryStateMachinePersister<>(new ConcurrentHashMap<>());
    }
}
