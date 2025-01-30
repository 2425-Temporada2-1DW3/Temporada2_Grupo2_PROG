package aplicacionFutbol;
import javax.swing.*;
import java.awt.event.*;
import java.io.File;

public class FileChooserExample {
    public static void main(String[] args) {
        // Crear un JFrame
        JFrame frame = new JFrame("Ejemplo de JFileChooser");
        frame.setSize(400, 200);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(null);
        
        // Crear un botón para abrir el JFileChooser
        JButton button = new JButton("Abrir JFileChooser");
        button.setBounds(100, 50, 200, 30);
        frame.add(button);

        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Crear un JFileChooser
                JFileChooser fileChooser = new JFileChooser();

                // Abrir el cuadro de diálogo de selección
                int result = fileChooser.showOpenDialog(frame);

                // Comprobar si el usuario seleccionó un archivo
                if (result == JFileChooser.APPROVE_OPTION) {
                    File selectedFile = fileChooser.getSelectedFile();
                    JOptionPane.showMessageDialog(frame, "Archivo seleccionado: " + selectedFile.getAbsolutePath());
                }
            }
        });

        // Hacer visible el JFrame
        frame.setVisible(true);
    }
}

