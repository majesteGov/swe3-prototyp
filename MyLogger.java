package hbv;
import java.util.logging.*;

public class MyLogger {
  public synchronized static void info(String msg){
    Logger.getLogger(Logger.GLOBAL_LOGGER_NAME).log(Level.INFO,msg);
  }
}
