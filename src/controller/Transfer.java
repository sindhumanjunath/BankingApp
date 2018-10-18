package controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.Model;

public class Transfer extends HttpServlet 
{
	public void service(HttpServletRequest request, HttpServletResponse response)
	{
		try
		{
			HttpSession session = request.getSession();
			String accno= (String) session.getAttribute("ACCNO");
			
			String taccno = request.getParameter("taccno");
			String tamt = request.getParameter("tamt");
			
			
			Model m = new Model();
			m.setAccno(accno);
			
			boolean status = m.transfer();
			
			if(status==true)
			{
				b = m.getBalance();
				session.setAttribute("BALANCE",balance);
				response.sendRedirect("/BankingApp/balance.jsp");
			}
			else
			{
				response.sendRedirect("/BankingApp/balanceFailed.jsp");
			}
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}

}
