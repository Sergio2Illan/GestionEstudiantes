import controllers.MainController;
import gui.EstudianteUI;
import models.DAOEstudianteImpl;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import utils.Conexion;

import javax.swing.*;

public class Main {

    private static final Logger logger = LogManager.getLogger(Main.class);

    // Método principal para ejecutar la interfaz
    public static void main(String[] args) {

        try{
            logger.info("Iniciando Aplicacion.");

            DAOEstudianteImpl dao = new DAOEstudianteImpl(Conexion.getConnection());

            MainController controller = new MainController(dao);

            EstudianteUI ventana = new EstudianteUI(controller);
            ventana.setVisible(true);
        } catch (Exception e) {
            logger.error(e.getMessage());
        }

    }
}
