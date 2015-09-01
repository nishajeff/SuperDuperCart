

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
import model.Product;

/**
 * Servlet implementation class products
 */
@WebServlet("/products")
public class products extends HttpServlet {
	private static final long serialVersionUID = 1L;
     String message="";  
    /**
     * @see HttpServlet#HttpServlet()
     */
    public products() {
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
		String message="";
		String q="select p from Product p ";
		EntityManager em=DBUtil.getEmFactory().createEntityManager();
		TypedQuery<Product>bq1 =em.createQuery(q,Product.class);
		List<Product> listp=bq1.getResultList();
		message+="<div align=\"center\"><table style=\"border:2px solid black\">";
        message+="<th style=\" background-color:gray;border:2px solid black\">Product ID</th><th style=\" background-color:gray;border:2px solid black\">Product Name</th><th style=\" background-color:gray;border:2px solid black\">Description</th><th style=\" background-color:gray;border:2px solid black\">Price</th>";
		for(Product temp:listp){
			message+="<tr ><td style=\" background-color:white;border:2px solid black\">"+temp.getPid()+				
					"</td><td style=\" background-color:white;border:2px solid black\">"+temp.getName()+
					"</td><td style=\" background-color:white;border:2px solid black\">"+temp.getDescript()+
					"</td><td style=\" background-color:white;border:2px solid black\">"+temp.getPrice()+
          		  "</td></tr>" ;  
		}
		message+="</div>";
		request.setAttribute("message", message);		
		getServletContext().getRequestDispatcher("/products.jsp").forward(request, response);
	}

}
