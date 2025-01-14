package aplicacionFutbol;

import java.awt.EventQueue;
import java.io.BufferedReader;
import java.io.EOFException;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.ListSelectionModel;
import javax.swing.border.EmptyBorder;


import java.awt.BorderLayout;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JList;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;


public class BorrarUsuario extends JFrame implements ActionListener,Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 4870481935552617691L;
	private JPanel contentPane;
	private JList<Usuario> lstUsuarios;
	private DefaultListModel<Usuario> dlm;
	private JButton btnBorrar;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					BorrarUsuario frame = new BorrarUsuario();
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
	public BorrarUsuario() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(new BorderLayout(0, 0));
		
		JPanel panel = new JPanel();
		contentPane.add(panel, BorderLayout.NORTH);
		
		btnBorrar = new JButton("Eliminar");
		btnBorrar.addActionListener(this);
		panel.add(btnBorrar);
	
		
		

		//********************************
		//Modelo de la lista
		//********************************
		// creo el modelo de datos de la lista
		dlm = new DefaultListModel<Usuario>();
		// creo la lista
		lstUsuarios = new JList<>(dlm);
		// asocio el modelo de datos a la lista
		//lstUsuarios.setModel(dlm);
		contentPane.add(lstUsuarios, BorderLayout.CENTER);
		
		// Permitir seleccion multiple
		lstUsuarios.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
		
	    // Leer usuarios del archivo
        List<Usuario> usuarios = leerUsuarios("usuario.ser");
        System.out.println("Usuarios leídos: " + usuarios.size());
		for (Usuario usuario : usuarios) {
            dlm.addElement(usuario);
        }
	}
	
	
	@Override
	public void actionPerformed(ActionEvent ee) {
		// TODO Auto-generated method stub
		Object o = ee.getSource();
		
		if (o == btnBorrar) {
			// obtenemos los elementos seleccionados de la lista
			List<Usuario> ValSelec = lstUsuarios.getSelectedValuesList();
			int[] IndSelec = lstUsuarios.getSelectedIndices();
			// comprobamos que haya elementos seleccionados
			if (IndSelec.length <= 0) {
				JOptionPane.showMessageDialog(this,(String)"No hay valores seleccionados ","error",JOptionPane.ERROR_MESSAGE);
			}else {
				// ELIMINAMOS LOS USUARIOS DEL DLM
				for (int i = IndSelec.length - 1; i >= 0; i--) {
					//obtengo el nombre del usuario a eliminar
				
					// corramos los indices almacenados en IndSelec
					dlm.removeElementAt(IndSelec[i]);
					
				}
				
				
			}
		}
		
	}
	
	
	public static List<Usuario> leerUsuarios(String nombreArchivo) {
	        List<Usuario> usuarios = new ArrayList<>();

	        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(nombreArchivo))) {
	            while (true) {
	            	try {
	            		Object obj = ois.readObject();
		                if (obj instanceof Usuario) {
		                    usuarios.add((Usuario) obj);
		                }
	            	} catch (EOFException e) {
	            		break;
	            	}
	                
	            }
	        } catch (IOException | ClassNotFoundException e) {
	            e.printStackTrace();
	        }
	        return usuarios;
	        

	    }



}
