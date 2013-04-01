package com.proxyservlet;

import interfaces.Resource;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.HttpURLConnection;
import java.net.InetSocketAddress;
import java.net.URL;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
//import java.net.Proxy;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.naming.factory.ResourceFactory;
import org.jsoup.Jsoup;
import org.jsoup.helper.Validate;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import resource.ResourceHandler;
import resource.ResoureFactory;
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
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {


		String url = "https://tenv7.highmarkbcbsde.com/";
		//String url = "http://184.106.167.222:8080/Timesheetwebadmin";
		String newLink = request.getRequestURI().split("WebProxy")[1];

		if (newLink.trim().equals("/")){
			newLink = "chmptl/chm/jsp/Splash.do?site=hbcbsde";
		}
		else{
			newLink = "chmptl/chm/jsp" + newLink;
		}
		
		Resource resourceHandler = ResoureFactory.getResourceInstance(url, newLink);
		String html = resourceHandler.getResource();
		//System.out.println(newLink);
		//rSystem.out.println(resourceHandler.getContentType());
		response.setContentType(resourceHandler.getContentType());
		PrintWriter printWriter = response.getWriter();
		printWriter.println(html);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		Map<String, String> cookieMap = getCookiesFromRequest(request);
		
		//Get all the form data being sent
		Enumeration<String> e = request.getParameterNames();
		Map<String, String> dataMap = new HashMap<String, String>();
		String currentParameter = "";
		
		while(e.hasMoreElements()){
			currentParameter = e.nextElement().toString();
			dataMap.put(currentParameter, request.getParameter(currentParameter));
		}
		
		//Map<String, String[]> dataMap = request.getParam
		//Document doc = Jsoup.connect("https://tenv7.highmarkbcbsde.com/chmptl/chm/jsp/logon.do").data("loginid", "51DEMO", "password", "highmark1").timeout(60000).post();
		Document doc = Jsoup.connect("https://tenv7.highmarkbcbsde.com/chmptl/chm/jsp/logon.do").cookies(cookieMap).data(dataMap).timeout(60000).post();
		PrintWriter printWriter = response.getWriter();
		printWriter.println(doc.html());
	}
	
	private Map<String, String> getCookiesFromRequest(HttpServletRequest request){
		Map<String, String> cookieMap = new HashMap<String, String>();
		for (int i = 0; i < request.getCookies().length; i++){
			//System.out.println(request.getCookies()[i].getName() + " = " + request.getCookies()[i].getValue());
			cookieMap.put(request.getCookies()[i].getName(), request.getCookies()[i].getValue());
		}
		
		return cookieMap;
	}

}
