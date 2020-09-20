package sample;


import javafx.scene.control.RadioMenuItem;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

import java.util.Random;

public class Case {
    Etats etats;
    Rectangle rectangle;
    private int coordX;
    private int coordY;
    int generationOuDevenuMalade;
    int maladeDepuis;
    public static int nbMalade=0;
    public static int nbVaccine=0;
    public static int nbMort=0;



    public Case(Rectangle rectangle,int x,int y) {
        this.etats=randomEtat();
        this.coordX=x;
        this.coordY=y;
        this.rectangle = rectangle;
        colorise();
    }

    public Case(Etats etats, Rectangle rectangle) {
        this.etats = etats;
        this.rectangle = rectangle;
    }

    /**
     * GET ET SET
     */

    public void setEtats(Etats etats) {
        this.etats = etats;
    }

    public int getCoordX() {
        return coordX;
    }

    public int getCoordY() {
        return coordY;
    }

    public Etats getEtats() {
        return etats;
    }


    /**
     * Gestion des etats
     */

    /**
     * Permet de donner un etat aléatoire entre Sain, Malade et Vaccine selon les nombre entrés par l'utilisateur
     * @return L'etat qui à été tiré aléatoirement
     */
    private Etats randomEtat(){
        Random random = new Random();
        double probability = random.nextDouble();
        double probaVaccine = (double) Param.nombreVaccine/(Param.nbCasesParLigne*Param.nbCasesParLigne);
        double probaMalade = (double) Param.nombreMalade/(Param.nbCasesParLigne*Param.nbCasesParLigne);
        if(probability<=probaVaccine){
            nbVaccine++;
            return Etats.Vaccine;
        }else if (probability>probaVaccine && probability<=(probaVaccine+probaMalade)){
            nbMalade++;
            return Etats.Malade;
        }else{
            return Etats.Sain;
        }
    }

    /**
     * permet de savoir si une case est saine ou non
     * @return un boolean a true si la case et saine et false sinon
     */
    public boolean estSain(){
        return this.getEtats().equals(Etats.Sain);
    }
    /**
     * permet de savoir si une case est malade ou non
     * @return un boolean a true si la case et malade et false sinon
     */
    public boolean estMalade(){
        return this.getEtats().equals(Etats.Malade);
    }



    /**
     * Permet de savoir si une case est contagieuse, c'est à dire si elle est malade et contaminé depuis plus de 1 generation
     * @return un boolean a true si la case est contagieuse et false sinon
     */
    public boolean estContagieux(){
        return this.estMalade() && this.generationOuDevenuMalade!=Simulation.getGeneration();
    }

    /**
     * Permet de faire changer d'état une case, plus la case est malade depuis longtemps plus elle aura de chance de changer d'état
     */
    public void peutChangerEtatApresMaladie() {
        Random random = new Random();
        double proba =random.nextInt(100);
        if (proba <= exponetielleTauxChangementEtat() && proba!=0 && this.generationOuDevenuMalade!=Simulation.getGeneration()) {
            double probaMort =random.nextInt(100);
            if(probaMort !=0 && probaMort <=Param.tauxMortParGen){
                this.setEtats(Etats.Mort);
                nbMort++;
            }else {
                this.setEtats(Etats.Vaccine);
                nbVaccine++;
            }
        }

        this.colorise();
    }

    public double exponetielleTauxChangementEtat(){
        return Math.exp(this.maladeDepuis-12)/10;
    }

    /**
     * Gestion de la contamination
     */

    /**
     * permet de rendre malade une case selon une probabilité
     * @param proba la probabilité selon laquelle une case peut tomber malade
     */
    public void tombeMalade(int proba){
        Random random = new Random();
        double probaMalade = random.nextInt(100);
        if(probaMalade!=0 && probaMalade<=proba){
            this.setEtats(Etats.Malade);
            this.generationOuDevenuMalade=Simulation.getGeneration();
            nbMalade++;
            this.colorise();
        }
    }

    /**
     * Colorisation des cases
     */

    /**
     * permet de donner la couleur a une case selon son etat
     */
    private void colorise(){
        switch(etats){
            case Sain:
                this.rectangle.setFill(Color.GREEN);
                break;
            case Malade:
                this.rectangle.setFill(Color.RED);
                break;
            case Vaccine:
                this.rectangle.setFill(Color.BLUE);
                break;
            case Mort:
                this.rectangle.setFill(Color.BLACK);
                break;
        }
    }

    /**
     * colorise toutes les cases du plateau
     * @param cases le plateau
     */
    public static void coloriseAll(Case[][] cases){
        for (int i = 0; i < cases.length; i++) {
            for (int j = 0; j < cases.length; j++) {
                cases[i][j].colorise();
            }
        }
    }




    /**
     * Methodes Utiles
     */

    /**
     * permet de savoir si une case est voisine a une autre
     * @param x la coordonné X d'une des cases
     * @param y la coordonné Y d'une des cases
     * @return true si les cases sont voisine false sinon
     */
    public boolean estVoisin(int x,int y){
       return ((x>=this.coordX-1 && x<=this.coordX+1)
               && (y>=this.coordY-1 && y<=this.coordY+1)
               && (x!=this.coordX  || y!=this.coordY));
    }

    /**
     * permet de savoir si une cases est dans le plateau
     * @return true si la case est dans le plateau, false sinon
     */
    public boolean estDansTableau(){
        return this.coordX>=0 && this.coordX<=Param.nbCasesParLigne-1 &&
                this.coordY>=0 && this.coordY<=Param.nbCasesParLigne-1;
    }

}
