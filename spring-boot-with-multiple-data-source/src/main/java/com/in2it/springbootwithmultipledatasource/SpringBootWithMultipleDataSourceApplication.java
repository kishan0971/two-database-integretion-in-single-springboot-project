package com.in2it.springbootwithmultipledatasource;

import java.time.LocalDate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.in2it.springbootwithmultipledatasource.order.entity.Order;
import com.in2it.springbootwithmultipledatasource.order.repository.OrderRepository;
import com.in2it.springbootwithmultipledatasource.product.entity.Cart;
import com.in2it.springbootwithmultipledatasource.product.entity.Product;
import com.in2it.springbootwithmultipledatasource.product.repository.CartRepository;
import com.in2it.springbootwithmultipledatasource.product.repository.ProductRepository;

@SpringBootApplication
public class SpringBootWithMultipleDataSourceApplication implements CommandLineRunner{

	@Autowired
	ProductRepository productRepository;
	
	@Autowired
	OrderRepository orderRepository;
	
	@Autowired
	CartRepository cartRepository;
	
	@Autowired
	com.in2it.springbootwithmultipledatasource.order.repository.ProductRepository2 productRepository2;
	
	public static void main(String[] args) {
		SpringApplication.run(SpringBootWithMultipleDataSourceApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		 Product product = new Product(13,"Desktop",2000000000);
		 productRepository.save(product);
		 
		 Order order = new Order(45, "sdasgdy", LocalDate.now());
		 orderRepository.save(order);
		 
		 Product product2 = productRepository.findById(1).get();
		 com.in2it.springbootwithmultipledatasource.order.entity.Product product3 = new com.in2it.springbootwithmultipledatasource.order.entity.Product();
		 product3.setId(product2.getId());
		 product3.setName(product2.getName());
		 product3.setPrice(product2.getPrice());
		 
		 productRepository2.save(product3);
		 
		 Cart cart = new Cart(1212, 3211);
		 cartRepository.save(cart);
		 
		
	}
	
	

	

}
