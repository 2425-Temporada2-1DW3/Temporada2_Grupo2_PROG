package aplicacionFutbol;

// Importación de bibliotecas necesarias para la interfaz gráfica y otras funcionalidades
import java.awt.BorderLayout; // Clase para gestionar el diseño de la ventana
import java.awt.Color; // Clase para manejar colores
import java.awt.EventQueue; // Clase para gestionar el hilo de eventos
import java.awt.Font; // Clase para manejar fuentes de texto
import java.awt.Image; // Clase para manejar imágenes
import java.awt.Toolkit; // Clase para obtener recursos del sistema
import java.awt.event.FocusAdapter; // Clase para manejar eventos de enfoque
import java.awt.event.FocusEvent; // Clase para eventos de enfoque
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.io.EOFException;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList; // Clase para manejar listas dinámicas
import java.util.Collections; // Clase para colecciones de objetos
import java.util.Comparator; // Clase para comparar objetos
import java.util.List; // Interfaz para listas

import javax.swing.BoxLayout; // Clase para gestionar un diseño de caja
import javax.swing.DefaultListModel;
import javax.swing.ImageIcon; // Clase para manejar iconos de imagen
import javax.swing.JButton; // Clase para crear botones
import javax.swing.JComboBox; // Clase para crear listas desplegables
import javax.swing.JFrame; // Clase para crear la ventana principal
import javax.swing.JLabel; // Clase para crear etiquetas de texto
import javax.swing.JOptionPane; // Clase para mostrar diálogos de mensajes
import javax.swing.JPanel; // Clase para crear paneles
import javax.swing.JScrollPane; // Clase para crear paneles con desplazamiento
import javax.swing.JTable; // Clase para crear tablas
import javax.swing.JTextField; // Clase para crear campos de texto
import javax.swing.border.EmptyBorder; // Clase para manejar bordes vacíos
import javax.swing.table.DefaultTableModel;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

import java.awt.FlowLayout;
import java.awt.Component;
import javax.swing.SwingConstants;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent; // Clase para manejar el modelo de la tabla

/**
 * 
 * Clase principal que representa la ventana de resultados de la liga.
 * 
 * Permite gestionar y mostrar los resultados de los partidos y la clasificación
 * de los equipos.
 * 
 */
public class VentanaMain extends JFrame {

	private static final long serialVersionUID = 16765566646456546L; // Identificador de versión de la clase
	private JPanel contentPane; // Panel principal que contendrá todos los componentes de la ventana

	public int temporadaActual;
	String temporada;
	public int jornadaEnJuego = 0;
	private static ArrayList<Partido> matrizJornadas = new ArrayList<>();
	// Etiquetas para mostrar los nombres de los equipos locales y visitantes
	private JLabel lblLocal_1 = new JLabel("Local 1");
	private JLabel lblVisitante_1 = new JLabel("Visitante 1");
	private JLabel lblLocal_2 = new JLabel("Local 2");
	private JLabel lblVisitante_2 = new JLabel("Visitante 2");
	private JLabel lblLocal_3 = new JLabel("Local 3");
	private JLabel lblVisitante_3 = new JLabel("Visitante 3");
	
	// Almacena los resultados de los partidos (goles)
	private List<int[][]> resultados;

	// Campos de texto para ingresar los goles de los equipos
	private JTextField golesLocal_1 = new JTextField(5);
	private JTextField golesVisitante_1 = new JTextField(5);
	private JTextField golesLocal_2 = new JTextField(5);
	private JTextField golesVisitante_2 = new JTextField(5);
	private JTextField golesLocal_3 = new JTextField(5);
	private JTextField golesVisitante_3 = new JTextField(5);
	
	// Lista de equipos que participan en la liga
	String[] equipos = { "athletic", "Barcelona", "Madrid", "Alaves", "Osasuna", "Eibar" };
	private int jornadaActual = 0; // Índice de la jornada actual
	private List<String[][]> jornadas; // Almacena las jornadas y los partidos correspondientes
	private JTable tablaClasificacion; // Tabla para mostrar la clasificación de los equipos
	private DefaultTableModel modeloTablaClasificacion; // Modelo de la tabla de clasificación
	private List<Equipo> listaEquipos; // Lista de objetos Equipo que representan a los equipos en la liga
	private boolean modoSoloLectura = false; // Variable para controlar el modo de solo lectura
	private final JComboBox<String> comboBox = new JComboBox<>(); // ComboBox para seleccionar la jornada
	private JLabel lblRol = new JLabel(); // JLabel para mostrar el rol del usuario
	Partido partido;

	// Método para cargar el escudo de un equipo dado su nombre
	private ImageIcon cargarEscudo(String equipo) {
		String ruta = "src/imagenes/" + equipo + ".png"; // Ruta de la imagen del escudo
		ImageIcon iconoOriginal = new ImageIcon(Toolkit.getDefaultToolkit().getImage(ruta)); // Carga la imagen
		Image imagenRedimensionada = iconoOriginal.getImage().getScaledInstance(20, 20, Image.SCALE_SMOOTH); // Redimensiona
		return new ImageIcon(imagenRedimensionada); // Devuelve el ImageIcon redimensionado
	}

