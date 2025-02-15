import javax.swing.JFrame;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Formatter;

public class Autenticacion extends JFrame implements ActionListener {
    //declaración de atributos
    private JTextField usser;
    private JButton Aceptar, Cancelar;
    private JLabel Titulo, Usuario, Contraseña;
    private JPasswordField password;
    private byte x=0;
    
    //constructor que crea la interfaz
    public Autenticacion(){
        setLayout(null);
        getContentPane().setBackground(Color.BLACK); //color de fondo
        
        //GUI= Generate User Interface
        Titulo=new JLabel("INICIAR SESIÓN");
        Titulo.setBounds(150, 10, 100, 20); //ubicacion,tamaño (UX,UY,TX,TY)
        Titulo.setForeground(Color.white); 
        add(Titulo);
        
        Usuario=new JLabel("Usuario");
        Usuario.setBounds(10, 50, 100, 20);
        Usuario.setForeground(Color.white);
        add(Usuario);
        
        Contraseña=new JLabel("Contraseña");
        Contraseña.setBounds(10, 100, 100, 20);
        Contraseña.setForeground(Color.white);
        add(Contraseña);
        
        usser=new JTextField();
        usser.setBounds(120, 50, 150, 20);
        add(usser);
        
        password=new JPasswordField();
        password.setBounds(120, 100, 150, 20);
        add(password);
        
        Aceptar=new JButton("Aceptar");
        Aceptar.setBounds(40, 150, 100, 30);
        add(Aceptar);
        Aceptar.addActionListener(this);
        
        Cancelar=new JButton("Cancelar");
        Cancelar.setBounds(240, 150, 100, 30);
        add(Cancelar);
        Cancelar.addActionListener(this);
    }
    
    //metodo principal
    public static void main(String []david){
        Autenticacion ventana=new Autenticacion();
        ventana.setBounds(750, 400, 400, 250);
        ventana.setVisible(true);
        ventana.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    //codigo de los botones
    @Override
    public void actionPerformed(ActionEvent ae) {
        
        FileReader arch1, arch2;//selecciona el archivo
        BufferedReader texto1, texto2;//lee el contenido del archivo seleccionado
        
        try{
            arch1=new FileReader("C:\\Users\\David\\OneDrive\\Documentos\\Agenda\\Usuario.txt");//se busca el archivo
            arch2=new FileReader("C:\\Users\\David\\OneDrive\\Documentos\\Agenda\\Contraseña.txt");
            texto1=new BufferedReader(arch1);//se crean las variables que llen el archivo
            texto2=new BufferedReader(arch2);
            String cadena1, cadena2;//variables que tendran valor de lo leido
            cadena1 = texto1.readLine();//se les asigna lo leido a las variables
            cadena2 = texto2.readLine();
            if (ae.getSource() == Aceptar) {
                String usuario = usser.getText();
                String contraseña = new String(password.getPassword());
                if (usuario.equals("")){
                    throw new NullPointerException();
                }
                if (contraseña.equals("")){
                    throw new IllegalArgumentException();
                }
                if (usuario.equals(cadena1) && contraseña.equals(cadena2)) {
                    this.dispose();
                    Opciones objeto=new Opciones();
                    objeto.abrirAgenda();
                }else{
                    x++;
                    if (x<3){
                        throw new SecurityException();
                    }else{
                        this.dispose();
                        throw new ArithmeticException();
                    }
                }
            }
        }
        catch(IOException ioe){
            JOptionPane.showMessageDialog(null,"No hay archivos guardados");
            System.out.println("Error: "+ioe);
        }
        
        catch(NullPointerException npe){ //los campos de usuario y/o contraseña están vacios
            JOptionPane.showMessageDialog(null,"*Ingrese su nombre de usuario*");
            System.out.println("Error: "+npe);
        }
        catch(IllegalArgumentException iae){
            JOptionPane.showMessageDialog(null,"*Ingrese su contraseña*");
            System.out.println("Error: "+iae);
        }
        catch(SecurityException nfe){ //la contraseña y/o el usuario son incorrectos
            usser.setText("");
            password.setText("");
            JOptionPane.showMessageDialog(null,"*Compruebe su nombre de"
                + " usuario y contraseña*");
            System.out.println("Error: "+nfe);
        }
        catch(ArithmeticException se){ //el usuario y/o contraseña no son correctos
            JOptionPane.showMessageDialog(null,"Ingresó sus datos 3 veces de "
                    + "manera incorrecta\nINTÉNTELO DE NUEVO");
            System.out.println("Error: "+se);
        }
        if (ae.getSource()==Cancelar) {
            this.dispose();
        }
    }
}