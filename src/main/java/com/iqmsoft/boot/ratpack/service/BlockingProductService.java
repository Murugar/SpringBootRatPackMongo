package com.iqmsoft.boot.ratpack.service;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import com.iqmsoft.boot.ratpack.model.Product;

public class BlockingProductService implements ProductService{
	
	private static final List<Product> STORAGE;
	
	static{
		STORAGE = Arrays.asList(new Product("Car"), new Product("Laptop"));
	}
	
	@Override
	public Product getById(String id) {
		for(Product product : STORAGE){
			if(product.getId() == id){
				return product;
			}
		}
		return null;
	}

	@Override
	public List<Product> list() {
		return Collections.unmodifiableList(STORAGE);
	}

}
