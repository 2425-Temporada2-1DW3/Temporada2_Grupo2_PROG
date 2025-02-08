package aplicacionFutbol;

import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.util.*;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.xml.parsers.*;
import javax.xml.transform.*;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import org.w3c.dom.*;
import com.itextpdf.text.*;
import com.itextpdf.text.pdf.*;

public class VentanaMain extends JFrame {
    private static final long serialVersionUID = 16765566646456546L;
    private JPanel contentPane;
    public int temporadaActual;
    String temporada;
    public static int jornadaEnJuego = 0;
    public static ArrayList<Partido> matrizJornadas = new ArrayList<>();
    private JLabel lblLocal_1 = new JLabel("Local 1");
    private JLabel lblVisitante_1 = new JLabel("Visitante 1");
    private JLabel lblLocal_2 = new JLabel("Local 2");
    private JLabel lblVisitante_2 = new JLabel("Visitante 2");
    private JLabel lblLocal_3 = new JLabel("Local 3");
    private JLabel lblVisitante_3 = new JLabel("Visitante 3");
    private List<int[][]> resultados;
    private JTextField golesLocal_1 = new JTextField(5);
    private JTextField golesVisitante_1 = new JTextField(5);
    private JTextField golesLocal_2 = new JTextField(5);
    private JTextField golesVisitante_2 = new JTextField(5);
    private JTextField golesLocal_3 = new JTextField(5);
    private JTextField golesVisitante_3 = new JTextField(5);
    String[] equipos = { "athletic", "Barcelona", "Madrid", "Alaves", "Osasuna", "Eibar" };
    private int jornadaActual = 0;
    private List<String[][]> jornadas;
    private JTable tablaClasificacion;
    private DefaultTableModel modeloTablaClasificacion;
    private List<Equipo> listaEquipos; // Definición de listaEquipos
    private boolean modoSoloLectura = false;
    private final JComboBox<String> comboBox = new JComboBox<>();
    private JLabel lblRol = new JLabel();
    Partido partido;
    private static final JComboBox<String> cbTemporadas = new JComboBox<>();
    private final JButton btnIniciarTemporada = new JButton("Iniciar temporada");
    private final JPanel panel_2 = new JPanel();
    private final JPanel panel_3 = new JPanel();
    private final JButton btnEquipos = new JButton("Equipos");
    private final JButton btnUsuarios = new JButton("Usuarios");
    private final JLabel lbltemporada = new JLabel("Temporada ");
    private static String[][][] matrizEquipos = {
        {
            {"1", "2023", "2023", "2023", "2023", "2023"},
            {"2", "2023", "2023", "2023", "2023", "2023"},
            {"3", "2023", "2023", "2023", "2023", "2023"},
            {"4", "2023", "2023", "2023", "2023", "2023"},
            {"5", "2023", "2023", "2023", "2023", "2023"},
            {"6", "2023", "2023", "2023", "2023", "2023"}
        },
        {
            {"1", "", "", "", "", ""},
            {"2", "", "", "", "", ""},
            {"3", "", "", "", "", ""},
            {"4", "", "", "", "", ""},
            {"5", "", "", "", "", ""},
            {"6", "", "", "", "", ""}
        },
    };
    public static String[] temporadas = {"2023", "2024"};
    private boolean[] resultadosGuardados = new boolean[10];
    private final JLabel lblNewLabel = new JLabel("vs");
    private final JLabel lblNewLabel_1 = new JLabel("vs");
    private final JLabel lblNewLabel_2 = new JLabel("vs");
    private final JLabel lblNewLabel_4 = new JLabel(" ");
    private final JLabel lblNewLabel_5 = new JLabel(" ");
    private final JLabel lblNewLabel_6 = new JLabel(" ");
    private final JLabel lblNewLabel_7 = new JLabel(" ");
    private final JLabel lblNewLabel_8 = new JLabel(" ");
    private final JLabel lblNewLabel_9 = new JLabel(" ");
    private final JLabel lblNewLabel_10 = new JLabel(" ");
    private final JPanel panel_1_1 = new JPanel();

    // Método para cargar el escudo de un equipo dado su nombre
    private ImageIcon cargarEscudo(String equipo) {
        String ruta = "src/imagenes/" + equipo + ".png";
        ImageIcon iconoOriginal = new ImageIcon(Toolkit.getDefaultToolkit().getImage(ruta));
        Image imagenRedimensionada = iconoOriginal.getImage().getScaledInstance(20, 20, Image.SCALE_SMOOTH);
        return new ImageIcon(imagenRedimensionada);
    }

