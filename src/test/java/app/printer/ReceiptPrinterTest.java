package app.printer;

import static org.junit.Assert.assertEquals;

import java.util.LinkedHashMap;
import java.util.Map;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import app.UtilFiles.ReadUtilFile;
import app.model.Product;


public class ReceiptPrinterTest {

	private ReceiptPrinter receiptPrinter;
	@Before
	public void setUp(){
		receiptPrinter = new ReceiptPrinter();
		new ReadUtilFile();
		ReadUtilFile.readProductItem();
		ReadUtilFile.readDiscountItem();
		ReadUtilFile.readDiscountConvertItem();
	}
	
	private Product createProduct(String barcode, String name, double price, String unit) {
		return new Product(barcode,name,price, unit, "", "");
	}

	@Test
	public void getReceiptHead()
	{
		assertEquals("***<没钱赚商店>购物清单***",receiptPrinter.getReceiptHead());
	}

	@Test
	public void printOneItemInReceiptItemsSection()
	{
		Assert.assertEquals("名称：可口可乐，数量：3瓶，单价：3.00(元)，小计：9.00(元)",receiptPrinter.printOneItemInItemsSection(createProduct("ITEM000001","可口可乐", 3.00, "瓶"),3));
		Assert.assertEquals("名称：可口可乐，数量：2瓶，单价：3.00(元)，小计：6.00(元)",receiptPrinter.printOneItemInItemsSection(createProduct("ITEM000001","可口可乐", 3.00, "瓶"),2));
		Assert.assertEquals("名称：羽毛球，数量：5个，单价：1.00(元)，小计：5.00(元)",
                receiptPrinter.printOneItemInItemsSection(createProduct("ITEM000005","羽毛球",1.00,"个"),5));
	}
	
/*
	@Test
	public void printMutipleItemInReceiptItemsSection()
	{
		LinkedHashMap<String,Integer> productsWithNumbers = new LinkedHashMap<String,Integer>();
		productsWithNumbers.put("ITEM000001",3);
		productsWithNumbers.put("ITEM000002",1);

		Assert.assertEquals("��ƣ��ɿڿ��֣�������3ƿ�����ۣ�3.00(Ԫ)��С�ƣ�9.00(Ԫ)\n��ƣ���Ƭ��������1��ۣ�4.00(Ԫ)��С�ƣ�4.00(Ԫ)\n",
							receiptPrinter.printMultipleItemsInItemSection(productsWithNumbers));
	}
*/
	
	@Test
	public void ThreeChooseOneTest()
	{
		LinkedHashMap<String,Integer> productsWithNumbers = new LinkedHashMap<String,Integer>();
		productsWithNumbers.put("ITEM000001",3);
		productsWithNumbers.put("ITEM000005",5);
		productsWithNumbers.put("ITEM000003",2);
		Assert.assertEquals("名称：可口可乐，数量：3瓶，单价：3.00(元)，小计：6.00(元)\n名称：羽毛球，数量：5个，单价：1.00(元)，小计：4.00(元)\n名称：苹果，数量：2斤，单价：5.00(元)，小计：9.00(元)，节省1.00(元)\n",
							receiptPrinter.threeChoseOne(productsWithNumbers));
	}
	
	@Test
	public void getReceiptSum()
	{
		LinkedHashMap<String,Integer> productsWithNumbers = new LinkedHashMap<String,Integer>();
		productsWithNumbers.put("ITEM000001",3);
		productsWithNumbers.put("ITEM000005",5);
		productsWithNumbers.put("ITEM000003",2);
		
		assertEquals("总计：19.00(元)\n节省：5.00(元)",receiptPrinter.getReceiptSum(productsWithNumbers));
	}
}
