package com.rexqwer.statemachinedemoapp.controller;

import com.rexqwer.statemachinedemoapp.machine.enums.ContextKeys;
import com.rexqwer.statemachinedemoapp.machine.enums.OrderEvent;
import com.rexqwer.statemachine.executor.EventExecutor;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("order-controller")
@RequiredArgsConstructor
@Tag(name = "Order")
@Slf4j
public class OrderActionsController {

    private final EventExecutor<OrderEvent, String> eventExecutor;

    @PostMapping(path = "{orderId}/start")
    @Operation(summary = "EVENT START")
    public void start(@PathVariable String orderId) {
        OrderEvent event = OrderEvent.START_EVENT;
        log.info("(controller) event {} initiated", event);
        eventExecutor.executeEvent(orderId, event);
        log.info("(controller) event {} completed", event);
    }

    @PostMapping(path = "{orderId}/middle")
    @Operation(summary = "EVENT MIDDLE")
    public String middle(@PathVariable String orderId) {
        OrderEvent event = OrderEvent.MIDDLE_EVENT;
        log.info("(controller) event {} initiated", event);
        // define the class you want to return
        Class<String> responseClass = String.class;
        String response = eventExecutor.executeEvent(
                orderId, OrderEvent.MIDDLE_EVENT, ContextKeys.ORDER_STATUS, responseClass);
        log.info("(controller) event {} completed", event);
        return response;
    }

    @PostMapping(path = "{orderId}/fork")
    @Operation(summary = "EVENT FORK")
    public void fork(@PathVariable String orderId) {
        OrderEvent event = OrderEvent.FORK_EVENT;
        log.info("(controller) event {} initiated", event);
        eventExecutor.executeEvent(orderId, event);
        log.info("(controller) event {} completed", event);
    }

    @PostMapping(path = "{orderId}/forkFirst")
    @Operation(summary = "EVENT FORK FIRST")
    public void forkFirst(@PathVariable String orderId) {
        OrderEvent event = OrderEvent.FORK_FIRST_EVENT;
        log.info("(controller) event {} initiated", event);
        eventExecutor.executeEvent(orderId, event);
        log.info("(controller) event {} completed", event);
    }

    @PostMapping(path = "{orderId}/forkSecond")
    @Operation(summary = "EVENT FORK SECOND")
    public void forkSecond(@PathVariable String orderId) {
        OrderEvent event = OrderEvent.FORK_SECOND_EVENT;
        log.info("(controller) event {} initiated", event);
        eventExecutor.executeEvent(orderId, event);
        log.info("(controller) event {} completed", event);
    }

    @PostMapping(path = "{orderId}/finish")
    @Operation(summary = "EVENT FINISH")
    public void finish(@PathVariable String orderId) {
        OrderEvent event = OrderEvent.FINISH_EVENT;
        log.info("(controller) event {} initiated", event);
        eventExecutor.executeEvent(orderId, event);
        log.info("(controller) event {} completed", event);
    }
}
