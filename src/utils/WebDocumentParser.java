package utils;

import java.io.IOException;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

public class WebDocumentParser {
	
	public static String parseWebDocument(String webAdress) throws IOException {
		Document doc = Jsoup.connect(webAdress).get();
		return doc.body().text();
	}

}
