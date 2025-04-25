package com.gcu.data;

import com.gcu.model.OrderDocument;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MongoOrdersRepository extends MongoRepository<OrderDocument, String> {}