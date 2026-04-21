class Nodo {
    int valor;
    Nodo izquierdo, derecho;

    public Nodo(int valor) {
        this.valor = valor;
        izquierdo = derecho = null;
    }
}

public class Parcial {
    Nodo raiz;

    public void insertar(int valor) {
        raiz = insertar(raiz, valor);
    }

    private Nodo insertar(Nodo raiz, int valor) {
        if (raiz == null) {
            raiz = new Nodo(valor);
            return raiz;
        }
        if (valor < raiz.valor) {
            raiz.izquierdo = insertar(raiz.izquierdo, valor);
        } 
        
        else {
            raiz.derecho = insertar(raiz.derecho, valor);
        }
        return raiz;
    }

    public void recorridoInOrden() {
        inOrden(raiz);
        System.out.println(); 
    }

    private void inOrden(Nodo raiz) {
        if (raiz != null) {
            inOrden(raiz.izquierdo);
            System.out.print(raiz.valor + " ");
            inOrden(raiz.derecho);
        }
    }

    public int contarHojas() {
        return contarHojas(raiz);
    }

    private int contarHojas(Nodo nodo) {
        if (nodo == null) return 0;
        if (nodo.izquierdo == null && nodo.derecho == null) return 1;
        
        return contarHojas(nodo.izquierdo) + contarHojas(nodo.derecho);
    }

    public void eliminar(int valor) {
        raiz = eliminar(raiz, valor);
    }

    private Nodo eliminar(Nodo raiz, int valor) {
        if (raiz == null) return raiz;

        if (valor < raiz.valor) {
            raiz.izquierdo = eliminar(raiz.izquierdo, valor);
        } else if (valor > raiz.valor) {
            raiz.derecho = eliminar(raiz.derecho, valor);
        } else {
            if (raiz.izquierdo == null) return raiz.derecho;
            else if (raiz.derecho == null) return raiz.izquierdo;

            raiz.valor = valorMinimo(raiz.derecho);
            raiz.derecho = eliminar(raiz.derecho, raiz.valor);
        }
        return raiz;
    }

    private int valorMinimo(Nodo raiz) {
        int min = raiz.valor;
        while (raiz.izquierdo != null) {
            min = raiz.izquierdo.valor;
            raiz = raiz.izquierdo;
        }
        return min;
    }

    public static void main(String[] args) {
        Parcial arbol = new Parcial();

       int[] datosIniciales = {55, 25, 75, 15, 35, 65, 8555, 25, 75, 15, 35, 65, 85};

        for (int n : datosIniciales) {
            arbol.insertar(n);
        }

        System.out.print("Secuencia In-Orden: ");
        arbol.recorridoInOrden();

        System.out.println("Cantidad de hojas en el árbol: " + arbol.contarHojas());

        System.out.println("\nInsertando el valor 30");
        arbol.insertar(30);
        System.out.print("Secuencia In-Orden actualizada: ");
        arbol.recorridoInOrden();

        System.out.println("\nEliminar nodo 65");
        arbol.eliminar(65);
        System.out.print("Secuencia In-Orden final: ");
        arbol.recorridoInOrden();
        
    }
}