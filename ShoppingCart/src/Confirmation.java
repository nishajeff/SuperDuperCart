

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
		BigDecimal Tax=new BigDecimal(0.06);
		BigDecimal Total =new BigDecimal(0L);
		MathContext mc = new MathContext(4); 
		HttpSession session = request.getSession(true);		
		Shopping shop=(Shopping) session.getAttribute("shop");
		Shopper s=(Shopper) session.getAttribute("user");
		HashMap<String, Cart> h=shop.getMap();       

        message+="<h3 align=\"center\">Thank you for shopping with us!</h3><br><h2 align=\"center\">Order Summary:</h2>";
       
		message+="<div align=\"center\"><table style=\"border:2px solid black\">";
        message+="<th style=\" background-color:gray;border:2px solid black\">Product Name</th><th style=\" background-color:gray;border:2px solid black\">Quantity</th><th style=\" background-color:gray;border:2px solid black\">Total</th>";
		
    
        for (Map.Entry<String,Cart> entry : h.entrySet())
        {
       	 Cart temp=entry.getValue();
			message+="<tr ><td style=\" background-color:white;border:2px solid black\">"+temp.getProduct().getName() +  
					
          		   "</td><td style=\" background-color:white;border:2px solid black\">"+temp.getQty()+
          		   "</td><td style=\"background-color:white;border:2px solid black\">" +temp.getTotal()+          		   
          		  "</td></tr>" ; 
			temp.setStatus("yes");
			Sum= Sum.add(temp.getTotal(), mc);
			model.DBUtil.insert(temp);
					
		}
        EntityManager em=DBUtil.getEmFactory().createEntityManager();
        String q="select c from Cart c where c.shopper.userId= "+s.getUserId()+" and c.status='no'";
		TypedQuery<Cart>bq2=em.createQuery(q,Cart.class);
		List<Cart> list_cart=bq2.getResultList();
		for(Cart temp:list_cart){
			message+="<tr ><td style=\" background-color:white;border:2px solid black\">"+temp.getProduct().getName() +  
					
	          		   "</td><td style=\" background-color:white;border:2px solid black\">"+temp.getQty()+
	          		   "</td><td style=\"background-color:white;border:2px solid black\">" +temp.getTotal()+          		   
	          		  "</td></tr>" ; 
			DBUtil.updateCart(temp);
			Sum= Sum.add(temp.getTotal(), mc);
		 }
		Total=Sum.multiply(Tax, mc);
		
		message+="<h4>Total= "+Sum+"</h4>";
		Sum=Sum.subtract(Total, mc);
		message+="<h3>Tax=6%</h3>";
		message+="<h2>Grand Total= "+Sum+"</h2>";
		request.setAttribute("message", message);						
		getServletContext().getRequestDispatcher("/Confirm.jsp").forward(request, response);
	}

}
