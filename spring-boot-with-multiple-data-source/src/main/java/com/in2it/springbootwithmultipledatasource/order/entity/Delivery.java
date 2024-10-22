package com.in2it.springbootwithmultipledatasource.order.entity;

import java.time.LocalDate;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Delivery {
	
	@Id
	private int id;
	private String address;
	private LocalDate deleveryDate;

}
