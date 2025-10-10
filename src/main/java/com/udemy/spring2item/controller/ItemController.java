package com.udemy.spring2item.controller;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.udemy.spring2item.entity.AppEntity;
import com.udemy.spring2item.entity.MoneyEntity;
import com.udemy.spring2item.entity.MoneyUpdate;
import com.udemy.spring2item.entity.SkillSheetEntity;
import com.udemy.spring2item.entity.Spend;
import com.udemy.spring2item.entity.SpendAdd;
import com.udemy.spring2item.entity.UserAddRequest;
import com.udemy.spring2item.service.MoneyService;

@Controller
public class ItemController {

	@Autowired
	private MoneyService moneyService;
	
	@GetMapping("/")
	public String Lp(Model model) {
		List<AppEntity> MoneyList =moneyService.selectAll();  
		List<Spend> spendList = moneyService.spendselectAll();
		model.addAttribute("MoneyList",MoneyList);
		model.addAttribute("spendList",spendList);
		
		try (Workbook workbook = WorkbookFactory.create(new FileInputStream("/Users/nakahodotakahiro/Downloads/ForJava.xlsx"));
			FileOutputStream fos = new FileOutputStream("/Users/nakahodotakahiro/Desktop/TestExcel.xlsx");){
			List<Spend> list = moneyService.spendselectAll();
			Sheet sheet  = workbook.getSheet("Sheet1");
			for(int i = 0; i < list.size(); i++) {
				Row row = sheet.getRow(i+1);
				for(int j = 0; j < 1; j++){
					Cell cellType = row.getCell(j);
					Cell cellDate = row.getCell(j+1);
					Cell cellAmount = row.getCell(j+2);
					cellType.setCellValue(list.get(i).getType());
					cellAmount.setCellValue(list.get(i).getAmount());	
					cellDate.setCellValue(list.get(i).getDate().toString());
				}
			}
			workbook.write(fos);
			workbook.close();
			fos.close();
		}catch(IOException e){
			System.out.println("ファイルを読み込めませんでした");
		}
		return "landing";
	}
	
	/**
	 * 支出一覧画面
	 * @param model
	 * @return 家計簿一覧画面
	 */
	@GetMapping("/out")
	public String listAll(Model model) {
		List<AppEntity> MoneyList = moneyService.selectAll();
		model.addAttribute("MoneyList",MoneyList);
		return "kakeibo";
	}
	
	/**
	 * 収入一覧
	 * @param model
	 * @return 収入一覧ページ
	 */
	@GetMapping("/spend")
	public String spend(Model model) {
		List<Spend> spendList = moneyService.spendselectAll();
		model.addAttribute("spendList",spendList);		
		return "spend";
	}
	
	/**
	 * 支出新規登録画面を表示
	 * @param model
	 * @return 支出新規と登録画面表示
	 */
	@GetMapping("/add")
	public String add(Model model) {
		model.addAttribute("userAddRequest",new UserAddRequest());
		return "Signup";
	}
	
	/**
	 *家計簿新規登録
	 *@param UserAddRequest
	 *preturn 家計簿情報一覧画面 
	 */
	@RequestMapping(value="/create",method=RequestMethod.POST)
	public String create(@Validated @ModelAttribute UserAddRequest userAddRequest,BindingResult result,Model model) {
		if(result.hasErrors()) {
			List<String> errorList = new ArrayList<String>();
			for(ObjectError error : result.getAllErrors()){
				errorList.add(error.getDefaultMessage());
			}
			model.addAttribute("validationError",errorList);
			model.addAttribute("userAddRequest",userAddRequest);
			return "Signup";
		}
		//家計簿情報の登録
		moneyService.save(userAddRequest);
		return "redirect:/";
	}
	
	/**
	 * 支出項目削除登録
	 * @param kakeiboid
	 * @return 家計簿情報一覧画面
	 */
	@GetMapping("/delete/{kakeiboid}")
	public String delete(@PathVariable("kakeiboid") Long kakeiboid,Model model) {
		moneyService.delete(kakeiboid);
		return "redirect:/";
	}
	
