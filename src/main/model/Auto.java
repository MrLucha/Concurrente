package main.model;

import java.util.Observable;

public class Auto extends Observable implements Runnable{
    Nodo posicion;
    Nodo velocidad;
    public boolean status;

    public Auto(Nodo posicion){
        this.posicion = posicion;
        velocidad = new Nodo(10);
        status = true;
    }

    @Override
    public void run() {
        while (status){
            System.out.println("posicion actual: "+posicion.x);
            //se suma 10 a la posicion en x, x=20->x=30
            posicion.add(velocidad);
            System.out.println("posicion +10: "+posicion.x);

            //Se evalua el valor x del nodo(get) si ya paso los limites del anchor pane
            if (posicion.x > 500 || posicion.x < 1){
                //Convierte la velocidad a negativo, +1->--1
                velocidad.x=velocidad.x*-1;

            }
            System.out.println("velocidad.x: "+velocidad.x);
            try {
                Thread.sleep(200);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            this.setChanged();

            //Pasa la nueva posicion x para que el controller actualice la vista(se mueva)
            this.notifyObservers(posicion);
        }
    }

}
