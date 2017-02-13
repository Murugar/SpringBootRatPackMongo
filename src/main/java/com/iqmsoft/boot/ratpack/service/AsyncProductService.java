package com.iqmsoft.boot.ratpack.service;

import java.util.List;

import com.iqmsoft.boot.ratpack.model.Product;

import ratpack.exec.Promise;

public interface AsyncProductService {

	Promise<Product> getById(String id);
	
	Promise<List<Product>> list();
}
