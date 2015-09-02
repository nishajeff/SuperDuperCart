

import java.io.IOException;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.DBUtil;
import model.Cart;

/**
 * Servlet implementation class admin
 */
@WebServlet("/admin")
public class admin extends HttpServlet {
	private static final long serialVersionUID = 1L;
     String message=""; 
    /**
     * @see HttpServlet#HttpServlet()
     */
    public admin() {
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
		String admin=request.getParameter("name");
		String message="";
		if(admin.equalsIgnoreCase("admin")){
		String q="select c from Cart c ";
		EntityManager em=DBUtil.getEmFactory().createEntityManager();
		TypedQuery<Cart>bq1 =em.createQuery(q,Cart.class);
		List<Cart> listp=bq1.getResultList();
		message+="<div align=\"center\"><table style=\"border:2px solid black\">";
        message+="<th style=\" background-color:gray;border:2px solid black\">Product ID</th><th style=\" background-color:gray;border:2px solid black\">Product Name</th><th style=\" background-color:gray;border:2px solid black\">Quantity</th><th style=\" background-color:gray;border:2px solid black\">Total</th><th style=\" background-color:gray;border:2px solid black\">Shopper</th><th style=\" background-color:gray;border:2px solid black\">Order Confirmed</th>";
		for(Cart temp:listp){
			message+="<tr ><td style=\" background-color:white;border:2px solid black\">"+temp.getProduct().getPid()+				
					"</td><td style=\" background-color:white;border:2px solid black\">"+temp.getProduct().getName()+
					"</td><td style=\" background-color:white;border:2px solid black\">"+temp.getQty()+
					"</td><td style=\" background-color:white;border:2px solid black\">"+temp.getTotal()+
						"</td><td style=\" background-color:white;border:2px solid black\">"+temp.getShopper().getName()+
						"</td><td style=\" background-color:white;border:2px solid black\">"+temp.getStatus()+
          		  "</td></tr>" ;  
		}
		message+="</div>";
		}
		else
			message+="<h4>Sign In as Admin Unsuccessful!Please try again.</h4>";
		request.setAttribute("message", message);		
		getServletContext().getRequestDispatcher("/details.jsp").forward(request, response);
	}

}
