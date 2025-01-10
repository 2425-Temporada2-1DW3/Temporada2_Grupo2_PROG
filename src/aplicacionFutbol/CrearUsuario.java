package aplicacionFutbol;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.BorderLayout;
import java.awt.FlowLayout;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JComboBox;
import javax.swing.JButton;
import javax.swing.JTextField;
import java.awt.GridLayout;
import javax.swing.BoxLayout;
import java.awt.CardLayout;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import javax.swing.SpringLayout;
import java.awt.Insets;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import net.miginfocom.swing.MigLayout;

public class CrearUsuario extends JFrame implements ActionListener{

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField tfNombre;
	private JTextField tfContrasena;
	private JTextField textField_2;
	private JButton btnAltaUsuario;
	private String rolSelec;


	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
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

	/**
	 * Create the frame.
	 */
	public CrearUsuario() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(new BorderLayout(0, 0));
		
		JPanel panelHead = new JPanel();
		contentPane.add(panelHead, BorderLayout.NORTH);
		panelHead.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		
		JLabel lblHead = new JLabel("Alta de usuarios");
		panelHead.add(lblHead);
		
		JPanel panel = new JPanel();
		contentPane.add(panel, BorderLayout.CENTER);
		panel.setLayout(new MigLayout("", "[][][][][52.00][grow]", "[][][][][][][]"));
		
		JLabel lblNombreusuario = new JLabel("Nombre de Usuario: ");
		panel.add(lblNombreusuario, "cell 4 1,alignx trailing");
		
		tfNombre = new JTextField();
		panel.add(tfNombre, "cell 5 1,growx");
		tfNombre.setColumns(10);
		
		JLabel lblContrasena = new JLabel("Contraseña: ");
		panel.add(lblContrasena, "cell 4 3,alignx trailing");
		
		tfContrasena = new JTextField();
		panel.add(tfContrasena, "cell 5 3,growx");
		tfContrasena.setColumns(10);
		
		JLabel lblContraseñaRep = new JLabel("Repetir contraseña: ");
		panel.add(lblContraseñaRep, "cell 4 5,alignx trailing");
		
		textField_2 = new JTextField();
		panel.add(textField_2, "cell 5 5,growx");
		textField_2.setColumns(10);
		
		JLabel lblRol_1 = new JLabel("Rol del nuevo Usuario: ");
		panel.add(lblRol_1, "cell 4 6,alignx trailing");
		
		JComboBox cbRoles = new JComboBox();
		cbRoles.addItem("Admin");
		cbRoles.addItem("Árbitro");
		cbRoles.addItem("Usuario");
		cbRoles.setSelectedItem("Usuario");
		
		cbRoles.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent r) {
				// obtengo la temporada seleccionada
				rolSelec = (String) cbRoles.getSelectedItem();
				
			}
		});
		
		
		panel.add(cbRoles, "cell 5 6,growx");
		
		JPanel panel_1 = new JPanel();
		contentPane.add(panel_1, BorderLayout.SOUTH);
		panel_1.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		
		btnAltaUsuario = new JButton("Dar de alta al usuario");
		btnAltaUsuario.addActionListener(this);
		panel_1.add(btnAltaUsuario);
	}

	@Override
	public void actionPerformed(ActionEvent ae) {
		// TODO Auto-generated method stub
		Object o = ae.getSource();
		if (o == btnAltaUsuario)
		JOptionPane.showMessageDialog(this, (String) "Creando un usuario con el rol "+rolSelec, "Error",JOptionPane.INFORMATION_MESSAGE);
	}

}