	// Método para mostrar la jornada actual en la interfaz
	private void mostrarJornadaActual() {
		if (jornadas == null || jornadas.isEmpty()) {
			return; // No hay jornadas disponibles, salir del método
		}
		String[][] partidos = jornadas.get(jornadaActual); // Acceso a los partidos de la jornada actual
		cargarJornadas("Jornadas.ser");
		
		// Cargar y asignar los nombres y escudos a los JLabels de los equipos
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
		
		boolean fill=false;
			for(int i=0; i<matrizJornadas.size(); i++) {
				partido = matrizJornadas.get(i);
				if ((partido.getTemporadaNumero() == cbTemporadas.getSelectedIndex()) && (partido.getJornadaNumero() == jornadaActual+1)){
					switch(partido.getPartidoNumero()) {
					case 0:
						golesLocal_1.setText(String.valueOf(partido.getMarcadorLocal())); // Goles locales del partido 1
						golesVisitante_1.setText(String.valueOf(partido.getMarcadorVisitante())); // Goles visitantes del partido 1
					case 1:
						golesLocal_2.setText(String.valueOf(partido.getMarcadorLocal())); // Goles locales del partido 2
						golesVisitante_2.setText(String.valueOf(partido.getMarcadorVisitante())); // Goles visitantes del partido 2
					case 2:
						golesLocal_3.setText(String.valueOf(partido.getMarcadorLocal())); // Goles locales del partido 3
						golesVisitante_3.setText(String.valueOf(partido.getMarcadorVisitante())); // Goles visitantes del partido 3
						fill=true;
					}
				}
			}
		
		if(!fill) {
			golesLocal_1.setText(""); // Goles locales del partido 1
			golesVisitante_1.setText(""); // Goles visitantes del partido 1
			golesLocal_2.setText(""); // Goles locales del partido 2
			golesVisitante_2.setText(""); // Goles visitantes del partido 2
			golesLocal_3.setText(""); // Goles locales del partido 3
			golesVisitante_3.setText(""); // Goles visitantes del partido 3
		}
		comboBox.setSelectedIndex(jornadaActual); // Actualiza el JComboBox para reflejar la jornada actual
		CambiarJornadaEditable();
	}

	// Método para generar los partidos de la liga
	public void generarPartidos() {
		int numEquipos = equipos.length; // Número de equipos
		int numJornadas = 10; // Número de jornadas
		jornadas = new ArrayList<>(); // Inicializa la lista de jornadas
		resultados = new ArrayList<>(); // Inicializa la lista de resultados
		listaEquipos = new ArrayList<>(); // Inicializa la lista de equipos

		// Crea objetos Equipo para cada equipo en la lista
		for (String equipo : equipos) {
			listaEquipos.add(new Equipo(equipo));
		}

		// Genera los partidos para cada jornada
		for (int jornada = 0; jornada < numJornadas; jornada++) {
			String[][] partidos = new String[numEquipos / 2][2]; // Array para almacenar los partidos de la jornada
			int[][] goles = new int[numEquipos / 2][2]; // Array para almacenar los goles de los partidos
			for (int partido = 0; partido < numEquipos / 2; partido++) {
				int equipoLocal = (jornada + partido) % (numEquipos - 1); // Determina el equipo local
				int equipoVisitante = (numEquipos - 1 - partido + jornada) % (numEquipos - 1); // Determina el equipo
				if (partido == 0) {
					equipoVisitante = numEquipos - 1; // Asegura que el último equipo sea el visitante en el primer
				}

				partidos[partido][0] = equipos[equipoLocal]; // Asigna el nombre del equipo local
				partidos[partido][1] = equipos[equipoVisitante]; // Asigna el nombre del equipo visitante
			}

		 jornadas.add(partidos); // Agrega los partidos de la jornada a la lista de jornadas
			resultados.add(goles); // Agrega los resultados de la jornada a la lista de resultados
		}
	}

	private boolean[] resultadosGuardados = new boolean[10]; // Controla si los resultados han sido guardados para cada jornada
	private final JLabel lblNewLabel = new JLabel("vs"); // Etiqueta para mostrar "vs" entre equipos
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
	private final JComboBox cbTemporadas = new JComboBox();
	private final JButton btnIniciarTemporada = new JButton("Iniciar temporada");
	private final JPanel panel_2 = new JPanel();
	private final JPanel panel_3 = new JPanel();
	private final JButton btnEquipos = new JButton("Equipos");
	private final JButton btnUsuarios = new JButton("Usuarios");
	private final JLabel lbltemporada = new JLabel("Temporada ");
	
