package gui;

import data.GestorEvento;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

public class VentanaRegistroEvento extends JFrame {
    private JTextField campoNombreEvento;
    private JTextField campoFechaEvento;
    private JTextField campoLugar;
    private JTextField campoArtistas;
    private JButton bNuevoEvento;
    private JButton bAgregarEvento;
    private JButton bVolver;
    private JPanel panelPrincipal;

    public VentanaRegistroEvento(){
    }
    public void mostrarInterfaz() {
        panelPrincipal = crearPanelPrincipal();
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        JLabel labelTitulo = crearLabelTitulo();
        panelPrincipal.add(labelTitulo);
        configurarDimensionesCampos();
        crearPanelesCampos(panelPrincipal);
        deshabilitarCampos();
        establecerBotones(panelPrincipal);

        bNuevoEvento.addActionListener(e -> {
            limpiarCampos();
            habilitarCampos();
            bAgregarEvento.setEnabled(true);
        });

        bAgregarEvento.addActionListener(e -> {
            boolean eventoAgregado = new GestorEvento().registrarDato(obtenerDatosEvento(), "C:/Users/Lenovo/Desktop/eventos.txt");
            if (eventoAgregado) {
                limpiarCampos();
                deshabilitarCampos();
                bAgregarEvento.setEnabled(false);
                JOptionPane.showMessageDialog(this, "El evento ha sido agregado con éxito.", "Éxito", JOptionPane.INFORMATION_MESSAGE);
            } else {
                JOptionPane.showMessageDialog(this, "Ha ocurrido un error al agregar el evento.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        });

        bVolver.addActionListener(e -> {
            dispose();
            new VentanaMenu().mostrarInterfaz();
        });

        add(panelPrincipal);
        pack();
        setLocationRelativeTo(null);
        setVisible(true);
    }

    private void crearPanelesCampos(JPanel panelPrincipal) {
        JPanel panelNombre = crearPanelCampo("Nombre del evento:", campoNombreEvento);
        panelPrincipal.add(panelNombre);
        JPanel panelFecha = crearPanelCampo("Fecha del evento:", campoFechaEvento);
        panelPrincipal.add(panelFecha);
        JPanel panelLugar = crearPanelCampo("Lugar:", campoLugar);
        panelPrincipal.add(panelLugar);
        JPanel panelArtistas = crearPanelCampo("Artistas:", campoArtistas);
        panelPrincipal.add(panelArtistas);
    }

    private void configurarDimensionesCampos() {
        campoNombreEvento = new JTextField(20);
        campoFechaEvento = new JTextField(20);
        campoLugar = new JTextField(20);
        campoArtistas = new JTextField(20);
    }

    private JPanel crearPanelPrincipal() {
        JPanel panelPrincipal = new JPanel();
        panelPrincipal.setLayout(new BoxLayout(panelPrincipal, BoxLayout.Y_AXIS));
        return panelPrincipal;
    }

    private JLabel crearLabelTitulo() {
        JLabel labelTitulo = new JLabel("AGREGAR EVENTO MUSICAL");
        labelTitulo.setFont(new Font("Arial", Font.BOLD, 14));
        labelTitulo.setBorder(new EmptyBorder(20, 0, 10, 0));
        labelTitulo.setAlignmentX(Component.CENTER_ALIGNMENT);
        return labelTitulo;
    }

    private JPanel crearPanelCampo(String labelText, JTextField textField) {
        JPanel panelCampo = new JPanel(new FlowLayout(FlowLayout.CENTER));
        JLabel labelCampo = new JLabel(labelText);
        panelCampo.add(labelCampo);
        panelCampo.add(textField);
        return panelCampo;
    }

    private void establecerBotones(JPanel panelPrincipal) {
        bNuevoEvento = crearBoton("Nuevo Evento", new Color(0, 150, 0));
        bAgregarEvento = crearBoton("Agregar Evento", new Color(255, 165, 0));
        bVolver = crearBoton("Volver al Inicio", new Color(250, 50, 50));
        JPanel panelBotones = crearPanelBotones(bNuevoEvento, bAgregarEvento, bVolver);
        bAgregarEvento.setEnabled(false);
        panelPrincipal.add(panelBotones);
    }


    private JButton crearBoton(String text, Color bgColor) {
        JButton button = new JButton(text);
        button.setBackground(bgColor);
        button.setForeground(Color.WHITE);
        button.setPreferredSize(new Dimension(150, 40));
        return button;
    }

    private JPanel crearPanelBotones(JButton agregarButton, JButton editarButton, JButton bVolver) {
        JPanel panelBotones = new JPanel(new FlowLayout(FlowLayout.CENTER));
        panelBotones.add(agregarButton);
        panelBotones.add(editarButton);
        panelBotones.add(bVolver);
        return panelBotones;
    }

    private void deshabilitarCampos() {
        campoNombreEvento.setEnabled(false);
        campoFechaEvento.setEnabled(false);
        campoLugar.setEnabled(false);
        campoArtistas.setEnabled(false);
    }

    private void habilitarCampos() {
        campoNombreEvento.setEnabled(true);
        campoFechaEvento.setEnabled(true);
        campoLugar.setEnabled(true);
        campoArtistas.setEnabled(true);
    }

    private void limpiarCampos() {
        campoNombreEvento.setText("");
        campoFechaEvento.setText("");
        campoLugar.setText("");
        campoArtistas.setText("");
    }

    private String[] obtenerDatosEvento() {
        String nombre = campoNombreEvento.getText();
        String fecha = campoFechaEvento.getText();
        String lugar = campoLugar.getText();
        String artistas = campoArtistas.getText();
        return new String[]{nombre, fecha, lugar, artistas};
    }

}