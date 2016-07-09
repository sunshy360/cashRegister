package app;

import java.util.ArrayList;

import model.Product;
import printer.ReceiptPrinter;

public class CashRegister {
	private ArrayList<Product> productList = new ArrayList<Product>();
	private ReceiptPrinter receiptPrinter = new ReceiptPrinter();
	
	public void add(Product product) {
		productList.add(product);
		
	}

	public double getTotalPrice() {
		double total = 0.0;
		for(Product product : productList) {
			total += product.getPrice();
		}
		return total;
	}

	public void setReceiptPrinter(ReceiptPrinter receiptPrinter) {
		this.receiptPrinter  = receiptPrinter;
		
	}

	public String printReceipt() {
		String receiptString = "";
		receiptString += receiptPrinter.getReceiptHead();
		receiptString += receiptPrinter.printMultipleItemsInItemSection(null);
		receiptString += receiptPrinter.getReceiptSum(this.getTotalPrice());
		return receiptString;
	}
	
}
