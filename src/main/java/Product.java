import java.math.BigDecimal;

public class Product {

	private BigDecimal price = null;
	private BigDecimal discount = null;

	public Product(BigDecimal price) {
		this.price = price;
	}
	
	public Product(BigDecimal bigDecimal,BigDecimal discount) {
		this(bigDecimal);
		this.discount = discount;
	}

	public BigDecimal getPrice() {
		return price;
	}

	public BigDecimal getDiscount() {
		return discount;
	}



}
