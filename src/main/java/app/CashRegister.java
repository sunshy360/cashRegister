package app;

import java.util.LinkedHashMap;

import model.Product;
import printer.ReceiptPrinter;

public class CashRegister {
	private LinkedHashMap<Product,Integer> productMap = new LinkedHashMap<Product,Integer>();
	private ReceiptPrinter receiptPrinter = new ReceiptPrinter();
	
	public void add(Product product) {
		if(productMap.containsKey(product)){
			productMap.put(product, productMap.get(product)+1);
		} else {
			productMap.put(product,1);
		}
		
	}

	public double getTotalPrice() {
		double total = 0.0;
		for(Product product : productMap.keySet()) {
			total += product.getPrice()*productMap.get(product);
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

	public int getProductNumber(Product product) {
		return productMap.get(product);
	}
	
}