    // Método para mostrar la jornada actual en la interfaz
    private void mostrarJornadaActual() {
        if (jornadas == null || jornadas.isEmpty()) {
            return;
        }
        String[][] partidos = jornadas.get(jornadaActual);
        cargarJornadas("Jornadas.ser");

        lblLocal_1.setText(partidos[0][0]);
        lblLocal_1.setIcon(cargarEscudo(partidos[0][0]));
        lblVisitante_1.setText(partidos[0][1]);
        lblVisitante_1.setIcon(cargarEscudo(partidos[0][1]));
        lblLocal_2.setText(partidos[1][0]);
        lblLocal_2.setIcon(cargarEscudo(partidos[1][0]));
        lblVisitante_2.setText(partidos[1][1]);
        lblVisitante_2.setIcon(cargarEscudo(partidos[1][1]));
        lblLocal_3.setText(partidos[2][0]);
        lblLocal_3.setIcon(cargarEscudo(partidos[2][0]));
        lblVisitante_3.setText(partidos[2][1]);
        lblVisitante_3.setIcon(cargarEscudo(partidos[2][1]));

        boolean fill = false;
        for (int i = 0; i < matrizJornadas.size(); i++) {
            partido = matrizJornadas.get(i);
            if (partido.getJornadaNumero() == jornadaActual + 1) {
                if ((partido.getTemporadaNumero() == cbTemporadas.getSelectedIndex())) {
                    golesLocal_1.setText(String.valueOf(partido.getMarcadorLocal_1()));
                    golesVisitante_1.setText(String.valueOf(partido.getMarcadorVisitante_1()));
                    golesLocal_2.setText(String.valueOf(partido.getMarcadorLocal_2()));
                    golesVisitante_2.setText(String.valueOf(partido.getMarcadorVisitante_2()));
                    golesLocal_3.setText(String.valueOf(partido.getMarcadorLocal_3()));
                    golesVisitante_3.setText(String.valueOf(partido.getMarcadorVisitante_3()));
                    fill = true;
                    break;
                }
            }
        }

        if (!fill) {
            golesLocal_1.setText("");
            golesVisitante_1.setText("");
            golesLocal_2.setText("");
            golesVisitante_2.setText("");
            golesLocal_3.setText("");
            golesVisitante_3.setText("");
        }
        CambiarJornadaEditable();
    }

    // Método para generar los partidos de la liga
    public void generarPartidos() {
        int numEquipos = equipos.length;
        int numJornadas = 10;
        jornadas = new ArrayList<>();
        resultados = new ArrayList<>();
        listaEquipos = new ArrayList<>();
        for (String equipo : equipos) {
            listaEquipos.add(new Equipo(equipo));
        }
        for (int jornada = 0; jornada < numJornadas; jornada++) {
            String[][] partidos = new String[numEquipos / 2][2];
            int[][] goles = new int[numEquipos / 2][2];
            for (int partido = 0; partido < numEquipos / 2; partido++) {
                int equipoLocal = (jornada + partido) % (numEquipos - 1);
                int equipoVisitante = (numEquipos - 1 - partido + jornada) % (numEquipos - 1);
                if (partido == 0) {
                    equipoVisitante = numEquipos - 1;
                }
                partidos[partido][0] = equipos[equipoLocal];
                partidos[partido][1] = equipos[equipoVisitante];
            }
            jornadas.add(partidos);
            resultados.add(goles);
        }
    }

