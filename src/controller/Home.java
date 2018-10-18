package controller;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.Model;

public class Home extends HttpServlet 
{
	public void service(HttpServletRequest request, HttpServletResponse response)
	{
		try
		{
			String accno=null;
			String custid=request.getParameter("custid");
			String pwd=request.getParameter("pwd");
			
			//System.out.println(custid+" "+pwd);
			
			Model m = new Model();
			m.setCustid(custid);
			m.setPwd(pwd);
			
			boolean status = m.login();
			accno = m.getAccno();
			
			if(status==true)
			{
				HttpSession session = request.getSession(true);
				session.setAttribute("ACCNO",accno);
				response.sendRedirect("/BankingApp/home.jsp");
			}
			else
			{
				response.sendRedirect("/BankingApp/loginFailed.jsp");
			}
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}
}
