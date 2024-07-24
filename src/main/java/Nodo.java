public class Nodo{
  int nNodo;
  Nodo siguiente;
  boolean ciclo;

  public Nodo(){
    nNodo = -1;
    siguiente = null;
    ciclo = false;
  }

  public int getNumero(){
    return this.nNodo;
  }

}