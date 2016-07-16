package app.printer;

import java.util.LinkedHashMap;

import app.model.Product;

public class ReceiptPrinter {

	public String getReceiptHead() {
		return "ûǮ׬�̵��嵥";
	}

	public String printOneItemInItemsSection(Product product, int number) {
		return String.format("���ƣ�%s��������%d%s�����ۣ�%1.2f��Ԫ����С�ƣ�%1.2f��Ԫ��",
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
		return String.format("�ܼƣ�%1.2f��Ԫ��", totalPrice);
	}
}
