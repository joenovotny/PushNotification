package com.gcu.business;

import com.gcu.data.MongoOrdersRepository;
import com.gcu.model.OrderModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.ArrayList;


@Primary
@Service
public class OrdersBusinessService implements OrdersBusinessInterface {

    @Autowired
    private MongoOrdersRepository ordersRepository;

    @Override
    public void test() {
        System.out.println("OrdersBusinessService (MongoDB) - test()");
    }

    @Override
    public List<OrderModel> getOrders() {
        List<OrderModel> orders = new ArrayList<>();
        ordersRepository.findAll().forEach(doc -> {
            orders.add(new OrderModel(
                doc.getId(),
                doc.getOrderNo(),
                doc.getProductName(),
                doc.getPrice(),
                doc.getQuantity()
            ));
        });
        return orders;
    }

    @Override
    public void init() {
        System.out.println("OrdersBusinessService - init()");
    }

    @Override
    public void destroy() {
        System.out.println("OrdersBusinessService - destroy()");
    }
}