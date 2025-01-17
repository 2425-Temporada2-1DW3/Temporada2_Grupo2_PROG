package aplicacionFutbol;

import java.awt.EventQueue;
import java.io.BufferedReader;
import java.io.EOFException;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
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

	private static final long serialVersionUID = 4870481935552617691L;
	private JPanel contentPane;
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
		/*
		 ELEMENTOS GRÁFICOS
		 */
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
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
		// añadimos la lista (con el dlm de CrearUsuario) al JFrame.
		try {
			//contentPane.add(CrearUsuario.lstUsuarios, BorderLayout.CENTER);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		/*
		 ELEMENTOS GRÁFICOS
		 */
		
	}
	
	
	@Override
	public void actionPerformed(ActionEvent ee) {
		// TODO Auto-generated method stub
		Object o = ee.getSource();
		
		if (o == btnBorrar) {
			// obtenemos los elementos seleccionados de la lista
			List<Usuario> ValSelec = CrearUsuario.lstUsuarios.getSelectedValuesList();
			int[] IndSelec = CrearUsuario.lstUsuarios.getSelectedIndices();
			
			
			// comprobamos que haya elementos seleccionados
			if (IndSelec.length <= 0) {
				JOptionPane.showMessageDialog(this,(String)"No hay valores seleccionados ","error",JOptionPane.ERROR_MESSAGE);
			}else {
				
				// ELIMINAMOS LOS USUARIOS DEL DLM
				for (int i = IndSelec.length - 1; i >= 0; i--) {
					// corramos los indices almacenados en IndSelec
					CrearUsuario.dlm.removeElementAt(IndSelec[i]);
				}
				 
				//UNA VEZ ELIMINADOS DEL DLM
				//GrabarUsuarios("Usuario.ser", CrearUsuario.dlm);
				
			}
		}
		
	}
	
	
	
	/* 
	 * GRABA LOS USUARIOS DLM ---> .SER
	 * */
	/*public static void GrabarUsuarios(String nombreArchivo, DefaultListModel<Usuario> dlm) {
		 try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(nombreArchivo))) {
			 for (int i = 0; i < dlm.size(); i++) {
	                oos.writeObject(dlm.get(i)); // Escribir cada objeto
	            }
	            System.out.println("Lista guardada en objetos.ser");
	        } catch (IOException e) {
	            e.printStackTrace();
	        }
	}*/
	
	/* 
	 * GRABA LOS USUARIOS DLM ---> .SER
	 * */
}
