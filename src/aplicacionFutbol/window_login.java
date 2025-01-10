package aplicacionFutbol;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
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

public class window_login {

	private JFrame frame;
	private JTextField textField;
	private JLabel lblNewLabel_1;
	private JPasswordField passwordField;
	private JButton btnNewButton;
	private JLabel iconoPersonaLogin;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					window_login window = new window_login();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public window_login() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.getContentPane().setBackground(new Color(174, 220, 230));
		frame.setName("Futsal Champagne - Login");
		frame.setMaximumSize(new Dimension(900, 750));
		frame.setMinimumSize(new Dimension(500, 400));
		frame.setBackground(new Color(128, 128, 0));
		frame.getContentPane().setFont(new Font("Tahoma", Font.PLAIN, 15));
		frame.setBounds(100, 100, 412, 298);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(new MigLayout("", "[7px:n,grow][30%:n:30%][40%:n:40%][7px:n,grow]", "[5px:n,grow][40px:110px:200px][20px:45px:90px][20px:45px:90px][30px:60px:115px][5px:n,grow]"));
		
		iconoPersonaLogin = new JLabel("");
		iconoPersonaLogin.setIcon(new ImageIcon("C:\\Users\\ik_1dw3a\\Documents\\GitHub\\Temporada2_Grupo2_PROG\\media\\Login top image.png"));
		frame.getContentPane().add(iconoPersonaLogin, "cell 1 1 2 1,alignx center,aligny center");
		
		//ImageIcon ico =new ImageIcon(getClass().getResource("C:\\Users\\ik_1dw3a\\Documents\\GitHub\\Temporada2_Grupo2_PROG\\media\\Login top image.png"));
		//ImageIcon img =new ImageIcon(ico.getImage().getScaledInstance(iconoPersonaLogin.getWidth(), iconoPersonaLogin.getHeight(), Image.SCALE_SMOOTH));
		//iconoPersonaLogin.setIcon(img);
	
		
				
		JLabel lblNewLabel = new JLabel("Usuario");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 15));
		frame.getContentPane().add(lblNewLabel, "cell 1 2,alignx right,aligny center");
		
		textField = new JTextField();
		frame.getContentPane().add(textField, "cell 2 2,alignx left,aligny center");
		textField.setColumns(20);
		
		lblNewLabel_1 = new JLabel("Contraseña");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.PLAIN, 15));
		frame.getContentPane().add(lblNewLabel_1, "cell 1 3,alignx right,aligny center");
		
		passwordField = new JPasswordField();
		passwordField.setColumns(20);
		frame.getContentPane().add(passwordField, "cell 2 3,alignx left,aligny center");
		
		btnNewButton = new JButton("Login");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnNewButton.setFont(new Font("Tahoma", Font.PLAIN, 25));
		frame.getContentPane().add(btnNewButton, "cell 1 4 2 1,alignx center,aligny top");
	}
}
