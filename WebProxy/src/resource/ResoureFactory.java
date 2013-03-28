package resource;

import interfaces.Resource;

public class ResoureFactory {

	public static Resource getResourceInstance(String domainName, String path){
		Resource reuturnedResource =null;
		if(path.contains(".css")){
				reuturnedResource = new CSS(domainName, path);
		}else if( path.contains(".js")){
			reuturnedResource = new JS(domainName, path);
		}
		else{
			reuturnedResource = new Page(domainName, path);
		}
			return reuturnedResource;	
	}
}
