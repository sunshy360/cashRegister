package app;

import static org.junit.Assert.assertEquals;

import java.util.LinkedHashMap;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import app.CashRegister;
import model.Product;
import printer.ReceiptPrinter;
import static org.mockito.Mockito.*;

public class CashRegisterTest {

	private CashRegister cashRegister;
	
	private Product product1 = null;

	@Before
	public void setUp(){
		cashRegister = new CashRegister();
		product1 = createProduct(10.50);
	}
	
	private Product createProduct(double price) {
		return new Product("AAAA", price, "GE");
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
		cashRegister.add(createProduct(20.50));
		assertEquals(41.50,cashRegister.getTotalPrice(),0.00000001);
	}
	
	@Test
	public void printReceipt(){
		ReceiptPrinter receiptPrinter = mock(ReceiptPrinter.class);
		cashRegister.setReceiptPrinter(receiptPrinter);
		cashRegister.add(product1);
		cashRegister.add(product1);
		cashRegister.add(createProduct(20.50));
		
		cashRegister.printReceipt();
		
		
		
		verify(receiptPrinter,times(1)).getReceiptHead();
		verify(receiptPrinter,times(1)).printMultipleItemsInItemSection(any(LinkedHashMap.class));
		verify(receiptPrinter,times(1)).getReceiptSum(cashRegister.getTotalPrice());
	}
	
}
