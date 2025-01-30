package aplicacionFutbol;

import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.io.EOFException;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;
import java.util.List;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import net.miginfocom.swing.MigLayout;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JFileChooser;
import javax.swing.ImageIcon;

public class VentanaGestionEquipos extends JFrame implements ActionListener, WindowListener {

    private static final long serialUID = 1L;
    private JPanel contentPane;
    private JButton btnAñadir;
    private JButton btnEliminar;
    private JButton btnAñadirEscudo;
    private JList<Equipo> lstEquipos;
    public static DefaultListModel<Equipo> dlm;
    private boolean modificado = false;
    private static boolean error = false;
    private boolean modificando = false;
    private static String carpetaDestino = "C:/MiAplicacion/imagenes/"; // Carpeta fija

    private JTextField textFieldNombre;
    private JTextField textFieldAno;
    private JLabel lblImagen;

    /**
     * Launch the application.
     */
    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    VentanaGestionEquipos frame = new VentanaGestionEquipos();
                    frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
                crearCarpetaCentral();
            }
        });
    }

    /**
     * Create the frame.
     */
    public VentanaGestionEquipos() {
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setBounds(100, 100, 552, 300);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

        setContentPane(contentPane);
        contentPane.setLayout(new BorderLayout(0, 0));

        JPanel head = new JPanel();
        contentPane.add(head, BorderLayout.NORTH);
        head.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));

        JLabel lblGestionEquipos = new JLabel("Gestión de equipos");
        head.add(lblGestionEquipos);

        JPanel body = new JPanel();
        contentPane.add(body, BorderLayout.CENTER);
        body.setLayout(new MigLayout("", "[37px,grow][grow][86px][84px][86px]", "[20px][][grow]"));

        JLabel lblNewLabel = new JLabel("Nombre");
        body.add(lblNewLabel, "flowx,cell 0 0,alignx left,aligny center");

        JLabel lblNewLabel_1 = new JLabel("Año de fundacion");
        body.add(lblNewLabel_1, "flowx,cell 1 0,alignx left,aligny center");

        btnAñadir = new JButton("Añadir");
        btnAñadir.addActionListener(this);
        body.add(btnAñadir, "flowx,cell 0 1,growx");

        btnEliminar = new JButton("Eliminar");
        btnEliminar.addActionListener(this);
        body.add(btnEliminar, "cell 0 1,growx");

        btnAñadirEscudo = new JButton("Añadir escudo");
        btnAñadirEscudo.addActionListener(this);
        body.add(btnAñadirEscudo, "cell 1 1");

        // ********************************
        // Modelo de la lista
        // ********************************
        // creo el modelo de datos de la lista
        dlm = new DefaultListModel<Equipo>();
        // creo la lista
        lstEquipos = new JList<>(dlm);
        // asocio el modelo de datos a la lista
        lstEquipos.setModel(dlm);
        // Permitir seleccion multiple
        lstEquipos.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
        body.add(lstEquipos, "cell 0 2,grow");

        textFieldNombre = new JTextField();
        body.add(textFieldNombre, "cell 0 0,alignx left,aligny top");
        textFieldNombre.setColumns(10);

        textFieldAno = new JTextField();
        body.add(textFieldAno, "cell 1 0,alignx left,aligny top");
        textFieldAno.setColumns(10);

        lblImagen = new JLabel("[Imagen]");
        body.add(lblImagen, "cell 2 2,alignx center");

        JPanel footer = new JPanel();
        contentPane.add(footer, BorderLayout.SOUTH);

        // Agregar WindowListener
        addWindowListener(this);

        cargarEquipos("Equipos.ser");

        // Añadir un ListSelectionListener para actualizar la imagen cuando se selecciona un equipo
        lstEquipos.addListSelectionListener(e -> {
            if (!e.getValueIsAdjusting()) {
                Equipo equipoSeleccionado = lstEquipos.getSelectedValue();
                if (equipoSeleccionado != null && equipoSeleccionado.getRutaImagen() != null) {
                    lblImagen.setIcon(new ImageIcon(equipoSeleccionado.getRutaImagen()));
                } else {
                    lblImagen.setIcon(null);
                    lblImagen.setText("[Imagen]");
                }
            }
        });
    }

    @Override
    public void windowOpened(WindowEvent e) {
        // TODO Auto-generated method stub
    }

    @Override
    public void windowClosing(WindowEvent e) {
        GrabarEquipos("Equipos.ser", dlm);
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
        Object o = ae.getSource();
        if (o == btnAñadir) {
            String nombre_equipo = textFieldNombre.getText().trim();
            String anodefundacionStr = textFieldAno.getText().trim();

            // Validar que ambos campos estén llenos
            if (nombre_equipo.isEmpty() || anodefundacionStr.isEmpty()) {
                JOptionPane.showMessageDialog(this, "No puede haber campos vacíos :(", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            // Validar que el nombre solo contenga letras y comience con mayúscula
            if (!nombre_equipo.matches("[A-Z][a-zA-Z]*")) {
                JOptionPane.showMessageDialog(this, "El nombre debe comenzar con una letra mayúscula y solo puede contener letras :(", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            // Validar que el año de fundación solo contenga números
            if (!anodefundacionStr.matches("\\d+")) {
                JOptionPane.showMessageDialog(this, "El año de fundación solo puede contener números :(", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            int anodefundacion = Integer.parseInt(anodefundacionStr);

            // Validar que no exista un equipo con el mismo nombre
            for (int i = 0; i < dlm.size(); i++) {
                if (dlm.get(i).getNombre().equalsIgnoreCase(nombre_equipo)) {
                    JOptionPane.showMessageDialog(this, "Ya existe un equipo con ese nombre :(", "Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }
            }

            // Validar que no se añadan más de 8 equipos
            if (dlm.size() >= 8) {
                JOptionPane.showMessageDialog(this, "No se pueden añadir más de 8 equipos :(", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            // Crear el objeto y añadirlo al dlm
            Equipo e = new Equipo(nombre_equipo, anodefundacion);
            dlm.addElement(e);

            // Limpiar los textfields
            textFieldNombre.setText("");
            textFieldAno.setText("");

            // Indicar que los valores han sido modificados
            GrabarEquipos("Equipos.ser", dlm);
        } else if (o == btnEliminar) {
        	  // Obtener los elementos seleccionados de la lista
            List<Equipo> ValSelec = lstEquipos.getSelectedValuesList();
            int[] IndSelec = lstEquipos.getSelectedIndices();

            if (IndSelec.length <= 0) {
                JOptionPane.showMessageDialog(this, "No hay valores seleccionados", "Error", JOptionPane.ERROR_MESSAGE);
            } else {
                // Eliminar los usuarios del dlm
                for (int i = IndSelec.length - 1; i >= 0; i--) {
                    dlm.removeElementAt(IndSelec[i]);
                }
                GrabarEquipos("Equipos.ser", dlm);
            }
        } else if (o == btnAñadirEscudo) {
            // Obtener los elementos seleccionados de la lista
            List<Equipo> ValSelec = lstEquipos.getSelectedValuesList();
            int[] IndSelec = lstEquipos.getSelectedIndices();

            // Comprobar que solo haya un elemento seleccionado
            if (IndSelec.length <= 0 || IndSelec.length > 1) {
                JOptionPane.showMessageDialog(this, "Debe seleccionar un equipo", "Error", JOptionPane.ERROR_MESSAGE);
            } else {
                /*
                 * ABRO EXPLORADOR DE ARCHIVOS
                 **/
                // Crear un JFileChooser
                JFileChooser fileChooser = new JFileChooser();

                // sera 1 cuando se pulsa cancelar o X y 0 cuando se pulsa abrir 
                int respuesta = fileChooser.showOpenDialog(null);
                // obtengo el objeto seleccionado 
                Equipo equipo = ValSelec.get(0);

                if (respuesta == JFileChooser.APPROVE_OPTION) {
                    /* OBTENGO DATOS DEL ARCHIVO SELECCIONADO */
                    // si pulsa abrir
                    // obtengo el archivo seleccionado
                    File selectedFile = new File(fileChooser.getSelectedFile().getAbsolutePath());
                    // obtengo el nombre del archivo
                    String fileName = fileChooser.getSelectedFile().getName();
                    // obtengo la ruta ORIGEN del archivo seleccionado
                    String filePath = selectedFile.getAbsolutePath();

                    /* OBTENGO DATOS DEL ARCHIVO DESTINO */
                    // creo la ruta DESTINO;
                    File archivoDestino = new File(carpetaDestino + fileName);
                    // obtengo el str de la ruta DESTINO
                    String archivoDestinoStr = archivoDestino.getAbsolutePath();

                    /* COMPRUEBO QUE NO HAYA UN EQUIPO CON ESA MISMA RUTA DE FOTO */
                    if (fotoRepe(archivoDestinoStr)) {
                        JOptionPane.showMessageDialog(null, "No puede tener dos equipos con la misma imagen.");
                    } else {
                        /* COPIO LA IMAGEN A LA CARPETA CENTRAL */
                        try {
                            // Copiar la imagen seleccionada a la carpeta central
                            Files.copy(selectedFile.toPath(), archivoDestino.toPath(), StandardCopyOption.REPLACE_EXISTING);

                            // Actualizar el JLabel con la imagen copiada
                            lblImagen.setIcon(new ImageIcon(archivoDestino.getAbsolutePath()));

                        } catch (IOException e) {
                            JOptionPane.showMessageDialog(null, "Error al copiar la imagen en " + carpetaDestino + ".");
                            e.printStackTrace();
                        }

                        // MUESTRO LA IMAGEN EN EL JFRAME
                        lblImagen.setText("");
                        lblImagen.setIcon(new ImageIcon(filePath));

                        // APLICO LA NUEVA RUTA AL EQUIPO
                        equipo.setRutaImagen(archivoDestinoStr);
                        // aplicamos los cambios en el dlm
                        dlm.set(IndSelec[0], equipo);
                        // aplicamos los cambios en el .ser
                        GrabarEquipos("Equipos.ser", dlm);
                    }
                }
            }
        }
    }

    /*
     * FUNCION DE CARGAR USUARIOS .SER ----> DLM
     * -------------------------------------------------------------------------
     */
    public static void cargarEquipos(String archivo) {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(archivo))) {
            // Leer el archivo objeto por objeto y agregarlo al DefaultListModel
            Object obj;
            while ((obj = ois.readObject()) != null) {
                if (obj instanceof Equipo) {
                    Equipo equipo = (Equipo) obj;
                    dlm.addElement(equipo);
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

    /*
     * GRABA LOS USUARIOS DLM ---> .SER
     * -------------------------------------------------------------------------
     */
    public static void GrabarEquipos(String nombreArchivo, DefaultListModel<Equipo> dlm) {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(nombreArchivo))) {
            for (int i = 0; i < dlm.size(); i++) {
                oos.writeObject(dlm.get(i)); // Escribir cada objeto
            }
            System.out.println("Lista guardada en " + nombreArchivo);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void crearCarpetaCentral() {
        File carpeta = new File(carpetaDestino);
        if (!carpeta.exists()) {
            if (carpeta.mkdirs()) {
                System.out.println("Carpeta creada en: " + carpetaDestino);
            } else {
                System.out.println("No se pudo crear la carpeta.");
            }
        }
    }

    public static boolean fotoRepe(String rutaImagenNueva) {
        String rutaImagenVieja;
        for (int i = 0; i < dlm.size(); i++) {
            rutaImagenVieja = dlm.get(i).getRutaImagen();
            if (rutaImagenVieja != null && rutaImagenVieja.equals(rutaImagenNueva)) {
                return true;
            }
        }
        return false;
    }

    public static DefaultListModel<Equipo> getDlm() {
        if (dlm == null) {
            dlm = new DefaultListModel<>();
        }
        return dlm;
    }
    
	public static void setDlm(DefaultListModel<Equipo> dlm) {
		VentanaGestionEquipos.dlm = dlm;
	}
    
    

        }