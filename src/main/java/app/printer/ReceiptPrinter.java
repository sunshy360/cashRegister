package app.printer;

import java.util.LinkedHashMap;

import app.model.Product;

public class ReceiptPrinter {

	public String getReceiptHead() {
		return "***<没钱赚商店>购物清单***";
	}

	public String printOneItemInItemsSection(Product product, int number) {
		return String.format("名称：%s，数量：%d%s，单价：%1.2f(元)，小计：%1.2f(元)",
								product.getName(),
								number,product.getUnit(),
								product.getPrice(),
								product.getPrice() * number);
	}

	public String printMultipleItemsInItemSection(LinkedHashMap<Product, Integer> productsWithNumbers) {
		String str = "";
		for(Product product : productsWithNumbers.keySet()){
			str += printOneItemInItemsSection(product, productsWithNumbers.get(product));
			str += "\n";
		}
		return str;
	}

	public String getReceiptSum(double totalPrice) {
		return String.format("总计：%1.2f(元)", totalPrice);
	}

	public String printOneItemInItemsSectionWhentDiscount(Product product, int number) {
		return String.format("名称：%s，数量：%d%s，单价：%1.2f(元)，小计：%1.2f(元)，节省：%1.2f(元)",
				product.getName(),
				number,product.getUnit(),
				product.getPrice(),
				product.getPrice() * number * 0.95,
				product.getPrice() * number * (1-0.95));
	}

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

	public String printOneItemInItemsSectionWhenByeTwoGetOneFree(Product product, int number) {
		if (number < 3)
			return printOneItemInItemsSection(product, number);
		else 
			return String.format("名称：%s，数量：%d%s，单价：%1.2f(元)，小计：%1.2f(元)",
					product.getName(),
					number,product.getUnit(),
					product.getPrice(),
					product.getPrice() * (number - number/3));
	}

	public String getReceiptByeTwoGetOneFreeHead() {
		return "买二赠一商品:\n";
	}
	
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
				str += printOneItemInItemsSectionWhenByeTwoGetOneFree(product, productsWithNumbers.get(product));
				str2 += printOneItemInItemsSectionWheByeTwoGetOneFreeList(product, productsWithNumbers.get(product));
				str2 += "\n";
			}
			str += "\n";
		}
		return str +"-----------\n" + getReceiptByeTwoGetOneFreeHead() + str2;
	}

	private String printOneItemInItemsSectionWheByeTwoGetOneFreeList(Product product, int number) {
		if (number < 3)
			return printOneItemInItemsSection(product, number);
		else 
			return String.format("名称：%s，数量：%d%s",
					product.getName(),
					number/3,
					product.getUnit());
	}
}
