import static org.junit.Assert.assertEquals;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class ReceiptPrinterTest {
	
	private ReceiptPrinter receiptPrinter;
	
	@Before
	public void setUp(){
		receiptPrinter = new ReceiptPrinter();
	}
	
	private Product createProduct(double price) {
		return new Product(price);
	}

	@Test
	public void getReceiptHead()
	{
		assertEquals("没钱赚商店清单",receiptPrinter.getReceiptHead());
	}

	@Test
	public void getReceiptItemsSectionJustOneItem()
	{
		Assert.assertEquals("名称：可口可乐，数量：3瓶，单价：3.00（元），小计：9.00（元）",receiptPrinter.oneItemInReceiptItem(createProduct(3.00),3));
		Assert.assertEquals("名称：可口可乐，数量：2瓶，单价：3.00（元），小计：6.00（元）",receiptPrinter.oneItemInReceiptItem(createProduct(3.00),2));
		
	}
}
