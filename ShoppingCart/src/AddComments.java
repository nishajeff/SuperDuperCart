

import java.io.IOException;
import java.math.BigDecimal;
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
import model.Shopper;

/**
 * Servlet implementation class AddComments
 */
@WebServlet("/AddComments")
public class AddComments extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AddComments() {
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
		HttpSession session = request.getSession(true);	
		Product p=new Product();
		int pid=(int) session.getAttribute("pid");
		Shopper s=(Shopper) session.getAttribute("user");
		String rating=request.getParameter("inlineRadioOptions");
		String comments=request.getParameter("comments");
		EntityManager em=DBUtil.getEmFactory().createEntityManager();
		String q="select p from Product p where p.pid="+pid;
		TypedQuery<Product>bq1 =em.createQuery(q,Product.class);
		List<Product> listp=bq1.getResultList();
		for(Product temp:listp){
			p=temp;
			message+="<h3 align=\"left\">Product Details:</h3>"+"ID: "+ temp.getPid()+"<br>Name: "+ temp.getName()+"<br>Desxription of the Product: "+temp.getDescript()+"<br> Price: "+temp.getPrice();
		}
		Comment c=new Comment();
		c.setProduct(p);
		c.setRating(new BigDecimal(rating));
		c.setComments(comments);
		c.setShopper(s);
		DBUtil.insertComments(c);	
		request.setAttribute("message", message);						
		getServletContext().getRequestDispatcher("/productdetails.jsp").forward(request, response);
		
	}

}
