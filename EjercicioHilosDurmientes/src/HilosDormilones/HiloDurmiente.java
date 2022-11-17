package HilosDormilones;

public class HiloDurmiente extends Thread{


    /**
     * <b>METODO HEREDADO DE LA CLASE THREAD QUE CONTIENE UN BUCLE INFINITO PARA IMPRIMIR EN PANTALLA QUE HILOS SE ESTAN EJECUTANDO
     * DETENIENDOLOS ENTRE 1 Y 10 SEGUNDOS DE FORMA ALEATORIA</b>
     */
    @Override
    public void run() {

        while(true){
            System.out.printf("Soy el bucle %s y estoy trabajando" + System.lineSeparator(),getName());
            try{
                Thread.sleep((long)(Math.random()+10000+1000));
            } catch (InterruptedException e) {
                System.out.printf("El hilo llamado %s ha sido interrumpido" + System.lineSeparator(),getName());
                return;
            }
        }
    }

    public static void main(String[] args) {

        // para crear los 5 hilos
        HiloDurmiente hilo1 = new HiloDurmiente();
        HiloDurmiente hilo2 = new HiloDurmiente();
        HiloDurmiente hilo3 = new HiloDurmiente();
        HiloDurmiente hilo4 = new HiloDurmiente();
        HiloDurmiente hilo5 = new HiloDurmiente();

        // para darle el nombre a los hilos
        hilo1.setName("Hilo1");
        hilo1.setName("Hilo2");
        hilo1.setName("Hilo3");
        hilo1.setName("Hilo4");
        hilo1.setName("Hilo5");

        // el start llama al run
        hilo1.start();
        hilo2.start();
        hilo3.start();
        hilo4.start();
        hilo5.start();
    }


}
