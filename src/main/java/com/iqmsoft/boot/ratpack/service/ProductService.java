package com.iqmsoft.boot.ratpack.service;

import java.util.List;

import com.iqmsoft.boot.ratpack.model.Product;

public interface ProductService {
	
	Product getById(String id);
	
	List<Product> list();
}
