package resource;

import java.io.IOException;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import com.oracle.jrockit.jfr.ContentType;

import interfaces.Resource;



public class CSS extends ResourceHandler{
	


	public CSS(String domain, String path){
			super(domain, path);
			contentType = "text/css";
	}
	
	@Override
	public String getResource() {
		String html = null;
		if(openResourceConnection()){
			html = doc.body().html().replace("&quot;", "\"");
			
			html = html.replaceAll("image\\s*:\\s*url\\s*\\(\\s*", "image:url("+domainName );
			//System.out.println(html);
			
		}
		closeResourceConnection();
		return html;
	}





}
