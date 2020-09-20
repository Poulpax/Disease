package sample;

public class Utile {

    static public boolean estDansTableau(int x,int y){
        return x>=0 && x<=Param.nbCasesParLigne-1 &&
                y>=0 && y<=Param.nbCasesParLigne-1 ;
    }

}
