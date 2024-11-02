package com.rexqwer.statemachine.manager;

import org.springframework.statemachine.StateMachine;
import org.springframework.statemachine.persist.AbstractStateMachinePersister;

public abstract class PersistableStateMachineManager<S, E, K> implements StateMachineManager<S, E, K> {

    private final AbstractStateMachinePersister<S, E, K> persister;

    public PersistableStateMachineManager(AbstractStateMachinePersister<S, E, K> persister) {
        this.persister = persister;
    }

    public void persist(StateMachine<S, E> stateMachine, K key) {
        try {
            persister.persist(stateMachine, key);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public void stop(StateMachine<S, E> stateMachine) {
        stateMachine.stop();
    }
}