package com.in2it.springbootwithmultipledatasource.order.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.in2it.springbootwithmultipledatasource.order.entity.Product;


@Repository
public interface ProductRepository2 extends JpaRepository<Product, Integer>{

}
