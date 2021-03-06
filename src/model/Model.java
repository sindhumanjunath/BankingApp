package model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import oracle.jdbc.driver.OracleDriver;

public class Model 
{
	String url="jdbc:oracle:thin:@//localhost:1521/XE";
	String un="system";
	String pw="system";

	Connection con=null;
	ResultSet res=null;
	PreparedStatement pstmt=null;

	private String custid;
	private String pwd;
	private String accno;
	private String balance;
	private String email;
	private String name;
	
	private String taccno;
	private String tamt;

	public Model() throws Exception
	{
		DriverManager.registerDriver(new OracleDriver());
		con = DriverManager.getConnection(url, un, pw);
	}


	public boolean login()
	{
		try
		{
			pstmt = con.prepareStatement("SELECT * FROM BANK WHERE CUSTID=? AND PWD=?");
			pstmt.setString(1,custid);
			pstmt.setString(2,pwd);
			res = pstmt.executeQuery();
			if(res.next()==true)
			{
				accno = res.getString("accno");
				return true;
			}
			return false;
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		return false;
	}

	public boolean checkbalance()
	{
		try
		{
			String s="SELECT * FROM BANK WHERE ACCNO=?";
			System.out.println(accno);
			
			pstmt = con.prepareStatement(s);
			pstmt.setString(1, accno);
			res = pstmt.executeQuery();
			if(res.next()==true)
			{
			  balance = res.getString("balance");
			  System.out.println(balance);
			  return true;
			}
			return false;
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		return false;
	}
	
	public boolean transfer()
	{
		try
		{
			String s="SELECT * FROM BANK WHERE ACCNO=?";
			pstmt = con.prepareStatement(s);
			pstmt.setString(1, accno);
			res = pstmt.executeQuery();
			if(res.next()==true)
			{
			  balance = res.getString("balance");
			  int bal = Integer.parseInt(balance);
			  int tam = Integer.parseInt(tamt);
			  if(bal>=tam)
			  {

					String s1="INSERT INTO BANK_STATEMENT  VALUES (?,?,?)";
					pstmt = con.prepareStatement(s1);
					pstmt.setString(1, accno);
					pstmt.setString(2, taccno);
					pstmt.setString(3, tamt);
					pstmt.executeUpdate();
					System.out.println("Bank statement succesfully inserted");
					return true;
			  }
			  
			  else
			  {
				  return false;
			  }
			}
			return false;
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		return false;
	}
	
	
	public String getCustid() {
		return custid;
	}

	public void setCustid(String custid) {
		this.custid = custid;
	}

	public String getPwd() {
		return pwd;
	}

	public void setPwd(String pwd) {
		this.pwd = pwd;
	}

	public String getAccno() {
		return accno;
	}

	public void setAccno(String accno) {
		this.accno = accno;
	}

	public String getBalance() {
		return balance;
	}

	public void setBalance(String balance) {
		this.balance = balance;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getTaccno() {
		return taccno;
	}

	public void setTaccno(String taccno) {
		this.taccno = taccno;
	}

	public String getTamt() {
		return tamt;
	}

	public void setTamt(String tamt) {
		this.tamt = tamt;
	}

}
