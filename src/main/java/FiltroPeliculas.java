import java.io.*;
import java.util.ArrayList;

public class FiltroPeliculas {
    public static void filtrarPorAnho(int i){
        File archivoPelis = new File("peliculas.csv");
        ArrayList<Pelicula> peliculas = new ArrayList<>();
        try(BufferedReader bfr = new BufferedReader(new FileReader(archivoPelis))) {
            String linea;
            while((linea = bfr.readLine()) != null) {
                var trozosLinea = linea.split(",");
                if ( i < Integer.parseInt(trozosLinea[2])){
                    peliculas.add(new Pelicula(
                            trozosLinea[0],
                            trozosLinea[1],
                            Integer.parseInt(trozosLinea[2]),
                            trozosLinea[3],
                            trozosLinea[4]
                    ));
                }
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        File dir = new File("salida");
        dir.mkdir();

            try(BufferedWriter bfw = new BufferedWriter(new FileWriter("PeliculasPosterioresA"+i+".csv"))) {
                for(Pelicula m : peliculas) {
                    bfw.write(m.getId() + ", " + m.getTitulo() + ", " + m.getAnho() + ", " + m.getDirector() + ", " + m.getGenero());
                    bfw.newLine();
                }
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
    }
}
