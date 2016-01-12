
package com.ndovado.webapp.shared;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import com.ndovado.tecservices.persistenza.base.ServizioPersistenzaBase;

public class WebappListener implements ServletContextListener {  
    public void contextInitialized(ServletContextEvent sce)
  {}

  public void contextDestroyed(ServletContextEvent sce)
  {
//    try {
//      Enumeration<Driver> enumer = DriverManager.getDrivers();
//      while (enumer.hasMoreElements()) {
//        DriverManager.deregisterDriver(enumer.nextElement());
//      }
//    } catch (java.sql.SQLException se) {
//      se.printStackTrace();
//    }   
    ServizioPersistenzaBase.shutdown();
  }
} 