    // Método para guardar los resultados de los partidos
    private void guardarResultados() {
        if (resultadosGuardados[jornadaActual]) {
            JOptionPane.showMessageDialog(this, "Los resultados de la jornada " + (jornadaActual + 1) + " ya han sido guardados.");
            return;
        }
        String[][] partidos = jornadas.get(jornadaActual);
        boolean partidoJugado = false;
        for (int i = 0; i < 3; i++) {
            int resultadoLocal = 0;
            int resultadoVisitante = 0;
            try {
                if (i == 0) {
                    if (golesLocal_1.getText().isEmpty() && golesVisitante_1.getText().isEmpty()) {
                        JOptionPane.showMessageDialog(this, "Los equipos " + partidos[i][0] + " y " + partidos[i][1] + " no han jugado.");
                        return;
                    }
                    resultadoLocal = Integer.parseInt(golesLocal_1.getText());
                    resultadoVisitante = Integer.parseInt(golesVisitante_1.getText());
                } else if (i == 1) {
                    if (golesLocal_2.getText().isEmpty() && golesVisitante_2.getText().isEmpty()) {
                        JOptionPane.showMessageDialog(this, "Los equipos " + partidos[i][0] + " y " + partidos[i][1] + " no han jugado.");
                        return;
                    }
                    resultadoLocal = Integer.parseInt(golesLocal_2.getText());
                    resultadoVisitante = Integer.parseInt(golesVisitante_2.getText());
                } else if (i == 2) {
                    if (golesLocal_3.getText().isEmpty() && golesVisitante_3.getText().isEmpty()) {
                        JOptionPane.showMessageDialog(this, "Los equipos " + partidos[i][0] + " y " + partidos[i][1] + " no han jugado.");
                        return;
                    }
                    resultadoLocal = Integer.parseInt(golesLocal_3.getText());
                    resultadoVisitante = Integer.parseInt(golesVisitante_3.getText());
                }
                resultados.get(jornadaActual)[i][0] = resultadoLocal;
                resultados.get(jornadaActual)[i][1] = resultadoVisitante;
                actualizarEquipos(partidos[i][0], resultadoLocal, resultadoVisitante);
                actualizarEquipos(partidos[i][1], resultadoVisitante, resultadoLocal);
                partidoJugado = true;
            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(this, "Por favor, ingresa números válidos para los goles.");
                return;
            }
        }
        if (partidoJugado) {
            int opcion = JOptionPane.showConfirmDialog(this, "¿Seguro que quiere guardar los resultados para esta jornada?", "Info", JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE, null);
            switch (opcion) {
                case JOptionPane.YES_OPTION:
                    actualizarTablaClasificacion();
                    jornadaEnJuego = jornadaActual + 1;
                    JOptionPane.showMessageDialog(this, "Resultados guardados correctamente.");
                    resultadosGuardados[jornadaActual] = true;
                    CambiarJornadaEditable();
                    generarJornadasXML(temporadaActual, jornadaEnJuego);
                    generarXML();
                    break;
                case JOptionPane.NO_OPTION:
                case JOptionPane.CANCEL_OPTION:
                case JOptionPane.CLOSED_OPTION:
            }
        }
    }

    // Método para generar el archivo XML de las jornadas
    private void generarJornadasXML(int temporada, int jornada) {
        partido = new Partido(temporada, jornada, Integer.parseInt(golesLocal_1.getText()), Integer.parseInt(golesVisitante_1.getText()), Integer.parseInt(golesLocal_2.getText()), Integer.parseInt(golesVisitante_2.getText()), Integer.parseInt(golesLocal_3.getText()), Integer.parseInt(golesVisitante_3.getText()));
        matrizJornadas.add(partido);
        GrabarJornadas("Jornadas.ser");
    }

