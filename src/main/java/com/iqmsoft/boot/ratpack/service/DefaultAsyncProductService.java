package com.iqmsoft.boot.ratpack.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.iqmsoft.boot.ratpack.model.Product;
import com.iqmsoft.boot.ratpack.repos.ProductRepository;

import ratpack.exec.Blocking;
import ratpack.exec.Promise;

@Component
public class DefaultAsyncProductService implements AsyncProductService{

	private ProductRepository repository;
	
	@Autowired
	public DefaultAsyncProductService(ProductRepository repository){
		this.repository = repository;
	}
	
	@Override
	public Promise<Product> getById(String id) {
		return Blocking.get(() -> repository.findOne(id));
	}

	@Override
	public Promise<List<Product>> list() {
		return Blocking.get(() -> {
			List<Product> list = new ArrayList<>();
			repository.findAll().forEach(list::add);
			return list;
		});
	}

}
