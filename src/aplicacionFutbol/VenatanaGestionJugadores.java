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
import javax.swing.JButton;
import javax.swing.JTextField;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class VenatanaGestionJugadores extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
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
		body.setLayout(new MigLayout("", "[243.00,grow][][43.00][grow]", "[][][grow]"));
		
		JLabel lblNombre = new JLabel("Nombre: ");
		body.add(lblNombre, "flowx,cell 0 0");
		
		tfNombre = new JTextField();
		body.add(tfNombre, "cell 0 0,growx");
		tfNombre.setColumns(10);
		
		JLabel lblApellidos = new JLabel("Apellidos: ");
		body.add(lblApellidos, "flowx,cell 1 0");
		
		tfApellidos = new JTextField();
		body.add(tfApellidos, "cell 1 0");
		tfApellidos.setColumns(10);
		
		JButton btnAñadir = new JButton("Añadir");
		body.add(btnAñadir, "flowx,cell 0 1");
		
		JButton btnModificar = new JButton("Modificar");
		btnModificar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		body.add(btnModificar, "cell 1 1");
		
		JButton btnEliminar = new JButton("Eliminar");
		body.add(btnEliminar, "cell 0 1");
		
		JList list = new JList();
		body.add(list, "cell 0 2,grow");
		
		JPanel footer = new JPanel();
		contentPane.add(footer, BorderLayout.SOUTH);
	}

}
