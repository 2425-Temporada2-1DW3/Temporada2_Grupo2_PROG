package aplicacionFutbol;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.BorderLayout;
import java.awt.FlowLayout;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JComboBox;
import javax.swing.JButton;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;

import java.awt.GridLayout;
import javax.swing.BoxLayout;
import javax.swing.DefaultListModel;

import java.awt.CardLayout;
import java.awt.Dimension;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import javax.swing.SpringLayout;
import java.awt.Insets;
import java.awt.Toolkit;
import java.awt.event.ActionListener;
import java.io.BufferedWriter;
import java.io.EOFException;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.PrintWriter;
import java.util.List;
import java.awt.event.ActionEvent;
import net.miginfocom.swing.MigLayout;
import javax.swing.JPasswordField;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

public class CrearUsuario extends JFrame implements ActionListener, WindowListener{

	/**
	 * 
	 */
	private static final long serialVersionUID = -6355583836049037827L;
	private JPanel contentPane;
	private JTextField tfNombre;
	private JButton btnAltaUsuario;
	private String rolSelec = "Usuario";
	private JPasswordField pfContrasena;
	private JPasswordField pfContrasena2;
	private JButton btnBorrarUsuarios;
	private Boolean modificado = false;
	private Boolean ValidData = true;
	public static DefaultListModel<Usuario> dlm;
	public static JList<Usuario> lstUsuarios;


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
		/*
		 ELEMENTOS GRÁFICOS
		 */
		this.addWindowListener(this); 
		// IMPEDIMOS QUE LA VENTANA SE CIERRE AL PULSAR LA X Y AÑADO EL WINDOWLISTENER
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
		panel.setLayout(new MigLayout("", "[][][][-46.00][52.00,grow][grow]", "[][][][][][][][][][][][grow][grow]"));
		
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
		
