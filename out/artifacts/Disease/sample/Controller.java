package sample;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import javax.xml.transform.sax.SAXSource;
import java.awt.event.MouseEvent;
import java.beans.EventHandler;
import java.net.URL;
import java.util.Random;
import java.util.ResourceBundle;

public class Controller implements Initializable {

    /**
     * Parametre Avancé
     */


    /**
     * Paramètre Maladie
     */
    @FXML
    Button buttonStart;

    @FXML
    Slider sliderTauxMortParGen;

    @FXML
    Label labelTauxMortParGen;

    public void setLabelTempsAvantMort(String labelTempsAvantMort) {
        this.labelTauxMortParGen.setText(labelTempsAvantMort);
    }

    @FXML
    Slider sliderTauxContamination;
    @FXML
    Label labelTauxContamination;

    public void setLabelTauxContamination(String labelTauxContamination) {
        this.labelTauxContamination.setText(labelTauxContamination);
    }

    @FXML
    Slider sliderNombreVaccine;
    @FXML
    Label labelTauxVaccine;

    public void setLabelTauxVaccine(String labelTauxVaccine) {
        this.labelTauxVaccine.setText(labelTauxVaccine);
    }

    @FXML
    Slider sliderNombreMalade;
    @FXML
    Label labelTauxMalade;

    public void setLabelTauxMalade(String labelTauxMalade) {
        this.labelTauxMalade.setText(labelTauxMalade);
    }

    /**
     * Paramètre Visuel
     */

    @FXML
    TextField textFieldTailleFenetre;

    @FXML
    TextField textFieldNbCaseParLigne;

    @FXML
    TextField textFieldTempsEntreGen;



    Stage stageDisease;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        Stage mainStage = new Stage();

        setVisualyDiseaseParam();

        stageDisease= new Stage();
        buttonStart.setOnAction((MouseEvent)->{
            onStartAction();
        });
        stageDisease.setOnCloseRequest((MouseEvent) -> {
            Graphic.stopAnimation();
            Simulation.generation=0;
            System.out.println("A la fin de la simulation il y avait " + Case.nbMalade + " malade, "+Case.nbVaccine+ " personne immunisé et "+Case.nbMort + " morts.");
        });
    }

    private void onStartAction(){
        System.out.println("test");
        setDiseaseParam();
        setVisualParam();
        Param.creatCases();
        try {
            Param.testParamCorrect();
            Graphic.construireMap(stageDisease);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * initialise les parametres de la maladie selon les entrées de l'utilisateur
     */
    private void setDiseaseParam() {
        Param.tauxMortParGen=(int)sliderTauxMortParGen.getValue();
        Param.tauxContamination=(int)sliderTauxContamination.getValue();
        Param.nombreVaccine=(int)sliderNombreVaccine.getValue();
        Param.nombreMalade=(int)sliderNombreMalade.getValue();
    }

    /**
     *  initialise les parametres visuels selon les entrées de l'utilisateur
     */
    private void setVisualParam() {
        Param.nbCasesParLigne=Integer.parseInt(textFieldNbCaseParLigne.getText());
        Param.tailleFenetre=Integer.parseInt(textFieldTailleFenetre.getText());
        Param.tempsAvantProchaineGen=Integer.parseInt(textFieldTempsEntreGen.getText());
    }



    //Initialisation des Paramètres
    //Maladie

    /**
     * permet de mettre a jour les slider dans le menus des parametres
     */
    private void setVisualyDiseaseParam(){
        //TauxMortParGen
        sliderTauxMortParGen.setOnMouseDragged((MouseEvent)->{majSliderTauxMortParGen();});

        sliderTauxMortParGen.setOnKeyPressed((MouseEvent) -> {majSliderTauxMortParGen();});

        sliderTauxMortParGen.setOnMouseClicked((MouseEvent) -> {majSliderTauxMortParGen();});

        //Taux Contamination
        sliderTauxContamination.setOnMouseDragged((MouseEvent) -> {majTauxContamination();});

        sliderTauxContamination.setOnKeyPressed((MouseEvent) -> {majTauxContamination();});

        sliderTauxContamination.setOnMouseClicked((MouseEvent) -> {majTauxContamination();});

        //sliderNombreVaccine
        sliderNombreVaccine.setOnMouseDragged((MouseEvent) -> {majSliderNombreVaccin();});

        sliderNombreVaccine.setOnKeyPressed((MouseEvent) -> {majSliderNombreVaccin();});

        sliderNombreVaccine.setOnMouseClicked((MouseEvent) -> {majSliderNombreVaccin();});

        //sliderNombreMalade
        sliderNombreMalade.setOnMouseDragged((MouseEvent) -> {majSliderNombreMalade();});

        sliderNombreMalade.setOnKeyPressed((MouseEvent) -> {majSliderNombreMalade();});

        sliderNombreMalade.setOnMouseClicked((MouseEvent) -> {majSliderNombreMalade();});

        /*
        //pb de 1 sur la value maybe ?
        textFieldNbCaseParLigne.setOnAction((MouseEvent)->{
            sliderNombreMalade.setMax((Double.parseDouble(textFieldNbCaseParLigne.getText())*Double.parseDouble(textFieldNbCaseParLigne.getText())-sliderNombreVaccine.getValue()));
            majSliderNombreMalade();
            sliderNombreVaccine.setMax((Double.parseDouble(textFieldNbCaseParLigne.getText())*Double.parseDouble(textFieldNbCaseParLigne.getText())-sliderNombreMalade.getValue()));
            majSliderNombreVaccin();
        });


         */

    }

    private void majSliderTauxMortParGen(){
        setLabelTempsAvantMort(String.valueOf((int) sliderTauxMortParGen.getValue()));
    }

    private void majTauxContamination(){
        setLabelTauxContamination(String.valueOf((int)sliderTauxContamination.getValue()));
    }

    private void majSliderNombreVaccin(){

        setLabelTauxVaccine(String.valueOf((int)sliderNombreVaccine.getValue()));
    }

    private void majSliderNombreMalade(){
        setLabelTauxMalade(String.valueOf((int)sliderNombreMalade.getValue()));
    }
}
