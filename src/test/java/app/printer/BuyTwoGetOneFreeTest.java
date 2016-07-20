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
	
	/*
	@Test
	public void printMuiltyItemReciptItemsSectionWhenHaveMultipleProductByeTwoGetOneFreeAndAnotherAsUsual()
	{
		LinkedHashMap<Product,Integer> productsWithNumbers = new LinkedHashMap<Product,Integer>();
		productsWithNumbers.put(createProduct("ITEM000001","�ɿڿ���", 3.00, "ƿ"),3);
		productsWithNumbers.put(createProduct("ITEM000005","��ë��", 1.00, "��"),5);
		productsWithNumbers.put(createProduct("ITEM000003","ƻ��", 5.00, "��"),2);
		Assert.assertEquals("��ƣ��ɿڿ��֣�������3ƿ�����ۣ�3.00(Ԫ)��С�ƣ�6.00(Ԫ)\n��ƣ���ë��������5�������ۣ�1.00(Ԫ)��С�ƣ�4.00(Ԫ)\n��ƣ�ƻ��������2����ۣ�5.00(Ԫ)��С�ƣ�10.00(Ԫ)\n-----------\n�����һ��Ʒ:\n��ƣ��ɿڿ��֣�������1ƿ\n��ƣ���ë��������1��\n",
							receiptPrinter.printMultipleItemsInItemSectionWhenHaveMutipleByeTwoGetOneFreeAndAnthorAsUsual(productsWithNumbers));
	}*/

}
