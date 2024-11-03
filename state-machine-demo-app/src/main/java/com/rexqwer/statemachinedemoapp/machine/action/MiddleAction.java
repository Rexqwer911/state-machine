package com.rexqwer.statemachinedemoapp.machine.action;

import com.rexqwer.statemachinedemoapp.machine.enums.ContextKeys;
import com.rexqwer.statemachinedemoapp.machine.enums.OrderEvent;
import com.rexqwer.statemachinedemoapp.machine.enums.OrderState;
import com.rexqwer.statemachine.util.ContextUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.statemachine.StateContext;
import org.springframework.statemachine.action.Action;

@Slf4j
public class MiddleAction implements Action<OrderState, OrderEvent> {
    @Override
    public void execute(StateContext<OrderState, OrderEvent> context) {
        String orderId = context.getStateMachine().getId();
        OrderEvent event = OrderEvent.MIDDLE_EVENT;
        log.info("OrderID {}, Starting executing {}", orderId, event);
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        // putting object into the context
        ContextUtils.put(context, ContextKeys.ORDER_STATUS, "SOME_ORDER_STATUS");

        log.info("OrderID {}, Finishing executing {}", orderId, event);
    }
}
