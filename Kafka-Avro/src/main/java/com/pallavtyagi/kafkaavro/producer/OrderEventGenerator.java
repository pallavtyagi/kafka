package com.pallavtyagi.kafkaavro.producer;

import com.pallavtyagi.kafkaavro.model.Order;

public class OrderEventGenerator {


    public Order generateEvent() {
        return Order.newBuilder().setOderId(Math.random()+"").setItemName("XX Order").setQuantity(3).build();
    }


}
