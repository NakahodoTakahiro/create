package com.udemy.spring2item.entity;

import java.io.Serializable;

import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = false)
public class MoneyUpdate extends UserAddRequest implements Serializable{
	
	@NotNull
	private Long kakeiboid;


}
