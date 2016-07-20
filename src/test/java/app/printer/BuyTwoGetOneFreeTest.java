package app.printer;

import java.util.LinkedHashMap;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import app.model.Product;

public class BuyTwoGetOneFreeTest {

	private ReceiptPrinter receiptPrinter;
	
	@Before
	public void setUp(){
		receiptPrinter = new ReceiptPrinter();
	}
	
	private Product createProduct(String barcode, String name, double price, String unit) {
		return new Product(barcode,name,price, unit, "", "");
	}
	
	@Test
	public void printOneItemInReceiptItemsSectionWhenByeTwoGetOneFree()
	{
		Assert.assertEquals("名称：可口可乐，数量：3瓶，单价：3.00(元)，小计：6.00(元)",
				receiptPrinter.printOneItemInItemsSectionWhenBuyTwoGetOneFree(createProduct("ITEM000001","可口可乐", 3.00, "瓶"),3));
	}

}
