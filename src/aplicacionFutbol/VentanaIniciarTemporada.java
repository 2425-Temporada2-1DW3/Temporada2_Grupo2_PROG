package aplicacionFutbol;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.io.EOFException;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

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
import javax.swing.DefaultListModel;

public class VentanaIniciarTemporada extends JFrame implements ActionListener, WindowListener {

    private static final long serialVersionUID = 1L;
    private static DefaultListModel<Equipo> equipos;
    private static List<Temporada> temporadas = new ArrayList<>();
    public static JPanel contentPane;
    public static JTextField anioTemporada;
    private JComboBox<Equipo> comboBox;
    private JComboBox<Equipo> comboBox_1;
    private JComboBox<Equipo> comboBox_2;
    private JComboBox<Equipo> comboBox_3;
    private JComboBox<Equipo> comboBox_4;
    private JComboBox<Equipo> comboBox_5;
    private JButton btnNewButton;
    public static Temporadas tmps;

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
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
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

        anioTemporada = new JTextField();
        body.add(anioTemporada, "cell 1 0,width 50:100:200,height 25:50:75");
        anioTemporada.setColumns(10);

        comboBox = new JComboBox<>();
        body.add(comboBox, "cell 1 1,growx,aligny top");

        comboBox_1 = new JComboBox<>();
        body.add(comboBox_1, "cell 1 2,growx");

        comboBox_2 = new JComboBox<>();
        body.add(comboBox_2, "cell 1 3,growx");

        comboBox_3 = new JComboBox<>();
        body.add(comboBox_3, "cell 1 4,growx");

        comboBox_4 = new JComboBox<>();
        body.add(comboBox_4, "cell 1 5,growx");

        comboBox_5 = new JComboBox<>();
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

        // Inicializar el DefaultListModel y cargar equipos en los JComboBox
        equipos = new DefaultListModel<>();
        cargarEquipos("Equipos.ser");
        cargarTemporadas("Temporadas.ser");
        cargarEquiposEnComboBox();
        

