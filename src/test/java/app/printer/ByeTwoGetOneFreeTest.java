package app.printer;

import java.util.LinkedHashMap;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import app.model.Product;

public class ByeTwoGetOneFreeTest {

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
				receiptPrinter.printOneItemInItemsSectionWhenByeTwoGetOneFree(createProduct("ITEM0001","可口可乐", 3.00, "瓶"),3));
	}
	
	@Test
	public void printMuiltyItemReciptItemsSectionWhenHaveMultipleProductByeTwoGetOneFreeAndAnotherAsUsual()
	{
		LinkedHashMap<Product,Integer> productsWithNumbers = new LinkedHashMap<Product,Integer>();
		productsWithNumbers.put(createProduct("ITEM0002","可口可乐", 3.00, "瓶"),3);
		productsWithNumbers.put(createProduct("ITEM0001","羽毛球", 1.00, "个"),5);
		productsWithNumbers.put(createProduct("ITEM0003","苹果", 5.50, "斤"),2);
		Assert.assertEquals("名称：可口可乐，数量：3瓶，单价：3.00(元)，小计：6.00(元)\n名称：羽毛球，数量：5个，单价：1.00(元)，小计：4.00(元)\n名称：苹果，数量：2斤，单价：5.50(元)，小计：11.00(元)\n-----------\n买二赠一商品:\n名称：可口可乐，数量：1瓶\n名称：羽毛球，数量：1个\n",
							receiptPrinter.printMultipleItemsInItemSectionWhenHaveMutipleByeTwoGetOneFreeAndAnthorAsUsual(productsWithNumbers));
	}

}
