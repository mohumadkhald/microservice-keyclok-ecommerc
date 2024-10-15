package org.springframework.samples.notificationservice.event;

import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class NotificationService {

    @KafkaListener(topics = "${spring.kafka.topic.order}", groupId = "notification-group")
    public void handleOrderNotification(OrderPlacedEvent orderPlacedEvent) {
        // Logic to send notification (e.g., via email)
        log.info("Received Order ID: {} for notification", orderPlacedEvent.getOrderId());
        // Send notification logic here
    }
}
