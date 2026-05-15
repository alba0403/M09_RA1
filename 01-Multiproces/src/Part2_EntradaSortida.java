import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;

public class Part2_EntradaSortida {

  public static void main(String[] args) throws IOException, InterruptedException {
    System.out.println("Sistema operatiu detectat: " + SO.nomSO());
    System.out.println("Enviem al procés 'sort':");

    // creem el proces sort, que ordenara el text que li enviem
    ProcessBuilder pb = new ProcessBuilder(SO.ordenar());
    pb.redirectErrorStream(true);

    Process proces = pb.start();

    String[] fruites = {"plàtan", "poma", "albergínia", "cireres", "maduixa"};

    // enviem les fruites a l'entrada estandard del proces
    try (PrintWriter pw = new PrintWriter(proces.getOutputStream())) {
      for (String fruita : fruites) {
        System.out.println("-> " + fruita);
        pw.println(fruita);
      }
    }

    System.out.println();
    System.out.println("Resposta del procés 'sort' (ordenat):");

    // llegim la sortida ordenada que retorna sort
    try (BufferedReader br = new BufferedReader(
        new InputStreamReader(proces.getInputStream()))) {
      String linia;
      while ((linia = br.readLine()) != null) {
        System.out.println("<- " + linia);
      }
    }

    int codiRetorn = proces.waitFor();
    System.out.println();
    System.out.println("Codi de retorn: " + codiRetorn);
  }
}
