package org.knime.postgres.jdbc;  
import java.sql.Connection; 
import java.sql.DriverManager; 
import java.sql.SQLException; 
 
public class ConnUtil 
{ 
    public static Connection getConn() 
    { 
        Connection conn = null; 
        try 
        { 
            Class.forName("org.postgresql.Driver"); 
            String url = "jdbc:postgresql://localhost:5432/pgis_knime_tg"; 
            try 
            { 
                conn = DriverManager.getConnection(url, "postgres", "taving"); 
            } 
            catch (SQLException e) 
            { 
                e.printStackTrace(); 
            } 
        } 
        catch (ClassNotFoundException e) 
        { 
            e.printStackTrace(); 
        } 
 
        return conn; 
    } 
    //TG 0713 S
    
    //TG 0713 E
 
} 