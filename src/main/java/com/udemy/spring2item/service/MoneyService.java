package com.udemy.spring2item.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.udemy.spring2item.entity.AppEntity;
import com.udemy.spring2item.entity.MoneyEntity;
import com.udemy.spring2item.entity.MoneyUpdate;
import com.udemy.spring2item.entity.Spend;
import com.udemy.spring2item.entity.SpendAdd;
import com.udemy.spring2item.entity.UserAddRequest;
import com.udemy.spring2item.mapper.MoneyMapper;

@Service
public class MoneyService {
	
	@Autowired
	private MoneyMapper moneyMapper;
	
	public List<AppEntity> selectAll(){
		return moneyMapper.selectAll();
	}
	
	public MoneyEntity findById(Long kakeiboid) {
		return moneyMapper.findById(kakeiboid);
	}
	
	public Spend spendfindById(Long id) {
		 return moneyMapper.spendfindById(id);
	 }
	
	public List<Spend> spendselectAll() {
		return moneyMapper.spendselectAll();
	}
	
	/**
	 * 支出項目情報削除
	 * @param kakeiboid
	 */
	public void delete(Long kakeiboid) {
		moneyMapper.delete(kakeiboid);
	}
	
	/**
	 * 収入項目削除
	 * @param id
	 */
	public void spenddelete(Long id) {
		moneyMapper.spenddelete(id);
	}
	
	/**
	 * 収入登録
	 * @param SpendAdd
	 */
	public void spendadd(SpendAdd spendAdd) {
		moneyMapper.spendadd(spendAdd);
		
	}
	
	
	public void save(UserAddRequest userAddRequest) {
		moneyMapper.save(userAddRequest);
	}

	public void update(MoneyUpdate update) {
		moneyMapper.update(update);
	}
	public void spendupdate(Spend spend) {
		moneyMapper.spendupdate(spend);
	}
	
}
