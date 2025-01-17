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
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.PrintWriter;
import java.awt.event.ActionEvent;
import net.miginfocom.swing.MigLayout;
import javax.swing.JPasswordField;
import java.awt.Toolkit;
import java.awt.Dimension;

public class CrearUsuario extends JFrame implements ActionListener{

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField tfNombre;
	private JButton btnAltaUsuario;
	private String rolSelec = "Usuario";
	private JPasswordField pfContrasena;
	private JPasswordField pfContrasena2;
	private JButton btnBorrarUsuarios;


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
		setMinimumSize(new Dimension(525, 350));
		setIconImage(Toolkit.getDefaultToolkit().getImage("C:\\Users\\ik_1dw3a\\Documents\\GitHub\\Temporada2_Grupo2_PROG\\media\\Login top image.png"));
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
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
		
		pfContrasena = new JPasswordField();
		panel.add(pfContrasena, "cell 5 3,growx");
		
		JLabel lblContraseñaRep = new JLabel("Repetir contraseña: ");
		panel.add(lblContraseñaRep, "cell 4 5,alignx trailing");
		
		pfContrasena2 = new JPasswordField();
		panel.add(pfContrasena2, "cell 5 5,growx");
		
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
		
		btnBorrarUsuarios = new JButton("Eliminar Usuarios");
		btnBorrarUsuarios.addActionListener(this);
		panel_1.add(btnBorrarUsuarios);
	}

	@Override
	public void actionPerformed(ActionEvent ae) {
		// TODO Auto-generated method stub
		Object o = ae.getSource();
		if (o == btnAltaUsuario) {
			/* OBTENEMOS LOS VALORES INTRODUCIDOS EN LOS CAMPOS DE TEXTO*/
			// obtengo el nombre escrito
			String tfNombreStr = tfNombre.getText().trim();
			
			// Obtener la contraseña1
            char[] password1 = pfContrasena.getPassword();
            String password1Str = new String(password1).trim();
            // Limpieza del arreglo por seguridad
            java.util.Arrays.fill(password1, '\0'); 
            

            // Obtener la contraseña2
            char[] password2 = pfContrasena2.getPassword();
            String password2Str = new String(password2).trim();
            // Limpieza del arreglo por seguridad
            java.util.Arrays.fill(password1, '\0'); 

            // Mostrar la contraseña en consola (¡Solo para pruebas!)
            System.out.println("Contraseña ingresada:"+password1Str+" "+password2Str);
            /* OBTENEMOS LOS VALORES INTRODUCIDOS EN LOS CAMPOS DE TEXTO*/
            
            /*COMPROBAMS QUE LOS DATOS INTRODUCIDOS SEAN VÁLIDOS*/
			// comprobamos que no haya ningun campo de texto vacío
			if (tfNombreStr.isEmpty() || password1Str.isEmpty() || password2Str.isEmpty()) {
				JOptionPane.showMessageDialog(this, (String) "No Puede haber campos vacios ", "Error",JOptionPane.ERROR_MESSAGE);
			}else if(!password1Str.equals(password2Str)){
				JOptionPane.showMessageDialog(this, (String) "Las contraseñas no coinciden._"+password1Str+"_"+password2Str+"_", "Error",JOptionPane.ERROR_MESSAGE);
			}else {
				// creamos el objeto
				Usuario u = new Usuario(tfNombreStr,password1Str,password2Str);
				// guardamos en usuarios.ser
				guardarUsuario("usuario.ser", u);
				
			}
			
			// mensaje informativo
			JOptionPane.showMessageDialog(this, (String) "Creando un usuario "+tfNombreStr+" con el rol "+rolSelec, "Error",JOptionPane.INFORMATION_MESSAGE);
		}else if(o == btnBorrarUsuarios) {
			BorrarUsuario bu = new BorrarUsuario();
			// muestro la ventana
			bu.setVisible(true);
		}
	}
	
	public static void guardarUsuario(String nombreArchivo, Usuario usuario) {
		try {
			FileOutputStream fos = new FileOutputStream(nombreArchivo, true);
			ObjectOutputStream oos = new ObjectOutputStream(fos);
			// añadimos el usuario al archivo usuarios.ser
			oos.writeObject(usuario);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
