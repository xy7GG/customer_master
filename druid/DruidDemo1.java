package cn.database.druid;

import java.io.InputStream;
import java.net.URL;
import java.sql.Connection;
import java.util.Properties;

import javax.sql.DataSource;

import com.alibaba.druid.pool.DruidDataSourceFactory; 

public class DruidDemo1 {
    public static void main(String[] args) throws Exception {
    	// 获取加载配置文件的对象
        Properties properties = new Properties();
 
        // 获取类的类加载器
        ClassLoader classLoader = DruidDemo1.class.getClassLoader();
        
        // 获取druid-1.0.9.properties配置文件资源输入流
        InputStream resourceAsStream = classLoader.getResourceAsStream("druid.properties");
        
        // 加载配置文件
        properties.load(resourceAsStream);
        
        // 获取连接池对象
        DataSource dataSource = DruidDataSourceFactory.createDataSource(properties);
        
        // 获取数据库连接对象
        Connection connection = dataSource.getConnection();
        
        // 打印获取到的数据库连接对象地址值
        System.out.println(connection);
    }     
    
}
 