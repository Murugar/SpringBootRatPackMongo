package com.iqmsoft.boot.ratpack.repos;

import org.springframework.stereotype.Repository;

import com.iqmsoft.boot.ratpack.model.Product;
import org.springframework.data.mongodb.repository.MongoRepository;


@Repository
public interface ProductRepository extends MongoRepository<Product, String>{

}
