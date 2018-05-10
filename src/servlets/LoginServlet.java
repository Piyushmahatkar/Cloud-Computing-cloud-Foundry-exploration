package servlets;

import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.DTO.PackagePermissionDTO;

import DTO.RobotDTO;
import DTO.UserDTO;
import Service.GetRobotRestClientService;

import java.sql.Blob;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import com.DAO.ConnectionFactory;
import com.mysql.jdbc.Statement;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.DriverManager;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.*;
import javax.xml.parsers.*;
import javax.xml.transform.*;

import org.w3c.dom.*;

import java.io.*;

import javax.xml.transform.stream.*;
import javax.xml.transform.dom.*;
import javax.xml.xpath.*;

import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.NodeList;


import java.sql.ResultSet;

import DAO.LoginDAO;
import DTO.UserDTO;
import Service.LoginRestClientService;
@WebServlet(name="LoginServlet", urlPatterns={"/loginservlet"})
public class LoginServlet extends HttpServlet {
	protected void doPOST(HttpServletRequest request, HttpServletResponse response)  
            throws ServletException, IOException {  
		String enteredUsername=request.getParameter("username");
	     String enteredPassword=request.getParameter("password");
	     String url = "jdbc:mysql://localhost:3306/robocode";
			String user = "root";
			String password = "root";
			try {
				Connection conn = DriverManager.getConnection(url, user, password);
				Statement statement = (Statement) conn.createStatement();
				String newstmt = "SELECT * from user WHERE UserName = '"+enteredUsername+"'"; 
				ResultSet resultSet = statement.executeQuery(newstmt);
				//System.out.println("swxwxdedx");
				String checkName = "abc";
				while(resultSet.next()){
					 checkName=resultSet.getString("UserName");
					//robotAccessDTO.setFilePath(resultSet.getString("filepath"));
				}
				if(!(checkName.equals("abc"))) {
				    response.sendRedirect("welcome.jsp");  
				}
			}
			catch (Exception e) {
				 e.printStackTrace();
			}
  
       
    } 
	 
}