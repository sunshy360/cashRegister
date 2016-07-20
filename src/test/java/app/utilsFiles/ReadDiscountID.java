package app.utilsFiles;

import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;

import org.junit.Test;

import app.UtilFiles.ReadUtilFile;
import app.UtilFiles.DiscountConvert;
import junit.framework.Assert;

public class ReadDiscountID {

	@Test
	public void test() {
		ReadUtilFile readUtilFile = new ReadUtilFile();
		Map<String, String> discountIDMap = readUtilFile.readDiscountItem();
		Map<String, DiscountConvert> discountConvertMap = readUtilFile.readDiscountConvertItem();
		Map<String, DiscountConvert> discountProduct = new LinkedHashMap<String, DiscountConvert>();
		
		for(String discountMessageInretMap : discountIDMap.keySet())
			for(String discountMessageInretMap2 : discountConvertMap.keySet())
		   	{
		   		String barcode = (String)discountIDMap.get(discountMessageInretMap);
		   		if(discountMessageInretMap.equals(discountMessageInretMap2) )
		   		{
			   		discountProduct.put(barcode, discountConvertMap.get(discountMessageInretMap2));
		   		}
		    }
		    
		 Iterator< String> it = discountProduct.keySet().iterator();
		 while(it.hasNext())
		 {
		  	String key = it.next();
		   	DiscountConvert dConvert = (DiscountConvert)discountProduct.get(key);
		   	if(key == "ITEM000001")
		   	{
		   		Assert.assertEquals(0.95, dConvert.getDiscount());
		   		Assert.assertEquals("可口可乐", dConvert.getDiscountMessage());
		   	}
		   	if(key == "ITEM000003")
		   	{
		   		Assert.assertEquals(0.9, dConvert.getDiscount());
		   		Assert.assertEquals("苹果", dConvert.getDiscountMessage());
		   	}
		 }
	}
}
