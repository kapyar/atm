package Controller;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;

public enum NotifyController {
	INSTANCE;
	
	private final String byteHandId = "6273";
	private final String bytehandKey = "756E12CD3A600BF1";
	private final String bytehandFrom = "SMS-INFO";
	private final String encodeCharset = "UTF-8";
	
	public boolean sendSms(final String to, final String text) {
	    try {
	        new URL("http://bytehand.com:3800/send?id=" + byteHandId + "&key=" + bytehandKey + "&to=" + URLEncoder.encode(to, encodeCharset) + "&from=" + URLEncoder.encode(bytehandFrom, encodeCharset) + "&text=" + URLEncoder.encode(text, encodeCharset)).getContent();
	        return true;
	    } catch (Exception e) {
	        return false;
	    }
	}
	
	
	
}
