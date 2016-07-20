package app.utilsFiles;

import java.util.HashSet;
import java.util.Set;

import org.junit.Assert;
import org.junit.Test;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import app.UtilFiles.ReadUtilFile;

public class ReadByeTwoGetOneFreeIDTest {

	@Test
	public void test() {
		String str = ReadUtilFile.readFile(".\\Data\\ByeTwoGetOneFreeID.json");
		//读取购物清单，将其转换为list，存放
		Gson gson = new Gson();
		Set<String> retSet = gson.fromJson(str,  new TypeToken<Set<String>>(){}.getType()); 
		      
        Set<String> set = new HashSet<String>();
        set.add("ITEM000001");
        set.add("ITEM000002");
        set.add("ITEM000005");

        Assert.assertEquals(set,retSet);
	}

}
