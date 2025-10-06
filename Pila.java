import java.util.Stack;

public class Pila {
    private Stack<Publicacion> pila = new Stack<>();

    public boolean estaVacia(){ return pila.isEmpty(); }

    public void push(Publicacion p){ pila.push(p); }

    public Publicacion pop() throws Exception {
        if (estaVacia()) throw new Exception("Pila vacía");
        return pila.pop();
    }

    public Publicacion peek() throws Exception {
        if (estaVacia()) throw new Exception("Pila sin elementos");
        return pila.peek();
    }

    public int cantidad() { return pila.size(); }   // <-- usado por la Ventana

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (int i = pila.size() - 1; i >= 0; i--) {  // incluye i==0
            sb.append(pila.get(i).toString());
        }
        return "Pila\n" + sb.toString();
    }
}
