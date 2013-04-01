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
		/*else{
			String[] resourceFolders = this.resourceLocation.split("/");
			String newPath = "";
			String resourceName = resourceFolders[resourceFolders.length-1];
			boolean flag = false;
			
			System.out.println("checking paths...\n");
			
			for (int j = 0; j < resourceFolders.length-1; j++){
				newPath = "";
				for (int i = j; i < resourceFolders.length-1; i++){
					newPath += "/" + resourceFolders[i];
					this.resourceLocation = newPath+"/"+resourceName;
					System.out.println(resourceLocation);
					if (openResourceConnection()){
						flag = true;
						break;
					}
				}
			}
			
			if (flag){
				System.out.println("Found: " + newPath);
			}
			else{
				System.out.println("not found");
			}
			
			this.resourceLocation = this.domainName + resourceFolders[3];
		}*/
		closeResourceConnection();
		return html;
	}





}
