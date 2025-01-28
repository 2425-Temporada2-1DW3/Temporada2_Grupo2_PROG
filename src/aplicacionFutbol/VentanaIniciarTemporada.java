package aplicacionFutbol;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.BorderLayout;
import javax.swing.JLabel;
import net.miginfocom.swing.MigLayout;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import javax.swing.JButton;
import javax.swing.JOptionPane;

public class VentanaIniciarTemporada extends JFrame implements ActionListener, WindowListener{

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField textField;
	private JComboBox comboBox;
	private JComboBox comboBox_1;
	private JComboBox comboBox_2;
	private JComboBox comboBox_3;
	private JComboBox comboBox_4;
	private JComboBox comboBox_5;
	private JButton btnNewButton;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					VentanaIniciarTemporada frame = new VentanaIniciarTemporada();
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
	public VentanaIniciarTemporada() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 342);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(new BorderLayout(0, 0));
		
		JPanel body = new JPanel();
		contentPane.add(body, BorderLayout.CENTER);
		body.setLayout(new MigLayout("", "[45px][360px,grow]", "[19px][21px][][][][][][]"));
		
		JLabel lblNewLabel = new JLabel("Año:");
		body.add(lblNewLabel, "cell 0 0,alignx right,aligny center");
		
		textField = new JTextField();
		body.add(textField, "cell 1 0,width 50:100:200,height 25:50:75");
		textField.setColumns(10);
		
		comboBox = new JComboBox();
		body.add(comboBox, "cell 1 1,growx,aligny top");
		
		comboBox_1 = new JComboBox();
		body.add(comboBox_1, "cell 1 2,growx");
		
		comboBox_2 = new JComboBox();
		body.add(comboBox_2, "cell 1 3,growx");
		
		comboBox_3 = new JComboBox();
		body.add(comboBox_3, "cell 1 4,growx");
		
		comboBox_4 = new JComboBox();
		body.add(comboBox_4, "cell 1 5,growx");
		
		comboBox_5 = new JComboBox();
		body.add(comboBox_5, "cell 1 6,growx");
		
		btnNewButton = new JButton("Siguiente");
		btnNewButton.addActionListener(this);
		body.add(btnNewButton, "cell 1 7,alignx right");
		
		JPanel header = new JPanel();
		contentPane.add(header, BorderLayout.NORTH);
		
		JLabel lblTxtExplicativo = new JLabel("Selecciona 6 equipos para comenzar la temporada");
		header.add(lblTxtExplicativo);
		
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
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == btnNewButton) {
			if (textField.getText().isEmpty() || 
				!textField.getText().matches("\\d+") || // Validar que el campo de texto solo contiene números
				comboBox.getSelectedItem() == null || 
				comboBox_1.getSelectedItem() == null || 
				comboBox_2.getSelectedItem() == null || 
				comboBox_3.getSelectedItem() == null || 
				comboBox_4.getSelectedItem() == null || 
				comboBox_5.getSelectedItem() == null) {
				
				JOptionPane.showMessageDialog(this, "Por favor, rellena todos los campos con valores válidos antes de continuar.", "Error", JOptionPane.ERROR_MESSAGE);
			} else {
				int result = JOptionPane.showOptionDialog(this,
					"¿Seguro que estos son los equipos que quieres seleccionar para esta nueva temporada? Después de guardar los equipos no se podrán editar en un futuro.",
					"Confirmación",
					JOptionPane.YES_NO_OPTION,
					JOptionPane.WARNING_MESSAGE,
					null,
					new Object[] {"Guardar", "Cancelar"},
					"Guardar");
				
				if (result == JOptionPane.YES_OPTION) {
					// Acción para el botón "Guardar" (sin función de momento)
				} else if (result == JOptionPane.NO_OPTION) {
					// Acción para el botón "Cancelar" (cerrar el pop-up)
				}
			}
		}
	}
}