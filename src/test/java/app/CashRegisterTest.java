package app;

import static org.junit.Assert.assertEquals;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

import java.util.LinkedHashMap;

import org.junit.Before;
import org.junit.Test;

import app.model.Product;
import app.printer.ReceiptPrinter;

public class CashRegisterTest {

	private CashRegister cashRegister;
	
	private Product product1 = null;

	@Before
	public void setUp(){
		cashRegister = new CashRegister();
		product1 = createProduct("ITEM0001",10.50);
	}
	
	private Product createProduct(String barcode, double price) {
		return new Product(barcode, "AAAA", price, "GE", "", "");
	}
	
	@Test
	public void getTotalPriceWhenJustHaveOneProduct() {
		cashRegister.add(product1);
		assertEquals(10.50,cashRegister.getTotalPrice(),0.0000001);
	}
	
	@Test
	public void getTotalPriceWhenHaveMultipleProducts() {
		cashRegister.add(product1);
		cashRegister.add(product1);
		cashRegister.add(createProduct("ITEM0002",20.50));
		assertEquals(41.50,cashRegister.getTotalPrice(),0.00000001);
	}
	
	@Test
	public void getProductNumber() {
		cashRegister.add(product1);
		cashRegister.add(product1);
		cashRegister.add(createProduct("ITEM0002",20.50));
		assertEquals(2,cashRegister.getProductNumber(product1));
		assertEquals(1,cashRegister.getProductNumber(createProduct("ITEM0002",20.50)));
	}
	
	@SuppressWarnings("unchecked")
	@Test
	public void printReceipt(){
		ReceiptPrinter receiptPrinter = mock(ReceiptPrinter.class);
		cashRegister.setReceiptPrinter(receiptPrinter);
		cashRegister.add(product1);
		cashRegister.add(product1);
		cashRegister.add(createProduct("ITEM0002",20.50));
		
		cashRegister.printReceipt();
				
		verify(receiptPrinter,times(1)).getReceiptHead();
		verify(receiptPrinter,times(1)).printMultipleItemsInItemSection(any(LinkedHashMap.class));
		verify(receiptPrinter,times(1)).getReceiptSum(cashRegister.getTotalPrice());
	}
	
}
