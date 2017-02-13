package com.iqmsoft.boot.ratpack;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.iqmsoft.boot.ratpack.model.Product;
import com.iqmsoft.boot.ratpack.repos.ProductRepository;
import com.iqmsoft.boot.ratpack.service.AsyncProductService;

import ratpack.registry.Registry;
import ratpack.server.RatpackServer;
import ratpack.spring.Spring;

public class App {

	static Logger log = LoggerFactory.getLogger(App.class); 
	
	public static void main(String[] args) throws Exception {
		Registry springBoot = Spring.spring(SpringBootApp.class);
		ProductRepository repo = springBoot.get(ProductRepository.class);
		
		repo.deleteAll();
		
		repo.save(new Product("one", "test 1"));
		repo.save(new Product("two", "test 2"));
		repo.save(new Product("three", "test 3"));
		repo.save(new Product("four", "test 4"));
		
		RatpackServer.start(s -> s 
			 .serverConfig( configBuilder -> configBuilder
	             .development(true)
			 )
			 .registry (springBoot)
			 .handlers( chain -> chain
				.prefix("product", a -> a.
					get(":id", ctx -> {
						String id = ctx.getPathTokens().get("id");
						ctx.get(AsyncProductService.class)
							.getById(id)
							.then(ctx::render);
						
					})
					.get(ctx -> {
						log.info("Retrieving list of products...");
						ctx.get(AsyncProductService.class)
						.list()
						.then(ctx::render);
					})
				)
			 )
			 
		);
	}
}
