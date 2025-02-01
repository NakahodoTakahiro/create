package com.udemy.spring2item.entity;

import java.io.Serializable;

import org.hibernate.validator.constraints.Range;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class SpendAdd implements Serializable {
	
	
	@NotEmpty(message="種別 : 種別を入力してください")
	@Size(max=10,message="種別 : 種別は10文字以内で入力してください")
	private String type;
	
	@NotNull(message="金額を入力してください")
	@Range(min=0, max=1000000,message="金額 : 0円以上100万円以下で入力してください" )
	private Long amount;
}
