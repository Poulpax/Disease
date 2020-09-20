package sample;

import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.image.WritableImage;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.io.IOException;
import java.util.Arrays;

public class Graphic {

    static Case[][] plateautmp;

    static Timeline timeline;

    /**
     * permet de construire le plateau
     * @param primaryStage le stage sur lequelle le plateau est construit
     */
    public static void construireMap(Stage primaryStage){
        //definir la scene principale
        Group groupe = new Group();
        Scene scene = new Scene(groupe, Param.tailleFenetre, Param.tailleFenetre, Color.LIGHTGREY);
        primaryStage.setTitle("Disease");
        primaryStage.setScene(scene);
        //definir les acteurs et les habiller
        dessinEnvironnement(groupe);
        //afficher le theatre
        primaryStage.show();
    }

    /**
     * creation des cellules et de leurs habits
     */
    static void  dessinEnvironnement(Group groupe) {
        Case.nbMort = Case.nbMalade = Case.nbVaccine = 0;
        plateautmp= Arrays.copyOf(Param.cases, Param.cases.length);
        for (int i = 0; i < Param.nbCasesParLigne; i++)
            for (int j = 0; j < Param.nbCasesParLigne; j++) {
                //Permet d'initialiser les cases
                Rectangle r = new Rectangle(Param.tailleCase * i, Param.tailleCase * j, Param.tailleCase, Param.tailleCase);
                r.setFill(Color.TRANSPARENT);
                r.setStroke(Color.TRANSPARENT);
                plateautmp[i][j] = new Case(r,i,j);
                groupe.getChildren().add(r);
            }
        if(!Param.nbBaseCorrect()){
            groupe.getChildren().clear();
            dessinEnvironnement(groupe);
        }else{
            animation();
        }
    }

    static private void animation(){

        timeline = new Timeline(new KeyFrame(Duration.millis(Param.tempsAvantProchaineGen), actionEvent -> {
            Simulation.run(plateautmp);
            Case.coloriseAll(plateautmp);
        }));
        timeline.setCycleCount(Animation.INDEFINITE);
        timeline.play();
    }

    static public void stopAnimation(){
        timeline.stop();
    }
}
