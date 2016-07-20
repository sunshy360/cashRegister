package app.UtilFiles;

import java.io.BufferedReader;
import java.io.Closeable;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import app.model.Product;

public class ReadUtilFile {

	public static Map<String, String> discountMap = new HashMap<String, String>();
	public static Map<String, DiscountConvert> discountConvertMap = new HashMap<String, DiscountConvert>();
	public static Map<String, Product> productMap = new HashMap<String, Product>();
	public static LinkedHashMap<String, Integer> productsWithNumbers = new LinkedHashMap<String, Integer>();
	
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
				close(bufferedReader);
			}
		}
	return str;
	}
	
	public static void close(Closeable closeable) {
		if(null != closeable){
			try {
				closeable.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	public static Map<String, Product> readProductItem()
	{
		String str = ReadUtilFile.readFile(".\\Data\\product.json");
		//json string product
		Gson gson = new Gson();
	    productMap = gson.fromJson(str, new TypeToken<Map<String, Product>>(){}.getType());
	    return productMap;
	}
	
	public static Map<String, String> readDiscountItem()
	{
		String str = readFile(".\\Data\\discountID.json");
		//折扣信息
		Gson gson = new Gson();
		discountMap = gson.fromJson(str, new TypeToken<Map<String, String>>(){}.getType());
	    
	    return discountMap;
	}

	public static Map<String, DiscountConvert> readDiscountConvertItem()
	{
		String str2 = readFile(".\\Data\\discountConvert.json");
		//折扣转换
		Gson gson2 = new Gson();
		discountConvertMap = gson2.fromJson(str2, new TypeToken<Map<String, DiscountConvert>>(){}.getType());
	    
	    return discountConvertMap;
	}
	
	public static ArrayList<String> readBuyTwoGetOneFreeID()
	{
		String str = ReadUtilFile.readFile(".\\Data\\ByeTwoGetOneFreeID.json");
		//买二赠一
		Gson gson = new Gson();
		ArrayList<String> retArray = gson.fromJson(str,  new TypeToken<ArrayList<String>>(){}.getType()); 
		return retArray;
	}
	
	public static Map<String, DiscountConvert> readDiscountIDConvert()
	{
		Map<String, String> discountIDMap = ReadUtilFile.readDiscountItem();
		Map<String, DiscountConvert> discountConvertMap = ReadUtilFile.readDiscountConvertItem();
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
		return discountProduct;
	}
	
	public static LinkedHashMap<String, Integer> shoppingCartItem(List<String> shoppingList)
	{        
        for(int i = 0; i < shoppingList.size(); i++)
        {
        	if(shoppingList.get(i).contains("-"))
            	{
            		String[] temp = shoppingList.get(i).split("-");
            		if(productsWithNumbers.containsKey(temp[0]))
            			productsWithNumbers.put(temp[0], productsWithNumbers.get(temp[0])+Integer.valueOf(temp[1]));
            		else
            			productsWithNumbers.put(temp[0], Integer.valueOf(temp[1]));
            	}
        	else{
        		if(productsWithNumbers.containsKey(shoppingList.get(i)))
        			productsWithNumbers.put(shoppingList.get(i), productsWithNumbers.get(shoppingList.get(i))+Integer.valueOf(1));  
        		else
        			productsWithNumbers.put(shoppingList.get(i), Integer.valueOf(1));
        	}	
        }
        return productsWithNumbers;
	}

	public static void clear(){
		discountMap = new HashMap<String, String>();
		discountConvertMap = new HashMap<String, DiscountConvert>();
		productMap = new HashMap<String, Product>();
		productsWithNumbers = new LinkedHashMap<String, Integer>();
	}
}
