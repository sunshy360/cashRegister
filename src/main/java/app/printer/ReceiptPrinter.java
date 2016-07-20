package app.printer;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import app.UtilFiles.DiscountConvert;
import app.UtilFiles.ReadUtilFile;
import app.model.Product;

public class ReceiptPrinter {

	public static ReadUtilFile readUtilFile = new ReadUtilFile(); 
	public static LinkedHashMap<String, Integer> buyTwoFreeOneList = new LinkedHashMap<String, Integer>(); 
	public static double totalPrice = 0.0; 
	public static double totalDiscount = 0.0; 
	
	public String getReceiptHead() {
		return "***<没钱赚商店>购物清单***";
	}

	public String printOneItemInItemsSection(Product product, Integer number) {
		double itemPrice = product.getPrice() * number;
		totalPrice += itemPrice;
		return String.format("名称：%s，数量：%d%s，单价：%1.2f(元)，小计：%1.2f(元)",
								product.getName(),
								number,
								product.getUnit(),
								product.getPrice(),
								itemPrice);
	}

	/*//abandon
	public String printMultipleItemsInItemSection(LinkedHashMap<String, Integer> productsWithNumbers) {
		String str = "";
		for(String s : productsWithNumbers.keySet()){
			Product product = ReadUtilFile.productMap.get(s);
			str += printOneItemInItemsSection(product, productsWithNumbers.get(s));
			str += "\n";
		}
		return str;
	}*/

	public String printOneItemInItemsSectionWhentDiscount(Product product, int number) {
		String discountType = "";
		//反向寻找折扣类型
		for(String s:readUtilFile.discountMap.keySet())
			if(readUtilFile.discountMap.get(s).equals(product.getBarcode()))
				discountType = s;
		double discountNumber= readUtilFile.discountConvertMap.get(discountType).getDiscount();
		
		double itemPrice = product.getPrice() * number * discountNumber;
		double itemDiscount = product.getPrice() * number * (1-discountNumber);
		totalPrice += itemPrice;
		totalDiscount += itemDiscount;
		
		return String.format("名称：%s，数量：%d%s，单价：%1.2f(元)，小计：%1.2f(元)，节省：%1.2f(元)",
				product.getName(),
				number,product.getUnit(),
				product.getPrice(),
				itemPrice,
				product.getPrice() * number * (1-discountNumber));
	}

/*
	public String printMultipleItemsInItemSectionWhenHaveMutipleDiscountAndAnthorAsUsual(
			LinkedHashMap<Product, Integer> productsWithNumbers) {
		String str = "";
		for(Product product : productsWithNumbers.keySet()){
			if(product.getBarcode() != "ITEM0003")
			{
				str += printOneItemInItemsSection(product, productsWithNumbers.get(product));
			}
			else
			{
				str += printOneItemInItemsSectionWhentDiscount(product, productsWithNumbers.get(product));
			}
			str += "\n";
		}
		return str;
	}
*/

	public String printOneItemInItemsSectionWhenBuyTwoGetOneFree(Product product, int number) {
		if (number < 3)
			return printOneItemInItemsSection(product, number);
		else {
			double itemPrice = product.getPrice() * (number - number/3);
			double itemDiscount = product.getPrice() * (number/3);
			totalPrice += itemPrice;
			totalDiscount += itemDiscount;
			
			return String.format("名称：%s，数量：%d%s，单价：%1.2f(元)，小计：%1.2f(元)",
					product.getName(),
					number,product.getUnit(),
					product.getPrice(),
					itemPrice);
		}
	}
	
/*
	public String printMultipleItemsInItemSectionWhenHaveMutipleByeTwoGetOneFreeAndAnthorAsUsual(
			LinkedHashMap<Product, Integer> productsWithNumbers) {
		String str = "";
		String str2 = "";
		for(Product product : productsWithNumbers.keySet()){
			if((product.getBarcode() != "ITEM0001") && (product.getBarcode() != "ITEM0002"))
			{
				str += printOneItemInItemsSection(product, productsWithNumbers.get(product));
			}
			else
			{
				str += printOneItemInItemsSectionWhenBuyTwoGetOneFree(product, productsWithNumbers.get(product));
				str2 += printOneItemInItemsSectionWheBuyTwoGetOneFreeList(product, productsWithNumbers.get(product));
				str2 += "\n";
			}
			str += "\n";
		}
		return str +"-----------\n" + getReceiptBuyTwoGetOneFreeHead() + str2;
	}
	public String printMuiltyItemReciptItemsSectionWhenHaveMultipleProductBuyTwoGetOneFreeAndHaveDiscountAndAnotherAsUsual(
			LinkedHashMap<String, Integer> productsWithNumbers) {
		for(String s : productsWithNumbers.keySet()){
			Product product = ReadUtilFile.productMap.get(s);
			if(ReadUtilFile.product)
			{
				str += printOneItemInItemsSection(product, productsWithNumbers.get(product));
			}
			else
			{
				str += printOneItemInItemsSectionWhenBuyTwoGetOneFree(product, productsWithNumbers.get(product));
				str2 += printOneItemInItemsSectionWheBuyTwoGetOneFreeList(product, productsWithNumbers.get(product));
				str2 += "\n";
			}
			str += "\n";
		}
	}
	*/
	
	public String threeChoseOne(LinkedHashMap<String, Integer> productsWithNumbers) {
		ArrayList<String> buyTwoGetOneFreeID = ReadUtilFile.readBuyTwoGetOneFreeID();
		Map<String, DiscountConvert> discountID = ReadUtilFile.readDiscountIDConvert();
		ReceiptPrinter receiptPrinter = new ReceiptPrinter();
		LinkedHashMap<String, Integer> shoppingCart = productsWithNumbers;
		
		String str = ""; 
		
		for(String string : shoppingCart.keySet())
		{		
			Product product = ReadUtilFile.productMap.get(string);
			//买二赠一
			if(buyTwoGetOneFreeID.contains(product.getBarcode()))
			{
				str += receiptPrinter.printOneItemInItemsSectionWhenBuyTwoGetOneFree(product, shoppingCart.get(product.getBarcode())) + "\n";
				buyTwoFreeOneList.put(string, shoppingCart.get(string));
			}
			//打折
			else if(discountID.get(product.getBarcode()) != null){
				str += receiptPrinter.printOneItemInItemsSectionWhentDiscount(product, shoppingCart.get(product.getBarcode())) + "\n";
			}
			//正常
			else{
				str += receiptPrinter.printOneItemInItemsSection(product, shoppingCart.get(product.getBarcode())) + "\n";
			}
		}
		return str;
	}
	
	public String getReceiptBuyTwoGetOneFreeHead() {
		return "买二赠一商品：\n";
	}

	public String printOneItemInItemsSectionWheBuyTwoGetOneFreeList() {
		String str = getReceiptBuyTwoGetOneFreeHead();
		for(String key : buyTwoFreeOneList.keySet()){ 
			Product product = readUtilFile.productMap.get(key);
			str += String.format("名称：%s，数量：%d%s",
					product.getName(),
					buyTwoFreeOneList.get(key)/3,
					product.getUnit());
		}
		return str;
	}

	public String getReceiptSum(LinkedHashMap<String, Integer> productsWithNumbers) {
		totalPrice = 0.0; 
		totalDiscount = 0.0; 
		threeChoseOne(productsWithNumbers);
		if(totalDiscount==0.0)
			return String.format("总计：%1.2f(元)", totalPrice);
		else
			return String.format("总计：%1.2f(元)\n节省：%1.2f(元)", totalPrice,totalDiscount);
	}
}
