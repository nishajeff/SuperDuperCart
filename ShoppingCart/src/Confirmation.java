

import java.io.IOException;
import java.math.BigDecimal;
import java.math.MathContext;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

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
import model.Storecredit;

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
		Random rn = new Random();
		int num = rn.nextInt(10000) + 1;
		BigDecimal O_num=new BigDecimal(num);
		EntityManager em=DBUtil.getEmFactory().createEntityManager();
        String q1="select c from Cart c where c.orderId= "+O_num;
		TypedQuery<Cart>bq3=em.createQuery(q1,Cart.class);
		List<Cart> l;
		do{
			l=bq3.getResultList();
			num= rn.nextInt(10000) + 1;
			O_num=new BigDecimal(num);
		}while(!(l.isEmpty()));
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
			temp.setOrderId(O_num);
			Sum= Sum.add(temp.getTotal(), mc);
			model.DBUtil.insert(temp);
					
		}
    
        
		Total=Sum.multiply(Tax, mc);
		
		message+="<h4>Total= "+Sum+"</h4>";
		Sum=Sum.add(Total, mc);
		message+="<h3>Tax=6%</h3>";
		
		String q3="select sc from Storecredit sc where sc.userId="+s.getUserId()+" and sc.status='unused'";
		TypedQuery<Storecredit>bq4=em.createQuery(q3,Storecredit.class);
		List<Storecredit> cr=bq4.getResultList();
		if(!(cr.isEmpty())){
				Sum=Sum.subtract(new BigDecimal(25), mc);
				DBUtil.updateStoreCredit(s);
				message+="<h4>Congratulations!You have a store credit of $25 deducted from the total Purchase Amount.</h4> ";
		}
		message+="<h2> Grand Total= "+Sum+"</h2>";
		message+="<p>Payment Accepted.</p><h4>The grand total of "+Sum+"$ has been charged on your credit card.</h4>";
		message+="<h4>Billing/Shipping Address:</h4>";
		message+=s.getAddress()+"<br>";
		message+="<p>If your shipping address is not the same as billing address please update shipping address.</p>";
		message+="<h2>Order will be shipped soon!Thank you.</h2>";
		
		
		
		//DBUtil.updateCartOrder(o);	
		request.setAttribute("message", message);						
		getServletContext().getRequestDispatcher("/Confirm.jsp").forward(request, response);
	}

}
