package com.SentimentAnalysis;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.algorithmia.*;
import com.algorithmia.algo.*;

import org.json.JSONObject;

/**
 * Servlet implementation class Prediction
 */
@WebServlet("/Prediction")
public class Prediction extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Prediction() {
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
		String userreview = request.getParameter("review");
		String prediction = null;
		System.out.println(userreview);
		try {
			AlgorithmiaClient client = Algorithmia.client("");
			Algorithm algo = client.algo("algo://nlp/SocialSentimentAnalysis/0.1.4");
			AlgoResponse result = algo.pipe(userreview);
			prediction = result.asJsonString();
			
			// remove [, ] before and after the string as json expects to start with {
			prediction = prediction.replace("[", "").replace("]", "");

			} catch(AlgorithmException e) {
            e.printStackTrace();
        }
		
		 JSONObject jsonObj = new JSONObject(prediction);
         Double score = jsonObj.getDouble("compound");
         System.out.println(score);
         if ((score >= -1.00) && ( score < 0)){
        	 	prediction = "Negative";
         } else if ((score > 0) && (score <= 1)){
        	 	prediction = "Positive";
         }

		request.setAttribute("review", userreview);
	    request.setAttribute("prediction", prediction);
	    request.setAttribute("score", score);
	    request.getRequestDispatcher("result.jsp").forward(request, response); 
	
	}

}
