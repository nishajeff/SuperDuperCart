

import java.io.IOException;
import java.math.BigDecimal;
import java.math.MathContext;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.Cart;
import model.DBUtil;
import model.Product;
import model.Shopper;
import model.Shopping;

/**
 * Servlet implementation class Confirmation
 */
@WebServlet("/Confirmation")
public class Confirmation extends HttpServlet {
	private static final long serialVersionUID = 1L;
    String message="";  
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Confirmation() {
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
		message="";
		BigDecimal Sum =new BigDecimal(0L);
		MathContext mc = new MathContext(4); 
		HttpSession session = request.getSession(true);		
		Shopping shop=(Shopping) session.getAttribute("shop");
		Shopper s=(Shopper) session.getAttribute("user");
		HashMap<String, Cart> h=shop.getMap();
       /* for (Map.Entry<String,Cart> entry : h.entrySet())
        {
        	System.out.println("Sum="+Sum);
       	 Cart temp=entry.getValue();
       	Sum= Sum.add(temp.getTotal(), mc);
       	 
       	 model.DBUtil.insert(temp);
        }*/

        message+="<h3 align=\"center\">Thank you for shopping with us!</h3><br><h2 align=\"center\">Order Summary:</h2>";
        /*Shopper s= (Shopper) session.getAttribute("user");	
        EntityManager em=DBUtil.getEmFactory().createEntityManager();
		String q="select c from Cart c where c.shopper.userId="+s.getUserId();
		TypedQuery<Cart>bq =em.createQuery(q,Cart.class);*/
		message+="<div align=\"center\"><table style=\"border:2px solid black\">";
        message+="<th style=\" background-color:gray;border:2px solid black\">Product Name</th><th style=\" background-color:gray;border:2px solid black\">Quantity</th><th style=\" background-color:gray;border:2px solid black\">Total</th>";
		//List<Cart> list=bq.getResultList();
		
		//for(Cart temp:list){
    
        for (Map.Entry<String,Cart> entry : h.entrySet())
        {
       	 Cart temp=entry.getValue();
			message+="<tr ><td style=\" background-color:white;border:2px solid black\">"+temp.getProduct().getName() +  
					
          		   "</td><td style=\" background-color:white;border:2px solid black\">"+temp.getQty()+
          		   "</td><td style=\"background-color:white;border:2px solid black\">" +temp.getTotal()+	
          		   
          		  "</td></tr>" ; 
			Sum= Sum.add(temp.getTotal(), mc);
			
		}
		message+="<h4>Grand Total= "+Sum+"</h4>";
		int deletedCount=DBUtil.deleteCart(s);
		//TypedQuery<Cart>bq=em.createQuery(q,Cart.class);
		//int deletedCount = bq.executeUpdate();
		message+="<h4>"+deletedCount+" Items from saved cart have been deleted on checkout.</h4>";
		request.setAttribute("message", message);						
		getServletContext().getRequestDispatcher("/Confirm.jsp").forward(request, response);
	}

}
