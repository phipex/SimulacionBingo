/*
 * To change this license header, choose License Headers in Project Properties. To change this
 * template file, choose Tools | Templates and open the template in the editor.
 */
package co.com.ies.pruebas.simulacion;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.apache.commons.dbutils.DbUtils;

/**
 *
 * @author desarrollo
 */
public class DbUtilsHelper implements AutoCloseable {

  private Connection connection;

  private PropertyLoader loader;
  
  

  private DbUtilsHelper() {
    try {
      this.setupDB();
    } catch (Exception ex) {
      Logger.getLogger(DbUtilsHelper.class.getName()).log(Level.SEVERE, null, ex);
    }

  }

  public Connection getConnection() {
    try {
      if (connection == null || connection.isClosed()) {
        setupDB();
      }

      return connection;
    } catch (SQLException ex) {
      Logger.getLogger(DbUtilsHelper.class.getName()).log(Level.SEVERE, null, ex);
    } catch (Exception ex) {
      Logger.getLogger(DbUtilsHelper.class.getName()).log(Level.SEVERE, null, ex);
    }
    return connection;
  }

  public void setConnection(Connection connection) {
    this.connection = connection;
  }

  public boolean isClose() {
    try {
      return connection.isClosed();
    } catch (SQLException ex) {
      Logger.getLogger(DbUtilsHelper.class.getName()).log(Level.SEVERE, null, ex);
    }
    return false;
  }

  public static DbUtilsHelper getInstance() {
    return DbUtilsHelperHolder.INSTANCE;
  }

  public void setupDB() throws Exception {
    


    // Step 1: Register JDBC driver
    DbUtils.loadDriver(getDriver());

    // Step 2: Open a connection
    System.out.println("Connecting to database...");
    connection = DriverManager.getConnection(getUrlDatabase(), getUserDatabase(), getPassDatabase());
  }

  private String getPassDatabase() {
    return getPropestyLoades().getPassDatabase();
  }

  private String getUserDatabase() {
    return getPropestyLoades().getUserDatabase();
  }

  private String getUrlDatabase() {
    
    
    return getPropestyLoades().getUrlDatabase();
  }
  
  private String getDriver() {
    return getPropestyLoades().getDriver();
  }

  private PropertyLoader getPropestyLoades() {
    
    if(loader == null) {
      loader = new PropertyLoader();
    }
    
    return loader;
    
  }
  
  @Override
  public void close() throws Exception {
    DbUtils.closeQuietly(connection);
  }

  private static class DbUtilsHelperHolder {

    private static final DbUtilsHelper INSTANCE = new DbUtilsHelper();
  }
}
