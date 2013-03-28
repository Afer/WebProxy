package resource;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import com.oracle.jrockit.jfr.ContentType;
import com.sun.xml.internal.bind.v2.runtime.reflect.opt.Const;

import interfaces.Resource;

public class Page extends ResourceHandler {


	

	public Page(String domainName, String resourceLocation) {
		super(domainName, resourceLocation);
		contentType = "text/html";
	}

	@Override
	public String getResource() {

		String html = null;

		if (openResourceConnection()) {
			Elements aLinks = doc.select("a[href]");
			Elements media = doc.select("[src]");
			Elements links = doc.select("link[href]");

			for (Element src : media) {
				src.attr("src", src.attr("abs:src"));
			}
			
			for (Element link : links){
				if (link.attr("href").charAt(0) == '/'){
					link.attr("href", link.attr("href").substring(1));
				}
			}

			for (Element a : aLinks) {
				a.attr("href", "" + a.attr("href").substring(1));
			}
			
			//String build = buildScript();
			//doc.head().append(buildScript());
			rewriteSDLC();
			doc.head().append("<script type='text/javascript' src='/test/namefinder/namefinder.js'></script>");
			html = doc.html();
		}
		closeResourceConnection();
		return html;
	}
	
	
	private void rewriteSDLC(){
		
		doc.body().html(doc.body().html().replaceAll("\\bSDLC\\b", "CLDS"));
		doc.body().html(doc.body().html().replaceAll("\\bsdlc\\b", "clds"));
		
	}

	private String buildScript(){
		
		String buildString = "<script type='text/javascript'>\n";
		
		/*//TODO: get properties list of jscript files to inject
		try{
			
			FileReader fstream = new FileReader("C:/Users/Administrator/Desktop/namefinder/namefinder.js");
			BufferedReader in = new BufferedReader(fstream);
			String currentLine = "";
			
			while ((currentLine = in.readLine()) != null) {
				buildString += currentLine + "\n";
			}
			
			in.close();
			fstream.close();
			
		}catch (Exception e){
			System.out.println("ERROR Reading javascript files: " + e.getMessage());
		}
		
		buildString += "</script>";*/
		
		return buildString;
	}



}
