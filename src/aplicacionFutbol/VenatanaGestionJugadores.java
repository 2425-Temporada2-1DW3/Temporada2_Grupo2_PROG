package aplicacionFutbol;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.HeadlessException;

import javax.swing.JLabel;
import net.miginfocom.swing.MigLayout;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;

import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.io.EOFException;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.List;
import java.awt.event.ActionEvent;

public class VenatanaGestionJugadores extends JFrame implements ActionListener, WindowListener{

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JButton btnAñadir;
	private JButton btnEliminar;
	private JButton btnModificar;
	private JList<Jugador> lstJugadores;
	private static DefaultListModel<Jugador> dlm;
	private boolean modificado = false;
	private static boolean error = false;
	private boolean modificando = false;
	private static String nombre;
	private static String apellidos;
	
	private JTextField tfNombre;
	private JTextField tfApellidos;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					VenatanaGestionJugadores frame = new VenatanaGestionJugadores();
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
	public VenatanaGestionJugadores() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 587, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(new BorderLayout(0, 0));
		
		JPanel header = new JPanel();
		contentPane.add(header, BorderLayout.NORTH);
		header.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		
		JLabel lblGestionJugadores = new JLabel("Gestión de jugadores");
		header.add(lblGestionJugadores);
		
		JPanel body = new JPanel();
		contentPane.add(body, BorderLayout.CENTER);
		body.setLayout(new MigLayout("", "[275.00,grow][][43.00][grow]", "[][][grow]"));
		
		JLabel lblNombre = new JLabel("Nombre: ");
		body.add(lblNombre, "flowx,cell 0 0");
		
		tfNombre = new JTextField();
		body.add(tfNombre, "cell 0 0,growx");
		tfNombre.setColumns(10);
		
		btnAñadir = new JButton("Añadir");
		btnAñadir.addActionListener(this);
		body.add(btnAñadir, "flowx,cell 0 1,growx");
		
		btnEliminar = new JButton("Eliminar");
		btnEliminar.addActionListener(this);
		body.add(btnEliminar, "cell 0 1,growx");
		
		//********************************
		//Modelo de la lista
		//********************************
		// creo el modelo de datos de la lista
		dlm = new DefaultListModel<Jugador>();
		// creo la lista
		lstJugadores = new JList<>(dlm);
		// asocio el modelo de datos a la lista
		lstJugadores.setModel(dlm);
		
