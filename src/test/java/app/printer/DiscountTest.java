package app.printer;

import java.util.LinkedHashMap;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import app.model.Product;
import app.printer.ReceiptPrinter;


public class DiscountTest {
	
	private ReceiptPrinter receiptPrinter;
	
	@Before
	public void setUp(){
		receiptPrinter = new ReceiptPrinter();
	}
	
	private Product createProduct(String barcode, String name, double price, String unit) {
		return new Product(barcode,name,price, unit, "", "");
	}
	
	@Test
	public void printOneItemInReceiptItemsSectionWhenDiscount()
	{
		Assert.assertEquals("名称：苹果，数量：2斤，单价：5.00(元)，小计：9.00(元)，节省：1.00(元)",
				receiptPrinter.printOneItemInItemsSectionWhentDiscount(createProduct("ITEM000003","苹果", 5.00, "斤"),2));
	}

}
