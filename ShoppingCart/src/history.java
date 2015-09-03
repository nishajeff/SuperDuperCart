

import java.io.IOException;
import java.math.BigDecimal;
import java.math.MathContext;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.Cart;
import model.DBUtil;
import model.Shopper;

/**
 * Servlet implementation class history
 */
@WebServlet("/history")
public class history extends HttpServlet {
	private static final long serialVersionUID = 1L;
     String message=""; 
    /**
     * @see HttpServlet#HttpServlet()
     */
    public history() {
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
		HttpSession session = request.getSession(true);		
		Shopper s=(Shopper) session.getAttribute("user");
		message="";
		EntityManager em=DBUtil.getEmFactory().createEntityManager();
        String q1="select c.orderId,sum(c.total) as TOT from Cart c where c.shopper.userId="+s.getUserId()+" and c.status='yes' group by c.orderId";	
		
		message+="<div align=\"center\"><table style=\"border:2px solid black\">";
        message+="<th style=\" background-color:gray;border:2px solid black\">Order No:</th><th style=\" background-color:gray;border:2px solid black\">Total Purchase per Order</th>";
			
        
        Query q = em.createQuery(q1);
       List <Object[]>results =q.getResultList();
       MathContext mc = new MathContext(4); 
        for (Object[] object : results) {
        	BigDecimal tot=new BigDecimal(0L);;
        	BigDecimal tax=((BigDecimal) object[1]).multiply(new BigDecimal(0.06),mc);
        	tot=((BigDecimal) object[1]).add(tax,mc);
        	//double tot=(double)object[1]+(double) object[1]*.06;
        	
        	message+="<tr ><td style=\" background-color:white;border:2px solid black\">"+object[0] +  
					
           		   
           		     "</td><td style=\"background-color:white;border:2px solid black\">" +tot+
           		  
           		  "</td></tr>" ; 
        }
        message+="</div>";
        request.setAttribute("message",message);
        getServletContext().getRequestDispatcher("/history.jsp").forward(request, response);
        
		}
	}


