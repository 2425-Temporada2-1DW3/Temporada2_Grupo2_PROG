// Importación de clases necesarias para la interfaz gráfica y gestión de eventos
import java.awt.Color; // Clase para manejar colores
import java.awt.EventQueue; // Clase para gestionar el hilo de eventos
import java.awt.Font; // Clase para manejar fuentes de texto
import java.awt.GridBagConstraints; // Clase para especificar restricciones de diseño en GridBagLayout
import java.awt.GridBagLayout; // Clase para un gestor de diseño flexible
import java.awt.Insets; // Clase para manejar márgenes
import java.awt.Toolkit; // Clase para obtener recursos del sistema
import java.awt.event.ActionEvent; // Clase para manejar eventos de acción
import java.awt.event.ActionListener; // Interfaz para escuchar eventos de acción
import javax.swing.ImageIcon; // Clase para manejar iconos de imagen
import javax.swing.JButton; // Clase para crear botones
import javax.swing.JFrame; // Clase para crear la ventana principal
import javax.swing.JLabel; // Clase para crear etiquetas de texto
import javax.swing.JOptionPane; // Clase para mostrar diálogos de mensajes
import javax.swing.JPanel; // Clase para crear paneles
import javax.swing.JPasswordField; // Clase para crear campos de contraseña
import javax.swing.JTextField; // Clase para crear campos de texto
import javax.swing.border.EmptyBorder; // Clase para manejar bordes vacíos

/**
 * Clase LoginWindow que representa una ventana de inicio de sesión.
 */
public class LoginWindow extends JFrame {
    private static final long serialVersionUID = 5223866328447369348L; // Serialización de la clase
    private JPanel contentPane; // Panel principal que contendrá los componentes
    private JTextField txtUsuario; // Campo de texto para el nombre de usuario
    private JPasswordField txtPassword; // Campo de texto para la contraseña

    public static void main(String[] args) {
        // Inicializa la ventana en el hilo de eventos de la GUI para evitar problemas
        // de concurrencia.
        EventQueue.invokeLater(() -> {
            try {
                LoginWindow frame = new LoginWindow(); // Crea una instancia de LoginWindow
                frame.setVisible(true); // Hace visible la ventana.
            } catch (Exception e) {
                e.printStackTrace(); // Imprime cualquier excepción en la consola.
            }
        });
    }

