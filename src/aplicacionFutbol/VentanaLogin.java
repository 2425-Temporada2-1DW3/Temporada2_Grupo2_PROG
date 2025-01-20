package aplicacionFutbol;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JPasswordField;
import java.awt.event.ActionListener;
import java.util.List;
import java.io.EOFException;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.awt.event.ActionEvent;
import net.miginfocom.swing.MigLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.Dimension;

import javax.swing.DefaultListModel;
import javax.swing.ImageIcon;
import java.awt.Toolkit;

public class VentanaLogin extends JFrame implements ActionListener{

	private JFrame VentanaLogin;
	private JTextField User;
	private JLabel TextoPassword;
	private JPasswordField Password;
	private JButton btnLogin;
	private JLabel icono;
	private JButton btnSingUp;
	public static String RolSesion = "Usuario";
	private boolean UserMissing = true;
	
	/**
	 * Launch the application.
	 * public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					CrearUsuario frame = new CrearUsuario();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					VentanaLogin window = new VentanaLogin();
					window.VentanaLogin.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public VentanaLogin() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		VentanaLogin = new JFrame();
		VentanaLogin.setTitle("Login");
		VentanaLogin.setFont(new Font("Courier New", Font.BOLD | Font.ITALIC, 12));
		VentanaLogin.setForeground(Color.DARK_GRAY);
		VentanaLogin.setIconImage(Toolkit.getDefaultToolkit().getImage("C:\\Users\\ik_1dw3a\\Documents\\GitHub\\Temporada2_Grupo2_PROG\\media\\Login top image.png"));
		VentanaLogin.getContentPane().setBackground(new Color(174, 220, 230));
		VentanaLogin.setName("vl");
		VentanaLogin.setMaximumSize(new Dimension(900, 800));
		VentanaLogin.setMinimumSize(new Dimension(500, 500));
		VentanaLogin.setBackground(new Color(128, 128, 0));
		VentanaLogin.getContentPane().setFont(new Font("Tahoma", Font.PLAIN, 15));
		VentanaLogin.setBounds(100, 100, 412, 298);
		VentanaLogin.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		VentanaLogin.getContentPane().setLayout(new MigLayout("", "[7px:n,grow][30%:n:30%][40%:n:40%][7px:n,grow]", "[5px:n,grow][40px:110px:200px][20px:45px:90px][20px:45px:90px][20px:60px:110px][20px:60px:110px][5px:n,grow]"));
		
		icono = new JLabel("");
		icono.setIcon(new ImageIcon("C:\\Users\\ik_1dw3a\\Documents\\GitHub\\Temporada2_Grupo2_PROG\\media\\Login top image.png"));
		VentanaLogin.getContentPane().add(icono, "cell 1 1 2 1,alignx center,aligny center");
		
		//ImageIcon ico =new ImageIcon(getClass().getResource("C:\\Users\\ik_1dw3a\\Documents\\GitHub\\Temporada2_Grupo2_PROG\\media\\Login top image.png"));
		//ImageIcon img =new ImageIcon(ico.getImage().getScaledInstance(iconoPersonaLogin.getWidth(), iconoPersonaLogin.getHeight(), Image.SCALE_SMOOTH));
		//iconoPersonaLogin.setIcon(img);
	
		
				
		JLabel TextoUser = new JLabel("User");
		TextoUser.setFont(new Font("Tahoma", Font.PLAIN, 15));
		VentanaLogin.getContentPane().add(TextoUser, "cell 1 2,alignx right,aligny center");
		
		User = new JTextField();
		VentanaLogin.getContentPane().add(User, "cell 2 2,alignx left,aligny center");
		User.setColumns(20);
		
		TextoPassword = new JLabel("Password");
		TextoPassword.setFont(new Font("Tahoma", Font.PLAIN, 15));
		VentanaLogin.getContentPane().add(TextoPassword, "cell 1 3,alignx right,aligny center");
		
		Password = new JPasswordField();
		Password.setColumns(20);
		VentanaLogin.getContentPane().add(Password, "cell 2 3,alignx left,aligny center");
		
		btnSingUp = new JButton("Sing Up");
		btnSingUp.setFont(new Font("Tahoma", Font.PLAIN, 25));
		btnSingUp.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				CrearUsuario cu = new CrearUsuario(RolSesion);
				// la muestro
				cu.setVisible(true);
			}
		});
		VentanaLogin.getContentPane().add(btnSingUp, "cell 1 5 2 1,alignx center,aligny top");
		
		btnLogin = new JButton("Login");
		btnLogin.setSize(new Dimension(10, 10));
		btnLogin.setFont(new Font("Tahoma", Font.PLAIN, 25));
		VentanaLogin.getContentPane().add(btnLogin, "cell 1 4 2 1,alignx center,aligny top");
		btnLogin.addActionListener(this);
		
		CrearUsuario.dlm = new DefaultListModel<Usuario>();
		CrearUsuario.cargarUsuarios("Usuario.ser");
	}


	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
		Object o = e.getSource();
		
		if (o == btnLogin) {
            	//Se llama la función de cargar usuarios desde la clase CrearUsuario
            	//CrearUsuario.cargarUsuarios("Usuario.ser");
                // Obtiene y limpia los valores ingresados en los campos de texto
                String username = User.getText().trim(); // Obtiene el texto del campo de usuario
                String password = new String(Password.getPassword()).trim(); // Obtiene el texto del campo de contraseña
                // Verificación de usuario y contraseña
                
                Usuario usuario;
                for (int i = 0; i <= CrearUsuario.dlm.getSize()-1; i++){
                	usuario = CrearUsuario.dlm.getElementAt(i);
                	if (username.equalsIgnoreCase(usuario.getNombre())) {
                		UserMissing = false;
                		//Si el usuario coincide verificamos la contraseña
                		if (!password.equals(usuario.getPwd())) {
                			//Si no coincide
                			JOptionPane.showMessageDialog(null, "Contraseña incorrecta."); // Muestra un mensaje de error si las credenciales son incorrectas.
                			
                			break;
                		} else {
                			//Si coincide se inicializa la main con el rol del usuario
                			RolSesion = usuario.getRol();
                			JOptionPane.showMessageDialog(null, "Bienvenido "+username); // Muestra un mensaje de error si las credenciales son incorrectas.
                            VentanaMain ventanaRol = new VentanaMain(RolSesion); // Crea una nueva ventana para el admin
                            ventanaRol.setVisible(true); // Hace visible la ventana del admin
                            dispose(); // Cierra la ventana actual
                            break;
                		}
                		//El usuario No coincide con ninguno de la lista
                		}
                	

                	}
                if (UserMissing) {
                	JOptionPane.showMessageDialog(null, "Usuario inexistente."); // Muestra un mensaje de error si las credenciales son incorrectas.
                }
	    	}
			
		}
		
}
