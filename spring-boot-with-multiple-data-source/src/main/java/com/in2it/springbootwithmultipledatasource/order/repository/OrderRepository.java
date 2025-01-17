package com.in2it.springbootwithmultipledatasource.order.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.in2it.springbootwithmultipledatasource.order.entity.Order;


@Repository
public interface OrderRepository extends JpaRepository<Order, Integer> {

}
