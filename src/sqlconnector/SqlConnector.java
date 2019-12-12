package sqlconnector;

import java.sql.Connection;
import java.sql.DriverManager;

public class SqlConnector {

public static Connection connection;
private static String Drivde="org.sqlite.JDBC";

public static Connection connect() {
	
    // TODO Auto-generated method stub
	try {
	 // 加载驱动,连接sqlite的jdbc
            Class.forName(Drivde);
            //连接数据库, 不存在则创建
            connection=DriverManager.getConnection("jdbc:sqlite:db/musicsheet.db");
            //创建连接对象，是Java的一个操作数据库的重要接口
 
	} catch (Exception e) {
            // TODO Auto-generated catch block
	        e.printStackTrace();
	}
	return connection;
       }

}