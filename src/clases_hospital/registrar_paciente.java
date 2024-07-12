package clases_hospital;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;

public class registrar_paciente extends paciente {
    public JPanel mainPanel;
    private JButton buscar_boton;
    private JButton registrar_boton;
    private JButton boton_salir;
    private JTextField campo_cedula;
    private JTextField campo_historial_clinico;
    private JTextField campo_nombre;
    private JTextField campo_apellido;
    private JTextField campo_telefono;
    private JTextField campo_edad;
    private JTextField campo_enfermedad;

    String url = "jdbc:mysql://localhost:3306/sistema_hospitalario";
    String user = "root";
    String password = "123456";
    String sql = "INSERT INTO paciente (cedula, n_historial_clinico, nombre, apellido, telefono, edad, descripcion_enfermedad) VALUES (?, ?, ?, ?, ?, ?, ?)";

    public registrar_paciente() {
        registrar_boton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                paciente paciente = new paciente();

                if (campo_cedula.getText().isEmpty() || campo_historial_clinico.getText().isEmpty() || campo_nombre.getText().isEmpty()
                        || campo_apellido.getText().isEmpty() || campo_telefono.getText().isEmpty() || campo_edad.getText().isEmpty()
                        || campo_enfermedad.getText().isEmpty())
                {
                    JOptionPane.showMessageDialog(null, "Por favor, llene todos los campos.");
                    return;
                }

                if (campo_cedula.getText().length() != 10)
                {
                    JOptionPane.showMessageDialog(null, "Ingrese una cédula válida.");
                    return;
                }

                if (Integer.parseInt(campo_edad.getText()) < 0)
                {
                    JOptionPane.showMessageDialog(null, "Ingrese una edad válida.");
                    return;
                }

                if (Integer.parseInt(campo_historial_clinico.getText()) < 0)
                {
                    JOptionPane.showMessageDialog(null, "Ingrese un número de historial clínico válido.");
                    return;
                }

                if (campo_telefono.getText().length() != 10)
                {
                    JOptionPane.showMessageDialog(null, "Ingrese un número de teléfono válido.");
                    return;
                }

                paciente.setCedula(campo_cedula.getText());
                paciente.setN_historial_clinico(Integer.parseInt(campo_historial_clinico.getText()));
                paciente.setNombre(campo_nombre.getText());
                paciente.setApellido(campo_apellido.getText());
                paciente.setTelefono(campo_telefono.getText());
                paciente.setEdad(Integer.parseInt(campo_edad.getText()));
                paciente.setDescripcion_enfermedad(campo_enfermedad.getText());

                try
                {
                    Connection conexion = DriverManager.getConnection(url, user, password);
                    PreparedStatement sentencia = conexion.prepareStatement(sql);
                    sentencia.setString(1, paciente.getCedula());
                    sentencia.setInt(2, paciente.getN_historial_clinico());
                    sentencia.setString(3, paciente.getNombre());
                    sentencia.setString(4, paciente.getApellido());
                    sentencia.setString(5, paciente.getTelefono());
                    sentencia.setInt(6, paciente.getEdad());
                    sentencia.setString(7, paciente.getDescripcion_enfermedad());
                    sentencia.executeUpdate();

                    JOptionPane.showMessageDialog(null, "Paciente registrado exitosamente.");
                }
                catch (SQLException ex)
                {
                    ex.printStackTrace();
                }
            }
        });
        buscar_boton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFrame frame = new JFrame("Buscar paciente");
                frame.setContentPane(new buscar_paciente().mainPanel);
                frame.pack();
                frame.setLocationRelativeTo(null);
                frame.setVisible(true);

                JFrame registrar_paciente_frame = (JFrame) SwingUtilities.getWindowAncestor(mainPanel);
                registrar_paciente_frame.dispose();
            }
        });
        boton_salir.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFrame frame = new JFrame("Login Sistema Hospitalario");
                frame.setContentPane(new login_form().mainPanel);
                frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                frame.pack();
                frame.setLocationRelativeTo(null);
                frame.setVisible(true);

                JFrame registrar_paciente_frame = (JFrame) SwingUtilities.getWindowAncestor(mainPanel);
                registrar_paciente_frame.dispose();
            }
        });
    }
}
