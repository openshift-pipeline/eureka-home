package org.cloud.demo.eureka.home.dao;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import org.cloud.demo.eureka.home.HomeApplication;
import org.springframework.boot.SpringApplication;

import com.alibaba.druid.pool.DruidDataSource;

import test.Druid;
public class mysqltestdao {
	//Druid德鲁伊,据说是魔兽世界中的一个角色,森林女神
    public static DruidDataSource dataSource;

    public static void main(String[] args) throws SQLException {
    	DruidTest();
	}
    
    //1.初始化Druid连接池
    static {
        //1.硬编码初始化Druid连接池
        try {
            dataSource = new DruidDataSource();
            //四个基本属性
            dataSource.setDriverClassName("com.mysql.jdbc.Driver");
            dataSource.setUrl("jdbc:mysql://mysql:3306/sampledb");
            dataSource.setUsername("userE7G");
            dataSource.setPassword("CETGIHD6RCEDn4CV");
            //其他属性
            //初始大小
            dataSource.setInitialSize(10);
            //最大大小
            dataSource.setMaxActive(50);
            //最小大小
            dataSource.setMinIdle(10);
            //检查时间
            dataSource.setMaxWait(5000);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public static Connection getConnection() {
        try {
            return dataSource.getConnection();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
    
    public static void closeAll(Connection connection, Statement statement, ResultSet resultSet) {
        try {
            if (resultSet != null) {
                resultSet.close();
            }
            if (statement != null) {
                statement.close();
            }
            if (connection != null) {
                connection.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public static void DruidTest() throws SQLException {
        //超过最大限制或报"TimeoutException",每打开一个关闭一个就不会发生异常
        for (int i = 0; i < 1; i++) {
            Connection connection = Druid.getConnection();
            System.out.println(connection.toString() + "\n------------------------------------");
            Statement sts = null;

            String sql = "select * from user ";
            ResultSet resul = null;
            try {
            sts = (Statement) connection.createStatement();
            resul = sts.executeQuery(sql);
            } catch (SQLException e) {
            e.printStackTrace();
            }
            System.out.println("查询的结果如下：");
            while(resul.next()){
            System.out.println("user_name: "+resul.getString("username")+",password: "+resul.getString("password"));
            }
            Druid.closeAll(connection, null, null);
        }
    }
}
