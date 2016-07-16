package app.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import app.model.Product;
import app.printer.ReceiptPrinter;

@Controller
public class HelloWorldController {

	@RequestMapping("/")
	public String helloWorld(@RequestParam("id")String id, Map<String, Object> model) {
		ReceiptPrinter receiptPrinter = new ReceiptPrinter();
		model.put("header",receiptPrinter.getReceiptHead());
		System.out.println("id:==============" +id);
		String hello = "Hello World!";
		model.put("id", id);
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
}
