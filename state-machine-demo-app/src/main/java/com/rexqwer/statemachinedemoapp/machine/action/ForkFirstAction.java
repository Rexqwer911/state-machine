package com.rexqwer.statemachinedemoapp.machine.action;

import com.rexqwer.statemachinedemoapp.machine.enums.OrderEvent;
import com.rexqwer.statemachinedemoapp.machine.enums.OrderState;
import lombok.extern.slf4j.Slf4j;
import org.springframework.statemachine.StateContext;
import org.springframework.statemachine.action.Action;

@Slf4j
public class ForkFirstAction implements Action<OrderState, OrderEvent> {
    @Override
    public void execute(final StateContext<OrderState, OrderEvent> context) {
        String orderId = context.getStateMachine().getId();
        OrderEvent event = OrderEvent.FORK_FIRST_EVENT;
        log.info("OrderID {}, Starting executing {}", orderId, event);
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        log.info("OrderID {}, Finishing executing {}", orderId, event);
    }
}
