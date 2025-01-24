package aplicacionFutbol;

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
import java.io.File;

public class ExportarXML {
    public static void main(String[] args) {
        String[][][] data = {
            {
                {"1", "Equipo A", "80", "60", "30", "30"},
                {"2", "Equipo B", "75", "55", "35", "20"},
                {"3", "Equipo C", "70", "50", "40", "10"},
                {"4", "Equipo D", "65", "45", "35", "10"},
                {"5", "Equipo E", "60", "40", "35", "5"},
                {"6", "Equipo F", "55", "35", "40", "-5"}
            },
            {
                {"1", "Equipo A", "85", "65", "30", "35"},
                {"2", "Equipo B", "78", "58", "35", "23"},
                {"3", "Equipo C", "72", "52", "40", "12"},
                {"4", "Equipo D", "68", "48", "35", "13"},
                {"5", "Equipo E", "62", "42", "35", "7"},
                {"6", "Equipo F", "57", "37", "40", "-3"}
            },
            {
                {"1", "Equipo A", "90", "70", "30", "40"},
                {"2", "Equipo B", "80", "60", "35", "25"},
                {"3", "Equipo C", "75", "55", "40", "15"},
                {"4", "Equipo D", "70", "50", "35", "15"},
                {"5", "Equipo E", "65", "45", "35", "10"},
                {"6", "Equipo F", "60", "40", "40", "0"}
            }
        };

        String[] temporadas = {"2023", "2024", "2025"};

        try {
            DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder docBuilder = docFactory.newDocumentBuilder();
            Document doc = docBuilder.newDocument();

            Element rootElement = doc.createElement("liga");
            doc.appendChild(rootElement);

            for (int t = 0; t < data.length; t++) {
                Element temporada = doc.createElement("temporada");
                rootElement.appendChild(temporada);

                Element nombreTemporada = doc.createElement("nombre");
                nombreTemporada.appendChild(doc.createTextNode(temporadas[t]));
                temporada.appendChild(nombreTemporada);

                for (String[] row : data[t]) {
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
            String filePath = "C:\\Users\\ik_1dw3a\\Desktopclasificacion.xml";
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
}