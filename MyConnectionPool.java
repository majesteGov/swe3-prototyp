package hbv;
import java.io.*;
import java.sql.*;
import javax.sql.*;
import java.util.*;

import java.lang.reflect.*;

public class MyConnectionPool {
  static int max=20;
  static int maxborrowed=0;
  static List<Connection> available = new ArrayList<>();
  static List<Connection> notavailable = new ArrayList<>();
  static String user;
  static String password;
  static String url;

  static synchronized void reset(){
    maxborrowed=0;
  }

  private static synchronized MyConnectionWrapper createConnection() throws SQLException {
    Connection org =DriverManager.getConnection(url,user,password) ;
    MyConnectionWrapper wrapper = new MyConnectionWrapper(org);
    return wrapper;
  }

  public static synchronized void init(String userp,String passwordp,String urlp){
    user=userp;
    password=passwordp;
    url=urlp;
    for(int i=0;i<max;++i){
      try{
        available.add(createConnection());
      }catch(SQLException e){
        MyLogger.info("connection init failed");
      }
    }
  }

  public static Connection borrowConnection(){
    Connection result = null;
    synchronized(MyConnectionPool.class){
      result = available.remove(available.size()-1);
    }
    try {
      if ( result == null || !result.isValid(1)){
        result = createConnection();
        MyLogger.info("creating new Connection");
      }
    } catch (Exception e){
      MyLogger.info("connection init failed");
      return null;
    }

    synchronized(MyConnectionPool.class){
      notavailable.add(result);
      if(maxborrowed < notavailable.size())
        maxborrowed = notavailable.size();
    }
    try{
      if(!result.isValid(1)){
        synchronized(MyConnectionPool.class){
          notavailable.remove(result);
          result=createConnection();
          notavailable.add(result);
        }
      }
    }catch(SQLException e){
      MyLogger.info("exception in db creation");
    }

    return result;
  }
  public static synchronized void releaseConnection(Connection con){
    notavailable.remove(con);
    available.add(con);
  }

  public static synchronized void destroy(){
    for(Connection con:available){
      try{
        con.close();
      }catch(SQLException se){
        MyLogger.info("close exception");
      }
    }
    for(Connection con:notavailable){
      try{
        con.close();
      }catch(SQLException se){
        MyLogger.info("close exception");
      }
    }
  }
}

