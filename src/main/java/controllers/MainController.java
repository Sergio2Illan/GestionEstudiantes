package controllers;

import gui.EstudianteUI;
import models.DAOEstudianteImpl;
import models.DAOEstudiantes;
import models.Estudiante;
import utils.Conexion;

import java.util.List;

public class MainController {


    //TODO: Crear DAOAlumnoImpl
    DAOEstudianteImpl dao;

    public MainController(){}


    public MainController(DAOEstudianteImpl dao){
        this.dao = dao;
    }


    public void insertarEstudiante(Estudiante es){
        dao.insertEstudiante(es);
    }



    public void actualizarEstudiante(Estudiante es, int id) {
        dao.updateEstudiante(es, id);
    }

    public void eliminarEstudiante(int id) {
        dao.deleteEstudiante(id);
    }
    public Estudiante getEstudiante(int id) {
        return dao.getById(id);
    }

    public List<Estudiante> getEstudiantes() {
        return dao.selectAll();
    }

    public void closeConnection() {
        Conexion.closeDataSource();
    }

}
