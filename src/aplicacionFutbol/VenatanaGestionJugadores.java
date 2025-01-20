package aplicacionFutbol;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.BorderLayout;
import java.awt.FlowLayout;
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
	private boolean error = false;

	
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
		String nombre;
		String apellidos;
		if(o == btnAñadir) {
			nombre = tfNombre.getText().trim();
			apellidos = tfApellidos.getText().trim();
			
			// comprobamos que no haya ningun campo de texto vacío
			if (nombre.isEmpty() ||apellidos.isEmpty()) {
				JOptionPane.showMessageDialog(this, (String) "No Puede haber campos vacios :(", "Error",JOptionPane.ERROR_MESSAGE);
				error = true;
			}else {
				// creamos el objeto
				Jugador j = new Jugador(nombre,apellidos);
				// lo añadimos al dlm
				
				dlm.addElement(j);
					
				
				//indicamos que los valores han sido modificados
				modificado = true;

			}
			
		}else if(o == btnEliminar) {
			
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
			}
			
		}else if(o == btnModificar) {
			
			
			
		}
	}

}
