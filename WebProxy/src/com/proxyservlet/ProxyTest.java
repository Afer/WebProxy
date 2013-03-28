package com.proxyservlet;

import interfaces.Resource;

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


		String url = "http://www.sdlcpartners.com";
		String newLink = request.getRequestURI().split("WebProxyLocal")[1];


		Resource resourceHandler = ResoureFactory.getResourceInstance(url, newLink);
		String html = resourceHandler.getResource();
		System.out.println(newLink);
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
	}

}
