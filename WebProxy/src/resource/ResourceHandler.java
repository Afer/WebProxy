package resource;

import java.io.IOException;
import java.lang.instrument.Instrumentation;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLSession;

import org.jsoup.Connection;
import org.jsoup.Connection.Response;
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
		this.resourceLocation = path;
	}
	
	public boolean openResourceConnection(){
		boolean returned = true;
		try {
			HttpsURLConnection.setDefaultHostnameVerifier(new HostnameVerifier() {
		        public boolean verify(String s, SSLSession sslSession) {
		            return true;
		        }
		    });
			
			//System.out.println(domainName + resourceLocation);
			doc = Jsoup.connect(domainName + resourceLocation).timeout(10000).get();
			//System.out.println(response.body());
			//doc = response.parse();
		} catch (IOException e) {
			System.out.println("Exception during connection to " + domainName+resourceLocation + " :: " + e.getMessage());
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
	
	public static void premain(String args, Instrumentation inst) {
	    HttpsURLConnection.setDefaultHostnameVerifier(new HostnameVerifier() {
	        public boolean verify(String s, SSLSession sslSession) {
	            return true;
	        }
	    });
	}
	
}
