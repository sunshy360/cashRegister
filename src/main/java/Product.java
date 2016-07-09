
public class Product {
	private double price = 0.0;
	private String name = "";
	private String unit = "";
	
	
	
	public Product(String name, double price, String unit) {
		this.price = price;
		this.name = name;
		this.unit = unit;
	}

	public double getPrice() {
		return price;
	}

	public String getName() {
		return name;
	}

	public String getUnit() {
		return unit ;
	}
}
