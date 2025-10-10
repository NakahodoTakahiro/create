package com.udemy.spring2item.entity;

import java.io.Serializable;
import java.util.Date;

import org.hibernate.validator.constraints.Range;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Data;


@Data
public class SkillSheetEntity implements Serializable{
	
	//技術者名
	@NotEmpty(message="技術者名を入力してください")
	private String Enjineername;
	
	//技術者名
	@NotEmpty(message="技術者名を入力してください")
	@Size(min=1,max=380,message="種別 : 種別は10文字以内で入力してください")
	private String Pr;
	
	//性別
	@NotEmpty(message="性別を入力してください")
	private String Sex;
	
	//年齢
	@Range(min=0, max=100,message="0〜100歳までで入力してください" )
	private int Age;
	
	//最寄駅
	@NotEmpty(message="最寄駅を入力してください")
	private String Nearstation;
	
	//学歴
	@NotEmpty(message="最終学歴を入力してください")
	private String Educationalackground;
	
	//趣味
	@NotEmpty(message="趣味を入力してください")
	private String Hobby;
	
	//出身地
	@NotEmpty(message="出身地を入力してください")
	private String From;
	
	//保有資格
	private String Education;
	
	//得意技術
	private String Skill;
	
	//顧客折衝
	private String Customerskill;
	
    //マネジメント
	private String Management;
	
	//開始期間 年
	@Pattern(regexp = "\\d+", message = "数字のみ入力してください")
	private int Fromstartyear;
	
	//開始期間 年
	@Pattern(regexp = "\\d+", message = "数字のみ入力してください")
    private int Fromstartmonth;

	//終了期間 年
	@Pattern(regexp = "\\d+", message = "数字のみ入力してください")
	private Date Tofinishyear;
	
	//終了期間 月
	@Pattern(regexp = "\\d+", message = "数字のみ入力してください")
	private Date Tofinishmonth;
	
	//業務詳細
	@NotEmpty(message="業務詳細を入力してください")
	@Size(min=0, max=1350,message="業務詳細は最大1350文字で入力しください")
	private String Taskdetail;
	
	//案件内容
	@Size(min=0, max=1350,message="案件内容は最大1350文字で入力しください")
	private String Projectdetail;
	
	//要件定義
	private String Detailconst;
	
	//基本設計
    private String Basicdesign;
    
    //詳細設計
    private String Detaildesign;
    
    //製造・単体
    private String Makeandunit;
    
    //結合テスト
    private String Ittest;
    
    //保守・運用
    private String Protect;
    
    //役割
    private String Role;
	
}
