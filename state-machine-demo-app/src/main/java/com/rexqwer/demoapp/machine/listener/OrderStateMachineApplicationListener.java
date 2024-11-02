package com.rexqwer.demoapp.machine.listener;

import com.rexqwer.demoapp.machine.enums.OrderEvent;
import com.rexqwer.demoapp.machine.enums.OrderState;
import lombok.extern.slf4j.Slf4j;
import org.springframework.messaging.Message;
import org.springframework.statemachine.StateContext;
import org.springframework.statemachine.StateMachine;
import org.springframework.statemachine.listener.StateMachineListener;
import org.springframework.statemachine.state.State;
import org.springframework.statemachine.transition.Transition;

@Slf4j
public class OrderStateMachineApplicationListener implements StateMachineListener<OrderState, OrderEvent> {

    // used for logging and tracing state machine conditions

    @Override
    public void stateChanged(State<OrderState, OrderEvent> from, State<OrderState, OrderEvent> to) {
    }

    @Override
    public void stateEntered(State<OrderState, OrderEvent> state) {
    }

    @Override
    public void stateExited(State<OrderState, OrderEvent> state) {
    }

    @Override
    public void eventNotAccepted(Message<OrderEvent> event) {
    }

    @Override
    public void transition(Transition<OrderState, OrderEvent> transition) {
    }

    @Override
    public void transitionStarted(Transition<OrderState, OrderEvent> transition) {
    }

    @Override
    public void transitionEnded(Transition<OrderState, OrderEvent> transition) {
    }

    @Override
    public void stateMachineStarted(StateMachine<OrderState, OrderEvent> stateMachine) {
    }

    @Override
    public void stateMachineStopped(StateMachine<OrderState, OrderEvent> stateMachine) {
    }

    @Override
    public void stateMachineError(StateMachine<OrderState, OrderEvent> stateMachine, Exception exception) {
    }

    @Override
    public void extendedStateChanged(Object key, final Object value) {
    }

    @Override
    public void stateContext(StateContext<OrderState, OrderEvent> stateContext) {
    }
}