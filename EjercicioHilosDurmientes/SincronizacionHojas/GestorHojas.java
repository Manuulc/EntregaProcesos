package SincronizacionHojas;

import java.util.ArrayList;
import java.util.List;

public class GestorHojas extends Thread {

    private static List<String> lista = new ArrayList<String>();

    private static synchronized void escribirYMostrarHoja(){ // hacemos el metodo sincronizado para que hasta que un hilo no acabe no entre otro
                                                            // asi conseguimos que no vaya desordenado y lo llamamos en el run()
        for (int i = 0; i < 10; i++) {
            lista.add(new String("Texto" + i));
        }

        for (String string : lista) {
            System.out.println(string);
        }

        System.out.println("Fin de hilo");
    }


    @Override
    public void run() {
        escribirYMostrarHoja();
    }

    public static void main(String[] args) {
        for (int i = 0; i < 10; i++) {
            new GestorHojas().start();
        }

    }

}
