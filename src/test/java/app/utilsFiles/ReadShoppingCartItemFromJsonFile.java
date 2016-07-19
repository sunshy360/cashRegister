package app.utilsFiles;

import java.util.ArrayList;
import java.util.List;

import org.junit.Assert;
import org.junit.Test;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.sun.org.apache.xalan.internal.xsltc.compiler.Template;

import app.UtilFiles.ReadUtilFile;

public class ReadShoppingCartItemFromJsonFile {

	@SuppressWarnings("unchecked")
	@Test
	public void test()
	{
		String str = ReadUtilFile.readFile(".\\Data\\shoppingCart.json");
		//读取购物清单，将其转换为list，存放
		Gson gson = new Gson();
		List<String> retList = gson.fromJson(str,  
                new TypeToken<List<String>>() {  
                }.getType()); 
		@SuppressWarnings("rawtypes")
		ArrayList list = new ArrayList();
		@SuppressWarnings("rawtypes")
		ArrayList list2 = new ArrayList();
        for (String str2 : retList) {
        	list.add(str2);
        }  
        
        for(int i = 0; i < list.size(); i++)
        {
        	if(list.get(i).toString().contains("-"))
            	{
            		String[] temp = list.get(i).toString().split("-");
            		for(int j = 0; j < Integer.parseInt(temp[1]);j++)
            			list2.add(temp[0]);
            	}
        	else
        		list2.add(list.get(i).toString());
            	
        }
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
