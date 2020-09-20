package sample;

import java.util.Objects;

public class Param {




    /**
     *Paramètre visuel
     */

    static public int nbCasesParLigne=100;
    static public int tailleFenetre=800;
    static public int tempsAvantProchaineGen;

    static public Case[][] cases;

    static public int tailleCase;


    public static void setNbCasesParLigneX(int nbCasesParLigne) {
        Param.nbCasesParLigne = nbCasesParLigne;
    }
    public static void setTailleFenetre(int tailleFenetre) {
        Param.tailleFenetre = tailleFenetre;
    }
    public static void setTempsAvantProchaineGen(int tempsAvantProchaineGen) {
        Param.tempsAvantProchaineGen = tempsAvantProchaineGen;
    }

    /**
    * Parametre Avancé
    */
    static public int tauxChangementEtatDepuisMalade;

    /**
     * Paramètre Maladie
     */

    static public int tauxMortParGen;
    static public int tauxContamination;
    static public int nombreVaccine;
    static public int nombreMalade;


    public static void setTauxMortParGen(int genAvantMort) {
        Param.tauxMortParGen = tauxMortParGen;
    }

    public static void setTauxContamination(int tauxContamination) {
        Param.tauxContamination = tauxContamination;
    }

    public static void setTauxVacciné(int tauxVacciné) {
        Param.nombreVaccine = tauxVacciné;
    }

    public static void setTauxMalade(int tauxMalade) {
        Param.nombreMalade = tauxMalade;
    }

    /**
     * Regarde si les parametres sont correctes
     * @throws ImpossibleRateException le taux de malade + le taux de vacciné est plus grand que 100%
     */
    public static void testParamCorrect() throws ImpossibleRateException {
        testTauxUnder100();
        testNbPixelAffichable();
    }


    private static void testNbPixelAffichable() {
    }

    /**
     * Permet de lancé une erreur si le taux de malade + le taux de vacciné est plus grand que 100%
     * @throws ImpossibleRateException le taux de malade + le taux de vacciné est plus grand que 100%
     */
    private static void testTauxUnder100() throws ImpossibleRateException {
        if(nombreVaccine+nombreMalade>10000){
            throw new ImpossibleRateException("Taux de malade et de vacciné superieur à 100%");
        }
    }


    /**
     * Initialise le plateau
     */
    public static void creatCases() {
        tailleCase = tailleFenetre / nbCasesParLigne;
        cases = new Case[nbCasesParLigne][nbCasesParLigne];
    }


    public static boolean nbBaseCorrect() {
        //Mettre pourcentage d'erreur plutot que une nombre 
        double nbMaladeMin = Param.nombreMalade - ((double)Param.nombreMalade * 10/100);
        double nbMaladeMax = Param.nombreMalade+((double)Param.nombreMalade* 10/100);

        double nbVaccineMin = Param.nombreVaccine - ((double)Param.nombreVaccine* 10/100);
        double nbVaccineMax = Param.nombreVaccine+((double)Param.nombreVaccine* 10/100);

        if((Case.nbMalade>=nbMaladeMin&& Case.nbMalade<=nbMaladeMax)
            &&(Case.nbVaccine>=nbVaccineMin && Case.nbVaccine<=nbVaccineMax)){
            return true;
        }else{
            return false;
        }
    }
}
