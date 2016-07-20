package app.utilsFiles;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;

import org.junit.Assert;
import org.junit.Test;

import app.UtilFiles.ReadUtilFile;

public class ReadShoppingCartItemFromJsonFile {
	
	@SuppressWarnings("unchecked")
	@Test
	public void test()
	{		
		ReadUtilFile readUtilFile = new ReadUtilFile();
		LinkedHashMap<String, Integer> map1= new LinkedHashMap<String, Integer>();
        List<String> list3 = new ArrayList<>();
        list3.add("ITEM000001");
        list3.add("ITEM000001");
        list3.add("ITEM000001");
        list3.add("ITEM000001");
        list3.add("ITEM000001");
        list3.add("ITEM000003-2");
        list3.add("ITEM000003");
        list3.add("ITEM000005");
        list3.add("ITEM000005");
        list3.add("ITEM000005");
        map1 = readUtilFile.shoppingCartItem(list3);
        
		LinkedHashMap<String, Integer> map2= new LinkedHashMap<String, Integer>();
		map2.put("ITEM000001", 5);
		map2.put("ITEM000003", 3);
		map2.put("ITEM000005", 3);
		
        Assert.assertEquals(map2,map1);
        
	}

}
