package com.gcu.business;

import com.gcu.data.MongoOrdersRepository;
import com.gcu.model.OrderList;
import com.gcu.model.OrderModel;
import com.gcu.model.OrderDocument;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/service")
public class OrdersRestService {

    @Autowired
    private OrdersBusinessInterface service;

    @Autowired
    private MongoOrdersRepository mongoRepo;

    // Return all orders as JSON
    @GetMapping(path = "/getjson", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<OrderModel> getOrdersAsJson() {
        return service.getOrders();
    }

    // Return all orders as XML
    @GetMapping(path = "/getxml", produces = MediaType.APPLICATION_XML_VALUE)
    public OrderList getOrdersAsXml() {
        OrderList orderList = new OrderList();
        orderList.setOrders(service.getOrders());
        return orderList;
    }

    @GetMapping(path = "/getjson/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<OrderModel> getOrderById(@PathVariable("id") String id) {
        return mongoRepo.findById(id)
            .map(doc -> ResponseEntity.ok(new OrderModel(
                doc.getId(),
                doc.getOrderNo(),
                doc.getProductName(),
                doc.getPrice(),
                doc.getQuantity()
            )))
            .orElse(ResponseEntity.noContent().build()); // 204 No Content for invalid ID
    }
}