        // Añadir ActionListener a los JComboBox
        comboBox.addActionListener(this);
        comboBox_1.addActionListener(this);
        comboBox_2.addActionListener(this);
        comboBox_3.addActionListener(this);
        comboBox_4.addActionListener(this);
        comboBox_5.addActionListener(this);
    }
    
    private void cargarEquiposEnComboBox() {
        // Añadir una opción vacía a cada JComboBox
        comboBox.addItem(null);
        comboBox_1.addItem(null);
        comboBox_2.addItem(null);
        comboBox_3.addItem(null);
        comboBox_4.addItem(null);
        comboBox_5.addItem(null);

        int size = equipos.getSize();
        for (int i = 0; i < size; i++) {
            Equipo equipo = equipos.getElementAt(i);
            comboBox.addItem(equipo);
            comboBox_1.addItem(equipo);
            comboBox_2.addItem(equipo);
            comboBox_3.addItem(equipo);
            comboBox_4.addItem(equipo);
            comboBox_5.addItem(equipo);
        }
    }

    private void actualizarComboBox() {
        Set<Equipo> seleccionados = new HashSet<>();
        if (comboBox.getSelectedItem() != null) seleccionados.add((Equipo) comboBox.getSelectedItem());
        if (comboBox_1.getSelectedItem() != null) seleccionados.add((Equipo) comboBox_1.getSelectedItem());
        if (comboBox_2.getSelectedItem() != null) seleccionados.add((Equipo) comboBox_2.getSelectedItem());
        if (comboBox_3.getSelectedItem() != null) seleccionados.add((Equipo) comboBox_3.getSelectedItem());
        if (comboBox_4.getSelectedItem() != null) seleccionados.add((Equipo) comboBox_4.getSelectedItem());
        if (comboBox_5.getSelectedItem() != null) seleccionados.add((Equipo) comboBox_5.getSelectedItem());

        actualizarComboBox(comboBox, seleccionados);
        actualizarComboBox(comboBox_1, seleccionados);
        actualizarComboBox(comboBox_2, seleccionados);
        actualizarComboBox(comboBox_3, seleccionados);
        actualizarComboBox(comboBox_4, seleccionados);
        actualizarComboBox(comboBox_5, seleccionados);
    }

    private void actualizarComboBox(JComboBox<Equipo> comboBox, Set<Equipo> seleccionados) {
        Equipo seleccionado = (Equipo) comboBox.getSelectedItem();
        comboBox.removeAllItems();
        comboBox.addItem(null); // Añadir opción vacía
        for (int i = 0; i < equipos.size(); i++) {
            Equipo equipo = equipos.getElementAt(i);
            if (!seleccionados.contains(equipo) || equipo.equals(seleccionado)) {
                comboBox.addItem(equipo);
            }
        }
        comboBox.setSelectedItem(seleccionado);
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
            if (anioTemporada.getText().isEmpty() || 
                !anioTemporada.getText().matches("\\d+") || // Validar que el campo de texto solo contiene números
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
                    // Crear el objeto Temporada y almacenarlo en la lista
                    int ano = Integer.parseInt(anioTemporada.getText().trim());
                    List<Equipo> equiposSeleccionados = new ArrayList<>();
                    equiposSeleccionados.add((Equipo) comboBox.getSelectedItem());
                    equiposSeleccionados.add((Equipo) comboBox_1.getSelectedItem());
                    equiposSeleccionados.add((Equipo) comboBox_2.getSelectedItem());
                    equiposSeleccionados.add((Equipo) comboBox_3.getSelectedItem());
                    equiposSeleccionados.add((Equipo) comboBox_4.getSelectedItem());
                    equiposSeleccionados.add((Equipo) comboBox_5.getSelectedItem());

                    Temporada temporada = new Temporada(ano, equiposSeleccionados);
                    temporadas.add(temporada);
                    // generamos un nuevo objeto con todas las temporadas.
                    tmps = new Temporadas(temporadas);
                    
                    guardarTemporadas("Temporadas.ser");

                    // Abrir la siguiente ventana para mostrar los equipos de la temporada
                    VentanaAnadirJugadores ventanaMostrarEquipos = new VentanaAnadirJugadores(temporada); // Crear instancia de la ventana de resultados
                    ventanaMostrarEquipos.setVisible(true); // Hacer visible la ventana
    				
                    this.dispose();
                } else if (result == JOptionPane.NO_OPTION) {
                    // Acción para el botón "Cancelar" (cerrar el pop-up)
                }
            }
        } else {
            actualizarComboBox();
        }
    }

    public static void cargarEquipos(String archivo) {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(archivo))) {
            // Leer el archivo objeto por objeto y agregarlo al DefaultListModel
            Object obj;
            while ((obj = ois.readObject()) != null) {
                if (obj instanceof Equipo) {
                    Equipo equipo = (Equipo) obj;
                    equipos.addElement(equipo);
                }
            }
        } catch (EOFException e) {
            // Se alcanza el final del archivo, esta excepción es normal cuando termina la
            // lectura
            System.out.println("Archivo cargado completamente.");
        } catch (FileNotFoundException e) {
            System.err.println("El archivo no existe: " + archivo);
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public static void guardarTemporadas(String archivo) {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(archivo))) {
            oos.writeObject(temporadas);
            System.out.println("Temporadas guardadas en " + archivo);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
	/*
	 * FUNCION DE CARGAR USUARIOS .SER ----> DLM
	 *---------------------------------------------------------------------------------------- */
	public void cargarTemporadas(String archivo) {
		 try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(archivo))) {
	            // Leer el archivo objeto por objeto y agregarlo al DefaultListModel
	            Object obj;
	            while ((obj = ois.readObject()) != null) {
	                if (obj instanceof Temporada) {
	                	Temporada temp = (Temporada) obj;
	                    temporadas.add(temp);
	                }
	            }
	        } catch (EOFException e) {
	            // Se alcanza el final del archivo, esta excepción es normal cuando termina la lectura
	            System.out.println("Archivo cargado completamente :(");
	        } catch (FileNotFoundException e) {
	            System.err.println("El archivo no existe: " + archivo);
	        } catch (IOException | ClassNotFoundException e) {
	            e.printStackTrace();
	        }
	}
}