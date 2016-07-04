import java.math.BigDecimal;
import java.util.ArrayList;

public class CashRegister {
	private ArrayList<Product> productList = new ArrayList<Product>();
	
	public void add(Product product) {
		productList.add(product);
		
	}

	public BigDecimal getTotalPrice() {
		BigDecimal total = new BigDecimal("0.0");
		for(Product product : productList) {
			if(product.getDiscount() != null)
				total = total.add(product.getPrice().multiply(product.getDiscount()));
			else
				total = total.add(product.getPrice());
		}
		return total;
	}

}
