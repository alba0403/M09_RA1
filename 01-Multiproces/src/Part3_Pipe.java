import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.List;

public class Part3_Pipe {

  public static void main(String[] args) throws IOException, InterruptedException {
    System.out.println("Sistema operatiu detectat: " + SO.nomSO());
    System.out.println("=== Fitxers .java trobats ===");

    // primer proces
    ProcessBuilder llistar = new ProcessBuilder(SO.llistarFitxers());

    // segon proces
    ProcessBuilder filtrar = new ProcessBuilder(SO.filtrar(".java"));

    llistar.redirectErrorStream(true);
    filtrar.redirectErrorStream(true);

    List<ProcessBuilder> pipeline = Arrays.asList(llistar, filtrar);

    // connecta la sortida del primer proces amb l'entrada del segon
    List<Process> processos = ProcessBuilder.startPipeline(pipeline);

    Process ultimProces = processos.get(processos.size() - 1);

    //llegim la sortida final del pipeline
    try (BufferedReader br = new BufferedReader(
        new InputStreamReader(ultimProces.getInputStream()))) {
      String linia;
      while ((linia = br.readLine()) != null) {
        System.out.println(linia);
      }
    }

    for (Process proces : processos) {
      proces.waitFor();
    }

    System.out.println();
    System.out.println("Pipeline completat.");
  }
}
