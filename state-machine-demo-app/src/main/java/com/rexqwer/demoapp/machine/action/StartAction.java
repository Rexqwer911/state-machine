package com.rexqwer.demoapp.machine.action;

import com.rexqwer.demoapp.machine.enums.OrderEvent;
import com.rexqwer.demoapp.machine.enums.OrderState;
import lombok.extern.slf4j.Slf4j;
import org.springframework.statemachine.StateContext;
import org.springframework.statemachine.action.Action;

@Slf4j
public class StartAction implements Action<OrderState, OrderEvent> {
    @Override
    public void execute(final StateContext<OrderState, OrderEvent> context) {
        String orderId = context.getStateMachine().getId();
        OrderEvent event = OrderEvent.START_EVENT;
        log.info("OrderID {}, Starting executing {}", orderId, event);
        try {
            Thread.sleep(10000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        log.info("OrderID {}, Finishing executing {}", orderId, event);
    }
}