		// Permitir seleccion multiple
		lstJugadores.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);

		body.add(lstJugadores, "cell 0 2,grow");
		
		
		JLabel lblApellidos = new JLabel("Apellidos: ");
		body.add(lblApellidos, "cell 0 0");
		
		tfApellidos = new JTextField();
		body.add(tfApellidos, "cell 0 0,growx");
		tfApellidos.setColumns(10);
		
		btnModificar = new JButton("Modificar");
		btnModificar.addActionListener(this);
		body.add(btnModificar, "cell 0 1,growx");
		
		JPanel footer = new JPanel();
		contentPane.add(footer, BorderLayout.SOUTH);
		
		cargarJugadores("Jugadores.ser");
	}

	@Override
	public void windowOpened(WindowEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void windowClosing(WindowEvent e) {
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

	@Override
	public void actionPerformed(ActionEvent ae) {
		// TODO Auto-generated method stub
		Object o = ae.getSource();
		//String nombre;
		//String apellidos;
		if(o == btnAñadir) {
			if (modificando == false) {
				nombre = tfNombre.getText().trim();
				apellidos = tfApellidos.getText().trim();
				
				// comprobamos que no haya ningun campo de texto vacío
				if (nombre.isEmpty() ||apellidos.isEmpty()) {
					JOptionPane.showMessageDialog(this, (String) "No Puede haber campos vacios :(", "Error",JOptionPane.ERROR_MESSAGE);
					error = true;
				}else {
					Jugador j;
					for (int i = dlm.getSize() -1 ; i >= 0; i--) {
						j = dlm.getElementAt(i); // Obtenemos el jugador
						if (j.getNombre().equalsIgnoreCase(nombre) || j.getApellidos().equalsIgnoreCase(apellidos)) {
							JOptionPane.showMessageDialog(this, (String) "El jugador "+nombre+" "+apellidos+" ya esta en uso.", "Error",JOptionPane.ERROR_MESSAGE);
							error = true;
							break;
						}
					}
				}

				if(error == false){
					// creamos el objeto
					Jugador j = new Jugador(nombre,apellidos);
					// lo añadimos al dlm
					dlm.addElement(j);
					// limpiamos los textfields
					tfNombre.setText("");
					tfApellidos.setText("");
					
					//indicamos que los valores han sido modificados
					modificado = true;
					GrabarJugadores("Jugadores.ser", dlm);
				}
			}
			
			
		}else if(o == btnEliminar) {
			if (modificando == false) {
				// obtenemos los elementos seleccionados de la lista
				List<Jugador> ValSelec = lstJugadores.getSelectedValuesList();
				int[] IndSelec = lstJugadores.getSelectedIndices();
				
				if (IndSelec.length <= 0) {
					JOptionPane.showMessageDialog(this,(String)"No hay valores seleccionados ","error",JOptionPane.ERROR_MESSAGE);
				}else {
					// ELIMINAMOS LOS USUARIOS DEL DLM
					Jugador jugador;
					for (int i = IndSelec.length - 1; i >= 0; i--) {
						jugador = ValSelec.get(i);
						dlm.removeElementAt(IndSelec[i]);
					}
					GrabarJugadores("Jugadores.ser", dlm);
				}
			}
			
			
		}else if(o == btnModificar) {
			
			// empezamos indicando que no hay errores
			error = false;
			// obtenemos los valores seleccionados
			List<Jugador> ValSelec = lstJugadores.getSelectedValuesList();
			int[] IndSelec = lstJugadores.getSelectedIndices();
			Jugador j;
			
			// compruebo que solo haya un elemento seleccionado
			if (IndSelec.length <= 0 || IndSelec.length > 1) {
				JOptionPane.showMessageDialog(this,(String)"debe selecionar un judador","error",JOptionPane.ERROR_MESSAGE);
				//error = true;
			}else {
				// obtengo el objeto seleccionado 
				j = ValSelec.get(0);
				
				if (modificando == false) {
					
					
					tfNombre.setText(j.getNombre());
					tfApellidos.setText(j.getApellidos());
					btnModificar.setText("Aplicar cambios");
					
					modificando = true;
				
		
			}else if (modificando == true) {
				
				Jugador jug;
				for (int i = dlm.getSize() -1 ; i >= 0; i--) {
					jug = dlm.getElementAt(i); // Obtenemos el jugador
					if (jug.getNombre().equalsIgnoreCase(nombre) || jug.getApellidos().equalsIgnoreCase(apellidos)) {
						JOptionPane.showMessageDialog(this, (String) "El jugador "+nombre+" "+apellidos+" ya esta en uso.", "Error",JOptionPane.ERROR_MESSAGE);
						error = true;
						break;
					}
				}
				if(!error) {
					//System.out.println(tfNombre.getText().trim());
					//System.out.println(tfApellidos.getText().trim());
					j.setNombre(tfNombre.getText().trim());
					j.setApellidos(tfApellidos.getText().trim());
					
					dlm.set(IndSelec[0],j);
					modificando = false;
					btnModificar.setText("Modificar");
					GrabarJugadores("Jugadores.ser", dlm);
				}
				
			}
			}
			
			
		}
			
	}
	
	
	/*
	 * FUNCION DE CARGAR USUARIOS .SER ----> DLM
	 *---------------------------------------------------------------------------------------- */
	public void cargarJugadores(String archivo) {
		 try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(archivo))) {
	            // Leer el archivo objeto por objeto y agregarlo al DefaultListModel
	            Object obj;
	            while ((obj = ois.readObject()) != null) {
	                if (obj instanceof Jugador) {
	                    Jugador jugador = (Jugador) obj;
	                    dlm.addElement(jugador);
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
	public static void GrabarJugadores(String nombreArchivo, DefaultListModel<Jugador> dlm) {
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
