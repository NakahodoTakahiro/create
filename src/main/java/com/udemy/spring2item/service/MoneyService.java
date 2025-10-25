package com.udemy.spring2item.service;

import java.io.ByteArrayOutputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.udemy.spring2item.entity.AppEntity;
import com.udemy.spring2item.entity.MoneyEntity;
import com.udemy.spring2item.entity.MoneyUpdate;
import com.udemy.spring2item.entity.SkillSheetEntity;
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
	
	/*
	 *スキルシートをExcelでダウンロードする 
	 */
	public byte[] createExcel(SkillSheetEntity skillsheet) {
		String path = "../Excel/SkillSheetTemplate.xlsx";
		ByteArrayOutputStream bos = new ByteArrayOutputStream();
		try (Workbook workbook = WorkbookFactory.create(new FileInputStream(path));){
			Sheet sheet = workbook.getSheetAt(0);
			// Excelに入力値を出力する
			setExcelBalue(sheet, 2, 3, skillsheet.getEnjineername());
			// 自分のPR
			setExcelBalue(sheet, 5, 3, skillsheet.getPr());
			// 性別
			setExcelBalue(sheet, 2, 8, skillsheet.getSex());
			// 年齢
			setExcelBalue(sheet, 3, 8, String.valueOf(skillsheet.getAge()));
			// 最寄駅
			setExcelBalue(sheet, 4, 8, skillsheet.getNearstation());
			//　最終学歴
			setExcelBalue(sheet, 5, 8, skillsheet.getEducationalackground());
			// 趣味
			setExcelBalue(sheet, 6, 8, skillsheet.getHobby());
			// 自分の出身
			setExcelBalue(sheet, 7, 8, skillsheet.getFrom());
			// 保有資格
			setExcelBalue(sheet, 8, 8, skillsheet.getEducation());
			// 得意技術
			setExcelBalue(sheet, 4, 11, skillsheet.getSkill());
			// 顧客折衝経験
			setExcelBalue(sheet, 6, 11, skillsheet.getCustomerskill());
			// マネジメント経験
			setExcelBalue(sheet, 8, 11, skillsheet.getManagement());
			// 開始年月日
			setExcelBalue(sheet, 12, 1, String.valueOf(skillsheet.getFromstartyear()));
			// リファクタする必要ありそう
			setExcelBalue(sheet, 12, 1, String.valueOf(skillsheet.getFromstartmonth()));
			
			getExcelFile(workbook);
			
		}catch(IOException e){
			System.out.println("Excelファイルを開けませんでした");
			
		}
		return bos.toByteArray();
	}
	// 指定したセルに値を書き込むメソッド
	public void setExcelBalue(Sheet sheet, int rowIndex, int colIndex, String value) {
		// rowIndex(0から始まり)の行を取得 or 作成
		Row row = sheet.getRow(rowIndex) != null ? sheet.getRow(rowIndex) : sheet.createRow(rowIndex);
		// colIndex（0始まり）のセルを取得 or 作成
        Cell cell = row.getCell(colIndex) != null ? row.getCell(colIndex) : row.createCell(colIndex);
		
        cell.setCellValue(value);
	}
	
	//Excelファイルをダウンロード
	public byte[] getExcelFile(Workbook workbook) throws IOException{
		ByteArrayOutputStream bos = new ByteArrayOutputStream();
		workbook.write(bos);
		return bos.toByteArray();
	}
	
	//Zipファイルのダウンロード
	public void getZipFile(ByteArrayOutputStream bosfile) throws IOException{
		ByteArrayOutputStream bos = new ByteArrayOutputStream();
		ZipOutputStream zos = new ZipOutputStream(bos);
		ZipEntry zipEntry = new ZipEntry("スキルシート.xlsx");
		zos.putNextEntry(zipEntry);
		zos.write(bosfile.toByteArray());
		zos.close();
	}
}
