package aplicacionfutbolsala;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.BorderLayout;
import java.awt.FlowLayout;
import javax.swing.JLabel;
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

public class CrearUsuario extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;

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
		
		JLabel lblRol = new JLabel("Rol del nuevo Usuario: ");
		panelHead.add(lblRol);
		
		JComboBox cbRoles = new JComboBox();
		cbRoles.addItem("Admin");
		cbRoles.addItem("Arbitro");
		cbRoles.addItem("Usuario");
		cbRoles.setSelectedItem("Usuario");
		panelHead.add(cbRoles);
		
		JPanel panelBody = new JPanel();
		contentPane.add(panelBody, BorderLayout.SOUTH);
		GridBagLayout gbl_panelBody = new GridBagLayout();
		gbl_panelBody.columnWidths = new int[]{426, 0};
		gbl_panelBody.rowHeights = new int[]{203, 0, 0, 0};
		gbl_panelBody.columnWeights = new double[]{1.0, Double.MIN_VALUE};
		gbl_panelBody.rowWeights = new double[]{0.0, 0.0, 1.0, Double.MIN_VALUE};
		panelBody.setLayout(gbl_panelBody);
	}

}
