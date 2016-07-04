import java.math.BigDecimal;

public class Product {

	private BigDecimal price = null;
	private String Barcode = null;
	private BigDecimal discount = null;

	public Product(BigDecimal price) {
		this.price = price;
	}
	
	public Product(BigDecimal bigDecimal,BigDecimal discount, String barcode) {
		this(bigDecimal);
		this.discount = discount;
		this.Barcode = barcode;
	}

	public BigDecimal getPrice() {
		return price;
	}

	public String getBarcode() {
		return Barcode;
	}

	public BigDecimal getDiscount() {
		return discount;
	}



}
