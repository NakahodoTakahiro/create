package com.udemy.spring2item.entity;

import java.io.Serializable;
import java.util.Date;

import lombok.Data;

@Data
public class Spend extends SpendAdd implements Serializable{

	/**
	 * 収入ID
	 */
	private Long id;
	
	/**
	 * 収入が入った日付
	 */
	private Date date;
}
