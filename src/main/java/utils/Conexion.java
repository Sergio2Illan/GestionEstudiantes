package utils;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Conexion {
    private static final Logger logger = LogManager.getLogger(Conexion.class);

    private static HikariDataSource dataSource;

    private Conexion(){}

    public synchronized static Connection getConnection() throws SQLException {
        logger.info("Iniciando conexión a BBDD...");
        try{
            if(dataSource == null || dataSource.isClosed()) {
                logger.info("Leyendo del datasource....");
                // Examines both filesystem and classpath for .properties file
                HikariConfig config = new HikariConfig("src/main/resources/datasource.properties");
                logger.info("Datasource cargado con exito.");
                dataSource = new HikariDataSource(config);
                logger.info("¡Conexión establecida con éxito!");
            }
        }catch (Exception e){
            logger.error("Error ---> : "+e.getLocalizedMessage());
        }

        return dataSource.getConnection();
    }

    public static void closeDataSource() {
        if (dataSource != null && !dataSource.isClosed()) {
            dataSource.close();
            logger.info("HikariDataSource cerrado correctamente.");
        }
    }

}
