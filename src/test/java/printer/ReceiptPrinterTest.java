package printer;

import static org.junit.Assert.assertEquals;

import java.util.LinkedHashMap;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import model.Product;
import printer.ReceiptPrinter;


public class ReceiptPrinterTest {
	
	private ReceiptPrinter receiptPrinter;
	
	@Before
	public void setUp(){
		receiptPrinter = new ReceiptPrinter();
	}
	
	private Product createProduct(String barcode, String name, double price, String unit) {
		return new Product(barcode,name,price, unit, "", "");
	}

	@Test
	public void getReceiptHead()
	{
		assertEquals("没钱赚商店清单",receiptPrinter.getReceiptHead());
	}

	@Test
	public void printOneItemInReceiptItemsSection()
	{
		Assert.assertEquals("名称：可口可乐，数量：3瓶，单价：3.00（元），小计：9.00（元）",receiptPrinter.printOneItemInItemsSection(createProduct("ITEM0002","可口可乐", 3.00, "瓶"),3));
		Assert.assertEquals("名称：可口可乐，数量：2瓶，单价：3.00（元），小计：6.00（元）",receiptPrinter.printOneItemInItemsSection(createProduct("ITEM0002","可口可乐", 3.00, "瓶"),2));
		Assert.assertEquals("名称：乒乓球，数量：1个，单价：1.00（元），小计：1.00（元）",
                receiptPrinter.printOneItemInItemsSection(createProduct("ITEM0001","乒乓球",1.00,"个"),1));
	}
	@Test
	public void printMutipleItemInReceiptItemsSection()
	{
		LinkedHashMap<Product,Integer> productsWithNumbers = new LinkedHashMap<Product,Integer>();
		productsWithNumbers.put(createProduct("ITEM0002","可口可乐", 3.00, "瓶"),3);
		productsWithNumbers.put(createProduct("ITEM0001","乒乓球", 1.00, "个"),1);
		Assert.assertEquals("名称：可口可乐，数量：3瓶，单价：3.00（元），小计：9.00（元）\n名称：乒乓球，数量：1个，单价：1.00（元），小计：1.00（元）\n",
							receiptPrinter.printMultipleItemsInItemSection(productsWithNumbers));
	}

	@Test
	public void getReceiptSum()
	{
		assertEquals("总计：25.00（元）",receiptPrinter.getReceiptSum(25.00));
	}
}
