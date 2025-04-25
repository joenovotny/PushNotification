package com.gcu.business;

import com.gcu.data.MongoOrdersRepository;
import com.gcu.model.OrderDocument;
import com.gcu.model.OrderModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service("mongoBusinessService")
public class MongoOrdersBusinessService implements OrdersBusinessInterface {

    @Autowired
    private MongoOrdersRepository mongoRepo;

    @Override
    public void test() {
        System.out.println("MongoOrdersBusinessService - test()");
    }

    @Override
    public List<OrderModel> getOrders() {
        List<OrderModel> orders = new ArrayList<>();
        List<OrderDocument> docs = mongoRepo.findAll();

        for (OrderDocument doc : docs) {
            orders.add(new OrderModel(
                doc.getId(),
                doc.getOrderNo(),
                doc.getProductName(),
                doc.getPrice(),
                doc.getQuantity()
            ));
        }

        return orders;
    }

    @Override
    public void init() {
        System.out.println("MongoOrdersBusinessService - init()");
    }

    @Override
    public void destroy() {
        System.out.println("MongoOrdersBusinessService - destroy()");
    }
}