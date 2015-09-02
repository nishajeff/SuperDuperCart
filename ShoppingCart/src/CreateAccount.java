

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

import model.DBUtil;
import model.Shopper;


/**
 * Servlet implementation class CreateAccount
 */
@WebServlet("/CreateAccount")
public class CreateAccount extends HttpServlet {
	private static final long serialVersionUID = 1L;
    String message="";
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CreateAccount() {
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
		String name=request.getParameter("name");
		String address=request.getParameter("address");
		
		Long num=Long.parseLong(request.getParameter("number"));
		Shopper s=new Shopper();
		s.setName(name);
		s.setAddress(address);
		s.setCreditNum(new BigDecimal(num));
		model.DBUtil.insertUser(s);
		EntityManager em=DBUtil.getEmFactory().createEntityManager();
		String q="select s from Shopper s where s.name='"+name+"'";		
		TypedQuery<Shopper>bq =em.createQuery(q,Shopper.class);
		List<Shopper> list=bq.getResultList();		
		message+="<h3 align=\"center\">Account Created.Please login  to start shopping.</h3>";
		message+="<div align=\"center\">";
		message+="<h4 >Your Account Information</h4>";
		System.out.println("message is:"+message);
		for(Shopper temp:list)
			message+="User ID: "+temp.getUserId()+"<br>Name:"+temp.getName()+"<br>Address: "+temp.getAddress();
		message+="</div>";
		//System.out.println("message is:"+message);
		request.setAttribute("message", message);						
			getServletContext().getRequestDispatcher("/home.jsp").forward(request, response);
		}
}


