import java.net.*;
import java.io.*;
import java.sql.*;

public class Module2{


		public static void dBMgmt(double d){
          
			try{
						// Load driver.
			
						Class.forName("com.mysql.cj.jdbc.Driver");

						// Creating connection.


						String url="jdbc:mysql://localhost:3306/harshitdb1";
						String username="root";
						String password="11Harshit@2002";
			
  						Connection con=DriverManager.getConnection(url,username,password);
						
						if(con.isClosed()){
										System.out.print("\n\n\t\tConnection is established");
								}
						else{
										System.out.println("\n\n\tConnection got establish.");
							}

							 String str1 = "create table if not exists stock1(sId int(20) primary key auto_increment,stockPrice double not null)";
						

							// Creating statement .
		
							Statement stmt = con.createStatement();
							stmt.executeUpdate(str1);
							System.out.print("\n\n\t\tTable have been created.");
	
							/* Inserting values into the tables .
									by creating a query. */
			
							String str2="insert into stock1(stockPrice) values (?)";
			
							PreparedStatement pstmt = con.prepareStatement(str2);
	
							// Setting value to query.

						    pstmt.setDouble(1,d);
							 pstmt.executeUpdate();
	
							System.out.print("\n\n\t\t\tValues is inserted into the table. ");

							con.close();
				  }catch(Exception e)
			      {
						e.printStackTrace();
					}
          
			}



		public static void main(String... args){
			int count=0;
			double avg;
			int n=1,max=0,min=1000000,l;
			String str;
			URL webpage=null;
			URLConnection conn=null;
			try{
					webpage=new URL("https://www.google.com/finance/quote/NIFTY_50:INDEXNSE?hl=en");
			while(n<300){
					n++;
					conn=webpage.openConnection();
					InputStreamReader reader=new InputStreamReader(conn.getInputStream(),"UTF8");
					BufferedReader buffer=new BufferedReader(reader);
					String line="";
						line=buffer.readLine();
						while(line != null)
						{
								if(line.contains("data-last-price="))
									{
											str=line.substring(line.indexOf("data-last-price=")+17,line.indexOf("data-last-price=")+22);
											l=Integer.parseInt(str);
											dBMgmt((double)l);
											System.out.println(l);
											if(l>max){
													max=l;
											}
											if(l<min){
												min=l;
											}
											avg=(double)((max+min)/2);
											if(l<avg){
													System.out.println("\n\n\tYou must buy the stock for the moment to generate profit");
													}
											else{
												 System.out.println("\n\n\tYou must sell the stock for the moment to generate profit");
												}
								    }
								line=buffer.readLine();
						}
					  }
					}
				catch(Exception e){
					e.printStackTrace();
				}
	}
}