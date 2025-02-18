import javax.swing.JFrame;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Opciones extends JFrame implements ActionListener,Interface {
    public JLabel Titulo;
    public JButton Semana, Dia;
    
    public Opciones(){
        setLayout(null);
        getContentPane().setBackground(Color.BLACK);
        
        Titulo=new JLabel("Escriba la opción semana o día");
        Titulo.setBounds(15, 10, 450, 25); //ubicacion,tamaño (UX,UY,TX,TY)
        Titulo.setForeground(Color.white);
        Titulo.setFont(new Font("Arial",Font.PLAIN,25));
        add(Titulo);
        
        Semana=new JButton("Semana");
        Semana.setBounds(120, 50, 150, 50);
        Semana.setFont(new Font("Arial",Font.PLAIN,20));
        add(Semana);
        Semana.addActionListener(this);
        
        Dia=new JButton("Día");
        Dia.setBounds(120, 120, 150, 50);
        Dia.setFont(new Font("Arial",Font.PLAIN,20));
        add(Dia);
        Dia.addActionListener(this);
    }
    @Override
    public void actionPerformed(ActionEvent ae) {
        if(ae.getSource() == Semana){
            this.dispose();
            Semana objeto1=new Semana();
            objeto1.abrirAgenda();
        }else{
            if(ae.getSource()==Dia){
                this.dispose();
                Día objeto2=new Día();
                objeto2.abrirAgenda();
            }
        }
    }
    
    public void abrirAgenda(){
        Opciones ventana=new Opciones();
        ventana.setBounds(750,400,400,250);
        ventana.setVisible(true);
        ventana.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
}