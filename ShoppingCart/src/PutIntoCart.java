

import java.io.IOException;
import java.math.BigDecimal;
import java.math.MathContext;
import java.util.List;

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


/**
 * Servlet implementation class PutIntoCart
 */
@WebServlet("/PutIntoCart")
public class PutIntoCart extends HttpServlet {
	private static final long serialVersionUID = 1L;
    String message="";   
    /**
     * @see HttpServlet#HttpServlet()
     */
    public PutIntoCart() {
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
		try{
		message="";
		HttpSession session = request.getSession(true);	
		Shopper s= (Shopper) session.getAttribute("user");			
		
		int qty=Integer.parseInt(request.getParameter("qty"));
		int id=Integer.parseInt(request.getParameter("pid"));
		
		model.Product new_P=new model.Product();
		EntityManager em=DBUtil.getEmFactory().createEntityManager();
		String q="select p from Product p where p.pid="+id;
		TypedQuery<Product>bq =em.createQuery(q,Product.class);
		List<Product> list=bq.getResultList();
		for(Product temp:list){
			new_P=temp;
		}
		MathContext mc = new MathContext(4); 
		model.Cart c=new model.Cart();				
		c.setProduct(new_P);
		c.setQty(new BigDecimal(qty));	
		BigDecimal  amt=c.getQty().multiply(new_P.getPrice(), mc);
		c.setTotal(amt);
		c.setShopper(s);
		model.DBUtil.insert(c);
		
		message+="<h3 align=\"center\">Shopping Cart:</h3>";
		 message+="<div align=\"center\"><table style=\"border:2px solid black\">";
         message+="<th style=\" background-color:gray;border:2px solid black\">Product Name</th><th style=\" background-color:gray;border:2px solid black\">Quantity</th><th style=\" background-color:gray;border:2px solid black\">Total</th>";
		 q="select c from Cart c where c.shopper.userId="+s.getUserId();		 
		 TypedQuery<Cart>bq1 =em.createQuery(q,Cart.class);
			List<Cart> list1=bq1.getResultList();
			for(Cart temp:list1){
				message+="<tr ><td style=\" background-color:white;border:2px solid black\">"+temp.getProduct().getName() +              
	             		   "</td><td style=\" background-color:white;border:2px solid black\">"+temp.getQty()+
	             		   "</td><td style=\"background-color:white;border:2px solid black\">" +temp.getTotal()+	             		    
	             		  "</td></tr>" ;  
			}
			request.setAttribute("message", message);		
			getServletContext().getRequestDispatcher("/Shopping.jsp").forward(request, response);
			}catch(Exception e){
				System.out.println(e.getMessage());

			}
		 
	}

}
