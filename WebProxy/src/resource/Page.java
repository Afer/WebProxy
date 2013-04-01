package resource;

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
		
				if(link.attr("href").toString().contains("http://") == false ){
					link.attr("href", "/WebProxyLocal/"+link.attr("href"));
				}
			}
			
			for (Element a : aLinks) {
				
				if (a.attr("href").charAt(0) == '/'){
					a.attr("href", "" + a.attr("href").substring(1));
				}
				 
				if(a.attr("herf").contains("http") == false ){
					a.attr("href", "/WebProxyLocal/"+a.attr("href"));
				}
			}
			rewriteSDLC();
			doc.head().append("<script type='text/javascript' src='/test/namefinder/namefinder.js'></script>");
			html = doc.html();
		}
		return html;
	}
	
	
	private void rewriteSDLC(){
		
		doc.body().html(doc.body().html().replaceAll("\\bSDLC\\b", "CLDS"));
		doc.body().html(doc.body().html().replaceAll("\\bsdlc\\b", "clds"));
		
	}

	



}
