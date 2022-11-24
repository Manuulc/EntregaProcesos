package Carniceria;

import java.util.concurrent.Semaphore;

public class SemaforoCharcuteria implements Runnable{

    Semaphore semaforoCarniceria = new Semaphore(4);
    Semaphore semaforoCharcuteria = new Semaphore(2);

    @Override
    public void run() {

        boolean entraEnCarniceria = false;
        boolean entraEnCharcuteria = false;

        // mientras no haya entrado en la carniceria ni en la charcuteria se comprobaran los huecos en el semaforo y si ha entrado o no
        while(!entraEnCarniceria || !entraEnCharcuteria){

            // aqui compruebo si hay sitio en la carniceria y si el hilo ya ha entrado en ella
            if(semaforoCarniceria.availablePermits() > 0 && !entraEnCarniceria){
                pasarCarniceria();
                entraEnCarniceria = true; // le cambiamos el booleano para que no vuelva a entrar en la carniceria
            }

            // aqui compruebo si hay sitio en la carniceria y si el hilo ya ha entrado en ella
            if(semaforoCharcuteria.availablePermits() > 0 && !entraEnCharcuteria){
                pasarCharcuteria();
                entraEnCharcuteria = true; // le cambiamos el booleano para que no vuelva a entrar en la charcuteria
            }

        }

    }

    public void pasarCarniceria(){
        try{
            semaforoCarniceria.acquire(); // coge sitio en el semaforo
            System.out.println("El " + Thread.currentThread().getName() + " esta siendo atendido en la carniceria");
            Thread.sleep((int) (Math.random()*10000)); // pausa mientras se le atiende
            System.out.println("El " + Thread.currentThread().getName() + " ya ha sido atendido en la carniceria");
            semaforoCarniceria.release(); // se libera un hueco del semaforo

        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void pasarCharcuteria(){
        try{
            semaforoCharcuteria.acquire(); // coge sitio en el semaforo
            System.out.println("El " + Thread.currentThread().getName() + " esta siendo atendido en la charcuteria");
            Thread.sleep((int) (Math.random()*10000)); // pausa mientras se le atiende
            System.out.println("El " + Thread.currentThread().getName() + " ya ha sido atendido en la charcuteria");
            semaforoCharcuteria.release(); // se libera un hueco del semaforo

        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {

        SemaforoCharcuteria sc = new SemaforoCharcuteria();

        // creo los hilos con el objeto de la clase SemaforoCharcuteria
        for (int i = 0; i < 10; i++) {
            Thread hilo = new Thread(sc);
            hilo.setName("Cliente " + i);
            hilo.start();
        }

    }
}
