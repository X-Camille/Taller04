package data;

import model.EventoMusical;

import java.io.*;
import java.util.ArrayList;
import java.util.Objects;

public class GestorEvento {
    private String[] lineas;

    public ArrayList<EventoMusical> leerArchivoEventos(String direccionArchivo) {
        ArrayList<EventoMusical> eventos = new ArrayList<>();

        try (BufferedReader br = new BufferedReader(new FileReader(direccionArchivo))) {
            String linea;
            while ((linea = br.readLine()) != null) {
                String[] datos = linea.split("/");

                if (datos.length >= 3) {
                    EventoMusical cafe = new EventoMusical(datos[0], datos[1], datos[2]);
                    eventos.add(cafe);
                }
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        if (eventos.isEmpty()) {
            System.out.println("No se encontraron datos en el archivo.");
        }
        return eventos;
    }

    public ArrayList<EventoMusical> buscarEventosPorNombre(String busqueda, String direccionArchivo){
        ArrayList<EventoMusical> eventos = leerArchivoEventos(direccionArchivo);
        ArrayList<EventoMusical> eventosEncontrados = new ArrayList<>(){};
        for (EventoMusical evento : eventos) {
            if (Objects.equals(evento.getNombreEvento(), busqueda)) {
                eventosEncontrados.add(evento);
            }
        }
        return eventosEncontrados;
    }

    public boolean registrarDato(String[] datosEvento, String direccionArchivo){
        boolean lineaVacia = false;
        try {
            EventoMusical evento = new EventoMusical(datosEvento[0], datosEvento[1], datosEvento[2]);
            File file = new File(direccionArchivo);
            if (!file.exists()) {
                lineaVacia = true;
            }
            FileWriter fw = new FileWriter(file, true);
            BufferedWriter bw = new BufferedWriter(fw);

            if (!lineaVacia) {
                bw.newLine();
            }
            bw.write(evento.toString());
            bw.close();
            return true;
        } catch (Exception e) {
            System.out.println("Error al ingresar dato, favor contactar con administrador");
        }
        return false;
    }

    public void cancelarEvento(EventoMusical eventoEliminado, String direccionArchivo) {
        ArrayList<EventoMusical> eventos = leerArchivoEventos(direccionArchivo);

        // Eliminar el evento correspondiente
        eventos.removeIf(evento -> evento.equals(eventoEliminado));

        try (BufferedWriter bw = new BufferedWriter(new FileWriter(direccionArchivo))) {
            for (EventoMusical evento : eventos) {
                bw.write(evento.toString());
                bw.newLine();
            }
        } catch (IOException e) {
            System.out.println("Error al cancelar el evento: " + e.getMessage());
        }
    }

}