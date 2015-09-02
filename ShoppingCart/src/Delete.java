

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.Cart;
import model.Product;
import model.Shopping;

/**
 * Servlet implementation class Delete
 */
@WebServlet("/Delete")
public class Delete extends HttpServlet {
	private static final long serialVersionUID = 1L;
    String message="";  
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Delete() {
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
		HttpSession session = request.getSession(true);
		message="";
		int count=(int) session.getAttribute("count"); 
		String name=request.getParameter("name");
			
		Shopping shop=(Shopping) session.getAttribute("shop");
		shop.getMap().remove(name);	
		count--;
		  session.setAttribute("shop", shop);
		message+="<h3 align=\"center\">Shopping Cart:</h3>";
		 message+="<div align=\"center\"><table style=\"border:2px solid black\">";
        message+="<th style=\" background-color:gray;border:2px solid black\">Product Name</th><th style=\" background-color:gray;border:2px solid black\">Quantity</th><th style=\" background-color:gray;border:2px solid black\">Total</th><th style=\" background-color:gray;border:2px solid black\">EDIT</th>";
        HashMap<String, Cart> h=shop.getMap();
        for (Map.Entry<String,Cart> entry : h.entrySet())
        {
       	 Cart temp=entry.getValue();
				message+="<tr ><td style=\" background-color:white;border:2px solid black\">"+temp.getProduct().getName() +              
	             		   "</td><td style=\" background-color:white;border:2px solid black\">"+temp.getQty()+
	             		   "</td><td style=\"background-color:white;border:2px solid black\">" +temp.getTotal()+
	             		     "</td><td><a href=\"Delete?name=" +temp.getProduct().getName()+ "\">"+"DELETE</a>"+
	             		  "</td></tr>" ;  
			}
      message+="</div>";
      String out="";
      if(count==1)
     	 out="item";
      else
     	 out="items";
      message+="<p align=\"center\">You have "+count+" "+out+" in your cart.</p>";
      List<Product>l=(List<Product>) session.getAttribute("Products");
     
      message+="<div align=\"left\"><table style=\"border:2px solid black\">";
      message+="<th style=\" background-color:gray;border:2px solid black\">ProductID";
      for(Product temp:l){
			message+="<tr ><td style=\" background-color:white;border:2px solid black\"><a href=\"details?id="+temp.getPid() +"\">"+temp.getPid() +"</a>"+
					
        		  
        		  "</td></tr>" ;  
		}
     message+=" </div>";
     session.setAttribute("count", count);
			request.setAttribute("message", message);		
			getServletContext().getRequestDispatcher("/Shopping.jsp").forward(request, response);
	}

}
