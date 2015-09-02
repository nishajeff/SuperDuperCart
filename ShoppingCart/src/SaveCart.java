

import java.io.IOException;
import java.math.BigDecimal;
import java.math.MathContext;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.Cart;
import model.DBUtil;
import model.Shopper;
import model.Shopping;

/**
 * Servlet implementation class SaveCart
 */
@WebServlet("/SaveCart")
public class SaveCart extends HttpServlet {
	private static final long serialVersionUID = 1L;
	String message1="";    
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SaveCart() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doPost(request,response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		message1="";
		HttpSession session=request.getSession(true);
		Shopper s=(Shopper) session.getAttribute("user");
		//int count=DBUtil.deleteCart(s);	
		
		Shopping shop=(Shopping) session.getAttribute("shop");
		HashMap<String, Cart> h=shop.getMap();
        for (Map.Entry<String,Cart> entry : h.entrySet())
        {
        	
	       	 Cart temp=entry.getValue();     
	       	 
	       	 model.DBUtil.insert(temp);
	      
        }

        message1+="<h3 align=\"center\">Order Saved.<br> You are signed out!Please come back and complete the order!</h3><br><h2>Order Summary:</h2>";
        
		message1+="<div align=\"center\"><table style=\"border:2px solid black\">";
        message1+="<th style=\" background-color:gray;border:2px solid black\">Product Name</th><th style=\" background-color:gray;border:2px solid black\">Quantity</th><th style=\" background-color:gray;border:2px solid black\">Total</th>";
		
    
        for (Map.Entry<String,Cart> entry : h.entrySet())
        {
       	 Cart temp=entry.getValue();
			message1+="<tr ><td style=\" background-color:white;border:2px solid black\">"+temp.getProduct().getName() +  
					
          		   "</td><td style=\" background-color:white;border:2px solid black\">"+temp.getQty()+
          		   "</td><td style=\"background-color:white;border:2px solid black\">" +temp.getTotal()+	
          		   
          		  "</td></tr>" ; 
			
			
		}
		session.invalidate();
		request.setAttribute("message", message1);						
		getServletContext().getRequestDispatcher("/Confirm.jsp").forward(request, response);
	}

		
}


