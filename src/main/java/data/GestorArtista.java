package data;

import model.Artista;
import model.EventoMusical;

import java.io.*;
import java.util.ArrayList;

public class GestorArtista {
    private String[] lineas;

    public ArrayList<Artista> leerArchivoArtistas(String direccionArchivo) {
        ArrayList<Artista> artistas = new ArrayList<>();

        try (BufferedReader br = new BufferedReader(new FileReader(direccionArchivo))) {
            String linea;
            while ((linea = br.readLine()) != null) {
                String[] datos = linea.split("/");

                if (datos.length >= 2) {
                    Artista artista = new Artista(datos[0], datos[1]);
                    artistas.add(artista);
                }
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        if (artistas.isEmpty()) {
            System.out.println("No se encontraron datos en el archivo.");
        }
        return artistas;
    }

    public boolean registrarDato(String[] datos, String direccionArchivo){
        boolean lineaVacia = false;
        try {
            Artista artista = new Artista(datos[0], datos[1]);
            File file = new File(direccionArchivo);
            if (!file.exists()) {
                lineaVacia = true;
            }
            FileWriter fw = new FileWriter(file, true);
            BufferedWriter bw = new BufferedWriter(fw);

            if (!lineaVacia) {
                bw.newLine();
            }
            bw.write(artista.toString());
            bw.close();
            return true;
        } catch (Exception e) {
            System.out.println("Error al ingresar dato, favor contactar con administrador");
        }
        return false;
    }
}