    // Método para generar el archivo XML de la clasificación
    private void generarXML() {
        for (int i = 0; i < listaEquipos.size(); i++) {
            matrizEquipos[temporadaActual][i][0] = String.valueOf(i + 1);
            matrizEquipos[temporadaActual][i][1] = listaEquipos.get(i).getNombre();
            matrizEquipos[temporadaActual][i][2] = String.valueOf(listaEquipos.get(i).getPuntos());
            matrizEquipos[temporadaActual][i][3] = String.valueOf(listaEquipos.get(i).getGolesFavor());
            matrizEquipos[temporadaActual][i][4] = String.valueOf(listaEquipos.get(i).getGolesContra());
            matrizEquipos[temporadaActual][i][5] = String.valueOf(listaEquipos.get(i).getDiferenciaGoles());
        }

        try {
            DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder docBuilder = docFactory.newDocumentBuilder();
            Document doc = docBuilder.newDocument();
            Element rootElement = doc.createElement("liga");
            doc.appendChild(rootElement);
            for (int t = 0; t < matrizEquipos.length; t++) {
                Element temporada = doc.createElement("temporada");
                rootElement.appendChild(temporada);
                Element nombreTemporada = doc.createElement("nombre");
                nombreTemporada.appendChild(doc.createTextNode(temporadas[t]));
                temporada.appendChild(nombreTemporada);
                for (String[] row : matrizEquipos[t]) {
                    Element equipo = doc.createElement("equipo");
                    temporada.appendChild(equipo);
                    Element posicion = doc.createElement("ranking");
                    posicion.appendChild(doc.createTextNode(row[0]));
                    equipo.appendChild(posicion);
                    Element nombre = doc.createElement("nombre");
                    nombre.appendChild(doc.createTextNode(row[1]));
                    equipo.appendChild(nombre);
                    Element puntos = doc.createElement("puntos");
                    puntos.appendChild(doc.createTextNode(row[2]));
                    equipo.appendChild(puntos);
                    Element golesFavor = doc.createElement("golesFavor");
                    golesFavor.appendChild(doc.createTextNode(row[3]));
                    equipo.appendChild(golesFavor);
                    Element golesContra = doc.createElement("golesContra");
                    golesContra.appendChild(doc.createTextNode(row[4]));
                    equipo.appendChild(golesContra);
                    Element diferenciaGoles = doc.createElement("diferenciaGoles");
                    diferenciaGoles.appendChild(doc.createTextNode(row[5]));
                    equipo.appendChild(diferenciaGoles);
                }
            }
            String filePath = "C:\\xampp\\htdocs\\Temporada2_Grupo2_LM\\HTML\\clasificacion.xml";
            TransformerFactory transformerFactory = TransformerFactory.newInstance();
            Transformer transformer = transformerFactory.newTransformer();
            DOMSource source = new DOMSource(doc);
            StreamResult result = new StreamResult(new File(filePath));
            transformer.transform(source, result);
            System.out.println("Archivo XML guardado en " + filePath);
        } catch (ParserConfigurationException | TransformerException e) {
            e.printStackTrace();
        }
    }

    // Método para actualizar los datos de los equipos después de un partido
    private void actualizarEquipos(String equipoNombre, int golesFavor, int golesContra) {
        for (Equipo equipo : listaEquipos) {
            if (equipo.getNombre().equals(equipoNombre)) {
                equipo.agregarResultado(golesFavor, golesContra);
                if (golesFavor > golesContra) {
                    equipo.agregarPuntos(3);
                } else if (golesFavor == golesContra) {
                    equipo.agregarPuntos(1);
                }
                break;
            }
        }
    }

