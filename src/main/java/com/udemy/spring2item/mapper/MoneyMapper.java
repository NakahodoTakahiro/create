package com.udemy.spring2item.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.udemy.spring2item.entity.AppEntity;
import com.udemy.spring2item.entity.MoneyEntity;
import com.udemy.spring2item.entity.MoneyUpdate;
import com.udemy.spring2item.entity.Spend;
import com.udemy.spring2item.entity.SpendAdd;
import com.udemy.spring2item.entity.UserAddRequest;


@Mapper
public interface MoneyMapper {

	/**
	 * 支出情報全件取得
	 * @param 
	 * @return 検索結果
	 */
	List<AppEntity> selectAll();
	
	/**
	 * 収入情報全件取得
	 * @return　検索結果
	 */
	List<Spend> spendselectAll();
	
	/**
	 * 収入情報登録
	 * @param spend リクエストデータ
	 * @return
	 */
	void spendadd(SpendAdd spendadd);
	
	/**
	 * 支出情報登録
	 * @param UserAddRequest
	 * @return
	 */
	void save(UserAddRequest userAddRequest);
	
	/**
	 * 支出項目削除
	 * @param kakeiboid
	 */
	void delete(Long kakeiboid);
	
	/**
	 * 収入項目削除
	 * @param id
	 */
	void spenddelete(Long id);
	
	
	/**
	 * 支出主キー検索
	 * @param kakeiboid
	 * @return 検索結果
	 */
	MoneyEntity findById(Long kakeiboid);
	
	/**
	 * 収入主キー検索
	 * @param id
	 * @return 検索結果
	 */
	Spend spendfindById(Long id);
	
	/**
	 * 支出簿情報更新
	 * @param KakeinoUpdate 更新用リクエストデータ
	 */
	void update(MoneyUpdate update);
	
	/**
	 *収入情報更新
	 *@param Spend 更新用リクエストデータ
	 */
	void spendupdate(Spend spend);
	
}
