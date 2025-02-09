package aplicacionFutbol;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.util.List;
import java.util.ArrayList;

public class VentanaAnadirJugadores extends JFrame implements ActionListener {

    private static final long serialVersionUID = 1L;
    private JPanel contentPane;
    private JList<String> lstEquipos;
    private JList<Jugador> lstJugadores;
    private DefaultListModel<Jugador> dlmJugadores; // Modelo para la lista de jugadores
    private DefaultListModel<String> dlmEquipos; // Modelo para la lista de equipos
    private JButton btnSiguiente;
    private JLabel lblEquipo;
    private int NumeroEquipo = 0;
    private int jugadoresSeleccionados = 0;
    private String equipoSeleccionado = "";
    private List<Equipo> equipos;
    private List<Jugador> jugadoresSeleccionadosEquipo;
    private List<String> equiposCompletados; // Lista para seguir el estado de los equipos completados
    private List<Jugador> jugadoresYaSeleccionados; // Lista de jugadores ya asignados a un equipo
    private List<Jugador> jugadoresDisponibles; // Lista completa de jugadores

    // Constructor que recibe la temporada
    public VentanaAnadirJugadores(Temporada temporada) {
        this.equipos = temporada.getEquipos();  // Obtener la lista de equipos desde la temporada
        this.jugadoresSeleccionadosEquipo = new ArrayList<>();  // Inicializar la lista de jugadores seleccionados
        this.equiposCompletados = new ArrayList<>(); // Inicializar la lista de equipos completados
        this.jugadoresYaSeleccionados = new ArrayList<>(); // Inicializar la lista de jugadores ya seleccionados
        this.jugadoresDisponibles = new ArrayList<>(); // Lista de jugadores disponibles

        setTitle("Añadir Jugadores");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 600, 400);
        contentPane = new JPanel();
        contentPane.setLayout(new BorderLayout());
        setContentPane(contentPane);

        // Panel para el nombre del equipo y la lista de jugadores
        JPanel panelSuperior = new JPanel();
        contentPane.add(panelSuperior, BorderLayout.NORTH);
        panelSuperior.setLayout(new BorderLayout());

        lblEquipo = new JLabel("Selecciona un equipo");
        lblEquipo.setHorizontalAlignment(SwingConstants.CENTER);
        panelSuperior.add(lblEquipo, BorderLayout.NORTH);

        // Crear el modelo de lista para los equipos
        dlmEquipos = new DefaultListModel<>();
        for (Equipo equipo : equipos) {
            dlmEquipos.addElement(equipo.getNombre());
        }

        lstEquipos = new JList<>(dlmEquipos);
        lstEquipos.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        lstEquipos.addListSelectionListener(e -> mostrarJugadores());

        JScrollPane scrollEquipos = new JScrollPane(lstEquipos);
        panelSuperior.add(scrollEquipos, BorderLayout.CENTER);

