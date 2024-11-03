package com.rexqwer.statemachinedemoapp.machine.manager;

import com.rexqwer.statemachinedemoapp.machine.enums.OrderEvent;
import com.rexqwer.statemachinedemoapp.machine.enums.OrderState;
import com.rexqwer.statemachine.manager.PersistableStateMachineManager;
import com.rexqwer.statemachine.persist.InMemoryStateMachinePersister;
import lombok.extern.slf4j.Slf4j;
import org.springframework.statemachine.StateMachine;
import org.springframework.statemachine.config.StateMachineFactory;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class OrderStateMachineManager extends PersistableStateMachineManager<OrderState, OrderEvent, String> {

    private final StateMachineFactory<OrderState, OrderEvent>                       stateMachineFactory;
    private final InMemoryStateMachinePersister<OrderState, OrderEvent, String>     persister;

    public OrderStateMachineManager(InMemoryStateMachinePersister<OrderState, OrderEvent, String> persister,
                                    StateMachineFactory<OrderState, OrderEvent> stateMachineFactory) {
        super(persister);
        this.persister = persister;
        this.stateMachineFactory = stateMachineFactory;
    }

    @Override
    public StateMachine<OrderState, OrderEvent> provideStateMachine(String orderId) {
        try {
            StateMachine<OrderState, OrderEvent> stateMachine = stateMachineFactory.getStateMachine(orderId);
            if (persister.containsMachine(orderId)) {
                persister.restore(stateMachine, orderId);
            }
            stateMachine.start();
            return stateMachine;
        } catch (Exception e) {
            log.error("Error while providing state machine for OrderID {}", orderId, e);
            throw new IllegalStateException("Error while providing state machine for OrderID " + orderId);
        }
    }
}
