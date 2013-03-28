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

			for (Element a : aLinks) {
				a.attr("href", "" + a.attr("href").substring(1));
			}

			html = doc.html();
		}
		closeResourceConnection();
		return html;
	}

	



}