        // Crear el modelo de lista para los jugadores
        dlmJugadores = new DefaultListModel<>();
        lstJugadores = new JList<>(dlmJugadores);
        lstJugadores.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION); // Permitir selección múltiple
        lstJugadores.setEnabled(false); // Inicialmente deshabilitar la selección

        // Añadir un ListSelectionListener para habilitar/deshabilitar el botón Siguiente
        lstJugadores.addListSelectionListener(e -> validarSeleccionJugadores());

        JScrollPane scrollJugadores = new JScrollPane(lstJugadores);
        contentPane.add(scrollJugadores, BorderLayout.CENTER);

        // Botón siguiente
        btnSiguiente = new JButton("Siguiente");
        btnSiguiente.addActionListener(this);
        btnSiguiente.setEnabled(false); // Deshabilitar el botón hasta que se seleccionen 5 jugadores
        contentPane.add(btnSiguiente, BorderLayout.SOUTH);

        // Cargar jugadores desde el archivo Jugadores.ser
        cargarJugadores("Jugadores.ser");
        VentanaMain.cargarJornadas("Jornadas.ser");
    }

    // Método que muestra los jugadores disponibles para el equipo seleccionado
    private void mostrarJugadores() {
        if (lstEquipos.getSelectedValue() != null) {
            equipoSeleccionado = lstEquipos.getSelectedValue();

            // Verificar si el equipo ya fue completado
            if (equiposCompletados.contains(equipoSeleccionado)) {
                lblEquipo.setText("El equipo " + equipoSeleccionado + " ya está completo.");
                return;  // Si el equipo ya está completo, no permitir más acciones
            }

            lblEquipo.setText("Jugadores para: " + equipoSeleccionado);

            // Limpiar la lista de jugadores seleccionados para este equipo
            jugadoresSeleccionadosEquipo.clear();

            // Habilitar la selección de jugadores
            lstJugadores.setEnabled(true);
            btnSiguiente.setEnabled(false); // Deshabilitar el botón hasta que se seleccionen 5 jugadores
            jugadoresSeleccionados = 0; // Resetear el contador de jugadores seleccionados

            // Filtrar los jugadores no asignados a ningún equipo y cargarlos en la lista
            actualizarJugadoresDisponibles();
        }
    }

    // Filtrar y actualizar la lista de jugadores no asignados
    private void actualizarJugadoresDisponibles() {
        dlmJugadores.clear();  // Limpiar la lista de jugadores

        // Agregar solo los jugadores que no han sido asignados a ningún equipo
        for (Jugador jugador : jugadoresDisponibles) {
            if (!jugadoresYaSeleccionados.contains(jugador)) { // Solo agregar jugadores no seleccionados
                dlmJugadores.addElement(jugador);
            }
        }
    }

    // Validar la cantidad de jugadores seleccionados y habilitar el botón Siguiente
    private void validarSeleccionJugadores() {
        List<Jugador> seleccionados = lstJugadores.getSelectedValuesList();
        jugadoresSeleccionados = seleccionados.size();
        if (jugadoresSeleccionados == 5) {
            btnSiguiente.setEnabled(true);  // Habilitar el botón solo cuando se seleccionen 5 jugadores
        } else {
            btnSiguiente.setEnabled(false); // Deshabilitar el botón si no son 5 jugadores
        }
    }

    // Método que maneja el evento del botón siguiente
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == btnSiguiente) {
        	if (VentanaMain.matrizJornadas.get(VentanaMain.matrizJornadas.size()-1).getJornadaNumero() >= 10) {
            // Obtener los jugadores seleccionados
            List<Jugador> seleccionados = lstJugadores.getSelectedValuesList();

            // Validar que se seleccionen 5 jugadores
            if (seleccionados.size() == 5) {
                // Agregar los jugadores seleccionados al equipo
            	Equipo equipo = new Equipo (lstEquipos.getName(), null, null, null, null, null);
            	
            	
                // Agregar estos jugadores a la lista de jugadores ya seleccionados
                jugadoresYaSeleccionados.addAll(seleccionados);

                // Deshabilitar los jugadores seleccionados
                for (Jugador jugador : seleccionados) {
                    dlmJugadores.removeElement(jugador);
                }

                // Marcar el equipo como completado
                equiposCompletados.add(equipoSeleccionado);

                // Deshabilitar la lista de jugadores
                lstJugadores.setEnabled(false);

                // Deshabilitar el botón siguiente
                btnSiguiente.setEnabled(false);

                // Deshabilitar la selección del equipo actual
                deshabilitarEquipo(equipoSeleccionado);

                // Mostrar algún mensaje de confirmación o pasar a otro equipo (según sea necesario)
                lblEquipo.setText("Equipo " + equipoSeleccionado + " completado. Selecciona el siguiente equipo.");
            }
        } else {
        	System.out.println("La temporada "+VentanaMain.matrizJornadas.get(VentanaMain.matrizJornadas.size()-1).getTemporadaNumero()+" no ha sido finalizada");
        }
        }
    }

    // Método para deshabilitar el equipo en la lista de equipos una vez completado
    private void deshabilitarEquipo(String equipo) {
        for (int i = 0; i < dlmEquipos.getSize(); i++) {
            if (dlmEquipos.getElementAt(i).equals(equipo)) {
                dlmEquipos.removeElementAt(i);
                break;
            }
        }
    }

    // Método que carga jugadores desde el archivo "Jugadores.ser"
    public void cargarJugadores(String archivo) {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(archivo))) {
            Jugador jugador;
            while ((jugador = (Jugador) ois.readObject()) != null) {
                jugadoresDisponibles.add(jugador);
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
}
