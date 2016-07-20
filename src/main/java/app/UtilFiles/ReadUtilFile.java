package app.UtilFiles;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import app.controller.DiscountConvert;
import app.model.Product;


public class ReadUtilFile {

	public static String readFile(String filePath)
	{
		String str = "";
		File file = new File(filePath);
		// 判断文件是否存在，且其属性是否为file
		if(file.exists() && file.isFile()){ 
			BufferedReader bufferedReader = null;
			String lineData = "";
			try {
				bufferedReader = new BufferedReader(new InputStreamReader(new FileInputStream(file),"GBK"));
				// 逐行从文件中读取输入数据
				while((lineData = bufferedReader.readLine()) != null){
					str += lineData;
				}
				} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}finally{
				CloseUtil.close(bufferedReader);
			}
		}
	return str;
	}
	
	public static Map<String, Object> ReadDiscountItem()
	{
		String str = readFile(".\\Data\\discountID.json");
		//读取打折清单中列表
		Gson gson = new Gson();
	    Map<String, Object> retMap = gson.fromJson(str, new TypeToken<Map<String, Object>>(){}.getType());
	    
	    return retMap;
	}
	
	public static Map<String, DiscountConvert> ReadDiscountConvertItem()
	{
		String str2 = readFile(".\\Data\\discountConvert.json");
	    //读取打折转换字符列表
		Gson gson2 = new Gson();
	    Map<String, DiscountConvert> retMap2 = gson2.fromJson(str2, new TypeToken<Map<String, DiscountConvert>>(){}.getType());
	    
	    return retMap2;
	}
	
	public static void ShoppingCartItem(List<String> list2)
	{
		String str = readFile(".\\Data\\shoppingCart.json");
		//读取购物清单，将其转换为list，存放
		Gson gson = new Gson();
		List<String> retList = gson.fromJson(str,  new TypeToken<List<String>>(){}.getType()); 
		@SuppressWarnings("rawtypes")
		ArrayList list = new ArrayList();
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
	}
	
	public static Map<String, Product> ReadProductItem()
	{
		String str = ReadUtilFile.readFile(".\\Data\\product.json");
		//将json string转为product对象 ，用map存放
		Gson gson = new Gson();
	    Map<String, Product> retMap = gson.fromJson(str, new TypeToken<Map<String, Product>>(){}.getType());
	    return retMap;
	}
}
