package gui;

import data.GestorEvento;
import model.EventoMusical;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class VentanaCancelacionEvento extends JFrame {
    private DefaultTableModel tableModel;
    private JTable table;
    private JTextField campoBusqueda;

    public void mostrarInterfaz(){
        setTitle("Seleccione el evento a cancelar (búsqueda por nombre):");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setSize(500, 400);

        JPanel panel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);

        JLabel labelBuscador = new JLabel("Buscador:");
        gbc.gridx = 0;
        gbc.gridy = 0;
        panel.add(labelBuscador, gbc);

        campoBusqueda = new JTextField();
        campoBusqueda.setPreferredSize(new Dimension(200, campoBusqueda.getPreferredSize().height)); // Establecer un ancho prefijado
        campoBusqueda.setMinimumSize(new Dimension(200, campoBusqueda.getPreferredSize().height)); // Establecer un tamaño mínimo
        gbc.gridx = 1;
        gbc.gridy = 0;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        panel.add(campoBusqueda, gbc);

        JButton botonBuscar = new JButton("Buscar");
        botonBuscar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String busqueda = campoBusqueda.getText();
                ArrayList<EventoMusical> eventos = new GestorEvento().buscarEventosPorNombre(busqueda, "C:/Users/Lenovo/Desktop/eventos.txt");
                llenarTablaConDatos(eventos);
            }
        });
        gbc.gridx = 2;
        gbc.gridy = 0;
        gbc.fill = GridBagConstraints.NONE;
        panel.add(botonBuscar, gbc);

        // Configuración de la tabla para mostrar resultados
        JButton botonDiscontinuar = new JButton("Cancelar Evento");
        tableModel = new DefaultTableModel();
        table = new JTable(tableModel);
        JScrollPane scrollPane = new JScrollPane(table);
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.gridwidth = 3;
        gbc.fill = GridBagConstraints.BOTH;
        gbc.weightx = 1.0;
        gbc.weighty = 1.0;
        panel.add(scrollPane, gbc);

        // Botón para volver al menú de inicio
        JButton botonVolver = new JButton("Volver al menú de inicio");
        botonVolver.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                dispose(); // Cierra esta ventana
                new VentanaMenu().mostrarInterfaz();
            }
        });

        table.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
            public void valueChanged(ListSelectionEvent event) {
                if (!event.getValueIsAdjusting()) {
                    boolean filaSeleccionada = table.getSelectedRow() != -1;
                    botonDiscontinuar.setEnabled(filaSeleccionada);
                }
            }
        });

        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.gridwidth = 3;
        gbc.fill = GridBagConstraints.NONE;
        panel.add(botonVolver, gbc);

        getContentPane().add(panel);
        setLocationRelativeTo(null);
        setVisible(true);

        botonDiscontinuar.setEnabled(false); // Inicialmente deshabilitado
        botonDiscontinuar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                int filaSeleccionada = table.getSelectedRow();
                if (filaSeleccionada != -1) {
                    // Obtener datos de la fila seleccionada
                    String nombreEvento = (String) table.getValueAt(filaSeleccionada, 0);
                    String fechaEvento = (String) table.getValueAt(filaSeleccionada, 1);
                    String lugar = (String) table.getValueAt(filaSeleccionada, 2);
                    String artistas = (String) table.getValueAt(filaSeleccionada, 3);

                    // Crear un objeto EventoMusical con los datos obtenidos
                    EventoMusical eventoSeleccionado = new EventoMusical(nombreEvento, fechaEvento, lugar);

                    // Eliminar el evento usando el GestorEvento
                    new GestorEvento().cancelarEvento(eventoSeleccionado, "C:/Users/Lenovo/Desktop/eventos.txt");

                    // Obtener de nuevo los eventos después de la eliminación
                    String busqueda = campoBusqueda.getText();
                    ArrayList<EventoMusical> eventos = new GestorEvento().buscarEventosPorNombre(busqueda, "C:/Users/Lenovo/Desktop/eventos.txt");

                    // Actualizar la tabla con los datos actualizados después de la eliminación
                    llenarTablaConDatos(eventos);
                }
            }
        });
        gbc.gridx = 0;
        gbc.gridy = 3;
        gbc.gridwidth = 3;
        gbc.fill = GridBagConstraints.NONE;
        panel.add(botonDiscontinuar, gbc);
    }

    private void llenarTablaConDatos(ArrayList<EventoMusical> eventosMusicales) {
        String[] columnas = {"Nombre", "Fecha", "Lugar", "Artistas"};
        Object[][] datos = new Object[eventosMusicales.size()][columnas.length];

        for (int i = 0; i < eventosMusicales.size(); i++) {
           EventoMusical eventoMusical = eventosMusicales.get(i);
            datos[i][0] = eventoMusical.getNombreEvento();
            datos[i][1] = eventoMusical.getFechaEvento();
            datos[i][2] = eventoMusical.getLugar();
            datos[i][3] = eventoMusical.getArtistasAsociados();
        }

        tableModel.setDataVector(datos, columnas);
    }

}