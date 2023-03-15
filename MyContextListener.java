package hbv;
import jakarta.servlet.*;
import java.util.*;
import java.time.*;
import java.util.concurrent.*;

public class MyContextListener implements ServletContextListener,ServletRequestListener{
	ScheduledThreadPoolExecutor executor;
	static int requests;
	static int connections;
	static int maxconnections;
	static int leasedConnections;

	public void contextInitialized(ServletContextEvent servletContextEvent) {
		ServletContext ctx = servletContextEvent.getServletContext();
		MyConnectionPool.init("jguimfackjeuna","gR7cqZhgai0fATxTMAMO","jdbc:mariadb://mysql-server:3306/jguimfackjeuna_db");
		MyJedisPool.init("localhost",6379,"gR7cqZhgai0fATxTMAMO");

		executor = new ScheduledThreadPoolExecutor(1);
		executor.scheduleAtFixedRate(new MonitorStateLogger(),0,1,TimeUnit.SECONDS);
		MyLogger.info("initialized");
	}
	public void contextDestroyed(ServletContextEvent servletContextEvent) {
		MyLogger.info("contextDestroyed");
		ServletContext ctx = servletContextEvent.getServletContext();
		MyConnectionPool.destroy();
		MyJedisPool.destroy();

		MyLogger.info("cancel timer");
		executor.shutdownNow();
	}

	public void requestInitialized(ServletRequestEvent evt){
		synchronized(MyContextListener.class){
			requests++;
			connections++;
			if(connections>maxconnections)maxconnections=connections;
		}
	}
	public void requestDestroyed(ServletRequestEvent evt){
		synchronized(MyContextListener.class){
			connections--;
		}
	}
	public synchronized static int getConnections(){
		return connections;
	}

	public synchronized static void incrementLeasedConnections(){
		leasedConnections++;
	}
	public synchronized static void decrementLeasedConnections(){
		leasedConnections--;
	}
	public static int getRequests(){
		return requests;
	}
	public static int getMaxConnections(){
		return maxconnections;
	}	
	public static void resetRequests(){
		maxconnections=0;
		requests=0;
	}



}

class MonitorStateLogger implements Runnable {
	int count;
	public void run(){
		MyLogger.info("tick ... "+MyContextListener.getRequests()+" "+(count++)+" con:"+MyContextListener.getConnections()+" maxcon:"+MyContextListener.getMaxConnections()+" maxborrowed:"+MyConnectionPool.maxborrowed);
		MyContextListener.resetRequests();
		MyConnectionPool.maxborrowed=0;
	}
}
