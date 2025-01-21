package aplicacionFutbol;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import java.io.File;

public class Clasificacion {
    public static void main(String[] args) {
        JFrame frame = new JFrame("Tabla Clasificatoria");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(600, 400);

        // Columnas de la tabla
        String[] columnNames = {"Posición", "Equipo", "Puntos", "Goles a Favor", "Goles en Contra", "Diferencia de Goles"};
        DefaultTableModel model = new DefaultTableModel(columnNames, 0);
        JTable table = new JTable(model);
        JScrollPane scrollPane = new JScrollPane(table);
        frame.add(scrollPane);

        // Cargar datos desde el archivo XML
        cargarDatosDesdeXML(model, "C:\\\\xampp\\\\htdocs\\\\Temporada2_Grupo2_LM\\\\HTML\\\\clasificacion.xml", "2023");

        frame.setVisible(true);
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
}