    // Constructor de la ventana de inicio de sesión
    public LoginWindow() {
        setTitle("Inicio Sesión"); // Establece el título de la ventana.
       
        // Establece el ícono de la ventana utilizando una imagen desde la ruta especificada.
        setIconImage(Toolkit.getDefaultToolkit().getImage("../media/Logo-transparente.png"));
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // Configura la operación de cierre de la ventana.
        setBounds(100, 100, 600, 400); // Establece la posición (x, y) y el tamaño (ancho, alto) de la ventana.

        // Configuración del panel principal
        contentPane = new JPanel(); // Crea un nuevo panel
        contentPane.setBackground(new Color(240, 240, 240)); // Establece el color de fondo del panel
        contentPane.setBorder(new EmptyBorder(10, 10, 10, 10)); // Establece un margen de 10 píxeles en todos los lados
        setContentPane(contentPane); // Establece el panel como contenido de la ventana
        contentPane.setLayout(new GridBagLayout()); // Establece el gestor de diseño GridBagLayout para el panel
        GridBagConstraints gbc = new GridBagConstraints(); // Crea un objeto para gestionar las restricciones de los componentes
        gbc.insets = new Insets(5, 5, 5, 5); // Establece el espaciado entre componentes.
        gbc.anchor = GridBagConstraints.CENTER; // Centra los componentes en la celda.

        // Logo
        JLabel lblLogo = new JLabel(""); // Crea una etiqueta para el logo
        // Establece el icono del logo utilizando una imagen desde la ruta especificada.
        lblLogo.setIcon(new ImageIcon("../media/Logo-transparente.png"));
        gbc.gridx = 0; // Coloca el logo en la columna 0
        gbc.gridy = 0; // Coloca el logo en la fila 0
        gbc.gridwidth = 2; // El logo ocupa dos columnas.
        contentPane.add(lblLogo, gbc); // Añade el logo al panel.

        // Etiqueta Usuario
        JLabel lblUsuario = new JLabel("Usuario:"); // Crea una etiqueta para el campo de usuario
        lblUsuario.setFont(new Font("Arial", Font.BOLD, 14)); // Establece la fuente de la etiqueta como negrita
        gbc.gridwidth = 1; // Restablece a una columna
        gbc.gridx = 0; // Coloca la etiqueta en la columna 0
        gbc.gridy = 1; // Coloca la etiqueta en la fila 1
        contentPane.add(lblUsuario, gbc); // Añade la etiqueta al panel

        // Campo de texto para el usuario
        txtUsuario = new JTextField(); // Crea un campo de texto para el nombre de usuario
        txtUsuario.setFont(new Font("Arial", Font.PLAIN, 14)); // Establece la fuente del campo de texto
        gbc.gridx = 1; // Coloca el campo de texto en la columna 1
        gbc.gridy = 1; // Coloca el campo de texto en la fila 1
        gbc.fill = GridBagConstraints.HORIZONTAL; // Asegura que el campo de texto llene el espacio horizontal
        contentPane.add(txtUsuario, gbc); // Añade el campo de texto al panel
        txtUsuario.setColumns(10); // Establece el ancho del campo de texto en 10 caracteres

        // Etiqueta Contraseña
        JLabel lblPassword = new JLabel("Contraseña:"); // Crea una etiqueta para el campo de contraseña
        lblPassword.setFont(new Font("Arial", Font.BOLD, 14)); // Establece la fuente de la etiqueta como negrita
        gbc.gridx = 0; // Coloca la etiqueta en la columna 0
        gbc.gridy = 2; // Coloca la etiqueta en la fila 2
        contentPane.add(lblPassword, gbc); // Añade la etiqueta al panel

        // Campo de contraseña
        txtPassword = new JPasswordField(); // Crea un campo de texto para la contraseña
        txtPassword.setFont(new Font("Arial", Font.PLAIN, 14)); // Establece la fuente del campo de texto
        gbc.gridx = 1; // Coloca el campo de contraseña en la columna 1
        gbc.gridy = 2; // Coloca el campo de contraseña en la fila 2
        contentPane.add(txtPassword, gbc); // Añade el campo de contraseña al panel

        // Botón Iniciar Sesión
        JButton btnIniciarSesion = new JButton("Iniciar Sesión"); // Crea un botón para iniciar sesión
        btnIniciarSesion.setFont(new Font("Arial", Font.BOLD, 14)); // Establece la fuente del botón como negrita
        btnIniciarSesion.setBackground(new Color(0, 120, 215)); // Establece el color de fondo del botón
        btnIniciarSesion.setForeground(Color.WHITE); // Establece el color del texto del botón
        gbc.gridx = 0; // Coloca el botón en la columna 0
        gbc.gridy = 3; // Coloca el botón en la fila 3
        gbc.gridwidth = 2; // El botón ocupa dos columnas

        // Acción para el botón Iniciar Sesión
        btnIniciarSesion.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // Obtiene y limpia los valores ingresados en los campos de texto
                String usuario = txtUsuario.getText().trim(); // Obtiene el texto del campo de usuario
                String password = new String(txtPassword.getPassword()).trim(); // Obtiene el texto del campo de contraseña

                // Verificación de usuario y contraseña
                if ("admin".equals(usuario) && "admin".equals(password)) { // Verifica si las credenciales son de admin
                    JOptionPane.showMessageDialog(null, "Bienvenido Admin."); // Muestra un mensaje de bienvenida
                    VentanaResultados ventanaAdmin = new VentanaResultados("admin"); // Crea una nueva ventana para el admin
                    ventanaAdmin.setVisible(true); // Hace visible la ventana del admin
                    dispose(); // Cierra la ventana actual
                } else if ("arbitro".equals(usuario) && "arbitro".equals(password)) { // Verifica si las credenciales son de árbitro
                    JOptionPane.showMessageDialog(null, "Bienvenido Arbitro."); // Muestra un mensaje de bienvenida
                    VentanaResultados ventanaArbitro = new VentanaResultados("arbitro"); // Crea una nueva ventana para el árbitro
                    ventanaArbitro.setVisible(true); // Hace visible la ventana del árbitro
                    dispose(); // Cierra la ventana actual
                } else {
                    JOptionPane.showMessageDialog(null, "Usuario o contraseña incorrectos."); // Muestra un mensaje de error si las credenciales son incorrectas.
                }
            }
        });

        // Acción cuando se presiona Enter en el campo de contraseña
        txtPassword.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                btnIniciarSesion.doClick(); // Simula un clic en el botón Iniciar Sesión al presionar Enter.
            }
        });

        contentPane.add(btnIniciarSesion, gbc); // Añade el botón Iniciar Sesión al panel.

        // Botón Invitado
        JButton btnInvitado = new JButton("Invitado"); // Crea un botón para acceso como invitado
        btnInvitado.setFont(new Font("Arial", Font.BOLD, 14)); // Establece la fuente del botón como negrita
        btnInvitado.setBackground(new Color(0, 120, 215)); // Establece el color de fondo del botón
        btnInvitado.setForeground(Color.WHITE); // Establece el color del texto del botón
        gbc.gridy = 4; // Coloca el botón en la fila 4
        contentPane.add(btnInvitado, gbc); // Añade el botón Invitado al panel
        btnInvitado.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                JOptionPane.showMessageDialog(null, "Bienvenido Invitado."); // Muestra un mensaje de bienvenida para el invitado
                VentanaResultados ventanaInvitado = new VentanaResultados("invitado"); // Crea una nueva ventana para el invitado
                ventanaInvitado.setVisible(true); // Hace visible la ventana del invitado
                dispose(); // Cierra la ventana actual
            }
        });

        // Centra la ventana en la pantalla.
        setLocationRelativeTo(null); // Establece la ubicación de la ventana en el centro de la pantalla.
    }
}