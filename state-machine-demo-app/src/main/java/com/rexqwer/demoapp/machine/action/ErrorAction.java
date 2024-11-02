package com.rexqwer.demoapp.machine.action;

import com.rexqwer.demoapp.machine.enums.OrderEvent;
import com.rexqwer.demoapp.machine.enums.OrderState;
import com.rexqwer.statemachine.keys.DefaultContextKeys;
import com.rexqwer.statemachine.util.ContextUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.statemachine.StateContext;
import org.springframework.statemachine.action.Action;

@Slf4j
public class ErrorAction implements Action<OrderState, OrderEvent> {
    @Override
    public void execute(final StateContext<OrderState, OrderEvent> context) {
        String orderId = context.getStateMachine().getId();
        log.info("OrderID {}, Starting executing ERROR_EVENT", orderId);
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        // putting exception to the context
        ContextUtils.put(context, DefaultContextKeys.EXCEPTION, context.getException());

        log.info("OrderID {}, Finishing executing ERROR_EVENT", orderId);
    }
}