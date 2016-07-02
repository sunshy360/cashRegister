import static org.junit.Assert.*;

import java.math.BigDecimal;
import java.util.ArrayList;

import org.junit.Test;

public class CashRegisterTest {

	@Test
	public void getTotalPriceWhenJustHaveOneProduct() {
		CashRegister cashRegister = new CashRegister();
		cashRegister.add(new Product(new BigDecimal("10.50")));
		assertEquals(0,new BigDecimal("10.50").compareTo(cashRegister.getTotalPrice()));
	}
	
	@Test
	public void getTotalPriceWhenHaveMultipleProducts() {
		CashRegister cashRegister = new CashRegister();
		cashRegister.add(new Product(new BigDecimal("10.50")));
		cashRegister.add(new Product(new BigDecimal("10.50")));
		cashRegister.add(new Product(new BigDecimal("20.50")));
		assertEquals(0,new BigDecimal("41.50").compareTo(cashRegister.getTotalPrice()));
	}
	

}
