

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

import model.Comment;
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
		session.setAttribute("pid", p_id);
		EntityManager em=DBUtil.getEmFactory().createEntityManager();
		String q="select p from Product p where p.pid="+p_id;
		TypedQuery<Product>bq1 =em.createQuery(q,Product.class);
		List<Product> listp=bq1.getResultList();
		for(Product temp:listp){
			message+="<h3 align=\"left\">Product Details:</h3>"+"ID: "+ temp.getPid()+"<br>Name: "+ temp.getName()+"<br>Desxription of the Product: "+temp.getDescript()+"<br> Price: "+temp.getPrice();
			
		}
		 message+="</div>";
		 String q1="select c from Comment c where c.product.pid="+p_id;
			TypedQuery<Comment>bq2 =em.createQuery(q1,Comment.class);
			List<Comment> listc=bq2.getResultList();
			message+="<div align=\"left\"><table style=\"border:2px solid black\">";
		      message+="<th style=\" background-color:gray;border:2px solid black\">Comments</th><th style=\" background-color:gray;border:2px solid black\">Rating</th><th style=\" background-color:gray;border:2px solid black\">Date</th>";
			for(Comment temp1:listc){
				message+="<tr ><td style=\" background-color:white;border:2px solid black\">"+temp1.getComments() +
						"</td><td style=\" background-color:white;border:2px solid black\">"+temp1.getRating() +
						"</td><td style=\" background-color:white;border:2px solid black\">"+temp1.getCdate() +
						"</td></tr>";
			}
	    
	     message+=" </div>";		
		 	message+="<form style=\"width:30%\" action =\"AddComments\"><b>Rate this Product</b><br><label class=\"radio-inline\">"

					+ "<input type=\"radio\" name=\"inlineRadioOptions\" id=\"inlineRadio1\" value=\"1\"> 1"

					+ "</label><label class=\"radio-inline\">"

					+ "<input type=\"radio\" name=\"inlineRadioOptions\" id=\"inlineRadio2\" value=\"2\"> 2"

					+ "</label><label class=\"radio-inline\">"

					+ "<input type=\"radio\" name=\"inlineRadioOptions\" id=\"inlineRadio3\" value=\"3\"> 3"

					+ "</label><label class=\"radio-inline\">"

					+ "<input type=\"radio\" name=\"inlineRadioOptions\" id=\"inlineRadio3\" value=\"4\"> 4"

					+ "</label><label class=\"radio-inline\">"

					+ "<input type=\"radio\" name=\"inlineRadioOptions\" id=\"inlineRadio3\" value=\"5\"> 5</label>"

					+ "<textarea class=\"form-control\" name =\"comments\" rows=\"3\"></textarea>"

					+ "<input type=\"submit\" value =\"Add Ratings\"></form>";		
			request.setAttribute("message", message);						
			getServletContext().getRequestDispatcher("/productdetails.jsp").forward(request, response);
		}
	}


