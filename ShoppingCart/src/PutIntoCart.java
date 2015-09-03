

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
		int count=(int) session.getAttribute("count");
		Shopper s= (Shopper) session.getAttribute("user");	
		String quant=request.getParameter("qty");
			
		int id=(int) session.getAttribute("pid");
		model.Product new_P=new model.Product();
		Shopping shop;
		if(session.getAttribute("shop")==null){
			shop=new Shopping();
			}	
			else
				shop=(Shopping) session.getAttribute("shop");
		if(!(quant==null)){
		int qty=Integer.parseInt(quant);	
		EntityManager em=DBUtil.getEmFactory().createEntityManager();
		String q="select p from Product p where p.pid="+id;
		TypedQuery<Product>bq =em.createQuery(q,Product.class);
		List<Product> list=bq.getResultList();
		for(Product temp:list){
			//System.out.println("hello");
			new_P=temp;
		}
		MathContext mc = new MathContext(4); 
		model.Cart c=new model.Cart();
		
		
		c.setProduct(new_P);
		c.setQty(new BigDecimal(qty));	
		BigDecimal  amt=c.getQty().multiply(new_P.getPrice(), mc);
		c.setTotal(amt);
		c.setShopper(s);
		c.setStatus("no");
		c.setOrderId(new BigDecimal(0L));
		System.out.println(c.getProduct().getName());		
		shop.putToMap(c.getProduct().getName(), c);
		count++;
		}
		
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
         String out="";
         if(count==1)
        	 out="item";
         else
        	 out="items";
         message+="<p align=\"center\">You have "+count+" "+out+" in your cart.</p>";
         message+="</div>";
         message+="<h4>Product List</h4>";
        List<Product>l=(List<Product>) session.getAttribute("Products");
        if(l==null)
        	System.out.println("Empty");
        message+="<div align=\"left\"><table style=\"border:2px solid black\">";
        message+="<th style=\" background-color:gray;border:2px solid black\">ProductID</th>";
        for(Product temp:l){
			message+="<tr ><td style=\" background-color:white;border:2px solid black\"><a href=\"details?id="+temp.getPid() +"\">"+temp.getPid() +"</a>"+          		  
          		  "</td></tr>" ;  
		}
       message+=" </div>";
       session.setAttribute("count", count);
     		session.setAttribute("shop", shop);
			request.setAttribute("message", message);		
			getServletContext().getRequestDispatcher("/Shopping.jsp").forward(request, response);
			}catch(Exception e){				
				e.printStackTrace();
			}
		 
	}

}
