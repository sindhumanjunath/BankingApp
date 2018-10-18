package controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.Model;

public class Balance extends HttpServlet
{
	public void service(HttpServletRequest request, HttpServletResponse response)
	{
		try
		{
			String balance = null;
			HttpSession session = request.getSession();
			String accno= (String) session.getAttribute("ACCNO");
			
			Model m = new Model();
			m.setAccno(accno);
			
			boolean status = m.checkbalance();
			
			if(status==true)
			{
				balance = m.getBalance();
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