package data;

import model.Artista;
import model.Asistente;
import model.EventoMusical;

import java.io.*;
import java.util.ArrayList;

public class GestorAsistente {
    private String[] lineas;

    public ArrayList<Asistente> leerArchivoAsistentes(String direccionArchivo) {
        ArrayList<Asistente> asistentes = new ArrayList<>();

        try (BufferedReader br = new BufferedReader(new FileReader(direccionArchivo))) {
            String linea;
            while ((linea = br.readLine()) != null) {
                String[] datos = linea.split("/");

                if (datos.length >= 2) {
                    Asistente asistente = new Asistente(datos[0], datos[1]);
                    asistentes.add(asistente);
                }
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        if (asistentes.isEmpty()) {
            System.out.println("No se encontraron datos en el archivo.");
        }
        return asistentes;
    }

    public boolean registrarDato(String[] datos, String direccionArchivo){
        boolean lineaVacia = false;
        try {
            Asistente asistente = new Asistente(datos[0], datos[1]);
            File file = new File(direccionArchivo);
            if (!file.exists()) {
                lineaVacia = true;
            }
            FileWriter fw = new FileWriter(file, true);
            BufferedWriter bw = new BufferedWriter(fw);

            if (!lineaVacia) {
                bw.newLine();
            }
            bw.write(asistente.toString());
            bw.close();
            return true;
        } catch (Exception e) {
            System.out.println("Error al ingresar dato, favor contactar con administrador");
        }
        return false;
    }
}