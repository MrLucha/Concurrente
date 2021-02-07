package main.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;

import javafx.animation.Animation;
import javafx.animation.TranslateTransition;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.util.Duration;
import main.model.Auto;
import main.model.Nodo;

import java.util.Observable;
import java.util.Observer;
import java.util.Random;

public class Controller implements Observer {
    ImageView imageView;
    Thread hilo;
    Auto auto;
    private boolean bandera;
    private int aciertos=0;
    private int errores=0;
    @FXML
    private AnchorPane anchorPane;

    @FXML
    public void initialize() throws FileNotFoundException {

        iniciar();

        //cursorPosition();
        /*Button button =new Button();
        button.setLayoutX(10);
        button.setLayoutY(10);
        button.setText("botton");
        anchorPane.getChildren().add(button);*/
    }

    @Override
    public void update(Observable o, Object arg) {
        Nodo posicion = ((Nodo)arg);
        imageView.setX(posicion.x);
        System.out.println("el image esta en la posicion: "+imageView.getX());
        //bola.setCenterX(posicion.x);
        //bola.setCenterY(posicion.y);
    }

    @FXML
    public void crearCarro(){
        //CREAR UNA IMAGEN
        //creating the file
        File file = new File("src/main/view/trump.png");

        //creating image and add url
        Image image = new Image(file.toURI().toString());

        //Creating the image view
        ImageView imageView = new ImageView(image);
        //System.out.println("hola");
        //Setting the image view parameters
        //posicion1=
        int x=(int)(Math.random()*503);
        int y=(int)(Math.random()*320);
        imageView.setX(x);
        imageView.setY(y);
        imageView.setFitWidth(97);
        imageView.setPreserveRatio(true);
        //imageView.setFitHeight(52);
        //System.out.println("id es: ");
        //System.out.println("ya paso");

        anchorPane.getChildren().add(imageView);

        TranslateTransition transition = new TranslateTransition();
        int duracion=(int)(Math.random()*5)+1;
        transition.setDuration(Duration.seconds(duracion));
        int tope=503-x;
        transition.setToX(tope);
        transition.setAutoReverse(true);
        transition.setCycleCount(Animation.INDEFINITE);
        transition.setNode(imageView);
        transition.play();

        imageView.setOnMouseClicked(mouseEvent -> {
            System.out.println("Toque auto");
            bandera=false;
            aciertos++;
            System.out.println("Aciertos: "+aciertos+" Errores: "+errores);
            imageView.setVisible(false);
            crearCarro();
        });
    }

    @FXML
    public void pruebaAnchor(){
        if (bandera==true){
            System.out.println("Toque anchor");
            errores++;
            System.out.println("Aciertos: "+aciertos+" Errores: "+errores);
        }
        else{
            bandera=true;
        }

    }


    /*@FXML
    public void cursorPosition2(){
        anchorPane.setOnMouseClicked(mouseEvent -> {
            int x=(int)mouseEvent.getX();
            int y =(int)mouseEvent.getY();
            System.out.println("x en: "+x+", y en: "+y);

            File file = new File("src/main/controller/camaro.png");
            Image image = new Image(file.toURI().toString());
            ImageView imageView = new ImageView(image);
            imageView.setX(x);
            imageView.setY(y);
            imageView.setFitWidth(180);
            imageView.setFitHeight(52);
            anchorPane.getChildren().add(imageView);
            TranslateTransition transition = new TranslateTransition();
            int duracion=(int)(Math.random()*20+1);
            transition.setDuration(Duration.seconds(duracion));
            int tope=425-x;
            transition.setToX(tope);
            transition.setAutoReverse(true);
            transition.setCycleCount(Animation.INDEFINITE);
            transition.setNode(imageView);
            transition.play();

            imageView.setOnMouseClicked(mouseEvent1 -> {
                System.out.println("te toque");
            });
        });
    }*/

    public void iniciar(){
        File file = new File("src/main/view/trump.png");
        Image image = new Image(file.toURI().toString());
        imageView = new ImageView(image);
        int x=(int)(Math.random()*503);
        int y=(int)(Math.random()*320);

        Nodo posicion = new Nodo(x,y);
        imageView.setX(x);
        imageView.setY(y);
        imageView.setFitWidth(97);
        imageView.setPreserveRatio(true);
        anchorPane.getChildren().add(imageView);

        auto = new Auto(posicion);
        auto.addObserver(this);
        hilo = new Thread(auto);
        hilo.start();


    }

    @FXML
    public void salir(){
        auto.status=false;
        Platform.exit();
        System.exit(1);
    }




    }
