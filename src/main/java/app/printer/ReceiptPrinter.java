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
}
