package models;

public class Estudiante {
    //private int id;
    private String nombre;
    private int edad;
    private String apellido;
    private String carrera;


    public Estudiante(){

    }

    public Estudiante(String nombre, int edad, String apellido, String carrera) {
        //this.id = id;
        this.nombre = nombre;
        this.edad = edad;
        this.apellido = apellido;
        this.carrera = carrera;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getEdad() {
        return edad;
    }

    public void setEdad(int edad) {
        this.edad = edad;
    }



    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public String getCarrera() {
        return carrera;
    }

    public void setCarrera(String carrera) {
        this.carrera = carrera;
    }

    @Override
    public String toString() {
        return "Estudiante{" +
                ", nombre='" + nombre + '\'' +
                ", edad=" + edad +
                ", apellido='" + apellido + '\'' +
                ", carrera='" + carrera + '\'' +
                '}';
    }
}
