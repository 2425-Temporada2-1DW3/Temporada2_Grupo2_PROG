package aplicacionFutbol;

import java.awt.EventQueue;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JFrame;
import net.miginfocom.swing.MigLayout;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JScrollPane;
import javax.swing.ListSelectionModel;
import javax.swing.JPanel;
import java.awt.BorderLayout;

import javax.swing.DefaultListModel;
import javax.swing.JButton;

public class VentanaAnadirJugadores extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JFrame frame;
	private static List<Equipo> equipos = new ArrayList<>();
	private static DefaultListModel<Equipo> dlm;
	private static JList<Equipo> lstEquipos;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					VentanaAnadirJugadores window = new VentanaAnadirJugadores(new Temporada());
					window.frame.setVisible(true);
				} catch (Exception e) {
					System.out.println("No se ha incioado la temporada vacia");
				}
			}
		});
	}

	/**
	 * Create the application.
	 * @param temporada 
	 */
	public VentanaAnadirJugadores(Temporada temporada) {
		
		JPanel panel = new JPanel();
		getContentPane().add(panel, BorderLayout.CENTER);
		panel.setLayout(new MigLayout("", "[5.00][grow][]", "[grow][][182.00,grow][][]"));
		
		JLabel LblEquipo = new JLabel("Nombre Equipos Seleccionado");
		panel.add(LblEquipo, "cell 1 1,alignx center,aligny center");
		
		JLabel lblAno = new JLabel("Ano");
		panel.add(lblAno, "cell 2 1 1 2,alignx center,aligny top");
		
		dlm = new DefaultListModel<Equipo>();
		// creo la lista
		lstEquipos = new JList<>(dlm);
		panel.add(lstEquipos, "cell 1 2 1 2,grow");
		// asocio el modelo de datos a la lista
		lstEquipos.setModel(dlm);
		lstEquipos.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
		
		JButton btnNewButton = new JButton("New button");
		panel.add(btnNewButton, "cell 2 3,alignx center");
		initialize();
		
		((Temporada) equipos).getEquipos();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}

}
