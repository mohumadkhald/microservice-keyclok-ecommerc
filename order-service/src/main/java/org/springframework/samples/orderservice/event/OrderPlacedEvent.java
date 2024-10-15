package org.springframework.samples.orderservice.event;

import lombok.Getter;
import lombok.Setter;
import org.springframework.context.ApplicationEvent;


@Getter
@Setter
public class OrderPlacedEvent extends ApplicationEvent {
    private String orderId;

    public OrderPlacedEvent(Object source, String orderNumber) {
        super(source);
        this.orderId = orderNumber;
    }

    public OrderPlacedEvent(String orderNumber) {
        super(orderNumber);
        this.orderId = orderNumber;
    }
}
