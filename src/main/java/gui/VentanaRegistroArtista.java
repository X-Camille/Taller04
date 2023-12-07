package gui;

import data.GestorArtista;
import data.GestorEvento;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

public class VentanaRegistroArtista extends JFrame {
    private JTextField campoNombreArtista;
    private JTextField campoGeneroMusical;
    private JButton bNuevoArtista;
    private JButton bAgregarArtista;
    private JButton bVolver;
    private JPanel panelPrincipal;

    public void mostrarInterfaz() {
        panelPrincipal = crearPanelPrincipal();
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        JLabel labelTitulo = crearLabelTitulo();
        panelPrincipal.add(labelTitulo);
        configurarDimensionesCampos();
        crearPanelesCampos(panelPrincipal);
        deshabilitarCampos();
        establecerBotones(panelPrincipal);

        bNuevoArtista.addActionListener(e -> {
            limpiarCampos();
            habilitarCampos();
            bAgregarArtista.setEnabled(true);
        });

        bAgregarArtista.addActionListener(e -> {
            boolean artistaAgregado = new GestorArtista().registrarDato(obtenerDatosArtista(), "C:/Users/Lenovo/Desktop/artistas.txt");
            if (artistaAgregado) {
                limpiarCampos();
                deshabilitarCampos();
                bAgregarArtista.setEnabled(false);
                JOptionPane.showMessageDialog(this, "El artista ha sido agregado con éxito.", "Éxito", JOptionPane.INFORMATION_MESSAGE);
            } else {
                JOptionPane.showMessageDialog(this, "Ha ocurrido un error al agregar el artista.", "Error", JOptionPane.ERROR_MESSAGE);
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
        JPanel panelNombreArtista = crearPanelCampo("Nombre del artista:", campoNombreArtista);
        panelPrincipal.add(panelNombreArtista);
        JPanel panelGeneroMusical = crearPanelCampo("Género musical:", campoGeneroMusical);
        panelPrincipal.add(panelGeneroMusical);
    }

    private void configurarDimensionesCampos() {
        campoNombreArtista= new JTextField(20);
        campoGeneroMusical = new JTextField(20);
    }

    private JPanel crearPanelPrincipal() {
        JPanel panelPrincipal = new JPanel();
        panelPrincipal.setLayout(new BoxLayout(panelPrincipal, BoxLayout.Y_AXIS));
        return panelPrincipal;
    }

    private JLabel crearLabelTitulo() {
        JLabel labelTitulo = new JLabel("AGREGAR ARTISTA");
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
        bNuevoArtista = crearBoton("Nuevo Artista", new Color(0, 150, 0));
        bAgregarArtista = crearBoton("Agregar Artista", new Color(255, 165, 0));
        bVolver = crearBoton("Volver al Inicio", new Color(250, 50, 50));
        JPanel panelBotones = crearPanelBotones(bNuevoArtista, bAgregarArtista, bVolver);
        bAgregarArtista.setEnabled(false);
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
        campoNombreArtista.setEnabled(false);
        campoGeneroMusical.setEnabled(false);
    }

    private void habilitarCampos() {
        campoNombreArtista.setEnabled(true);
        campoGeneroMusical.setEnabled(true);
    }

    private void limpiarCampos() {
        campoNombreArtista.setText("");
        campoGeneroMusical.setText("");
    }

    private String[] obtenerDatosArtista() {
        String nombre = campoNombreArtista.getText();
        String fecha = campoGeneroMusical.getText();
        return new String[]{nombre, fecha};
    }

}