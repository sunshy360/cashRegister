package app.printer;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.Map;

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
				str += "<p>" + receiptPrinter.printOneItemInItemsSectionWhenBuyTwoGetOneFree(product, shoppingCart.get(product.getBarcode())) + "</p>\n";
				buyTwoFreeOneList.put(string, shoppingCart.get(string));
			}
			//打折
			else if(discountID.get(product.getBarcode()) != null){
				str += "<p>" + receiptPrinter.printOneItemInItemsSectionWhentDiscount(product, shoppingCart.get(product.getBarcode())) + "</p>\n";
			}
			//正常
			else{
				str += "<p>" + receiptPrinter.printOneItemInItemsSection(product, shoppingCart.get(product.getBarcode())) + "</p>\n";
			}
		}
		return str;
	}
	
	public String getReceiptBuyTwoGetOneFreeHead() {
		return "买二赠一商品：\n";
	}

	public String printOneItemInItemsSectionWheBuyTwoGetOneFreeList() {
		String str = "<p>"+getReceiptBuyTwoGetOneFreeHead()+"</p>";
		for(String key : buyTwoFreeOneList.keySet()){ 
			Product product = readUtilFile.productMap.get(key);
			str += "<p>" + String.format("名称：%s，数量：%d%s",
							product.getName(),
							buyTwoFreeOneList.get(key)/3,
							product.getUnit()) + "</p>";
		}
		return str;
	}

	public String getReceiptSum(LinkedHashMap<String, Integer> productsWithNumbers) {
		//totalPrice = 0.0; 
		//totalDiscount = 0.0; 
		//threeChoseOne(productsWithNumbers);
		if(totalDiscount==0.0)
			return String.format("总计：%1.2f(元)", totalPrice);
		else
			return String.format("总计：%1.2f(元)\n 节省：%1.2f(元)", totalPrice,totalDiscount);
	}

	public void clear() {
		totalPrice = 0.0; 
		totalDiscount = 0.0; ; 
		LinkedHashMap<String, Integer> buyTwoFreeOneList = new LinkedHashMap<String, Integer>();
	}
}