    // Método principal que inicia la aplicación
    public static void main(String[] args) {
        EventQueue.invokeLater(() -> {
            try {
                String RolSesion = "Admin";
                VentanaMain frame = new VentanaMain(RolSesion);
                frame.setVisible(true);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }

    // Constructor de la clase VentanaResultados
    public VentanaMain(String RolSesion) {
        setTitle("Resultados y clasificación");
        setIconImage(Toolkit.getDefaultToolkit().getImage("src/imagenes/balon.png"));
        this.modoSoloLectura = RolSesion.equals("Usuario");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 900, 700);
        contentPane = new JPanel();
        contentPane.setBackground(new Color(240, 240, 240));
        contentPane.setBorder(new EmptyBorder(10, 10, 10, 10));
        setContentPane(contentPane);
        contentPane.setLayout(new BorderLayout(0, 0));
        JPanel izquierda = new JPanel();
        izquierda.setBackground(new Color(240, 240, 240));
        contentPane.add(izquierda, BorderLayout.WEST);
        izquierda.setLayout(new BorderLayout(0, 0));
        JPanel panel = new JPanel();
        panel.setBackground(new Color(240, 240, 240));
        izquierda.add(panel, BorderLayout.NORTH);

        for (int i = 1; i <= 10; i++) {
            comboBox.addItem("Jornada " + i);
        }
        comboBox.addActionListener(e -> {
            jornadaActual = comboBox.getSelectedIndex();
            mostrarJornadaActual();
        });

        JButton btnAnterior = new JButton("");
        btnAnterior.setIcon(new ImageIcon("C:\\Users\\ik_1dw3a\\Documents\\GitHub\\Temporada2_Grupo2_PROG\\media\\left.png"));
        btnAnterior.setBackground(new Color(255, 255, 255));
        btnAnterior.setForeground(Color.WHITE);
        btnAnterior.addActionListener(e -> {
            jornadaActual = Math.max(jornadaActual - 1, 0);
            actualizarComboBox();
        });

        panel.add(btnAnterior);
        panel.add(comboBox);
        JButton btnSiguiente = new JButton("");
        btnSiguiente.setIcon(new ImageIcon("C:\\Users\\ik_1dw3a\\Documents\\GitHub\\Temporada2_Grupo2_PROG\\media\\right.png"));
        btnSiguiente.setBackground(new Color(255, 255, 255));
        btnSiguiente.setForeground(Color.WHITE);
        btnSiguiente.addActionListener(e -> {
            jornadaActual = Math.min(jornadaActual + 1, jornadas.size() - 1);
            actualizarComboBox();
        });

        panel.add(btnSiguiente);
        JPanel panel_1 = new JPanel();
        panel_1.setBackground(new Color(240, 240, 240));
        izquierda.add(panel_1, BorderLayout.CENTER);
        panel_1.setLayout(new BoxLayout(panel_1, BoxLayout.Y_AXIS));
        panel_1.add(lblNewLabel_8);
        panel_1.add(lblLocal_1);
        golesLocal_1.setEditable(!modoSoloLectura);
        golesLocal_1.addFocusListener(new FocusAdapter() {
            public void focusGained(FocusEvent e) {
                golesLocal_1.selectAll();
            }
        });
        panel_1.add(golesLocal_1);
        golesVisitante_1.setEditable(!modoSoloLectura);
        golesVisitante_1.addFocusListener(new FocusAdapter() {
            public void focusGained(FocusEvent e) {
                golesVisitante_1.selectAll();
            }
        });
        panel_1.add(lblNewLabel);
        panel_1.add(golesVisitante_1);
        panel_1.add(lblVisitante_1);
        panel_1.add(lblNewLabel_4);
        panel_1.add(lblLocal_2);
        golesLocal_2.setEditable(!modoSoloLectura);
        golesLocal_2.addFocusListener(new FocusAdapter() {
            public void focusGained(FocusEvent e) {
                golesLocal_2.selectAll();
            }
        });
        panel_1.add(golesLocal_2);
        golesVisitante_2.setEditable(!modoSoloLectura);
        golesVisitante_2.addFocusListener(new FocusAdapter() {
            public void focusGained(FocusEvent e) {
                golesVisitante_2.selectAll();
            }
        });
        panel_1.add(lblNewLabel_1);
        panel_1.add(golesVisitante_2);
        panel_1.add(lblVisitante_2);
        panel_1.add(lblNewLabel_5);
        panel_1.add(lblNewLabel_6);
        panel_1.add(lblLocal_3);
        golesLocal_3.setEditable(!modoSoloLectura);
        golesLocal_3.addFocusListener(new FocusAdapter() {
            public void focusGained(FocusEvent e) {
                golesLocal_3.selectAll();
            }
        });
        panel_1.add(golesLocal_3);
        golesVisitante_3.setEditable(!modoSoloLectura);
        golesVisitante_3.addFocusListener(new FocusAdapter() {
            public void focusGained(FocusEvent e) {
                golesVisitante_3.selectAll();
            }
        });
        panel_1.add(lblNewLabel_2);
        panel_1.add(golesVisitante_3);
        panel_1.add(lblVisitante_3);
        JButton btnGuardar = new JButton("Guardar Resultados");
        btnGuardar.setBackground(new Color(0, 120, 215));
        btnGuardar.setForeground(Color.WHITE);
        btnGuardar.setEnabled(!modoSoloLectura);
        btnGuardar.addActionListener(e -> guardarResultados());
        panel_1.add(lblNewLabel_7);
        panel_1.add(btnGuardar);
        JButton btnRestablecer = new JButton("Restablecer");
        btnRestablecer.setBackground(new Color(0, 120, 215));
        btnRestablecer.setForeground(Color.WHITE);
        btnRestablecer.addActionListener(e -> {
            int opcion = JOptionPane.showConfirmDialog(this, "¿Seguro que quiere restablecer la temporada?", "Info", JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE, null);
            switch (opcion) {
                case JOptionPane.YES_OPTION:
                    golesLocal_1.setText("");
                    golesVisitante_1.setText("");
                    golesLocal_2.setText("");
                    golesVisitante_2.setText("");
                    golesLocal_3.setText("");
                    golesVisitante_3.setText("");
                    jornadaEnJuego = 0;
                    Arrays.fill(resultadosGuardados, false);
                    CambiarJornadaEditable();
                    generarXML();
                    break;
                case JOptionPane.NO_OPTION:
                case JOptionPane.CANCEL_OPTION:
                case JOptionPane.CLOSED_OPTION:
            }
        });
        agregarValidacionJornada(golesLocal_1);
        agregarValidacionJornada(golesVisitante_1);
        agregarValidacionJornada(golesLocal_2);
        agregarValidacionJornada(golesVisitante_2);
        agregarValidacionJornada(golesLocal_3);
        agregarValidacionJornada(golesVisitante_3);

        if (modoSoloLectura) {
            btnRestablecer.setEnabled(false);
        }
        panel_1.add(lblNewLabel_9);
        panel_1.add(btnRestablecer);
        JButton btnAtras = new JButton("Salir");
        btnAtras.setBackground(new Color(0, 120, 215));
        btnAtras.setForeground(Color.WHITE);
        btnAtras.addActionListener(e -> System.exit(EXIT_ON_CLOSE));
        panel_1.add(lblNewLabel_10);
        panel_1.add(btnAtras);

        modeloTablaClasificacion = new DefaultTableModel();
        modeloTablaClasificacion.addColumn("Posición");
        modeloTablaClasificacion.addColumn("Equipo");
        modeloTablaClasificacion.addColumn("Puntos");
        modeloTablaClasificacion.addColumn("Goles a Favor");
        modeloTablaClasificacion.addColumn("Goles en Contra");
        modeloTablaClasificacion.addColumn("Diferencia de Goles");
        tablaClasificacion = new JTable(modeloTablaClasificacion);
        tablaClasificacion.setEnabled(false);
        tablaClasificacion.setFillsViewportHeight(true);
        tablaClasificacion.setBackground(new Color(255, 255, 255));
        tablaClasificacion.setForeground(new Color(0, 0, 0));
        tablaClasificacion.setFont(new Font("Arial", Font.PLAIN, 14));
        JScrollPane scrollPane = new JScrollPane(tablaClasificacion);
        contentPane.add(scrollPane, BorderLayout.CENTER);
        panel_1_1.setAlignmentY(Component.BOTTOM_ALIGNMENT);

        contentPane.add(panel_1_1, BorderLayout.NORTH);
        panel_1_1.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));

        panel_1_1.add(lbltemporada);

        panel_1_1.add(cbTemporadas);

        for (String temporada : temporadas) {
            cbTemporadas.addItem("temporada " + temporada);
        }
        temporadaActual = temporadas.length - 1;
        cbTemporadas.setSelectedIndex(temporadaActual);
        temporada = String.valueOf(cbTemporadas.getSelectedItem()).substring(10);

        // Verificar y crear el archivo XML si no existe
        File xmlFile = new File("C:\\xampp\\htdocs\\Temporada2_Grupo2_LM\\HTML\\clasificacion.xml");
        if (!xmlFile.exists()) {
            try {
                xmlFile.createNewFile();
                System.out.println("Archivo XML creado: " + xmlFile.getPath());
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        cargarDatosDesdeXML(modeloTablaClasificacion, "C:\\xampp\\htdocs\\Temporada2_Grupo2_LM\\HTML\\clasificacion.xml", temporada);

        cbTemporadas.addActionListener(e -> {
            temporada = String.valueOf(cbTemporadas.getSelectedItem()).substring(10);
            modeloTablaClasificacion.setRowCount(0);
            cargarDatosDesdeXML(modeloTablaClasificacion, "C:\\xampp\\htdocs\\Temporada2_Grupo2_LM\\HTML\\clasificacion.xml", temporada);
            mostrarJornadaActual();
        });

        btnIniciarTemporada.addActionListener(e -> {
            if (jornadaEnJuego < 9) {
                JOptionPane.showMessageDialog(tablaClasificacion, "La jornada " + (jornadaEnJuego + 1) + " todavía no ha sido jugada.");
            } else {
                VentanaIniciarTemporada vit = new VentanaIniciarTemporada();
                vit.setVisible(true);
            }
        });

        panel_1_1.add(btnIniciarTemporada);

        contentPane.add(panel_2, BorderLayout.SOUTH);
        panel_2.setLayout(new BorderLayout(0, 0));

        panel_2.add(panel_3, BorderLayout.EAST);
        panel_3.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
        btnUsuarios.addActionListener(e -> {
            CrearUsuario cu = new CrearUsuario(RolSesion);
            cu.setVisible(true);
        });

        panel_3.add(btnUsuarios);

        panel_3.add(btnEquipos);
        btnEquipos.addActionListener(e -> {
            VentanaGestionEquipos vge = new VentanaGestionEquipos();
            vge.setVisible(true);
        });
        panel_2.add(lblRol, BorderLayout.WEST);
        try {
            lblRol.setText("Rol: " + VentanaLogin.RolSesion);
            if (RolSesion.equals("Usuario")) {
                btnUsuarios.setVisible(false);
                btnIniciarTemporada.setVisible(false);
            }
        } catch (Exception e1) {
            e1.printStackTrace();
        }
        generarPartidos();
        cargarJornadas("Jornadas.ser");
        if (matrizJornadas.size() < 1) {
            Partido partido = new Partido();
            matrizJornadas.add(partido);
            partido.setTemporadaNumero(temporadaActual);
        }
        jornadaEnJuego = matrizJornadas.get(matrizJornadas.size() - 1).getJornadaNumero();
        jornadaActual = Math.min(jornadaEnJuego, jornadas.size() - 1);
        actualizarComboBox();
        setLocationRelativeTo(null);

        JButton btnExportarPDF = new JButton("Exportar PDF");
        btnExportarPDF.setBackground(new Color(0, 120, 215));
        btnExportarPDF.setForeground(Color.WHITE);
        btnExportarPDF.addActionListener(e -> exportarTablaAPDF());
        panel_3.add(btnExportarPDF);
    }

    // Método para actualizar el JComboBox de jornadas
    private void actualizarComboBox() {
        comboBox.setSelectedIndex(jornadaActual);
    }

    // Método para cargar las jornadas desde un archivo
    public static void cargarJornadas(String archivo) {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(archivo))) {
            Object obj;
            while ((obj = ois.readObject()) != null) {
                if (obj instanceof Partido) {
                    Partido partido = (Partido) obj;
                    matrizJornadas.add(partido);
                }
            }
        } catch (EOFException e) {
            System.out.println("Archivo cargado completamente.");
        } catch (FileNotFoundException e) {
            System.err.println("El archivo no existe: " + archivo);
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    // Método para guardar las jornadas en un archivo
    public static void GrabarJornadas(String nombreArchivo) {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(nombreArchivo))) {
            for (Partido partido : matrizJornadas) {
                oos.writeObject(partido);
            }
            System.out.println("Lista guardada en " + nombreArchivo);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // Método para cambiar la editabilidad de la jornada
    private void CambiarJornadaEditable() {
        if (cbTemporadas.getSelectedIndex() == temporadaActual) {
            if (jornadaEnJuego != jornadaActual) {
                CambiarSoloLectura(true);
            } else {
                CambiarSoloLectura(false);
            }
        } else {
            CambiarSoloLectura(true);
        }
    }

    // Método para cambiar el modo de solo lectura
    private void CambiarSoloLectura(boolean editable) {
        golesLocal_1.setEnabled(!editable);
        golesVisitante_1.setEnabled(!editable);
        golesLocal_2.setEnabled(!editable);
        golesVisitante_2.setEnabled(!editable);
        golesLocal_3.setEnabled(!editable);
        golesVisitante_3.setEnabled(!editable);
    }

    // Método para agregar validación a los JTextField
    private void agregarValidacionJornada(JTextField textField) {
        textField.addKeyListener(new KeyAdapter() {
            @Override
            public void keyTyped(KeyEvent e) {
                if (jornadaActual != jornadaEnJuego) {
                    JOptionPane.showMessageDialog(tablaClasificacion, "La jornada " + (jornadaEnJuego + 1) + " todavía no ha sido jugada.");
                    e.consume();
                }
            }
        });
    }

    // Método para cargar datos desde un archivo XML
    private static void cargarDatosDesdeXML(DefaultTableModel model, String filePath, String temporadaSeleccionada) {
        try {
            File xmlFile = new File(filePath);
            if (!xmlFile.exists()) {
                // Crear el archivo si no existe
                xmlFile.createNewFile();
                System.out.println("Archivo creado: " + filePath);
            }
            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
            Document doc = dBuilder.parse(xmlFile);
            doc.getDocumentElement().normalize();
            NodeList temporadas = doc.getElementsByTagName("temporada");
            for (int i = 0; i < temporadas.getLength(); i++) {
                if (temporadas.item(i).getFirstChild().getTextContent().equals(temporadaSeleccionada)) {
                    NodeList equipos = temporadas.item(i).getChildNodes();
                    for (int j = 1; j < equipos.getLength(); j++) {
                        if (equipos.item(j).getNodeName().equals("equipo")) {
                            NodeList datosEquipo = equipos.item(j).getChildNodes();
                            String[] rowData = new String[6];
                            for (int k = 0; k < datosEquipo.getLength(); k++) {
                                switch (datosEquipo.item(k).getNodeName()) {
                                    case "ranking":
                                        rowData[0] = datosEquipo.item(k).getTextContent();
                                        break;
                                    case "nombre":
                                        rowData[1] = datosEquipo.item(k).getTextContent();
                                        break;
                                    case "puntos":
                                        rowData[2] = datosEquipo.item(k).getTextContent();
                                        break;
                                    case "golesFavor":
                                        rowData[3] = datosEquipo.item(k).getTextContent();
                                        break;
                                    case "golesContra":
                                        rowData[4] = datosEquipo.item(k).getTextContent();
                                        break;
                                    case "diferenciaGoles":
                                        rowData[5] = datosEquipo.item(k).getTextContent();
                                        break;
                                }
                            }
                            model.addRow(rowData);
                        }
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // Método para actualizar la tabla de clasificación después de cada jornada
    private void actualizarTablaClasificacion() {
        Collections.sort(listaEquipos, new Comparator<Equipo>() {
            @Override
            public int compare(Equipo e1, Equipo e2) {
                int puntosComparar = Integer.compare(e2.getPuntos(), e1.getPuntos());
                if (puntosComparar == 0) {
                    int golesFavorComparar = Integer.compare(e2.getGolesFavor(), e1.getGolesFavor());
                    if (golesFavorComparar == 0) {
                        return Integer.compare(e2.getDiferenciaGoles(), e1.getDiferenciaGoles());
                    }
                    return golesFavorComparar;
                }
                return puntosComparar;
            }
        });
        modeloTablaClasificacion.setRowCount(0);
        for (int i = 0; i < listaEquipos.size(); i++) {
            Equipo equipo = listaEquipos.get(i);
            modeloTablaClasificacion.addRow(new Object[] { i + 1, equipo.getNombre(), equipo.getPuntos(), equipo.getGolesFavor(), equipo.getGolesContra(), equipo.getDiferenciaGoles() });
        }
    }

    // Clase interna que representa a un equipo en la liga
    class Equipo {
        private String nombre;
        private int golesFavor;
        private int golesContra;
        private int puntos;

        public Equipo(String nombre) {
            this.nombre = nombre;
            this.golesFavor = 0;
            this.golesContra = 0;
            this.puntos = 0;
        }

        public String getNombre() {
            return nombre;
        }

        public void agregarResultado(int golesFavor, int golesContra) {
            this.golesFavor += golesFavor;
            this.golesContra += golesContra;
        }
        public int getGolesFavor() {
            return golesFavor;
        }

        public int getGolesContra() {
            return golesContra;
        }

        public int getDiferenciaGoles() {
            return golesFavor - golesContra;
        }

        public int getPuntos() {
            return puntos;
        }

        public void agregarPuntos(int puntos) {
            this.puntos += puntos;
        }
    }

    // Método para exportar la tabla de clasificación a un archivo PDF
    private void exportarTablaAPDF() {
        com.itextpdf.text.Document document = new com.itextpdf.text.Document();
        try {
            PdfWriter.getInstance(document, new FileOutputStream("clasificacion.pdf"));
            document.open();
            PdfPTable pdfTable = new PdfPTable(tablaClasificacion.getColumnCount());
            for (int i = 0; i < tablaClasificacion.getColumnCount(); i++) {
                PdfPCell cell = new PdfPCell(new Paragraph(tablaClasificacion.getColumnName(i)));
                cell.setHorizontalAlignment(com.itextpdf.text.Element.ALIGN_CENTER);
                pdfTable.addCell(cell);
            }
            for (int rows = 0; rows < tablaClasificacion.getRowCount(); rows++) {
                for (int cols = 0; cols < tablaClasificacion.getColumnCount(); cols++) {
                    pdfTable.addCell(tablaClasificacion.getModel().getValueAt(rows, cols).toString());
                }
            }
            document.add(pdfTable);
            document.close();
            JOptionPane.showMessageDialog(this, "Tabla exportada a PDF correctamente.");
        } catch (DocumentException | FileNotFoundException e) {
            e.printStackTrace();
        }
    }
}