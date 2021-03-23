package com.cg;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Scanner;

public class LoginAutheticationDatabase {

	public static void main(String[] args) {
		
		Scanner scanner = new Scanner(System.in) ; 		
		String pUsername;
		String pPassword;
				
		System.out.println("Welcome to Login Page");
		
		while(true) {
		System.out.println("--Choose any number--");				
		System.out.println("for registration enter 1");
		System.out.println("for login enter 2");
		System.out.println("for log out enter 3");
		int num = scanner.nextInt();
		switch(num) 
			{
			case 1: 
				System.out.println("--Registration--");				
						try 
						{						
							Class.forName("com.mysql.jdbc.Driver");
							System.out.println("Connecting to database .....");
							String url = "jdbc:mysql://localhost:3306/userdb";
							String username = "root";
							String password = "root";
							Connection con = DriverManager.getConnection(url,username,password);
							System.out.println("Connection has established");														
									Statement stmt = con.createStatement();
									System.out.println("Enter username ");
									pUsername = scanner.next();
									System.out.println("Enter password ");
									pPassword = scanner.next();
									String SQL = "insert into userdetails(user_name,user_password) values('"+pUsername+"','"+pPassword+"')";									
									int rs = stmt.executeUpdate(SQL);
									System.out.println("data saved");
									con.close();																						 														
						}
						catch(Exception e) {
							e.printStackTrace();
						}											
				break;
		
			case 2: 
	
					String n = null, p = null;
				try 
				{
					Class.forName("com.mysql.jdbc.Driver");
					System.out.println("Connecting to the database.....");
					String url = "jdbc:mysql://localhost:3306/userdb";
					String username = "root";
					String password = "root";
					Connection con = DriverManager.getConnection(url,username,password);
					System.out.println("Connection has established");					
					System.out.println("--Login--");
					System.out.println("Enter username");
					pUsername = scanner.next();
					System.out.println("Enter password");
					pPassword = scanner.next(); 
					
					String SQL = 
					"select user_name, user_password from userdetails where user_name=? and user_password=?";
					PreparedStatement ptmt = con.prepareStatement(SQL);
					 ptmt.setString(1,pUsername);
					 ptmt.setString(2,pPassword);	
					 ResultSet rs = ptmt.executeQuery();
					 
					 	if(rs.next())	
					 		System.out.println("Successfully logged in");
					 		else
					 			System.out.println("Wrong credentials");
					 
					con.close();
					}
				catch(Exception e) {
					e.printStackTrace();
				}
				
				break;
				
			case 3:
				System.out.println("--Log out--");
				System.out.println("Successfully logged out");
				break;
				
			default : 
				System.out.println("Invalid input");
				System.exit(0);
		
			}
		}
	}
}
