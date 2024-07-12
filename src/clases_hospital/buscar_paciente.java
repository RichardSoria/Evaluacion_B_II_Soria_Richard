package clases_hospital;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;

public class buscar_paciente {
    public JPanel mainPanel;
    private JButton buscar_registro;
    private JTextField campo_cedula_buscar;
    private JButton boton_regresar_registrar;
    private JButton cerrar_sesion;

    String url = "jdbc:mysql://localhost:3306/sistema_hospitalario";
    String user = "root";
    String password = "123456";
    String sql = "SELECT * FROM paciente WHERE cedula = ?";

    public buscar_paciente() {
        buscar_registro.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                if(campo_cedula_buscar.getText().isEmpty())
                {
                    JOptionPane.showMessageDialog(null, "Por favor, llene todos los campos.");
                    return;
                } else if (campo_cedula_buscar.getText().length() != 10)
                {
                    JOptionPane.showMessageDialog(null, "Ingrese una cédula válida.");
                    return;
                }

                try
                {
                    Connection conexion = DriverManager.getConnection(url, user, password);
                    PreparedStatement sentencia = conexion.prepareStatement(sql);
                    sentencia.setString(1, campo_cedula_buscar.getText());
                    ResultSet resultado = sentencia.executeQuery();
                    campo_cedula_buscar.setText("");

                    if (resultado.next())
                    {
                        String cedula = resultado.getString("cedula");
                        String n_historial_clinico = resultado.getString("n_historial_clinico");
                        String nombre = resultado.getString("nombre");
                        String apellido = resultado.getString("apellido");
                        String telefono = resultado.getString("telefono");
                        String edad = resultado.getString("edad");
                        String descripcion_enfermedad = resultado.getString("descripcion_enfermedad");

                        JOptionPane.showMessageDialog(null, "Paciente encontrado" +
                                "\n" + "Cédula: " + cedula + "\n" + "Número de historial clínico: " + n_historial_clinico + "\n" + "Nombre: " + nombre + "\n" + "Apellido: " + apellido + "\n" + "Teléfono: " + telefono + "\n" + "Edad: " + edad + "\n" + "Descripción de la enfermedad: " + descripcion_enfermedad);
                    }
                    else
                    {
                        JOptionPane.showMessageDialog(null, "No se encontraron registros.");
                    }
                }
                catch (SQLException ex)
                {
                    JOptionPane.showMessageDialog(null, "Error al buscar paciente.");
                }





            }
        });
        boton_regresar_registrar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFrame frame = new JFrame("Registrar paciente");
                frame.setContentPane(new registrar_paciente().mainPanel);
                frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                frame.pack();
                frame.setLocationRelativeTo(null);
                frame.setVisible(true);

                JFrame buscar_paciente_frame = (JFrame) SwingUtilities.getWindowAncestor(mainPanel);
                buscar_paciente_frame.dispose();
            }
        });
        cerrar_sesion.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFrame frame = new JFrame("Inicio de sesión");
                frame.setContentPane(new login_form().mainPanel);
                frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                frame.pack();
                frame.setLocationRelativeTo(null);
                frame.setVisible(true);

                JFrame buscar_paciente_frame = (JFrame) SwingUtilities.getWindowAncestor(mainPanel);
                buscar_paciente_frame.dispose();
            }
        });
    }
}
