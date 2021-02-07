package main.model;

public class Nodo {
    public int x;
    public int y;

    public Nodo(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public Nodo(int x) {
        this.x = x;
    }

    public void add(Nodo nodo){
        //Si veloocidad es +1, suma 1. 500->501
        //Si veloocidad es -1, resta 1. 501->500
        this.x += nodo.x;
    }

}
