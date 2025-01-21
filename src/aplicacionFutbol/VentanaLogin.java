package aplicacionFutbol;

import java.awt.EventQueue;
import javax.swing.*;
import java.awt.event.ActionListener;
import net.miginfocom.swing.MigLayout;
import java.awt.*;
import java.awt.event.ActionEvent;

public class VentanaLogin extends JFrame implements ActionListener {

    private JTextField User;
    private JLabel TextoPassword;
    private JPasswordField Password;
    private JButton btnLogin;
    private JLabel icono;
    private JButton btnSingUp;
    public static String RolSesion = "Usuario";
    private boolean UserMissing = true;

    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    VentanaLogin window = new VentanaLogin();
                    window.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    public VentanaLogin() {
        initialize();
    }

    private void initialize() {
        setTitle("Login");
        setFont(new Font("Courier New", Font.BOLD | Font.ITALIC, 12));
        setForeground(Color.DARK_GRAY);
        setIconImage(Toolkit.getDefaultToolkit().getImage("C:\\Users\\ik_1dw3a\\Documents\\GitHub\\Temporada2_Grupo2_PROG\\media\\Login top image.png"));
        getContentPane().setBackground(new Color(174, 220, 230));
        setName("vl");
        setMaximumSize(new Dimension(900, 800));
        setMinimumSize(new Dimension(500, 500));
        setBackground(new Color(128, 128, 0));
        getContentPane().setFont(new Font("Tahoma", Font.PLAIN, 15));
        setBounds(100, 100, 412, 298);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        getContentPane().setLayout(new MigLayout("", "[7px:n,grow][30%:n:30%][40%:n:40%][7px:n,grow]",
                "[5px:n,grow][40px:110px:200px][20px:45px:90px][20px:45px:90px][20px:60px:110px][20px:60px:110px][5px:n,grow]"));

        icono = new JLabel("");
        icono.setIcon(new ImageIcon("C:\\Users\\ik_1dw3a\\Documents\\GitHub\\Temporada2_Grupo2_PROG\\media\\Login top image.png"));
        getContentPane().add(icono, "cell 1 1 2 1,alignx center,aligny center");

        JLabel TextoUser = new JLabel("User");
        TextoUser.setFont(new Font("Tahoma", Font.PLAIN, 15));
        getContentPane().add(TextoUser, "cell 1 2,alignx right,aligny center");

        User = new JTextField();
        getContentPane().add(User, "cell 2 2,alignx left,aligny center");
        User.setColumns(20);

        TextoPassword = new JLabel("Password");
        TextoPassword.setFont(new Font("Tahoma", Font.PLAIN, 15));
        getContentPane().add(TextoPassword, "cell 1 3,alignx right,aligny center");

        Password = new JPasswordField();
        Password.setColumns(20);
        getContentPane().add(Password, "cell 2 3,alignx left,aligny center");

        btnSingUp = new JButton("Sing Up");
        btnSingUp.setFont(new Font("Tahoma", Font.PLAIN, 25));
        btnSingUp.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                CrearUsuario cu = new CrearUsuario(RolSesion);
                cu.setVisible(true);
            }
        });
        getContentPane().add(btnSingUp, "cell 1 5 2 1,alignx center,aligny top");

        btnLogin = new JButton("Login");
        btnLogin.setFont(new Font("Tahoma", Font.PLAIN, 25));
        getContentPane().add(btnLogin, "cell 1 4 2 1,alignx center,aligny top");
        btnLogin.addActionListener(this);

        CrearUsuario.dlm = new DefaultListModel<Usuario>();
        CrearUsuario.cargarUsuarios("Usuario.ser");
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        Object o = e.getSource();

        if (o == btnLogin) {
            String username = User.getText().trim();
            String password = new String(Password.getPassword()).trim();

            Usuario usuario;
            for (int i = 0; i <= CrearUsuario.dlm.getSize() - 1; i++) {
                usuario = CrearUsuario.dlm.getElementAt(i);
                if (username.equalsIgnoreCase(usuario.getNombre())) {
                    UserMissing = false;
                    if (!password.equals(usuario.getPwd())) {
                        JOptionPane.showMessageDialog(this, "Contraseña incorrecta.");
                        break;
                    } else {
                        RolSesion = usuario.getRol();
                        JOptionPane.showMessageDialog(this, "Bienvenido/a " + username);
                        System.out.println("Se ha iniciado sesión con el usuario: " + username + " y el rol " + RolSesion);
                        VentanaMain ventanaRol = new VentanaMain(RolSesion);
                        ventanaRol.setVisible(true);
                        this.dispose(); // Cierra la ventana actual correctamente
                        return;
                    }
                }
            }

            if (UserMissing) {
                JOptionPane.showMessageDialog(this, "Usuario inexistente.");
            }
        }
    }
}