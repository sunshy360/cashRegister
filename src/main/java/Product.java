import java.math.BigDecimal;

public class Product {

	private BigDecimal price = null;

	public Product(BigDecimal price) {
		this.price = price;
	}
	
	public BigDecimal getPrice() {
		return price;
	}


}
