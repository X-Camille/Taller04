package gui;

import data.GestorAsistente;
import data.GestorEvento;
import model.Asistente;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

public class VentanaRegistroAsistente extends JFrame {
    private JTextField campoContacto;
    private JTextField campoPreferenciaMusical;
    private JButton bNuevoAsistente;
    private JButton bAgregarAsistente;
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

        bNuevoAsistente.addActionListener(e -> {
            limpiarCampos();
            habilitarCampos();
            bAgregarAsistente.setEnabled(true);
        });

        bAgregarAsistente.addActionListener(e -> {
            boolean asistenteAgregado = new GestorAsistente().registrarDato(obtenerDatosEvento(), "C:/Users/Lenovo/Desktop/asistentes.txt");
            if (asistenteAgregado) {
                limpiarCampos();
                deshabilitarCampos();
                bAgregarAsistente.setEnabled(false);
                JOptionPane.showMessageDialog(this, "El asistente ha sido agregado con éxito.", "Éxito", JOptionPane.INFORMATION_MESSAGE);
            } else {
                JOptionPane.showMessageDialog(this, "Ha ocurrido un error al agregar el asistente.", "Error", JOptionPane.ERROR_MESSAGE);
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
        JPanel panelContacto = crearPanelCampo("Contacto:", campoContacto);
        panelPrincipal.add(panelContacto);
        JPanel panelPreferenciaMusical = crearPanelCampo("Preferencia musical:", campoPreferenciaMusical);
        panelPrincipal.add(panelPreferenciaMusical);
    }

    private void configurarDimensionesCampos() {
        campoContacto = new JTextField(20);
        campoPreferenciaMusical = new JTextField(20);
    }

    private JPanel crearPanelPrincipal() {
        JPanel panelPrincipal = new JPanel();
        panelPrincipal.setLayout(new BoxLayout(panelPrincipal, BoxLayout.Y_AXIS));
        return panelPrincipal;
    }

    private JLabel crearLabelTitulo() {
        JLabel labelTitulo = new JLabel("AGREGAR ASISTENTE");
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
        bNuevoAsistente = crearBoton("Nuevo Asistente", new Color(0, 150, 0));
        bAgregarAsistente = crearBoton("Agregar Asistente", new Color(255, 165, 0));
        bVolver = crearBoton("Volver al Inicio", new Color(250, 50, 50));
        JPanel panelBotones = crearPanelBotones(bNuevoAsistente, bAgregarAsistente, bVolver);
        bAgregarAsistente.setEnabled(false);
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
        campoContacto.setEnabled(false);
        campoPreferenciaMusical.setEnabled(false);
    }

    private void habilitarCampos() {
        campoContacto.setEnabled(true);
        campoPreferenciaMusical.setEnabled(true);
    }

    private void limpiarCampos() {
        campoContacto.setText("");
        campoPreferenciaMusical.setText("");
    }

    private String[] obtenerDatosEvento() {
        String contacto = campoContacto.getText();
        String preferenciaMusical = campoPreferenciaMusical.getText();
        return new String[]{contacto, preferenciaMusical};
    }

}