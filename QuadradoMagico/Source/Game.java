package QuadradoMagico;

 
public class Game {
 
    private int[][] tab = new int[3][3];
    String trava = "";
    int travar = 0;

   

    public void fazJogada(int coluna, int linha, String num) {
        if (tab[coluna][linha] == 0) {
                tab[coluna][linha] = Integer.parseInt(num);
            }
    }

    public void verificaZero() {
            travar = 1;
            for (int cont = 0; cont < 3; cont++) {
                    for (int cont2 = 0; cont2 < 3; cont2++) {
                            if (tab[cont][cont2] == 0) {
                                    travar = 0;
                            }
                    }
            }

    }

    public Boolean verificaVit() {
            verificaZero();

            if (tab[0][0] + tab[0][1] + tab[0][2] == 15 && tab[1][0] + tab[1][1] + tab[1][2] == 15
                            && tab[2][0] + tab[2][1] + tab[2][2] == 15 &&
                            tab[0][0] + tab[1][0] + tab[2][0] == 15 && tab[0][1] + tab[1][1] + tab[2][1] == 15
                            && tab[0][2] + tab[1][2] + tab[2][2] == 15 &&
                            tab[0][0] + tab[1][1] + tab[2][2] == 15 && tab[2][0] + tab[1][1] + tab[0][2] == 15 && travar == 1) {
                    return true;
            }
            return false;
    }

    public Boolean verificaDe() throws DerrotaException{

            verificaZero();
            if ((tab[0][0] + tab[0][1] + tab[0][2] != 15 || tab[1][0] + tab[1][1] + tab[1][2] != 15
                            || tab[2][0] + tab[2][1] + tab[2][2] != 15 ||
                            tab[0][0] + tab[1][0] + tab[2][0] != 15 || tab[0][1] + tab[1][1] + tab[2][1] != 15
                            || tab[0][2] + tab[1][2] + tab[2][2] != 15 ||
                            tab[0][0] + tab[1][1] + tab[2][2] != 15 || tab[2][0] + tab[1][1] + tab[0][2] != 15)
                            && travar == 1) {
                   throw new DerrotaException();
            }
            return false;
    }

    public void limp() {
            for (int cont = 0; cont < 3; cont++) {
                    for (int cont2 = 0; cont2 < 3; cont2++) {
                            tab[cont][cont2] = 0;
                    }
            }

    }

}


