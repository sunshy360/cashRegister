import java.util.ArrayList;

public class CashRegister {
	private ArrayList<Product> productList = new ArrayList<Product>();
	
	public void add(Product product) {
		productList.add(product);
		
	}

	public double getTotalPrice() {
		double total = 0.0;
		for(Product product : productList) {
			total += product.getPrice();
		}
		return total;
	}

}