	//Se inicializa el array
	String[][][] matrizEquipos= {
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
	
	String[] temporadas = {"2023", "2024"};

	// Método para guardar los resultados de los partidos
	private void guardarResultados() {
		// Verificar si los resultados ya han sido guardados para la jornada actual
		if (resultadosGuardados[jornadaActual]) {
			JOptionPane.showMessageDialog(this,
					"Los resultados de la jornada " + (jornadaActual + 1) + " ya han sido guardados.");
			return; // Salir del método si ya se han guardado
		}

		String[][] partidos = jornadas.get(jornadaActual); // Obtener los partidos de la jornada actual
		boolean partidoJugado = false; // Variable para verificar si al menos un partido fue jugado
		for (int i = 0; i < 3; i++) { // Iterar sobre los 3 partidos de la jornada
			int resultadoLocal = 0; // Inicializar goles del equipo local
			int resultadoVisitante = 0; // Inicializar goles del equipo visitante
			try {
				if (i == 0) { // Para el primer partido
					// Verificar si los campos están vacíos
					if (golesLocal_1.getText().isEmpty() && golesVisitante_1.getText().isEmpty()) {
						// Si ambos campos están vacíos, mostrar un mensaje
						JOptionPane.showMessageDialog(this,
								"Los equipos " + partidos[i][0] + " y " + partidos[i][1] + " no han jugado.");
						return; // Salir del método y no guardar resultados para este partido
					}

					resultadoLocal = Integer.parseInt(golesLocal_1.getText()); // Obtener goles del equipo local
					resultadoVisitante = Integer.parseInt(golesVisitante_1.getText()); // Obtener goles del equipo visitante
				} else if (i == 1) { // Para el segundo partido
					if (golesLocal_2.getText().isEmpty() && golesVisitante_2.getText().isEmpty()) {
						JOptionPane.showMessageDialog(this,
								"Los equipos " + partidos[i][0] + " y " + partidos[i][1] + " no han jugado.");
						return; // Salir del método y no guardar resultados para este partido
					}

					resultadoLocal = Integer.parseInt(golesLocal_2.getText());
					resultadoVisitante = Integer.parseInt(golesVisitante_2.getText());
				} else if (i == 2) { // Para el tercer partido
					if (golesLocal_3.getText().isEmpty() && golesVisitante_3.getText().isEmpty()) {
						JOptionPane.showMessageDialog(this,
								"Los equipos " + partidos[i][0] + " y " + partidos[i][1] + " no han jugado.");
						return; // Salir del método y no guardar resultados para este partido
					}

				
				}

				// Guardar resultados
				resultadoLocal = Integer.parseInt(golesLocal_3.getText());
				resultadoVisitante = Integer.parseInt(golesVisitante_3.getText());
				resultados.get(jornadaActual)[i][0] = resultadoLocal; // Guardar goles del local
				resultados.get(jornadaActual)[i][1] = resultadoVisitante; // Guardar goles del visitante
				// Actualizar equipos con los resultados
				actualizarEquipos(partidos[i][0], resultadoLocal, resultadoVisitante);
				actualizarEquipos(partidos[i][1], resultadoVisitante, resultadoLocal);
				partidoJugado = true; // Al menos un partido fue jugado
			} catch (NumberFormatException e) { // Manejo de excepciones para entradas no válidas
				JOptionPane.showMessageDialog(this, "Por favor, ingresa números válidos para los goles.");
				return; // Salir del método si hay un error
			}
		}

		// Solo mostrar mensaje si al menos un partido fue jugado
		if (partidoJugado) {
			int opcion = JOptionPane.showConfirmDialog(this,(String)"Seguro que quiere guardar los restultados para esta temporada?","Info",JOptionPane.YES_NO_CANCEL_OPTION,JOptionPane.QUESTION_MESSAGE,null);
			switch (opcion) {
				case JOptionPane.YES_OPTION: // opcion "Si"
					//guarda
					actualizarTablaClasificacion(); // Actualizar la tabla de clasificación
					jornadaEnJuego=jornadaActual+1;
					JOptionPane.showMessageDialog(this, "Resultados guardados correctamente."); // Mensaje de éxito
					resultadosGuardados[jornadaActual] = true; // Marcar resultados como guardados para la jornada actual
					CambiarJornadaEditable();
					generarJornadasXML(temporadaActual,jornadaEnJuego);
					generarXML();
					break;
				case JOptionPane.NO_OPTION: // opcion "No"
				case JOptionPane.CANCEL_OPTION: 
				case JOptionPane.CLOSED_OPTION:
			}
		}
	}

	private void generarJornadasXML(int temporada, int jornada) {
		partido = new Partido(temporada, jornada, 0, Integer.parseInt(golesLocal_1.getText()), Integer.parseInt(golesVisitante_1.getText()));
		System.out.println(partido.getMarcadorLocal() +" - "+ partido.getMarcadorVisitante());
		matrizJornadas.add(partido);
		GrabarJornadas("Jornadas.ser");
		
		partido = new Partido(temporada, jornada, 1, Integer.parseInt(golesLocal_2.getText()), Integer.parseInt(golesVisitante_2.getText()));
		System.out.println(partido.getMarcadorLocal() +" - "+ partido.getMarcadorVisitante());
		matrizJornadas.add(partido);
		GrabarJornadas("Jornadas.ser");
		
		partido = new Partido(temporada, jornada, 2, Integer.parseInt(golesLocal_3.getText()), Integer.parseInt(golesVisitante_3.getText()));
		System.out.println(partido.getMarcadorLocal() +" - "+ partido.getMarcadorVisitante());
		matrizJornadas.add(partido);
		GrabarJornadas("Jornadas.ser");
}

	private void generarXML() {
		//Se rellena la matriz con los datos correspondientes
		for (int i = 0; i< listaEquipos.size(); i++) {
			matrizEquipos[temporadaActual][i][0]  = String.valueOf(i+1);
			matrizEquipos[temporadaActual][i][1]  = listaEquipos.get(i).getNombre();
			matrizEquipos[temporadaActual][i][2]  = String.valueOf(listaEquipos.get(i).getPuntos());
			matrizEquipos[temporadaActual][i][3]  = String.valueOf(listaEquipos.get(i).getGolesFavor());
			matrizEquipos[temporadaActual][i][4]  = String.valueOf(listaEquipos.get(i).getGolesContra());
			matrizEquipos[temporadaActual][i][5]  = String.valueOf(listaEquipos.get(i).getDiferenciaGoles());
		}
		
		//muestra la matriz en la consola
		System.out.println("Index de la temporada: "+temporadaActual);
		for (int i = 0; i< listaEquipos.size(); i++) {
			for (int j = 0; j<6; j++) {
					System.out.print(matrizEquipos[temporadaActual][i][j]+" ");
			}
			System.out.println("");
		}
		System.out.println("");
		
		//Se genera el archivo xml
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

            // Especifica la ruta absoluta para guardar el archivo XML
            String filePath = "C:\\xampp\\htdocs\\Temporada2_Grupo2_LM\\HTML\\clasificacion.xml";
            TransformerFactory transformerFactory = TransformerFactory.newInstance();
            Transformer transformer = transformerFactory.newTransformer();
            DOMSource source = new DOMSource(doc);
            StreamResult result = new StreamResult(new File(filePath));
            transformer.transform(source, result);

            System.out.println("Archivo XML guardado en " + filePath);
            System.out.println("");

        } catch (ParserConfigurationException | TransformerException e) {
            e.printStackTrace();
        }
	}

