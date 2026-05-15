/**
 * Classe d'utilitat que proporciona les comandes correctes
 * segons el sistema operatiu on s'executa el programa.
 */
public class SO {
  public static final boolean ES_WINDOWS =
    System.getProperty("os.name").toLowerCase().startsWith("win");

  /** Retorna la comanda per llistar fitxers del directori actual. */
  public static String[] llistarFitxers(){
    if (ES_WINDOWS) {
      return new String[]{"cmd", "/c", "dir"};
    } else {
      return new String[]{"ls", "-la"};
    }
  }

  /** Retorna la comanda per ordenar línies alfabèticament. */
  public static String[] ordenar(){
    if (ES_WINDOWS) {
      return new String[]{"cmd", "/c", "sort"};
    } else {
      return new String[]{"sort"};
    }
  }

  /**
   * Retorna la comanda per filtrar linies que contenen un patro.
   * Equivalent a: grep <patro> / findstr <patro>
   */
  public static String[] filtrar(String patro){
    if (ES_WINDOWS) {
      return new String[]{"cmd", "/c", "findstr", patro};
    } else {
      return new String[]{"grep", patro};
    }
  }

  public static String nomSO(){
    return System.getProperty("os.name");
  }
}
