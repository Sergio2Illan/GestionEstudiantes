package models;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import utils.Conexion;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class DAOEstudianteImpl implements DAOEstudiantes {

    private static final Logger logger = LogManager.getLogger(DAOEstudianteImpl.class);

    private Connection con;

    public DAOEstudianteImpl(Connection con){
        this.con = con;
    }


    @Override
    public void insertEstudiante(Estudiante estudiante) {
        String sql = "Insert into estudiante (nombre, apellido, edad, carrera) values(?,?,?,?)";
        try (PreparedStatement stmt = con.prepareStatement(sql)){

            stmt.setString(1, estudiante.getNombre());
            stmt.setString(2, estudiante.getApellido());
            stmt.setInt(3, estudiante.getEdad());
            stmt.setString(4, estudiante.getCarrera());

            stmt.executeUpdate();
            logger.info("Estudiante insertado");

        } catch (Exception e) {
            logger.error("Error al realizar el Insert del estudiante en la base de datos: " + e.getMessage());
        }

    }

    @Override
    public void updateEstudiante(Estudiante estudiante, int id) {
        String sql = "update estudiante set nombre=?, apellido=?, edad=? , carrera=? where id=?";
        try (PreparedStatement stmt = con.prepareStatement(sql)){

            stmt.setString(1, estudiante.getNombre());
            stmt.setString(2, estudiante.getApellido());
            stmt.setInt(3, estudiante.getEdad());
            stmt.setString(4, estudiante.getCarrera());
            stmt.setInt(5, id);

            stmt.executeUpdate();
            logger.info("Estudiante Actualizado.");

        } catch (Exception e) {
            logger.error("Error al realizar el update del estudiante en la base de datos: " + e.getMessage());
        }
    }

    @Override
    public void deleteEstudiante(int id) {
        String sql = "delete from estudiante where id=?";
        try(PreparedStatement stmt = con.prepareStatement(sql)){

            stmt.setInt(1, id);
            stmt.executeUpdate();
            logger.info("Estudiante eliminado.");

        } catch (Exception e) {
            logger.error("Error al realizar el update del estudiante en la base de datos: " + e.getMessage());
        }
    }

    @Override
    public Estudiante getById(int id) {
        Estudiante estudiante = null;
        String sql = "select * from estudiante where id=?";
        try(PreparedStatement stmt = con.prepareStatement(sql)){
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();
            estudiante = new Estudiante();
            while (rs.next()) {
                estudiante.setNombre(rs.getString("nombre"));
                estudiante.setApellido(rs.getString("apellido"));
                estudiante.setEdad(rs.getInt("edad"));
                estudiante.setCarrera(rs.getString("carrera"));
            }
            logger.info("Estudiante en la base de datos.");
        } catch (Exception e) {
            logger.error("No se ha encontrado el estudiante en la base de datos: " + e.getMessage());
        }
        return estudiante;
    }

    @Override
    public List<Estudiante> selectAll() {
        List<Estudiante> estudiantes = new ArrayList<>();
        String sql = "select * from estudiante";
        try(PreparedStatement stmt = con.prepareStatement(sql)){
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                estudiantes.add(new Estudiante(rs.getString("nombre"),rs.getInt("edad"),rs.getString("apellido"),rs.getString("carrera")));
            }
            logger.info("Hay estudiantes en la base de datos.");
        } catch (Exception e) {
            logger.error("No se ha encontrado el estudiantes en la base de datos: " + e.getMessage());
        }
        return estudiantes;
    }
}
