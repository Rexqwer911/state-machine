package com.rexqwer.statemachinedemoapp.machine.guard;

import com.rexqwer.statemachinedemoapp.machine.enums.OrderEvent;
import com.rexqwer.statemachinedemoapp.machine.enums.OrderState;
import org.springframework.statemachine.StateContext;
import org.springframework.statemachine.guard.Guard;

public class StateGuard implements Guard<OrderState, OrderEvent> {

    @Override
    public boolean evaluate(StateContext<OrderState, OrderEvent> context) {
        return true;
    }
}