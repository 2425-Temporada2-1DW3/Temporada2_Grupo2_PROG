package aplicacionFutbol;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JPasswordField;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import net.miginfocom.swing.MigLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.Dimension;
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
		
		btnLogin = new JButton("Login");
		btnLogin.setSize(new Dimension(10, 10));
		btnLogin.setFont(new Font("Tahoma", Font.PLAIN, 25));
		VentanaLogin.getContentPane().add(btnLogin, "cell 1 4 2 1,alignx center,aligny top");
		btnLogin.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // Obtiene y limpia los valores ingresados en los campos de texto
                String usuario = User.getText().trim(); // Obtiene el texto del campo de usuario
                String password = new String(Password.getPassword()).trim(); // Obtiene el texto del campo de contraseña

                // Verificación de usuario y contraseña
                if ("admin".equals(usuario) && "admin".equals(password)) { // Verifica si las credenciales son de admin
                    JOptionPane.showMessageDialog(null, "Bienvenido Admin."); // Muestra un mensaje de bienvenida
                    VentanaMain ventanaAdmin = new VentanaMain("admin"); // Crea una nueva ventana para el admin
                    ventanaAdmin.setVisible(true); // Hace visible la ventana del admin
                    dispose(); // Cierra la ventana actual
                } else if ("arbitro".equals(usuario) && "arbitro".equals(password)) { // Verifica si las credenciales son de árbitro
                    JOptionPane.showMessageDialog(null, "Bienvenido Arbitro."); // Muestra un mensaje de bienvenida
                    VentanaMain ventanaArbitro = new VentanaMain("arbitro"); // Crea una nueva ventana para el árbitro
                    ventanaArbitro.setVisible(true); // Hace visible la ventana del árbitro
                    dispose(); // Cierra la ventana actual
                } else {
                    JOptionPane.showMessageDialog(null, "Usuario o contraseña incorrectos."); // Muestra un mensaje de error si las credenciales son incorrectas.
                }
            }
        });
		
		btnSingUp = new JButton("Sing Up");
		btnSingUp.setFont(new Font("Tahoma", Font.PLAIN, 25));
		btnSingUp.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				CrearUsuario cu = new CrearUsuario();
				// la muestro
				cu.setVisible(true);
			}
		});
		VentanaLogin.getContentPane().add(btnSingUp, "cell 1 5 2 1,alignx center,aligny top");
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
	}
}
