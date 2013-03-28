package resource;

import java.io.IOException;

import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import interfaces.Resource;

public abstract class ResourceHandler implements Resource {

	protected String resourceLocation;
	protected String domainName;
	protected String contentType = null;
	protected Document doc;
	
	public ResourceHandler(String domain, String path){
		this.domainName = domain;
	}
	
	public boolean openResourceConnection(){
		boolean returned = true;
		try {

			doc = Jsoup.connect(domainName + resourceLocation).timeout(10000).get();
		} catch (IOException e) {
			returned = false;
		} finally{
			return returned;
		}
	}
	
	public boolean closeResourceConnection(){
		doc = null;
		return true;
	}

	public String getContentType() {
		// TODO Auto-generated method stub
		return contentType;
	}
	
}
