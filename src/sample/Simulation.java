package sample;

public class Simulation {
    static int generation = 0;

    private Simulation() {
    }

    public static int getGeneration() {
        return generation;
    }

    static public void run(Case[][] cases) {
        System.out.println("Nous en somme a la géneration : " + generation);
        generation++;
        for (int i = 0; i < cases.length; i++)
            for (int j = 0; j < cases.length; j++) {
                if (cases[i][j].estMalade()) {
                    cases[i][j].peutChangerEtatApresMaladie();
                    maladeDepuisPlus(cases,i,j);
                    //Contamination
                    if (cases[i][j].estContagieux()) {
                        contaminationVoisins(cases, i, j);
                    }
                }
            }
    }

    /**
     * Permet la contamination des voisins d'une cases donnée
     *
     * @param cases le plateau
     * @param xCase les coordonnés x de la case
     * @param yCase les coordonnés y de la case
     */
    private static void contaminationVoisins(Case[][] cases, int xCase, int yCase) {
        for (int x = xCase - 1; x <= xCase + 1; x++) {
            for (int y = yCase - 1; y <= yCase + 1; y++) {
                if (Utile.estDansTableau(x, y)) {
                    if (cases[xCase][yCase].estVoisin(x, y)) {
                        if (cases[x][y].estSain()) {
                            cases[x][y].tombeMalade(Param.tauxContamination);
                        }
                    }
                }
            }
        }
    }

    private static void maladeDepuisPlus(Case[][] cases,int x,int y){
        if(cases[x][y].generationOuDevenuMalade!=getGeneration()){
            cases[x][y].maladeDepuis++;
        }
    }
}
