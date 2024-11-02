package com.rexqwer.statemachine.manager;

import org.springframework.statemachine.StateMachine;

public interface StateMachineManager<S, E, K> {

    StateMachine<S, E> provideStateMachine(K key);
}