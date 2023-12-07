package gui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class VentanaMenu extends JFrame implements ActionListener {

    private JButton bCrearEventoMusical;
    private JButton bCancelarEventoMusical;
    private JButton bRegistrarArtista;
    private JButton bRegistrarAsistente;
    private JButton bVenderEntrada;

    public void mostrarInterfaz() {
        configurarVentana();
        JPanel panel = crearPanel();
        agregarComponentes(panel);
        mostrarVentana(panel);
    }

    private void configurarVentana() {
        setTitle("Menú");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(400, 400);
    }

    private JPanel crearPanel() {
        JPanel panel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);

        JLabel titleLabel = new JLabel("Menú", SwingConstants.CENTER);
        configurarTitulo(titleLabel, gbc, panel);

        bCrearEventoMusical = crearBoton("Crear Evento Musical", new Color(255, 255, 100));
        configurarBoton(bCrearEventoMusical, gbc, panel, 2);

        bCancelarEventoMusical = crearBoton("Cancelar Evento Musical", new Color(255, 255, 100));
        configurarBoton(bCancelarEventoMusical, gbc, panel, 3);


        bRegistrarArtista = crearBoton("Registrar Artista", new Color(255, 255, 100));
        configurarBoton(bRegistrarArtista, gbc, panel, 5);

        bRegistrarAsistente = crearBoton("Registrar Asistente", new Color(255, 255, 100));
        configurarBoton(bRegistrarAsistente, gbc, panel, 6);

        bVenderEntrada = crearBoton("Vender Entrada", new Color(255, 255, 100));
        configurarBoton(bVenderEntrada, gbc, panel, 7);

        return panel;
    }

    private void configurarTitulo(JLabel label, GridBagConstraints gbc, JPanel panel) {
        label.setFont(new Font("Arial", Font.BOLD, 20));
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 2;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        panel.add(label, gbc);
    }

    private JButton crearBoton(String texto, Color color) {
        JButton button = new JButton(texto);
        button.addActionListener(this);
        button.setBackground(color);
        return button;
    }

    private void configurarBoton(JButton button, GridBagConstraints gbc, JPanel panel, int gridY) {
        gbc.gridx = 0;
        gbc.gridy = gridY;
        gbc.gridwidth = 1;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        panel.add(button, gbc);
    }

    private void agregarComponentes(JPanel panel) {
        getContentPane().add(panel, BorderLayout.CENTER);
    }

    private void mostrarVentana(JPanel panel) {
        setLocationRelativeTo(null);
        setVisible(true);
    }

    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == bCrearEventoMusical) {
            dispose();
            new VentanaRegistroEvento().mostrarInterfaz();
        } else if (e.getSource() == bCancelarEventoMusical) {
            dispose();
            new VentanaCancelacionEvento().mostrarInterfaz();
        } else if (e.getSource() == bRegistrarArtista) {
            dispose();
            new VentanaRegistroArtista().mostrarInterfaz();
        } else if (e.getSource() == bRegistrarAsistente) {
            dispose();
            new VentanaRegistroAsistente().mostrarInterfaz();
        } else if (e.getSource() == bVenderEntrada) {
            dispose();
        }
    }
}