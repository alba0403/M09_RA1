import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Part1_LlegirSortida {

  public static void main(String[] args) throws IOException, InterruptedException {
    System.out.println("Sistema operatiu detectat: " + SO.nomSO());
    System.out.println("=== Contingut del directori ===");

    // creem el proces que llistara els fitxers del directori actual
    ProcessBuilder pb = new ProcessBuilder(SO.llistarFitxers());

    pb.redirectErrorStream(true);

    Process proces = pb.start();

    // llegim la sortida del proces linia a linia
    try (BufferedReader br = new BufferedReader(
        new InputStreamReader(proces.getInputStream()))) {
      String linia;
      while ((linia = br.readLine()) != null) {
        System.out.println(linia);
      }
    }

    int codiRetorn = proces.waitFor();
    System.out.println();
    System.out.println("El procés ha acabat amb codi: " + codiRetorn);
  }
}
