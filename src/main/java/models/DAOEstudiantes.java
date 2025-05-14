package models;

import java.util.List;

public interface DAOEstudiantes {

    void insertEstudiante(Estudiante estudiante);
    void updateEstudiante(Estudiante estudiante, int id);
    void deleteEstudiante(int id);
    Estudiante getById(int id);
    List<Estudiante> selectAll();
}
