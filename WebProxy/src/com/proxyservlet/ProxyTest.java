package com.proxyservlet;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.HttpURLConnection;
import java.net.InetSocketAddress;
import java.net.URL;
//import java.net.Proxy;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.jsoup.Jsoup;
import org.jsoup.helper.Validate;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

/**
 * Servlet implementation class Proxy
 */
@WebServlet("/")
public class ProxyTest extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ProxyTest() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		//System.setProperty("http.proxyHost", "192.168.5.1");
		//System.setProperty("http.proxyPort", "1080");
		
		String url = "http://www.sdlcpartners.com";
		
		String newLink = request.getRequestURI();//.split("ProxyServlet")[1];
		//String contextPath = request.getPathInfo();
		
		//System.out.println(newLink);
		//System.out.println(contextPath);
		
		Document doc;
		
		if (!newLink.trim().equals("/")){
			doc = Jsoup.connect(url+newLink).get();
		}
		else{
			doc = Jsoup.connect(url).get();
		}
	
		Elements aLinks = doc.select("a[href]");
		Elements media = doc.select("[src]");
		Elements links = doc.select("link[href]");
		
		//System.out.println(request.getParameter("param1"));
		//System.out.println(request.getParameter("param2"));
		//System.out.println(request.getContextPath());
		
		
		
		for (Element src : media){
			//printWriter.println(src.tagName() + " :: " + src.attr("abs:src"));
			src.attr("src", src.attr("abs:src"));
		}
		
		for (Element href : links){
			href.attr("href",href.attr("abs:href"));
		}
		
		/*for (Element a : aLinks){
			a.attr("href", a.attr("rel:href"));
		}*/
		
		String html = doc.html();
		//String html = doc.select("style").first().data();
		response.setContentType("text/html");
        PrintWriter printWriter  = response.getWriter();
        printWriter.println(html);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

}
