package resources;
import java.sql.*;
import java.util.Properties;

import org.apache.groovy.json.internal.JsonParserCharArray;

import com.jcraft.jsch.JSch;
import com.jcraft.jsch.Session;
public class DataBaseConnection {


	public static void  getdbData(String user,String pass,String db,String query){
		Session session=null;
		try{
			
			boolean is_tuneling=true;
			if(is_tuneling){}
			String remotehost="mysql-web-rw.preprod.internal";
			int remoteport=3306;
			int LPort = 3343;
			String mysqlHost= "127.0.0.1";
			JSch jsch = new JSch();
			java.util.Properties config = new java.util.Properties(); 
	    	config.put("StrictHostKeyChecking", "no");
			 session=jsch.getSession("mandeep", "bastion.lenskartserver.net",22);
			session.setPassword("T2ze8NHlKXKbnp");
			session.setConfig(config);
			session.connect();
			System.out.println("Connected");
			int asignedport=session.setPortForwardingL(LPort, remotehost, remoteport);
			
			Properties properties = new Properties();
			properties.setProperty("user", "environment");
			properties.setProperty("password", "EnviRonP%64IojktS#nt");
			properties.setProperty("useSSL", "false");
			properties.setProperty("autoReconnect", "true");
			String databaseName="3Sep";
			String url1= "jdbc:mysql://"+mysqlHost+":"+LPort+"/"+databaseName;
			Class.forName("com.mysql.jdbc.Driver");
////			Connection con = DriverManager.getConnection(url, user, pass);
			Connection con=DriverManager.getConnection(url1,properties);
			System.out.println("Connection done");
			Statement st=con.createStatement();
			ResultSet result=st.executeQuery(query);
//			while(result.next()){
//			System.out.println(result.getString(1)+"  "+result.getInt(2)+"  "+result.getInt(3));
//			}
			session.disconnect();
			
			st.close();
			con.close();
		}catch(Exception e){
			session.disconnect();
			System.out.println("Exception occured"+e);
		}
	}

	public static void main(String[] args) {
//		// TODO Auto-generated method stub
//		String url="jdbc:mysql://localhost:3306/students";
//		String user="root";
//		String pass="MyNewPass";
//		String query="select *From result where marks in(select max(marks)from result)";
//		getdbData(url,user,pass,query);
		
		
//		String url="jdbc:mysql://mysql-scm-rw.preprod.internal:3306/inventory";
		String user="environment";
		String password="EnviRonP%64IojktS#nt";
		String query="SELECT*from salesman_details;";
		String db="3sep";
		getdbData(user,password,db,query);
	}

}
