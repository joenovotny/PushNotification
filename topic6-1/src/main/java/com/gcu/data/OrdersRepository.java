package com.gcu.data;

import com.gcu.data.entity.OrderEntity;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrdersRepository extends MongoRepository<OrderEntity, String> {
    // MongoDB auto-generates basic queries like findAll(), save(), deleteById()
}