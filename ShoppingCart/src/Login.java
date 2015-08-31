

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
import model.Shopper;


/**
 * Servlet implementation class Login
 */
@WebServlet("/Login")
public class Login extends HttpServlet {
	private static final long serialVersionUID = 1L;
    String message="";   
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Login() {
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
		HttpSession session = request.getSession();
		
		
		String id=request.getParameter("userID");
		// get parameters from the request
		session.setAttribute("userid", id);
		int idi=Integer.parseInt(id);		
		EntityManager em=DBUtil.getEmFactory().createEntityManager();
		String q="select s from Shopper s where s.userId="+idi;
		Shopper s=new Shopper();
		TypedQuery<Shopper>bq =em.createQuery(q,Shopper.class);
		List<Shopper> list=bq.getResultList();
		if(list==null || list.isEmpty())
			message+="Login Unsuccessful.Create an account and try again!";
		else{
		message+="Login Successful!<br>";
		for(Shopper temp:list){
			message+="Welcome back "+temp.getName()+"!Start Tweeting...<br>";
			s=temp;
		}
		session.setAttribute("user", s);
		}
		request.setAttribute("message", message);
		if(list==null || list.isEmpty())
			getServletContext().getRequestDispatcher("/output.jsp").forward(request, response);
		else
		getServletContext().getRequestDispatcher("/Shopping.jsp").forward(request, response);
	}
}


