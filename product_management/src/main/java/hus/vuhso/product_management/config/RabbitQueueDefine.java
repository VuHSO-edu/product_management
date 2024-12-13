package hus.vuhso.product_management.config;

import org.springframework.amqp.core.AmqpAdmin;
import org.springframework.amqp.core.Queue;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RabbitQueueDefine {
    @Autowired
    AmqpAdmin amqpAdmin;

    public Queue initQueue() {
        Queue queue = new Queue("product_queue");
        amqpAdmin.declareQueue(queue);
        return queue;
    }
}
