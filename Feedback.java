package com.SentimentAnalysis;

import java.io.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


/**
 * Servlet implementation class Feedback
 */
@WebServlet("/Feedback")
public class Feedback extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Feedback() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.setContentType("text/html");
		//PrintWriter out 	= response.getWriter();
		String userpred ="", review ="", prediction = "";
		String userfeedback = request.getParameter("userfeedback");
		if (userfeedback == "yes") {
			userpred = "Positive";
		} else {
			userpred = "Negative";
		}
		request.setAttribute("pred", userfeedback);	
		
		Cookie[] cookies = request.getCookies();  
		if (cookies != null) {
			prediction = cookies[0].getValue();
			review = cookies[1].getValue();
		    System.out.println(review + " " + prediction);
		       }    	
	    	
		Connection conn = null;
		
		try {
			DriverManager.registerDriver(new org.sqlite.JDBC());
			String url = "jdbc:sqlite:/moviereview.db";
			conn = DriverManager.getConnection(url);
			System.out.println("Connection to SQLite has been established.");
			} 
		catch(SQLException e) {
			System.out.println(e.getMessage());
			System.out.println("ERROR IN CATCH");
		}
		
		String sql = "INSERT INTO reviews_table(USER_REVIEW, PREDICTED_SENTIMENT, USER_SENTIMENT) VALUES(?,?,?)";
		
		try {	
			PreparedStatement stmt  = conn.prepareStatement(sql);
			stmt.setString(1, review);
			stmt.setString(2, prediction);
			stmt.setString(3, userpred);
			stmt.executeUpdate();
		} 
		catch(SQLException e) {
			System.out.println(e.getMessage());
			System.out.println("ERROR IN prepare statemtn");
		} finally {
			try {
				if (conn != null) {
					conn.close();
				}
			} catch (SQLException ex) {
				System.out.println(ex.getMessage());
			}
		} 
					
		request.getRequestDispatcher("index.jsp").forward(request, response);

	}

}