		// action listener para obtener el rol seleccionado
		cbRoles.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent r) {
				// obtengo la temporada seleccionada
				rolSelec = (String) cbRoles.getSelectedItem();
				
			}
		});
		
		panel.add(cbRoles, "cell 5 6,growx");
		
		
		//lstUsuarios = new JList<>(dlm);
		//
		

		
		JPanel panel_1 = new JPanel();
		contentPane.add(panel_1, BorderLayout.SOUTH);
		panel_1.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		
		btnBorrarUsuarios = new JButton("Eliminar Usuarios");
		btnBorrarUsuarios.addActionListener(this);
		panel_1.add(btnBorrarUsuarios);
		
		/*
		 ELEMENTOS GRÁFICOS
		 */
		
		/*
		 ELEMENTOS LÓGICOS
		 */
		
		

		//********************************
		//Modelo de la lista
		//********************************
		// creo el modelo de datos de la lista
		dlm = new DefaultListModel<Usuario>();
		// creo la lista
		lstUsuarios = new JList<>(dlm);
		// asocio el modelo de datos a la lista
		lstUsuarios.setModel(dlm);
		
		// Permitir seleccion multiple
		lstUsuarios.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);

		cargarUsuarios("Usuario.ser");
		
		btnAltaUsuario = new JButton("Añadir al usuario");
		panel.add(btnAltaUsuario, "cell 5 8");
		btnAltaUsuario.addActionListener(this);
		
		panel.add(lstUsuarios, "cell 1 11 5 2,grow");
		/*
		 ELEMENTOS LÓGICOS
		 */

	}

	@Override
	public void actionPerformed(ActionEvent ae) {
		// TODO Auto-generated method stub
		Object o = ae.getSource();
		if (o == btnAltaUsuario) {
			/* 
			 * OBTENEMOS LOS VALORES INTRODUCIDOS EN LOS CAMPOS DE TEXTO
			 ----------------------------------------------------------*/
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

            // Mostrar la datos en consola (¡Solo para pruebas!)
            System.out.println("Usuario: "+tfNombreStr+" Contraseñas ingresadas:"+password1Str+" "+password2Str+" ROL: "+rolSelec);
			/*----------------------------------------------------------------- 
			 * OBTENEMOS LOS VALORES INTRODUCIDOS EN LOS CAMPOS DE TEXTO
			 */
            
            /*
             * COMPROBAMS SI LOS DATOS INTRODUCIDOS SON VÁLIDOS
             * ----------------------------------------------------*/
			// comprobamos que no haya ningun campo de texto vacío
			if (tfNombreStr.isEmpty() || password1Str.isEmpty() || password2Str.isEmpty()) {
				ValidData = false;
				JOptionPane.showMessageDialog(this, (String) "No Puede haber campos vacios ", "Error",JOptionPane.ERROR_MESSAGE);
			// comprobamos que las contraseñas coincidan
			}else if(!password1Str.equals(password2Str)){
				ValidData = false;
				JOptionPane.showMessageDialog(this, (String) "Las contraseñas no coinciden._"+password1Str+"_"+password2Str+"_", "Error",JOptionPane.ERROR_MESSAGE);
			}else {
				// ¡¡¡¡FALTA!!!! comprobamos que no sea un usuario que ya existse
				Usuario usuario;
				for (int i = dlm.getSize() -1 ; i >= 0; i--) {
					usuario = dlm.getElementAt(i); // Obtenemos el usuario
					if (usuario.getNombre().equalsIgnoreCase(tfNombreStr)) {
						JOptionPane.showMessageDialog(this, (String) "El nombre "+tfNombreStr+" ya esta en uso.", "Error",JOptionPane.ERROR_MESSAGE);
						ValidData = false;
						break;
					}
					
				}
			}
				
			/*---------------------------------------------------------
			 * COMPROBAMOS QUE LOS DATOS INTRODUCIDOS SEAN VÁLIDOS
			 * */
			if (ValidData == false) {
				JOptionPane.showMessageDialog(this, (String) "Hay un error con los datos del usuario", "Error",JOptionPane.ERROR_MESSAGE);
			/*
			* AÑADIMOS AL USUARIO (COMO OBJETO)
			* -------------------------------------------*/
			}else if(ValidData == true){
				// creamos el objeto
				Usuario u = new Usuario(tfNombreStr,password1Str,rolSelec);
				// lo añadimos al dlm
				try {
					dlm.addElement(u);
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				//indicamos que los valores han sido modificados
				modificado = true;
				// mensaje informativo
				JOptionPane.showMessageDialog(this, (String) "Creando un usuario "+tfNombreStr+" con el rol "+rolSelec, "Error",JOptionPane.INFORMATION_MESSAGE);
			}

			/*--------------------------------------------------
			 * AÑADIMOS AL USUARIO (COMO OBJETO)
			 * 
			 * */
			/*
			 *ELIMINAR USUARIOS DEL DLM
			 * ------------------------------------------------------------------*/
		}else if(o == btnBorrarUsuarios) {

			// obtenemos los elementos seleccionados de la lista
			List<Usuario> ValSelec = CrearUsuario.lstUsuarios.getSelectedValuesList();
			int[] IndSelec = CrearUsuario.lstUsuarios.getSelectedIndices();
			// comprobamos que haya elementos seleccionados
			if (IndSelec.length <= 0) {
				JOptionPane.showMessageDialog(this,(String)"No hay valores seleccionados ","error",JOptionPane.ERROR_MESSAGE);
			}else {
				// ELIMINAMOS LOS USUARIOS DEL DLM
				Usuario usuario;
				for (int i = IndSelec.length - 1; i >= 0; i--) {
					usuario = ValSelec.get(i);
					
					if (!usuario.getNombre().equals("Root")) {
						// eliminamos el usuario ¡siempre y cuando no se llame root!
						dlm.removeElementAt(IndSelec[i]);
					}
				}
				 
				//ELIMINAMOS DEL .SER
				GrabarUsuarios("Usuario.ser", CrearUsuario.dlm);
				
			}
		}
		/*-----------------------------------------------------------------------------------------
		 *ELIMINAR USUARIOS DEL DLM
		 */
	}


	@Override
	public void windowOpened(WindowEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void windowClosed(WindowEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void windowIconified(WindowEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void windowDeiconified(WindowEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void windowActivated(WindowEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void windowDeactivated(WindowEvent e) {
		// TODO Auto-generated method stub
		
	}

	
	/*
	 * VENATNA APLICAR CAMBIOS [ SI // NO // CANCELAR ]
	 * ----------------------------------------------------*/
	@Override
	public void windowClosing(WindowEvent e) {
		
		if(modificado) {
			// si se han añadido usuario
			int opcion = JOptionPane.showConfirmDialog(this,(String)"Se han añadido usuarios, ¿Desea guardarlos permanentemente?","Info",JOptionPane.YES_NO_CANCEL_OPTION,JOptionPane.QUESTION_MESSAGE,null);
			switch (opcion) {
				case JOptionPane.YES_OPTION: // opcion "Si"
					// guardo los usuarios 
					GrabarUsuarios("Usuario.ser", dlm);
					break;
				case JOptionPane.NO_OPTION: // opcion "No"
					dispose();
					return;
				case JOptionPane.CANCEL_OPTION: 
				case JOptionPane.CLOSED_OPTION:
					// si pulsa Cancelar
					// Si cierra la ventana
					return;
			}
		}
		
		// salgo de la aplicacion
		System.exit(0);
		
	}
	
	/*----------------------------------------------------------
	 * VENATNA [ SI // NO // CANCELAR ]
	 * */
	
	
	/*
	 * FUNCION DE CARGAR USUARIOS .SER ----> DLM
	 *---------------------------------------------------------------------------------------- */
	
	public static void cargarUsuarios(String archivo) {
		 try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(archivo))) {
	            // Leer el archivo objeto por objeto y agregarlo al DefaultListModel
	            Object obj;
	            while ((obj = ois.readObject()) != null) {
	                if (obj instanceof Usuario) {
	                    Usuario usuario = (Usuario) obj;
	                    dlm.addElement(usuario);
	                }
	            }
	        } catch (EOFException e) {
	            // Se alcanza el final del archivo, esta excepción es normal cuando termina la lectura
	            System.out.println("Archivo cargado completamente.");
	        } catch (FileNotFoundException e) {
	            System.err.println("El archivo no existe: " + archivo);
	        } catch (IOException | ClassNotFoundException e) {
	            e.printStackTrace();
	        }
	}
	
	
	/*
	 * GRABA LOS USUARIOS DLM ---> .SER
	 * -----------------------------------------------------------------------------------------------------------------*/
	public static void GrabarUsuarios(String nombreArchivo, DefaultListModel<Usuario> dlm) {
		 try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(nombreArchivo))) {
			 for (int i = 0; i < dlm.size(); i++) {
	                oos.writeObject(dlm.get(i)); // Escribir cada objeto
	            }
	            System.out.println("Lista guardada en objetos.ser");
	        } catch (IOException e) {
	            e.printStackTrace();
	        }
	}
}
