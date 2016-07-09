import static org.junit.Assert.*;

import java.math.BigDecimal;
import java.util.ArrayList;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class CashRegisterTest {

	private CashRegister cashRegister;
	
	private Product product1 = null;

	@Before
	public void setUp(){
		cashRegister = new CashRegister();
		product1 = createProduct(new BigDecimal("10.50"));
	}
	
	private Product createProduct(BigDecimal price) {
		return new Product(price);
	}
	
	@Test
	public void getTotalPriceWhenJustHaveOneProduct() {
		cashRegister.add(product1);
		assertEquals(0,new BigDecimal("10.50").compareTo(cashRegister.getTotalPrice()));
	}
	
	@Test
	public void getTotalPriceWhenHaveMultipleProducts() {
		cashRegister.add(product1);
		cashRegister.add(product1);
		cashRegister.add(createProduct(new BigDecimal("20.50")));
		assertEquals(0,new BigDecimal("41.50").compareTo(cashRegister.getTotalPrice()));
	}
	
}
