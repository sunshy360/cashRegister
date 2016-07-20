package app.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import app.UtilFiles.ReadUtilFile;
import app.model.Product;
import app.printer.ReceiptPrinter;

@Controller
public class ReceiptPrinterController {

	@RequestMapping("/")
	//@RequestParam("id")String id,
	public String hellopage(Map<String, Object> model) {
		ReceiptPrinter receiptPrinter = new ReceiptPrinter();
		model.put("header",receiptPrinter.getReceiptHead());
		String hello = "Hello World!";
		//model.put("id", id);
		model.put("hello", hello);
		List<String> lStrings = new ArrayList<>();
		lStrings.add("aaaaa");
		lStrings.add("bbbbbb");
		lStrings.add("ccccc");
		lStrings.add("ddddd");
		model.put("lst", lStrings);
		Product product = new Product("aaa", "dafdafafaf", 12, "ad", "", "");
		model.put("proudct",product);
		return "homepage";
	}

	@RequestMapping(value="/readJson", method = RequestMethod.POST)
	@ResponseBody
	public String readJson(@RequestBody String str) {
		System.out.println("request:"+str);
		ReceiptPrinter receiptPrinter = new ReceiptPrinter();
		String receipt ="<p align='center'>"+ receiptPrinter.getReceiptHead()+"</p>" +"@\n";
		ReadUtilFile.clear();
		
		ReadUtilFile.readProductItem();
		ReadUtilFile.readDiscountItem();
		ReadUtilFile.readDiscountConvertItem();
		
		List<String> shoppingList = new ArrayList<String>();
		for(String s:str.split("&"))
			shoppingList.add(s.split("=")[1]);
		ReadUtilFile.shoppingCartItem(shoppingList);
		//System.out.println("productsWithNumbers"+ReadUtilFile.productsWithNumbers);
		//System.out.println("receipt:"+receipt);

		receipt += receiptPrinter.threeChoseOne(ReadUtilFile.productsWithNumbers)+"@\n";
		
		if(!receiptPrinter.buyTwoFreeOneList.isEmpty())
			receipt += receiptPrinter.printOneItemInItemsSectionWheBuyTwoGetOneFreeList()+"@\n";
		
		if(receiptPrinter.totalDiscount==0.0)
			receipt += receiptPrinter.getReceiptSum(ReadUtilFile.productsWithNumbers)+"@\n";

		System.out.println("receipt:"+receipt);
		
		return receipt;
	}
	
}
