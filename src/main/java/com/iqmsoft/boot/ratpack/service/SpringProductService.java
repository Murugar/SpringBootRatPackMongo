package com.iqmsoft.boot.ratpack.service;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.iqmsoft.boot.ratpack.model.Product;
import com.iqmsoft.boot.ratpack.repos.ProductRepository;

@Service
public class SpringProductService implements ProductService {
	
	static Logger log = LoggerFactory.getLogger(SpringProductService.class); 

	private ProductRepository productRepository;
	
	@Autowired
	public SpringProductService(ProductRepository repository){
		this.productRepository = repository;
		log.info("created"); 
	}

	@Override
	public Product getById(String id) {
		return productRepository.findOne(id);
	}

	@Override
	public List<Product> list() {
		List<Product> list = new ArrayList<>();
		productRepository.findAll().forEach(list::add);
		return list;
	}
	
}
