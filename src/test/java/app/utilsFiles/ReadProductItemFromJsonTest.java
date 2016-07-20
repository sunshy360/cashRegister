package app.utilsFiles;

import java.util.Map;

import org.junit.Test;

import app.UtilFiles.ReadUtilFile;
import app.model.Product;
import junit.framework.Assert;

public class ReadProductItemFromJsonTest {

	@SuppressWarnings("deprecation")
	@Test
	public void test()
	{
	    Map<String, Product> retMap = ReadUtilFile.ReadProductItem();
		for(String barcode: retMap.keySet())
	    {
	    	Product product = retMap.get(barcode);
	    	//System.out.println("barcode:" + barcode + "    name：" + product.getName());
	    	if(barcode == "ITEM0001")
	    		Assert.assertEquals("可口可乐", product.getName());
	    	if(barcode == "ITEM0004")
	    		Assert.assertEquals("中性笔", product.getName());
	    }
	}
}
