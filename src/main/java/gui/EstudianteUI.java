package gui;
import controllers.MainController;
import models.Estudiante;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.List;

import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class EstudianteUI extends JFrame {

    private static final Logger logger = LogManager.getLogger(EstudianteUI.class);

    private MainController controller;

    // Campos de entrada
    private JTextField nombreField;
    private JTextField apellidoField;
    private JTextField edadField;
    private JTextField carreraField;
    private JTextField idField;

    // Área para mostrar los resultados
    private JTextArea resultadosArea;

    // Constructor de la ventana
    public EstudianteUI(MainController controller) {
        this.controller = controller;
        setTitle("Gestión de Estudiantes");
        setSize(500, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        // Panel principal
        JPanel panel = new JPanel();
        panel.setLayout(new BorderLayout());

        // Panel de formularios (inputs)
        JPanel formPanel = new JPanel(new GridLayout(5, 2, 5, 5));

        formPanel.add(new JLabel("ID:"));
        idField = new JTextField();
        formPanel.add(idField);

        formPanel.add(new JLabel("Nombre:"));
        nombreField = new JTextField();
        formPanel.add(nombreField);

        formPanel.add(new JLabel("Apellido:"));
        apellidoField = new JTextField();
        formPanel.add(apellidoField);

        formPanel.add(new JLabel("Edad:"));
        edadField = new JTextField();
        formPanel.add(edadField);

        formPanel.add(new JLabel("Carrera:"));
        carreraField = new JTextField();
        formPanel.add(carreraField);

        panel.add(formPanel, BorderLayout.NORTH);

        // Panel de botones
        JPanel buttonPanel = new JPanel(new FlowLayout());

        JButton insertarBtn = new JButton("Insertar");
        JButton actualizarBtn = new JButton("Actualizar");
        JButton eliminarBtn = new JButton("Eliminar");
        JButton findByIdBtn = new JButton("Encontrar por id");
        JButton listarBtn = new JButton("Listar");

        buttonPanel.add(insertarBtn);
        buttonPanel.add(actualizarBtn);
        buttonPanel.add(eliminarBtn);
        buttonPanel.add(findByIdBtn);
        buttonPanel.add(listarBtn);

        panel.add(buttonPanel, BorderLayout.CENTER);

        // Área de resultados
        resultadosArea = new JTextArea(10, 40);
        resultadosArea.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(resultadosArea);
        panel.add(scrollPane, BorderLayout.SOUTH);

        // Añadir panel a la ventana
        add(panel);

        // Listeners para los botones (puedes conectar con tu DAO aquí)
        insertarBtn.addActionListener(e -> {
            resultadosArea.setText("");

            Estudiante es =  getDataFromFields();

            logger.info("Se va ha insertar en bbdd el siguiente estudiante: "+es.toString());

            this.controller.insertarEstudiante(es);

            // Aquí va tu lógica de inserción a BBDD
            resultadosArea.setText("Insertado: " + es.getNombre() + " " + es.getApellido() + ", Edad: " + es.getEdad() + ", Carrera: " + es.getCarrera());

        });

        actualizarBtn.addActionListener(e -> {
            // Lógica de actualización
            resultadosArea.setText("");
            int id = Integer.parseInt(idField.getText().trim().isEmpty() ? "0" : idField.getText().trim());
            Estudiante es =  getDataFromFields();
            this.controller.actualizarEstudiante(es, id);
            resultadosArea.setText("Estudiante Actualizado.");
        });

        eliminarBtn.addActionListener(e -> {
            // Lógica de eliminación
            resultadosArea.setText("");
            int id = Integer.parseInt(idField.getText().trim().isEmpty() ? "0" : idField.getText().trim());
            controller.eliminarEstudiante(id);
            resultadosArea.setText("Se ha eliminado al estudiante seleccionado.");
        });

        findByIdBtn.addActionListener(e -> {
            resultadosArea.setText("");
            int id = Integer.parseInt(idField.getText().trim().isEmpty() ? "0" : idField.getText().trim());
            Estudiante es = controller.getEstudiante(id);
            resultadosArea.setText("Encontrado: " + es.getNombre() + " " + es.getApellido() + ", Edad: " + es.getEdad() + ", Carrera: " + es.getCarrera());
        });

        listarBtn.addActionListener(e -> {
            // Lógica para obtener todos los registros de la BBDD
            List<Estudiante> estudiantes = controller.getEstudiantes();
            resultadosArea.setText("");

            for(Estudiante estudiante : estudiantes){
                resultadosArea.append(estudiante.toString());
                resultadosArea.append("\n");
            }
        });

        // Agregar un WindowListener para capturar el evento de cierre
        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {

                controller.closeConnection();
                logger.info("La ventana se está cerrando");

                System.exit(0); // Cierra el programa
            }
        });
    }

    private Estudiante getDataFromFields() {
        String nombre = nombreField.getText().trim().isEmpty() ? "Anonymous" : nombreField.getText().trim();
        String apellido = apellidoField.getText().trim().isEmpty() ? "Anonymous" : apellidoField.getText().trim();
        String textoEdad = edadField.getText().trim();
        int edad = textoEdad.isEmpty() ? 0 : Integer.parseInt(textoEdad);
        String carrera = carreraField.getText().trim().isEmpty() ? "Anonymous" : carreraField.getText().trim();

        return new Estudiante(nombre,edad,apellido,carrera);
    }


}

