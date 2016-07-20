package app.UtilFiles;

public class DiscountConvert {

	private double discount = 0.0;
	private String discountMessage = "";
	
	public DiscountConvert( double discount, String discountMessage) {
		this.discount = discount;
		this.discountMessage = discountMessage;
	}

	public double getDiscount() {
		return discount;
	}

	public String getDiscountMessage() {
		return discountMessage;
	}
	 
}
