/**
 * 
 */
'use strict'
{
	function sample(MoneyList){
		alert(MoneyList)
	}
const kakeibo = /*[[${MoneyList}]]*/"MoneyList";
const spend = /*[[${spendList}]]*/"spendList";

const today = new Date();
let pre = "<pre>予算&#009;&#009;支出&#009;&#009;残り</pre>"
let date = today.getFullYear() + "年" + (today.getMonth()+1) + "月" + "の状況"
let result = spend[0].amount + "-" + kakeibo[0].amount + "=" + (spend[0].amount - kakeibo[0].amount)
let yosan = spend[0].amount;
let sisyutu = kakeibo[0].amount
let nokori = spend[0].amount - kakeibo[0].amount
document.getElementById("yosan").textContent = yosan
document.getElementById("sisyutu").textContent = sisyutu
document.getElementById("nokori").textContent = nokori
}