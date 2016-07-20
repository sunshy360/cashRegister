package app.utilsFiles;

import java.util.ArrayList;
import java.util.List;

import org.junit.Assert;
import org.junit.Test;

import app.UtilFiles.ReadUtilFile;

public class ReadShoppingCartItemFromJsonFile {
	
	@SuppressWarnings("unchecked")
	@Test
	public void test()
	{
		List<String> list2 = new ArrayList<>();
		ReadUtilFile.ShoppingCartItem(list2);
        
        List<String> list3 = new ArrayList<>();
        list3.add("ITEM000001");
        list3.add("ITEM000001");
        list3.add("ITEM000001");
        list3.add("ITEM000001");
        list3.add("ITEM000001");
        list3.add("ITEM000003");
        list3.add("ITEM000003");
        list3.add("ITEM000005");
        list3.add("ITEM000005");
        list3.add("ITEM000005");

        Assert.assertEquals(list3,list2);
        
	}

}
