package co.com.ies.pruebas.simulacion;

import java.net.URL;
import org.apache.commons.configuration.CompositeConfiguration;
import org.apache.commons.configuration.ConfigurationException;
import org.apache.commons.configuration.PropertiesConfiguration;
import org.apache.commons.configuration.SystemConfiguration;


public class PropertyLoader {
  CompositeConfiguration config;
  
  // JDBC driver name and database URL
  static final String JDBC_DRIVER_DEFAULT = "org.postgresql.Driver1";
  static final String DB_URL_DEFAULT = "jdbc:postgresql://localhost:5432/bingo1";

  // Database credentials
  static final String USER_DEFAULT = "postgres1";
  static final String PASS_DEFAULT = "b1ng03nl1n341";

  public PropertyLoader() {
    try {
      String file = "app.propierties";
      URL url = org.apache.commons.configuration.ConfigurationUtils.locate(file);
      config = new CompositeConfiguration();
      //config.addConfiguration(new SystemConfiguration());
     
      config.addConfiguration(
          new PropertiesConfiguration(url));

    } catch (ConfigurationException ex) {
      throw new RuntimeException(ex);
    }
  }
  
  public String getPassDatabase() {
    
    String pass = config.getString("database.pass");
    
    if(pass != null && !"".contentEquals(pass)) {
      return pass;
    }
    return PASS_DEFAULT;
  }

  public String getUserDatabase() {
    String user = config.getString("dababase.user");
    
    if(user != null && !"".contentEquals(user)) {
      return user;
    }
    return USER_DEFAULT;
  }

  public String getUrlDatabase() {
    String baseUrl = config.getString("database.baseurl");
    String database = config.getString("database.database");
    
    if(database != null && !"".contentEquals(database)) {
      return baseUrl+database;
    }
    
    return DB_URL_DEFAULT;
  }
  
  public String getDriver() {
    String driver = config.getString("database.driver");
    
    if(driver != null && !"".contentEquals(driver)) {
      return driver;
    }
    return JDBC_DRIVER_DEFAULT;
  }
}
