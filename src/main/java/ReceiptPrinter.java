import java.math.BigInteger;

public class ReceiptPrinter {

	public String getReceiptHead() {
		return "没钱赚商店清单";
	}

	public String oneItemInReceiptItem(Product product, int number) {
		String str = String.format("名称：可口可乐，数量：%d瓶，单价：%1.2f（元），小计：%1.2f（元）",number,product.getPrice().doubleValue(),product.getPrice().doubleValue() * number);
		return str;
	}
}