	// Método para actualizar los datos de los equipos después de un partido
	private void actualizarEquipos(String equipoNombre, int golesFavor, int golesContra) {
		for (Equipo equipo : listaEquipos) { // Iterar sobre la lista de equipos
			if (equipo.getNombre().equals(equipoNombre)) { // Buscar el equipo correspondiente
				equipo.agregarResultado(golesFavor, golesContra); // Actualizar goles
				if (golesFavor > golesContra) {
					equipo.agregarPuntos(3); // Victoria
				} else if (golesFavor == golesContra) {
					equipo.agregarPuntos(1); // Empate
				}
				break; // Salir del bucle una vez encontrado el equipo
			}
		}
	}

	// Método principal que inicia la aplicación
	public static void main(String[] args) {
		EventQueue.invokeLater(() -> {
			try {
				String RolSesion = "Admin"; // Cambia esto según sea necesario (admin, arbitro, Usuario)
				//Rol por defecto de la ventana
				
				VentanaMain frame = new VentanaMain(RolSesion); // Crear instancia de la ventana de resultados
				frame.setVisible(true); // Hacer visible la ventana
			} catch (Exception e) {
				e.printStackTrace(); // Manejo de excepciones
			}
		});
	}

	// Constructor de la clase VentanaResultados
	public VentanaMain(String RolSesion) {
		setTitle("Resultados y clasificación"); // Título de la ventana
		setIconImage(Toolkit.getDefaultToolkit().getImage("src/imagenes/balon.png")); // Icono de la ventana
		this.modoSoloLectura = RolSesion.equals("Usuario"); // Modo solo lectura si es Usuario
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // Cerrar la aplicación al cerrar la ventana
		setBounds(100, 100, 900, 700); // Dimensiones de la ventana
		contentPane = new JPanel(); // Crear el panel principal
		contentPane.setBackground(new Color(240, 240, 240)); // Color de fondo del panel
		contentPane.setBorder(new EmptyBorder(10, 10, 10, 10)); // Bordes del panel
		setContentPane(contentPane); // Establecer el panel como contenido de la ventana
		contentPane.setLayout(new BorderLayout(0, 0)); // Layout del panel
		JPanel izquierda = new JPanel(); // Panel para la parte izquierda de la ventana
		izquierda.setBackground(new Color(240, 240, 240)); // Color de fondo
		contentPane.add(izquierda, BorderLayout.WEST); // Añadir panel a la izquierda
		izquierda.setLayout(new BorderLayout(0, 0)); // Layout del panel izquierdo
		JPanel panel = new JPanel(); // Panel para los controles de la parte superior
		panel.setBackground(new Color(240, 240, 240)); // Color de fondo
		izquierda.add(panel, BorderLayout.NORTH); // Añadir panel a la parte superior del panel izquierdo
		
		

		// Añadir las opciones al JComboBox para seleccionar la jornada
		for (int i = 1; i <= 10; i++) {
			comboBox.addItem("Jornada " + i); // Añadir jornadas al JComboBox
		}

		// Establecer la acción del JComboBox para cambiar la jornada actual
		comboBox.addActionListener(e -> {
			jornadaActual = comboBox.getSelectedIndex(); // Actualizar jornada actual
			mostrarJornadaActual(); // Actualiza la vista
		});
		
		JButton btnAnterior = new JButton(""); // Botón para ir a la jornada anterior
		btnAnterior.setIcon(new ImageIcon("C:\\Users\\ik_1dw3a\\Documents\\GitHub\\Temporada2_Grupo2_PROG\\media\\left.png"));
		btnAnterior.setBackground(new Color(255, 255, 255)); // Color de fondo del botón
		btnAnterior.setForeground(Color.WHITE); // Color del texto del botón
		btnAnterior.addActionListener(e -> {
				jornadaActual = Math.max(jornadaActual - 1, 0); // Decrementa pero no pasa de 0
				mostrarJornadaActual(); // Actualiza la vista
		});
		
		panel.add(btnAnterior); // Añadir botón anterior al panel

		panel.add(comboBox); // Añadir JComboBox al panel
		JButton btnSiguiente = new JButton(""); // Botón para ir a la siguiente jornada
		btnSiguiente.setIcon(new ImageIcon("C:\\Users\\ik_1dw3a\\Documents\\GitHub\\Temporada2_Grupo2_PROG\\media\\right.png"));
		btnSiguiente.setBackground(new Color(255, 255, 255)); // Color de fondo del botón
		btnSiguiente.setForeground(Color.WHITE); // Color del texto del botón
		btnSiguiente.addActionListener(e -> {
				jornadaActual = Math.min(jornadaActual + 1, jornadas.size() - 1); // Incrementa pero no pasa del tamaño de jornadas
				mostrarJornadaActual(); // Actualiza la vista
		});
		
		

		panel.add(btnSiguiente); // Añadir botón siguiente al panel
		JPanel panel_1 = new JPanel(); // Panel para mostrar los resultados de los partidos
		panel_1.setBackground(new Color(240, 240, 240)); // Color de fondo
		izquierda.add(panel_1, BorderLayout.CENTER); // Añadir panel al centro del panel izquierdo
		panel_1.setLayout(new BoxLayout(panel_1, BoxLayout.Y_AXIS));
		panel_1.add(lblNewLabel_8); // Añadir etiqueta vacía para separación
		panel_1.add(lblLocal_1); // Añadir etiqueta para el equipo local 1
		golesLocal_1.setEditable(!modoSoloLectura); // Hacer editable el campo si no está en modo solo lectura
		golesLocal_1.addFocusListener(new FocusAdapter() { // Añadir listener para seleccionar texto al enfocar
			public void focusGained(FocusEvent e) {
				golesLocal_1.selectAll(); // Seleccionar todo el texto al enfocar
			}
		});

		panel_1.add(golesLocal_1); // Añadir campo de texto para goles del equipo local 1
		golesVisitante_1.setEditable(!modoSoloLectura); // Hacer editable el campo si no está en modo solo lectura
		golesVisitante_1.addFocusListener(new FocusAdapter() { // Añadir listener para seleccionar texto al enfocar
			public void focusGained(FocusEvent e) {
				golesVisitante_1.selectAll(); // Seleccionar todo el texto al enfocar
			}
		});

		panel_1.add(lblNewLabel); // Añadir etiqueta "vs" entre los equipos
		panel_1.add(golesVisitante_1); // Añadir campo de texto para goles del equipo visitante 1
		panel_1.add(lblVisitante_1);
		panel_1.add(lblNewLabel_4); // Añadir etiqueta vacía para separación
		panel_1.add(lblLocal_2); // Añadir etiqueta para el equipo local 2
		golesLocal_2.setEditable(!modoSoloLectura); // Hacer editable el campo si no está en modo solo lectura
		golesLocal_2.addFocusListener(new FocusAdapter() { // Añadir listener para seleccionar texto al enfocar
			public void focusGained(FocusEvent e) {
				golesLocal_2.selectAll(); // Seleccionar todo el texto al enfocar
			}
		});

		panel_1.add(golesLocal_2); // Añadir campo de texto para goles del equipo local 2
		golesVisitante_2.setEditable(!modoSoloLectura); // Hacer editable el campo si no está en modo solo lectura
		golesVisitante_2.addFocusListener(new FocusAdapter() { // Añadir listener para seleccionar texto al enfocar
			public void focusGained(FocusEvent e) {
				golesVisitante_2.selectAll(); // Seleccionar todo el texto al enfocar
			}
		});

		panel_1.add(lblNewLabel_1); // Añadir etiqueta "vs" entre los equipos
		panel_1.add(golesVisitante_2); // Añadir campo de texto para goles del equipo visitante 2
		panel_1.add(lblVisitante_2); // Añadir etiqueta para el equipo visitante 2
		panel_1.add(lblNewLabel_5); // Añadir etiqueta vacía para separación
		panel_1.add(lblNewLabel_6); // Añadir etiqueta vacía para separación
		panel_1.add(lblLocal_3); // Añadir etiqueta para el equipo local 3 
		golesLocal_3.setEditable(!modoSoloLectura); // Hacer editable el campo si no está en modo solo lectura
		golesLocal_3.addFocusListener(new FocusAdapter() { // Añadir listener para seleccionar texto al enfocar
			public void focusGained(FocusEvent e) {
				golesLocal_3.selectAll(); // Seleccionar todo el texto al enfocar
			}
		});

		panel_1.add(golesLocal_3); // Añadir campo de texto para goles del equipo local 3
		golesVisitante_3.setEditable(!modoSoloLectura); // Hacer editable el campo si no está en modo solo lectura
		golesVisitante_3.addFocusListener(new FocusAdapter() { // Añadir listener para seleccionar texto al enfocar
			public void focusGained(FocusEvent e) {
				golesVisitante_3.selectAll(); // Seleccionar todo el texto al enfocar
			}
		});

		panel_1.add(lblNewLabel_2); // Añadir etiqueta "vs" entre los equipos
		panel_1.add(golesVisitante_3); // Añadir campo de texto para goles del equipo visitante 3
		panel_1.add(lblVisitante_3); // Añadir etiqueta para el equipo visitante 3
		JButton btnGuardar = new JButton("Guardar Resultados"); // Botón para guardar los resultados
		btnGuardar.setBackground(new Color(0, 120, 215)); // Color de fondo del botón
		btnGuardar.setForeground(Color.WHITE); // Color del texto del botón
		btnGuardar.setEnabled(!modoSoloLectura); // Habilitar el botón si no está en modo solo lectura
		btnGuardar.addActionListener(e -> guardarResultados());// Acción al hacer clic en el botón para guardar resultados
		panel_1.add(lblNewLabel_7); // Añadir etiqueta vacía para separación
		panel_1.add(btnGuardar); // Añadir botón de guardar al panel
		JButton btnRestablecer = new JButton("Restablecer"); // Botón para restablecer los campos de entrada
		btnRestablecer.setBackground(new Color(0, 120, 215)); // Color de fondo del botón
		btnRestablecer.setForeground(Color.WHITE); // Color del texto del botón
		btnRestablecer.addActionListener(e -> {
			// Limpiar los campos de texto de goles
			int opcion = JOptionPane.showConfirmDialog(this,(String)"Seguro que quiere restablecer la temporadas?","Info",JOptionPane.YES_NO_CANCEL_OPTION,JOptionPane.QUESTION_MESSAGE,null);
			switch (opcion) {
				case JOptionPane.YES_OPTION: // opcion "Si"
					// restablece
					golesLocal_1.setText("");
					golesVisitante_1.setText("");
					golesLocal_2.setText("");
					golesVisitante_2.setText("");
					golesLocal_3.setText("");
					golesVisitante_3.setText("");
					jornadaEnJuego=0;
					for(int i=0;i<10;i++) {
						resultadosGuardados[i] = false;
					}
					CambiarJornadaEditable();
					generarXML();
					break;
				case JOptionPane.NO_OPTION: // opcion "No"
				case JOptionPane.CANCEL_OPTION: 
				case JOptionPane.CLOSED_OPTION:
			}	
		});

		// Agregar la validación a los JTextField
		agregarValidacionJornada(golesLocal_1);
		agregarValidacionJornada(golesVisitante_1);
		agregarValidacionJornada(golesLocal_2);
		agregarValidacionJornada(golesVisitante_2);
		agregarValidacionJornada(golesLocal_3);
		agregarValidacionJornada(golesVisitante_3);
		
		if (modoSoloLectura) {
			btnRestablecer.setEnabled(false); // Deshabilitar el botón si está en modo solo lectura
		}

		panel_1.add(lblNewLabel_9); // Añadir etiqueta vacía para separación
		panel_1.add(btnRestablecer); // Añadir botón de restablecer al panel
		JButton btnAtras = new JButton("Cerrar Sesión"); // Botón para cerrar sesión
		btnAtras.setBackground(new Color(0, 120, 215)); // Color de fondo del botón
		btnAtras.setForeground(Color.WHITE); // Color del texto del botón
		btnAtras.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				VentanaLogin.RolSesion = "Usuario";
				VentanaLogin vl = new VentanaLogin();
				// la muestro
				vl.setVisible(true);
				dispose();
			}
		});

		panel_1.add(lblNewLabel_10); // Añadir etiqueta vacía para separación
		panel_1.add(btnAtras); // Añadir botón de cerrar sesión al panel

		// Configuración de la tabla de clasificación
		modeloTablaClasificacion = new DefaultTableModel(); // Crear modelo de tabla
		modeloTablaClasificacion.addColumn("Posición"); // Columna para posición
		modeloTablaClasificacion.addColumn("Equipo"); // Columna para nombre del equipo
		modeloTablaClasificacion.addColumn("Puntos"); // Columna para puntos
		modeloTablaClasificacion.addColumn("Goles a Favor"); // Columna para goles a favor
		modeloTablaClasificacion.addColumn("Goles en Contra"); // Columna para goles en contra
		modeloTablaClasificacion.addColumn("Diferencia de Goles"); // Columna para diferencia de goles
		tablaClasificacion = new JTable(modeloTablaClasificacion); // Crear tabla con el modelo
		tablaClasificacion.setEnabled(false); // Deshabilitar edición de la tabla
		tablaClasificacion.setFillsViewportHeight(true); // Llenar el alto del viewport
		tablaClasificacion.setBackground(new Color(255, 255, 255)); // Color de fondo de la tabla
		tablaClasificacion.setForeground(new Color(0, 0, 0)); // Color del texto de la tabla
		tablaClasificacion.setFont(new Font("Arial", Font.PLAIN, 14)); // Fuente de la tabla
		JScrollPane scrollPane = new JScrollPane(tablaClasificacion); // Crear scroll pane para la tabla
		contentPane.add(scrollPane, BorderLayout.CENTER);
		panel_1_1.setAlignmentY(Component.BOTTOM_ALIGNMENT);
		
		contentPane.add(panel_1_1, BorderLayout.NORTH);
		panel_1_1.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		
		panel_1_1.add(lbltemporada);
		
		panel_1_1.add(cbTemporadas);
				
		cbTemporadas.addItem("Temporada 2023"); // Añadir temporadas a la JComboBox
		cbTemporadas.addItem("Temporada 2024");
		
		//Iniciamos la temporada por defecto
		temporadaActual = temporadas.length-1; //La temporada actual es la ultima temporada dentro del programa
		cbTemporadas.setSelectedIndex(temporadaActual);
		temporada = String.valueOf(cbTemporadas.getSelectedItem());
		temporada = temporada.substring(10);
		
		cargarDatosDesdeXML(modeloTablaClasificacion, "C:\\xampp\\htdocs\\Temporada2_Grupo2_LM\\HTML\\clasificacion.xml", temporada); // Actualiza la vista
				
		cbTemporadas.addActionListener(e -> {
			temporada = String.valueOf(cbTemporadas.getSelectedItem());
			temporada = temporada.substring(10);
			modeloTablaClasificacion.setRowCount(0); // Limpiar la tabla antes de llenarla
			cargarDatosDesdeXML(modeloTablaClasificacion, "C:\\xampp\\htdocs\\Temporada2_Grupo2_LM\\HTML\\clasificacion.xml", temporada); // Actualiza la vista
			mostrarJornadaActual();
		});
		
		btnIniciarTemporada.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				// abrir VentanaIniciarTemporada
			}
		});
		
		panel_1_1.add(btnIniciarTemporada);
		
		contentPane.add(panel_2, BorderLayout.SOUTH);
		panel_2.setLayout(new BorderLayout(0, 0));
		
		panel_2.add(panel_3, BorderLayout.EAST);
		panel_3.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		btnUsuarios.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				CrearUsuario cu = new CrearUsuario(RolSesion);
				// la muestro
				cu.setVisible(true);
			}
		});
		
		panel_3.add(btnUsuarios);
		
		panel_3.add(btnEquipos);
		panel_2.add(lblRol, BorderLayout.WEST);
		try {
			lblRol.setText("Rol: " + VentanaLogin.RolSesion);
			if (RolSesion.equals("Usuario")){
				btnUsuarios.setVisible(false);
				btnIniciarTemporada.setVisible(false);
			}
		} catch (Exception e1) {
			e1.printStackTrace();
		}
		generarPartidos(); // Generar los partidos al iniciar
		cargarJornadas("Jornadas.ser");
		if(matrizJornadas.size() < 1) {
			Partido partido = new Partido();
			matrizJornadas.addFirst(partido);
			partido.setTemporadaNumero(temporadaActual);
		}
		jornadaEnJuego = matrizJornadas.get(matrizJornadas.size()-1).getJornadaNumero();
		jornadaActual = jornadaEnJuego;
		mostrarJornadaActual(); // Mostrar la primera jornada
		// Centrar la ventana en la pantalla
		setLocationRelativeTo(null);
	}
	
	public static void cargarJornadas(String archivo) {
		 try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(archivo))) {
	            // Leer el archivo objeto por objeto y agregarlo al DefaultListModel
	            Object obj;
	            while ((obj = ois.readObject()) != null) {
	                if (obj instanceof Partido) {
	                    Partido partido = (Partido) obj;
	                    matrizJornadas.addLast(partido);
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
	
	public static void GrabarJornadas(String nombreArchivo) {
		 try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(nombreArchivo))) {
			 for (int i = 0; i < matrizJornadas.size(); i++) {
	                oos.writeObject(matrizJornadas.get(i)); // Escribir cada objeto
	            }
	            System.out.println("Lista guardada en objetos.ser");
	        } catch (IOException e) {
	            e.printStackTrace();
	        }
	}
	
	private void CambiarJornadaEditable() {
		if (cbTemporadas.getSelectedIndex() == temporadaActual) {
			if (jornadaEnJuego != (jornadaActual)) {
				CambiarSoloLectura(true);							
			} else {
				CambiarSoloLectura(false);
			}
		} else {
			CambiarSoloLectura(true);
		}
	}
	
	private void CambiarSoloLectura(boolean editable) {
		golesLocal_1.setEnabled(!editable);
		golesVisitante_1.setEnabled(!editable);
		golesLocal_2.setEnabled(!editable);
		golesVisitante_2.setEnabled(!editable);
		golesLocal_3.setEnabled(!editable);
		golesVisitante_3.setEnabled(!editable);
		
	}
	
	// Agregar un KeyListener al JTextField
			private void agregarValidacionJornada(JTextField textField) {
				textField.addKeyListener(new KeyAdapter() {
				@Override
				public void keyTyped(KeyEvent e) {
					if (jornadaActual != jornadaEnJuego) {
						// Mostrar mensaje de error
						JOptionPane.showMessageDialog(tablaClasificacion,"La jornada " + (jornadaEnJuego + 1) + " todavia no ha sido jugada."); // Mensaje de éxito
						// Consumir el evento para evitar que se escriba texto
						e.consume();
					}
				}
			});
			}
		
	private static void cargarDatosDesdeXML(DefaultTableModel model, String filePath, String temporadaSeleccionada) {
        try {
            File xmlFile = new File(filePath);
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
		// Ordenar la lista de equipos según los puntos, goles a favor y diferencia de goles
		Collections.sort(listaEquipos, new Comparator<Equipo>() {
			@Override
			public int compare(Equipo e1, Equipo e2) {
				// Primero comparar por puntos
				int puntosComparar = Integer.compare(e2.getPuntos(), e1.getPuntos());
				// Si los puntos son iguales, comparar por goles a favor
				if (puntosComparar == 0) {
					int golesFavorComparar = Integer.compare(e2.getGolesFavor(), e1.getGolesFavor());
					// Si los goles a favor son iguales, comparar por diferencia de goles
					if (golesFavorComparar == 0) {
						return Integer.compare(e2.getDiferenciaGoles(), e1.getDiferenciaGoles());
					}
					return golesFavorComparar; // Retornar comparación de goles a favor
				}
				return puntosComparar; // Retornar comparación de puntos
			}
		});
		
		modeloTablaClasificacion.setRowCount(0); // Limpiar la tabla antes de llenarla
		for (int i = 0; i < listaEquipos.size(); i++) { // Iterar sobre la lista de equipos
			Equipo equipo = listaEquipos.get(i); // Obtener el equipo actual
			modeloTablaClasificacion.addRow(new Object[] { i + 1, equipo.getNombre(), equipo.getPuntos(),
					equipo.getGolesFavor(), equipo.getGolesContra(), equipo.getDiferenciaGoles() }); // Añadir fila a la tabla
		}
	}

	// Clase interna que representa a un equipo en la liga
	class Equipo {
		private String nombre; // Nombre del equipo
		private int golesFavor; // Goles a favor del equipo
		private int golesContra; // Goles en contra del equipo
		private int puntos; // Puntos acumulados por el equipo

		// Constructor de la clase Equipo
		public Equipo(String nombre) {
			this.nombre = nombre; // Asignar el nombre del equipo
			this.golesFavor = 0; // Inicializar goles a favor
			this.golesContra = 0; // Inicializar goles en contra
			this.puntos = 0; // Inicializar puntos
		}

		public String getNombre() {
			return nombre; // Retornar el nombre del equipo
		}

		public void agregarResultado(int golesFavor, int golesContra) {
			this.golesFavor += golesFavor; // Sumar goles a favor
			this.golesContra += golesContra; // Sumar goles en contra
		}

		public int getGolesFavor() {
			return golesFavor; // Retornar goles a favor
		}

		public int getGolesContra() {
			return golesContra; // Retornar goles en contra
		}

		public int getDiferenciaGoles() {
			return golesFavor - golesContra; // Calcular y retornar la diferencia de goles
		}

		public int getPuntos() {
			return puntos; // Retornar puntos acumulados
		}

		public void agregarPuntos(int puntos) {
			this.puntos += puntos; // Sumar puntos al total
		}
	}
}