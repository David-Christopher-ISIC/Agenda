import javax.swing.JFrame;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Formatter;

public class Semana extends JFrame implements ActionListener {
    // Attributes
    private JLabel Titulo;
    private JLabel[] DiaLabels, HoraLabels;
    private JTextField[] ActivityFields;
    private JButton Guardar, Cerrar;
    private Formatter registro;

    public Semana() {
        setLayout(null);
        getContentPane().setBackground(Color.BLACK);

        Titulo = new JLabel("AGENDA DE ACTIVIDADES");
        Titulo.setBounds(750, 10, 400, 30); // Location, size (UX, UY, TX, TY)
        Titulo.setForeground(Color.white);
        Titulo.setFont(new Font("Arial", Font.PLAIN, 30));
        add(Titulo);

        String[] dias = {"Domingo", "Lunes", "Martes", "Miércoles", "Jueves", "Viernes", "Sábado"};
        DiaLabels = new JLabel[7];
        for (int i = 0; i < 7; i++) {
            DiaLabels[i] = new JLabel(dias[i]);
            DiaLabels[i].setBounds(100 + i * 250, 50, 200, 25);
            DiaLabels[i].setForeground(Color.white);
            DiaLabels[i].setFont(new Font("Arial", Font.PLAIN, 25));
            add(DiaLabels[i]);
        }

        HoraLabels = new JLabel[10];
        short y = 110;
        for (short x = 7; x < 13; x++) {
            HoraLabels[x - 7] = new JLabel(x + "am");
            HoraLabels[x - 7].setBounds(20, y, 65, 25);
            HoraLabels[x - 7].setForeground(Color.white);
            HoraLabels[x - 7].setFont(new Font("Arial", Font.PLAIN, 25));
            add(HoraLabels[x - 7]);
            y += 85;
        }
        for (short x = 1; x < 5; x++) {
            HoraLabels[x + 5] = new JLabel(x + "pm");
            HoraLabels[x + 5].setBounds(20, y, 65, 25);
            HoraLabels[x + 5].setForeground(Color.white);
            HoraLabels[x + 5].setFont(new Font("Arial", Font.PLAIN, 25));
            add(HoraLabels[x + 5]);
            y += 85;
        }

        ActivityFields = new JTextField[70];
        int fieldIndex = 0;
        for (int col = 0; col < 7; col++) {
            for (int row = 0; row < 10; row++) {
                ActivityFields[fieldIndex] = new JTextField();
                ActivityFields[fieldIndex].setBounds(100 + col * 250, 100 + row * 85, 200, 50);
                add(ActivityFields[fieldIndex]);
                fieldIndex++;
            }
        }

        Guardar = new JButton("Guardar");
        Guardar.setBounds(1800, 10, 100, 30);
        add(Guardar);
        Guardar.addActionListener(this);

        Cerrar = new JButton("Cerrar");
        Cerrar.setBounds(1800, 50, 100, 30);
        add(Cerrar);
        Cerrar.addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent ae) {
        if (ae.getSource() == Guardar) {
            try {
                registro = new Formatter("C:\\Users\\David\\OneDrive\\Documentos\\Agenda\\Actividades\\Semana\\Registro.txt");
                for (int i = 0; i < ActivityFields.length; i++) {
                    int dayIndex = i / 10;
                    int hourIndex = i % 10;
                    String day = DiaLabels[dayIndex].getText();
                    String hour = HoraLabels[hourIndex].getText();
                    String activity = ActivityFields[i].getText();
                    registro.format("%s %s: %s%n", day, hour, activity);
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
        Semana ventana = new Semana();
        ventana.setBounds(0, 0, 1925, 1000);
        ventana.setVisible(true);
        ventana.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    public static void main(String[] args) {
        Semana agenda = new Semana();
        agenda.abrirAgenda();
    }
}