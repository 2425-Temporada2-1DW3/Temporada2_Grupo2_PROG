package aplicacionFutbol;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import java.awt.FlowLayout;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JComboBox;

public class VentanaPrincipal extends JFrame implements ActionListener{

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JPanel panel;
	private JButton btnCrearUsuario;
	private JPanel panel_1;
	private JButton btnIniciarTemporada;
	private JComboBox cbTemporadas;
	private JButton btnVerTemporada;
	private String tempSelec;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					VentanaPrincipal frame = new VentanaPrincipal();
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
	public VentanaPrincipal() {
		setTitle("Venatana Principal");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 549, 385);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(new BorderLayout(0, 0));
		
		panel = new JPanel();
		contentPane.add(panel, BorderLayout.NORTH);
		panel.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		
		btnCrearUsuario = new JButton("Crear Usuario");
		btnCrearUsuario.addActionListener(this);
		panel.add(btnCrearUsuario);
		
		btnIniciarTemporada = new JButton("Iniciar Temporada");
		btnIniciarTemporada.addActionListener(this);
		panel.add(btnIniciarTemporada);
		
		panel_1 = new JPanel();
		contentPane.add(panel_1, BorderLayout.SOUTH);
		panel_1.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		
		cbTemporadas = new JComboBox();
		panel_1.add(cbTemporadas);
		cbTemporadas.addItem("2023");
		cbTemporadas.addItem("2024");
		cbTemporadas.setSelectedItem("2023");
		cbTemporadas.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent t) {
				// obtengo la temporada seleccionada
				tempSelec = (String) cbTemporadas.getSelectedItem();
				
			}
		});

		
		btnVerTemporada = new JButton("Ver Temporada");
		btnVerTemporada.addActionListener(this);
		panel_1.add(btnVerTemporada);
	}

	@Override
	public void actionPerformed(ActionEvent ae) {
		// TODO Auto-generated method stub
		// obtengo sobre que componente se ha realizado la accion
		Object o = ae.getSource();
		if (o == btnCrearUsuario) {
			// si se pulsa en introduccion de datos
			//VentanaHola vh = new VentanaHola();
			// la muestro
			//vh.setVisible(true);
			// oculto la ventana de inicio
			//this.setVisible(false);
		}else if (o == btnIniciarTemporada) {
			JOptionPane.showMessageDialog(this, (String) "crear temporada", "Error",JOptionPane.INFORMATION_MESSAGE);
		}else if (o == btnVerTemporada) {
			JOptionPane.showMessageDialog(this, (String) "visualizar temporada "+tempSelec, "Error",JOptionPane.INFORMATION_MESSAGE);
		}
		
	}

}
/* 
*/
