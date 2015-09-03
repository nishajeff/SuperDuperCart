

import java.io.IOException;
import java.math.BigDecimal;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.DBUtil;
import model.Storecredit;

/**
 * Servlet implementation class credit
 */
@WebServlet("/credit")
public class credit extends HttpServlet {
	private static final long serialVersionUID = 1L;
    String message="";  
    /**
     * @see HttpServlet#HttpServlet()
     */
    public credit() {
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
		request.getParameter("id");
		BigDecimal ID=new BigDecimal(Long.parseLong(request.getParameter("id")));
		Storecredit sc=new Storecredit();
		sc.setUserId(ID);
		sc.setStatus("unused");
		DBUtil.insertStoreCredit(sc);
		message+="Store Credit successfully added to Shopper!";
		request.setAttribute("message", message);						
		getServletContext().getRequestDispatcher("/storecredit.jsp").forward(request, response);
	}

}
