package com.example.repository;

import com.example.entity.Customer;
import com.example.entity.Product;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.InputStream;
import java.util.List;

public class ShopMyBatisDAO { // MyBatis API
      // DB연결 -> config.xml(환경설정파일) -> API read 연결작업을 대신 해주면 된다.
     private static SqlSessionFactory sqlSessionFactory; // Connection(SqlSession) Pool
      static{  // 초기화 블럭
           try{
               String resource = "mybatis-config/config.xml";
               InputStream inputStream = Resources.getResourceAsStream(resource);
               sqlSessionFactory =new SqlSessionFactoryBuilder().build(inputStream);
           }catch(Exception e){
               e.printStackTrace();
           }
      }
      
      public List<Product> productList(){
          SqlSession session = sqlSessionFactory.openSession();
          List<Product> list = session.selectList("productList");
          session.close();
          return list;
      }

      public Customer customer_login(Customer cus) {
          SqlSession session = sqlSessionFactory.openSession();
          Customer cusDto = session.selectOne("customer_login", cus);
          session.close();
          return cusDto; // null or Customer
      }

}
