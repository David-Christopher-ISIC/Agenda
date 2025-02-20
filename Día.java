import javax.swing.JFrame;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Formatter;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class Día extends JFrame implements ActionListener {
    // Atributos
    public JLabel Titulo, Dia; // Texto estático
    public JLabel[] Horas; // Array de JLabels para las horas
    public JTextField[] Actividades; // Array de JTextFields para las actividades
    public JButton Guardar, Cerrar; // Botones
    public Formatter registro = null;

    public Día() {
        setLayout(null);
        getContentPane().setBackground(Color.BLACK);

        Titulo = new JLabel("AGENDA DE ACTIVIDADES");
        Titulo.setBounds(300, 10, 400, 30); // Ubicación, tamaño (UX, UY, TX, TY)
        Titulo.setForeground(Color.white);
        Titulo.setFont(new Font("Arial", Font.PLAIN, 30));
        add(Titulo);

        Horas = new JLabel[10]; // Array para las horas
        Actividades = new JTextField[10]; // Array para los campos de entrada

        short y = 110;
        for (int i = 0; i < 6; i++) { // Horas de la mañana
            Horas[i] = new JLabel((7 + i) + "am");
            Horas[i].setBounds(20, y, 65, 25);
            Horas[i].setForeground(Color.white);
            Horas[i].setFont(new Font("Arial", Font.PLAIN, 25));
            add(Horas[i]);
            Actividades[i] = new JTextField();
            Actividades[i].setBounds(100, y - 10, 800, 50);
            add(Actividades[i]);
            y += 85;
        }
        for (int i = 6; i < 10; i++) { // Horas de la tarde
            Horas[i] = new JLabel((i - 5) + "pm");
            Horas[i].setBounds(20, y, 65, 25);
            Horas[i].setForeground(Color.white);
            Horas[i].setFont(new Font("Arial", Font.PLAIN, 25));
            add(Horas[i]);
            Actividades[i] = new JTextField();
            Actividades[i].setBounds(100, y - 10, 800, 50);
            add(Actividades[i]);
            y += 85;
        }

        Guardar = new JButton("Guardar");
        Guardar.setBounds(800, 10, 100, 30);
        add(Guardar);
        Guardar.addActionListener(this);

        Cerrar = new JButton("Cerrar");
        Cerrar.setBounds(800, 50, 100, 30);
        add(Cerrar);
        Cerrar.addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent ae) {
        if (ae.getSource() == Guardar) {
            try {
                registro = new Formatter("C:\\Users\\David\\OneDrive\\Documentos\\Agenda\\Actividades\\Dia\\Registro.txt");
                for (int i = 0; i < 10; i++) {
                    registro.format("%s: %s%n", Horas[i].getText(), Actividades[i].getText());
                }
            } catch (Exception e) {
                System.out.print("Error: " + e);
            } finally {
                if (registro != null) {
                    registro.close();
                }
            }
        } else if (ae.getSource() == Cerrar) {
            this.dispose();
        }
    }

    public void abrirAgenda() {
        Día ventana = new Día();
        ventana.setBounds(500, 0, 1000, 1000);
        ventana.setVisible(true);
        ventana.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    public static void main(String[] args) {
        Día agenda = new Día();
        agenda.abrirAgenda();
    }
}