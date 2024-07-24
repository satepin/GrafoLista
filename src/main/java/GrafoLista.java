import java.util.Random;
import java.util.Stack;
import java.util.LinkedList;

public class GrafoLista {
  Random random = new Random();
  int Nodos;
  int maxNodo = 7;
  Nodo[] ListaAdy = new Nodo[maxNodo + 1];

  public GrafoLista() {
    int i;
    Nodos = 0;
    System.out.println("Limite de Nodos:" + maxNodo);
    for (i = 0; i < maxNodo + 1; i++) {
      ListaAdy[i] = null;
    }
  }

  public void crearNodo(int nNodo) {
    if (Nodos < maxNodo) {
      if (buscarNodo(nNodo) == -1) {
        ListaAdy[Nodos] = new Nodo();
        ListaAdy[Nodos].nNodo = nNodo;
        Nodos++;

      } else {
        System.out.println("\n\nEl Vertice ya existe");
      }
    } else {
      System.out.println("\n\nLa Lista de adyacencia se encuentra llena");
    }

  }

  public int buscarNodo(int nNodo) {
    for (int i = 0; i < Nodos + 1; i++) {
      if (ListaAdy[i] != null) {
        if (ListaAdy[i].nNodo == nNodo) {
          return i;
        }
      }
    }
    return -1;
  }

  public void crearArco(int nNodo1, int nNodo2) {
    int i;
    if (buscarNodo(nNodo1) != -1 && buscarNodo(nNodo2) != -1) {
      Nodo aux = ListaAdy[buscarNodo(nNodo1)];
      i = buscarNodo(nNodo1);
      while (aux.siguiente != null) {
        aux = aux.siguiente;
        if (aux.nNodo == nNodo2) {
          return;
        }
      }
      aux.siguiente = new Nodo();
      if (!aux.siguiente.ciclo) {
        aux.siguiente.nNodo = nNodo2;
        aux.siguiente.siguiente = null;
        if (nNodo1 == nNodo2) {
          aux.siguiente.ciclo = true;
        }
      }

    } else {
      System.out.println("\n\nUno de los Nodos no existe");
    }
  }

  public void busqueda(int nNodo) {
    int i;
    if (buscarNodo(nNodo) != -1) {
      i = buscarNodo(nNodo);
      System.out.println("\n\nEl Vertice " + ListaAdy[i].nNodo + " tiene arcos con: ");
      Nodo aux = ListaAdy[i];
      while (aux.siguiente != null) {
        System.out.print("-> " + aux.siguiente.nNodo);
        aux = aux.siguiente;
      }
      System.out.println("\n");
    } else {
      System.out.println("\n\nEl Vertice no existe");
    }
  }

  public void borrarNodo(int nNodo) {
    int j = buscarNodo(nNodo);
    Nodo aux;

    if (j != -1) {
      for (int i = 0; i < maxNodo; i++) {
        aux = ListaAdy[i];
        if (aux != null) {
          borrarArco(aux.nNodo, nNodo);
        }
      }
      aux = ListaAdy[j];
      while (aux != null) {
        Nodo siguiente = aux.siguiente;
        if (siguiente != null) {
          borrarArco(nNodo, siguiente.nNodo);
        }
        aux = siguiente;
      }
      nullR(ListaAdy[j]);
      while (j < maxNodo - 1) {
        ListaAdy[j] = ListaAdy[j + 1];
        j++;
      }
      ListaAdy[maxNodo - 1] = null;
    }
  }

  public void nullR(Nodo borrar) {
    if (borrar.siguiente != null) {
      nullR(borrar.siguiente);
    }
    borrar.siguiente = null;
  }

  public int buscarArco(int nNodo1, int nNodo2) {
    int i = 0;
    if (buscarNodo(nNodo1) != -1 && buscarNodo(nNodo2) != -1) {
      while (ListaAdy[i].siguiente != null) {
        if (ListaAdy[i].nNodo == nNodo1) {
          return 1;
        }
        i++;
      }
    }
    return -1;
  }

  public void borrarArco(int nNodo1, int nNodo2) {
    Nodo aux = ListaAdy[buscarNodo(nNodo1)];
    while (aux != null) {
      if (aux.siguiente != null && aux.siguiente.nNodo == nNodo2) {
        Nodo borrar = aux.siguiente;
        aux.siguiente = borrar.siguiente;
        borrar = null;
        System.out.println("Se ha borrado el arco entre " + nNodo1 + " y " + nNodo2);
      } else {
        aux = aux.siguiente;
      }
    }
  }

  public void imprimirLista() {
    int i;
    for (i = 0; i < maxNodo; i++) {
      if (ListaAdy[i] != null) {
        System.out.print(ListaAdy[i].nNodo);
        Nodo aux = ListaAdy[i];
        while (aux.siguiente != null) {
          System.out.print("-> " + aux.siguiente.nNodo);
          aux = aux.siguiente;
        }
      } else {
        break;
      }

      System.out.println(" -> null");
    }
  }

  public void autoCarga() {
    int i;
    for (i = 0; i < maxNodo; i++) {
      crearNodo(10 + random.nextInt(89));
    }
    for (i = 0; i < Nodos * 1 + 3; i++) {
      crearArco(ListaAdy[random.nextInt(Nodos)].getNumero(),
          ListaAdy[random.nextInt(Nodos)].getNumero());
    }
  }

  public void explorarProfundidad() {
    if (Nodos == 0) {
      System.out.println("El grafo está vacío.");
      return;
    }
    System.out.println();
    boolean[] visitado = new boolean[maxNodo];
    Stack<Integer> stack = new Stack<Integer>();

    for (int j = 0; j < Nodos; j++) {
      if (!visitado[j]) {
        if (ListaAdy[j] != null) {
          stack.push(ListaAdy[j].nNodo);

          while (!stack.isEmpty()) {
            int nNodo = stack.pop();
            int idx = buscarNodo(nNodo);

            if (!visitado[idx]) {
              System.out.print(nNodo + " ");
              visitado[idx] = true;
            }

            Nodo aux = ListaAdy[idx].siguiente;
            while (aux != null) {
              int idxAux = buscarNodo(aux.nNodo);
              if (!visitado[idxAux]) {
                stack.push(aux.nNodo);
              }
              aux = aux.siguiente;
            }
          }
        }

      }
    }
    System.out.println("\n\n");
  }

}