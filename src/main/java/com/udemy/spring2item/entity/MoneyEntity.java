package com.udemy.spring2item.entity;

import java.io.Serializable;
import java.time.LocalDate;

import lombok.Data;

@Data
public class MoneyEntity implements Serializable {

	/**
	 * ID
	 */
	private Long kakeiboid;
	
	/**
	 * 入力した日付
	 */
	private LocalDate kakeibodate;
	
	/**
	 * 家計簿の種別
	 */
	private String kakeibotype;
	
	/**
	 * 購入した商品名
	 */
	private String Productname;
	
	/**
	 * 購入金額
	 */
	private Long Amount;
}
