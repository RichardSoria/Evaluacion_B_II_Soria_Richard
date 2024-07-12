package clases_hospital;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;

public class login_form {
    public JPanel mainPanel;
    private JTextField usuario_campo;
    private JPasswordField campo_contrasena;
    private JButton boton_iniciar_sesion;

    String url = "jdbc:mysql://localhost:3306/sistema_hospitalario";
    String user = "root";
    String password = "123456";
    String sql = "SELECT * FROM USUARIO WHERE username = ? AND password = ?";


    public login_form() {
        boton_iniciar_sesion.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                if (usuario_campo.getText().isEmpty() || campo_contrasena.getText().isEmpty())
                {
                    JOptionPane.showMessageDialog(null, "Por favor, llene todos los campos.");
                    return;
                }

                try
                {
                    Connection conexion = DriverManager.getConnection(url, user, password);
                    PreparedStatement sentencia = conexion.prepareStatement(sql);
                    sentencia.setString(1, usuario_campo.getText());
                    sentencia.setString(2, campo_contrasena.getText());
                    ResultSet resultado = sentencia.executeQuery();

                    if(resultado.next())
                    {
                        JOptionPane.showMessageDialog(null, "Bienvenid@ " + usuario_campo.getText());

                        JFrame frame = new JFrame("Registrar paciente");
                        frame.setContentPane(new registrar_paciente().mainPanel);
                        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                        frame.pack();
                        frame.setLocationRelativeTo(null);
                        frame.setVisible(true);

                        JFrame login_frame = (JFrame) SwingUtilities.getWindowAncestor(mainPanel);
                        login_frame.dispose();
                    }
                    else
                    {
                        JOptionPane.showMessageDialog(null, "Usuario o contraseña incorrectos.");
                    }
                }
                catch (SQLException ex)
                {
                    ex.printStackTrace();
                }
            }
        });
    }
}
