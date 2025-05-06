package com.mycompany.testapi.dao;

import com.mycompany.testapi.model.Order;
import com.mycompany.testapi.model.Orderitem;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class OrderDao {
    private static final Map<Integer, List<Order>> ordersByCustomer = new HashMap<>();
    private static int currentId = 1;

    public Order createOrder(int customerId, List<Orderitem> items) {
        Order order = new Order(currentId++, customerId, new Date());
        order.setItems(items);

        ordersByCustomer.computeIfAbsent(customerId, k -> new ArrayList<>()).add(order);

        return order;
    }

    public List<Order> getOrdersByCustomerId(int customerId) {
        return ordersByCustomer.getOrDefault(customerId, new ArrayList<>());
    }

    public Order getOrderById(int customerId, int orderId) {
        List<Order> orders = ordersByCustomer.get(customerId);
        if (orders != null) {
            for (Order order : orders) {
                if (order.getId() == orderId) {
                    return order;
                }
            }
        }
        return null;
    }
}
