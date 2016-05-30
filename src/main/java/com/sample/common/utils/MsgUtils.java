package com.sample.common.utils;

import java.io.File;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

public class MsgUtils {
	
	private  static  String PATH  = "src/main/resources/message/msg.xml";
	
    public static String getMessage(String code) {
    	
    	if (code == null || "".equals(code)) {
    		return "";
    	}
    	
    	SAXReader reader = new SAXReader();
    	try {
			Document document = reader.read(new File(PATH));
			Element node = document.getRootElement();
			Element msg = node.elementByID(code);
			
			return msg.getText();
		} catch (DocumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}  
    	return "";
    }
}
