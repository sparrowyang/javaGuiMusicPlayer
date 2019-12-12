package sqlconnector;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import gui.MusicList;
import net.JsonGeter;


public class SheetAdmin {
	
	public static Connection conn;
	public static Statement statement; 
	/**
	 * 创建数据库
	 * @throws SQLException
	 */
	public static void creatDB() throws SQLException {
		
		conn=SqlConnector.connect();
		statement=(Statement) conn.createStatement();
		
		String sql="create table musicsheets("
				+ "listuuid varchar(20),"
				+ "listName varchar(20),"
				+ "creator  varchar(20),"
				+ "createDate varchar(20),"
				+ "songPictrue varchar(20))"; 

		statement.executeUpdate("drop table if exists musicsheets");
		statement.executeUpdate(sql); 
	}
	
	/**
	 * 插入数据
	 * @throws Exception 
	 */
	private static void insertdata() throws Exception {
		MusicList.a=JsonGeter.jsonToMap();
		
		for (int i = 0; i < MusicList.a.length; i++) {
			MusicList.a[i].getName().replace(' ', '\'');
			
			String insetString="insert into musicsheets values("
				+ "\'"+MusicList.a[i].getlistid()+"\',"
				//+ "\'"+MusicList.a[i].getName()+"\',"
				+ "\'"+MusicList.a[i].getCreatorId()+"\',"
				+ "\'"+MusicList.a[i].getCreator()+"\',"
				+ "\'"+MusicList.a[i].getDateCreated()+"\',"
				+ "\'"+MusicList.a[i].getPicture()+"\')";
			System.out.println(insetString);
			statement.executeUpdate(insetString);
		}
		
		
		
		ResultSet rSet=statement.executeQuery("select*from musicsheets");
		while (rSet.next()) {            
		          	 //依次输出 也可以这样写 rSet.getString(“name”)
			System.out.println(rSet.getString(1));
			System.out.println(rSet.getString(2));
		          	System.out.println(rSet.getString(3));
		          	System.out.println(rSet.getString(4));
		          	System.out.println(rSet.getString(5));
		          	
		           }
		
	}
	
	public static void main(String[] args) throws Exception {
		creatDB();
		insertdata();
	}
	
	/* 
           
           String sql="create table tables(name varchar(20),pwd varchar(20))"; 
           //判断是否有表tables的存在。有则删除
           statement.executeUpdate("drop table if exists tables");
           //创建数据库
           statement.executeUpdate(sql); 
           //向数据库中插入数据
           statement.executeUpdate("insert into tables values('zhou','156546')");
           //搜索数据库，将搜索的放入数据集ResultSet中
           ResultSet rSet=statement.executeQuery("select*from tables");
           //遍历这个数据集
           while (rSet.next()) {            
          	 //依次输出 也可以这样写 rSet.getString(“name”)
          	 System.out.println("姓名："+rSet.getString(1));
          	 System.out.println("密码："+rSet.getString("pwd"));
           }
           rSet.close();//关闭数据集
           connection.close();//关闭数据库连接*/

}
