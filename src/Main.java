import javax.swing.*;
import clases_hospital.login_form;

public class Main
{
    public static void main(String[] args)
    {
        JFrame frame = new JFrame("Login Sistema Hospitalario");
        frame.setContentPane(new login_form().mainPanel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);

    }
}