

import java.io.IOException;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.DBUtil;
import model.Product;

/**
 * Servlet implementation class details
 */
@WebServlet("/details")
public class details extends HttpServlet {
	private static final long serialVersionUID = 1L;
     String message="";  
    /**
     * @see HttpServlet#HttpServlet()
     */
    public details() {
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
		HttpSession session = request.getSession(true);	
		String id=request.getParameter("id");
		int p_id=Integer.parseInt(id);
		EntityManager em=DBUtil.getEmFactory().createEntityManager();
		String q="select p from Product p where p.pid="+p_id;
		TypedQuery<Product>bq1 =em.createQuery(q,Product.class);
		List<Product> listp=bq1.getResultList();
		for(Product temp:listp){
			message+="<h3 align=\"center\">Product Details:</h3>"+"ID: "+ temp.getPid()+"<br>Name: "+ temp.getName()+"<br>Desxription of the Product: "+temp.getDescript()+"<br> Price: "+temp.getPrice();
			
		}
		 message+="</div>";
	     List<Product>l=(List<Product>) session.getAttribute("Products");
	     
	      message+="<div align=\"left\"><table style=\"border:2px solid black\">";
	      message+="<th style=\" background-color:gray;border:2px solid black\">ProductID";
	      for(Product temp:l){
				message+="<tr ><td style=\" background-color:white;border:2px solid black\"><a href=\"details?id="+temp.getPid() +"\">"+temp.getPid() +"</a>"+
						
	        		  
	        		  "</td></tr>" ;  
			}
	     message+=" </div>";

			request.setAttribute("message", message);						
			getServletContext().getRequestDispatcher("/Shopping.jsp").forward(request, response);
		}
	}


