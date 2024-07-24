public class Main {
  public static void main(String[] args) {
    boolean loop = true;
    int menu = 0;
    GrafoLista grafo = new GrafoLista();
    Entrada scanner = new Entrada();
    System.out.println("Crear nuevo grafo:\n1.Grafo con datos\n2.Grafo vacio\nIngrese cualquier otro valor para salir");
    menu = scanner.intIn();
    switch (menu) {
      case 1:
        System.out.println("Generando grafo con datos");
        grafo.autoCarga(); //Permite que un vertice tenga un ciclo consigo mismo una unica vez, pero no permite que un vertice repita arco dos veces con el mismo vertice
      case 2:
        do {
          grafo.imprimirLista(); //la lista se mostrara automaticamente tras cualquier operacion. esta solo se imprime hasta las dimensiones cargadas en esta, por lo que comenzara con un unico dato 0
          System.out.println("\n\n1.Crear nodo\n2.Crear arco\n3.Buscar nodo\n4.Eliminar nodo\n5.Eliminar arco\n6.Exploracion en profundidad\n7.Salir");
          menu = scanner.intIn();
          switch (menu) {
            case 1:
              grafo.crearNodo(scanner.intIn());
              break;
            case 2:
              grafo.crearArco(scanner.intIn(), scanner.intIn());
              break;
            case 3:
              grafo.busqueda(scanner.intIn());
              break;
            case 4:
              grafo.borrarNodo(scanner.intIn());
              break;
            case 5:
              grafo.borrarArco(scanner.intIn(), scanner.intIn());
              break;
            case 6:
              grafo.explorarProfundidad();
              break;
            case 7:
              loop = false;
              break;
            default:
              System.out.println("Ingrese una opcion valida");
          }
        } while (loop);
        break;
      default:
        break;
    }
    scanner.close();
    System.out.println("Cerrando programa. . .");
  }
}