	/**
	 * 収入項目削除
	 * @param id
	 * @return spend(支出画面)
	 */
	@GetMapping("/spenddelete/{id}")
	public String spenddelete(@PathVariable("id") Long id,Model model) {
		moneyService.spenddelete(id);
		return "redirect:/spend";
	}
	
	/**
	 * 支出編集画面表示
	 * @param kakeiboid
	 * @retun 支出編集画面
	 */
	@GetMapping("/edit/{kakeiboid}")
	public String edit(@PathVariable Long kakeiboid,Model model) {
		MoneyEntity entity = moneyService.findById(kakeiboid);
		MoneyUpdate update = new MoneyUpdate();
		
		update.setKakeiboid(entity.getKakeiboid());
		update.setKakeibotype(update.getKakeibotype());
		update.setAmount(entity.getAmount());
		update.setProductname(entity.getProductname());
		
		model.addAttribute("moneyUpdate",update);
		return "edit";
		
	}
	
	/**
	 * 収入編集画面表示
	 * @param　id
	 * @return 収入編集画面
	 */
	@GetMapping("/spendedit/{id}")
	public String spendedit(@PathVariable("id") Long id,Model model) {
		Spend spend = moneyService.spendfindById(id);
		model.addAttribute("spend",spend);
		return "spendupdate";
	}
	
	/**
	 * 支出簿情報更新
	 * @param KakeiboUpdate
	 * @param Model
	 * @return 支出一覧画面
	 */
	@PostMapping("/update")
	public String update(@Validated @ModelAttribute	MoneyUpdate update,BindingResult result,Model model) {
		
		if(result.hasErrors()) {
			//入力チェックエラーの場合
			List<String> errorList = new ArrayList<String>();
			for(ObjectError error : result.getAllErrors()) {
				errorList.add(error.getDefaultMessage());
			}
			model.addAttribute("validationError", errorList);
			model.addAttribute("kakeiboUpdate", update);
			
			return "edit";
		}
		moneyService.update(update);
		return "redirect:/out";
	}
	
	/**
	 * 収入情報更新
	 * @param Spend リクエストデータ
	 * @return 収入一覧
	 */
	@PostMapping("/spendupdate")
	public String spendupdate(@Validated @ModelAttribute Spend spend,BindingResult result,Model model) {
		
		if(result.hasErrors()) {
			List<String> errorList = new ArrayList<String>();
			for(ObjectError error : result.getAllErrors()) {
				errorList.add(error.getDefaultMessage());
			}
			model.addAttribute("errorList",errorList);
			model.addAttribute("spend", spend);
			
			return "spendupdate";
		}
		moneyService.spendupdate(spend);
		return "redirect:/spend";
		
		
	}
	
	/**
	 * 収入新規登録画面を表示
	 * @param model
	 */
	@GetMapping("/spendadd")
	public String spendadd(Model model) {
		model.addAttribute("spendAdd",new SpendAdd());
		return "spendadd";
	}
	
	/**
	 * 収入新規登録
	 * @param spendAdd リクエストデータ
	 * @return 収入一覧画面
	 */
	@PostMapping("/createspend")
	public String creatspend(@Validated @ModelAttribute SpendAdd spendAdd ,BindingResult result, Model model) {
		if(result.hasErrors()) {
			//入力チェックエラー
			List<String> errorList = new ArrayList<String>();
			for(ObjectError error:result.getAllErrors()) {
				errorList.add(error.getDefaultMessage());
			}
			
			model.addAttribute("validationError",errorList);
			model.addAttribute("spendAdd", spendAdd);
			
			return "spendadd";
		}
		
		moneyService.spendadd(spendAdd);
		return "redirect:/spend";
	}
	
	/*
	 * スキルシートダウンロード
	 * @param
	 * @return
	 */
	@PostMapping("/excel")
	public String getNameAndBirth(SkillSheetEntity skillsheet,int birth) {
		return "沖縄県島尻郡与那原町字板良敷";
	}
		
}
