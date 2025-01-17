package com.in2it.springbootwithmultipledatasource.product.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.in2it.springbootwithmultipledatasource.product.entity.Cart;

@Repository
public interface CartRepository extends JpaRepository<Cart, Integer> {

}
