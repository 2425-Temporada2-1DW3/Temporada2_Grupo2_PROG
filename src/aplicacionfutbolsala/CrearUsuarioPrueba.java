package aplicacionfutbolsala;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.BorderLayout;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import java.awt.GridLayout;
import javax.swing.JTextField;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import java.awt.FlowLayout;
import javax.swing.BoxLayout;
import javax.swing.SpringLayout;
import javax.swing.JButton;

public class CrearUsuarioPrueba extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField tfUsername;
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					CrearUsuarioPrueba frame = new CrearUsuarioPrueba();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public CrearUsuarioPrueba() {
		setTitle("Dar de alta a usuarios");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(new BorderLayout(0, 0));
		
		JPanel panel = new JPanel();
		contentPane.add(panel, BorderLayout.NORTH);
		
		JLabel lblRol = new JLabel("Rol del nuevo Usuario: ");
		panel.add(lblRol);
		
		JComboBox cbRoles = new JComboBox();
		cbRoles.addItem("Admin");
		cbRoles.addItem("Arbitro");
		cbRoles.addItem("Usuario");
		cbRoles.setSelectedItem("Usuario");
		panel.add(cbRoles);
		
		JPanel panel_1 = new JPanel();
		contentPane.add(panel_1, BorderLayout.CENTER);
		panel_1.setLayout(new BorderLayout(0, 0));
		
		JPanel panel_2 = new JPanel();
		panel_1.add(panel_2, BorderLayout.SOUTH);
		panel_2.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		
		JPanel panel_1_1 = new JPanel();
		panel_2.add(panel_1_1);
		panel_1_1.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		
		JLabel lblPassword_1 = new JLabel("Contraseña: ");
		panel_1_1.add(lblPassword_1);
		
		textField_1 = new JTextField();
		textField_1.setColumns(10);
		panel_1_1.add(textField_1);
		
		JButton btnAltaUsuario = new JButton("Dar de alta al usuario");
		panel_2.add(btnAltaUsuario);
		
		JPanel panel_3 = new JPanel();
		panel_1.add(panel_3, BorderLayout.CENTER);
		panel_3.setLayout(new BorderLayout(0, 0));
		
		JPanel panel_4 = new JPanel();
		panel_3.add(panel_4, BorderLayout.NORTH);
		panel_4.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		
		JLabel lblUsername = new JLabel("Nombre de Usuario: ");
		panel_4.add(lblUsername);
		
		tfUsername = new JTextField();
		panel_4.add(tfUsername);
		tfUsername.setColumns(10);
		
		JPanel panel_5 = new JPanel();
		panel_3.add(panel_5, BorderLayout.WEST);
		
		JPanel panel_6 = new JPanel();
		panel_3.add(panel_6, BorderLayout.SOUTH);
		panel_6.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		
		JLabel lblPassword = new JLabel("Contraseña: ");
		panel_6.add(lblPassword);
		
		JPanel panel_2_1 = new JPanel();
		panel_6.add(panel_2_1);
		panel_2_1.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		
		JLabel lblContraseñaConf = new JLabel("Repetir contraseña: ");
		panel_2_1.add(lblContraseñaConf);
		
		textField_2 = new JTextField();
		textField_2.setColumns(10);
		panel_2_1.add(textField_2);
		
		textField = new JTextField();
		textField.setColumns(10);
		panel_6.add(textField);
	}
}
