package NumeroOculto;

public class NumeroOculto extends Thread {

    private static boolean acierto = false;
    private static int numeroOculto;

    public static void main(String[] args) {

        numeroOculto = (int) (Math.random() * 101);

        System.out.println("El número oculto a adivinar es " + numeroOculto);


        for (int i = 0; i < 10; i++) {
            new NumeroOculto().start();
        }
    }

    @Override
    public void run() {

        int numeroRandom;
        int resultado;

        try {
            Thread.sleep(1500); // aqui pongo una pausa porque si no el hilo 1 siempre empieza antes y encontrará antes el numero
        } catch (InterruptedException e) {
            System.out.println("Se ha interrumpido la ejecucion del hilo");
        }

        while (!acierto) { // Mientras acierto sea falso seguira comprobando, es decir, hasta que alguien no acierte resultado no sera -1 porque en el
            // metodo de arriba hasta que acertado no sea true no devolvera -1, cuando sea -1 se interrumpiran los demas hilos

            numeroRandom = (int) (Math.random() * 101);
            System.out.println("El hilo " + currentThread().getName() + " ha sacado " + numeroRandom);
            resultado = propuestaNumero(numeroRandom); // guardo en resultado la devolucion de propuestaNumero con el random creado


            if (resultado == -1) {

                // cuando el metodo propuestaNumero devuelva -1 se interrumpen los hilos que no han ganado


                Thread.currentThread().interrupt();
                System.out.println("Se ha interrumpido el " + Thread.currentThread().getName() + " porque otro ya ha acertado el numero");


            }
        }
    }

    synchronized public static int propuestaNumero(int num) {

        int resultado = 0;

        if (!acierto && num == numeroOculto) {
            acierto = true;
            resultado = 1;

            try {
                System.out.println("Tenemos hilo ganador...");
                Thread.sleep(3000); // esta pausa es para dar emocion
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("¡¡¡¡El hilo " + Thread.currentThread().getName() + " ha adivinado el numero oculto!!!!");

        } else if (acierto) {
            resultado = -1;
        }
        return resultado;
    }
}
