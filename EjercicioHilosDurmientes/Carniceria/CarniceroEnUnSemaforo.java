package Carniceria;

import java.util.concurrent.Semaphore;

public class CarniceroEnUnSemaforo implements Runnable {

    Semaphore semaphore = new Semaphore(4);

    public static void main(String[] args) {

        CarniceroEnUnSemaforo ceus = new CarniceroEnUnSemaforo();


        for (int i = 0; i < 10; i++) {
            Thread hilo = new Thread(ceus);
            hilo.setName("Cliente " + i);
            hilo.start();
        }

    }

    @Override
    public void run() {
        try{
            semaphore.acquire();
            System.out.println("El " + Thread.currentThread().getName() + " esta siendo atendido");
            Thread.sleep((int)(Math.random()*10000));
            System.out.println("El " + Thread.currentThread().getName() + " ya ha sido atendido");
            semaphore.release();
        } catch (InterruptedException e) {
            System.out.println("Se ha interrumpido el hilo" );
        }
    }
}
