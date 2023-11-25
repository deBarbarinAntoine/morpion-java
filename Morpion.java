import java.util.ArrayList;
import java.util.List;

public class Morpion {

    //Initialisation des variables nécessaires pour le fonctionnement du Morpion.
    int xSize = 3;
    int ySize = 3;
    String [] [] cell = new String [xSize] [ySize];
    Integer [] [] player1Values = new Integer [xSize] [ySize];
    Integer [] [] player2Values = new Integer [xSize] [ySize];
    
    int lastX;
    int lastY;

    int maxX = xSize-1;
    int maxY = ySize-1;

    int nbCellRight;
    int nbCellLeft;

    int nbCellUp;
    int nbCellDown;

    int nbCellDiag1Up;
    int nbCellDiag1Down;

    int nbCellDiag2Up;
    int nbCellDiag2Down;

    int cellAlignLine = xSize;
    int cellAlignColumn = ySize;
    int cellAlignD1;
    int cellAlignD2;

    //To erase after fixing the program
    int countColumn = 0;
    int countLine = 0;
    int countDiag1 = 0;
    int countDiag2 = 0;
    Integer [] [] valuesDebug = new Integer [xSize] [ySize];
    Integer [] [] cellValueDebug = new Integer [xSize] [ySize];

    boolean newGame = true;
    boolean gameIsFinished = false;

    Morpion () {
        create();
    }

    public void create () {
    /*Liste avec toutes les infos des cases du Morpion.
    Index de la liste (x,y) en partant du bas à gauche, comme sur un graphique :
    +-------+-------+-------+
Y=2 |  0,2  |  1,2  |  2,2  |
    +-------+-------+-------+
Y=1 |  0,1  |  1,1  |  2,1  |
    +-------+-------+-------+
Y=0 |  0,0  |  1,0  |  2,0  |
    +-------+-------+-------+
       X=0     X=1     X=2
    */

        //Remplissage des cases du Morpion et des tableaux de valeurs de chaque joueur.
        for (int i = 0; i < xSize; i++) {
            for (int j = 0; j < ySize; j++) {
                cell [i] [j] = " ";
            }
        }

        for (int i = 0; i < xSize; i++) {
            for (int j = 0; j < ySize; j++) {
                player1Values [i] [j] = 0;
            }
        }

        for (int i = 0; i < xSize; i++) {
            for (int j = 0; j < ySize; j++) {
                player2Values [i] [j] = 0;
            }
        }
    }

    public void display () {

        System.out.println ("\n"+"\n"+"\n"+"\n"+"\n"+"\n"+"\n");
        System.out.println ("\t"+"\t"+"\t"+" +-------+-------+-------+");
        System.out.println ("\t"+"\t"+"Y-2"+"\t"+" |   "+cell [0] [2]+"   |   "+cell [1] [2]+"   |   "+cell [2] [2]+"   |");
        System.out.println ("\t"+"\t"+"\t"+" +-------+-------+-------+");
        System.out.println ("\t"+"\t"+"Y-1"+"\t"+" |   "+cell [0] [1]+"   |   "+cell [1] [1]+"   |   "+cell [2] [1]+"   |");
        System.out.println ("\t"+"\t"+"\t"+" +-------+-------+-------+");
        System.out.println ("\t"+"\t"+"Y-0"+"\t"+" |   "+cell [0] [0]+"   |   "+cell [1] [0]+"   |   "+cell [2] [0]+"   |");
        System.out.println ("\t"+"\t"+"\t"+" +-------+-------+-------+ \n");
        System.out.println ("\t"+"\t"+"\t"+"    X-0     X-1     X-2   "+"\n"+"\n");
    }

    //Fonction qui vérifie la disponibilité de la case sélectionnée et de jouer dans la case.
    public boolean checkCell (String X, String Y, String token, Player player) {

        boolean goodInput = true;
        int x = 4;
        int y = 4;

        newGame = false;

        //Conversion des coordonnées en entier.
        try {
            x = Integer.parseInt(X);
            y = Integer.parseInt(Y);
        }
        catch (Exception e) {
            goodInput = false;
        }

        if (goodInput) {
            

            if (x < 3 && y < 3)
            {
                //Écriture dans le Morpion.
                if (cell [x] [y] == " ") {
                    cell [x] [y] = token;
                    lastX = x;
                    lastY = y;

                    //Écriture dans le tableau de valeurs du joueur correspondant.
                    if (player.id == 0) {
                        player1Values [x] [y] = 1;
                    }
                    else {
                        player2Values [x] [y] = 1;
                    }

                    return true;
                }
            }
        }

        return false;
    }

    public boolean checkEndGame () {

        int emptyCells = 0;

        for (int i = 0; i < xSize; i++) {
            for (int j = 0; j < ySize; j++) {
                if (cell [i] [j] == " ") emptyCells++;
            }
        }

        if (emptyCells > 0) gameIsFinished = false;
        else gameIsFinished = true;

        if (gameIsFinished) return true;
        else return false;

    }

    public boolean checkWinner (Player player) {

        if (newGame) return false;
        if (player.turn < 3) return false;

        if (player.id == 0) {

            if(checkValues(player1Values)) return true;
            else return false;

        }
        else {

            if (checkValues(player2Values)) return true;
            else return false;
        }
        
    }

    public int locateCell (int x, int y) {

        int result = 0;
        
        nbCellRight = maxX-x;
        nbCellLeft = x;

        nbCellUp = maxY-y;
        nbCellDown = y;

        if (x == 1 && y == 1) {

            nbCellDiag1Up = (nbCellRight+nbCellUp)/2;
            nbCellDiag1Down = (nbCellLeft+nbCellDown)/2;

            nbCellDiag2Up = (nbCellLeft+nbCellUp)/2;
            nbCellDiag2Down = (nbCellRight+nbCellDown)/2;

        }

        else {

            nbCellDiag1Up = (nbCellRight*nbCellUp)/2;
            nbCellDiag1Down = (nbCellLeft*nbCellDown)/2;

            nbCellDiag2Up = (nbCellLeft*nbCellUp)/2;
            nbCellDiag2Down = (nbCellRight*nbCellDown)/2;
     
        }

        cellAlignD1 = nbCellDiag1Up+nbCellDiag1Down+1;
        cellAlignD2 = nbCellDiag2Up+nbCellDiag2Down+1;

        if (cellAlignD1 == 3) result += 1;
        if (cellAlignD2 == 3) result += 2;
        
        return result;

    }

    public String selectCell (Player player) {

        Integer [] [] values = new Integer [xSize] [ySize];
        Integer [] [] cellValue = new Integer [xSize] [ySize];

        for (int i = 0; i < xSize; i++) {
            for (int j = 0; j < ySize; j++) {
                cellValue [i] [j] = 0;
            }
        }
        
        if (player.id == 0) {

            for (int i = 0; i < xSize; i++) {
                for (int j = 0; j < ySize; j++) {
                    values [i] [j] = player1Values [i] [j] + (player2Values [i] [j] * 3);
                }
            }
        }
        else {

            for (int i = 0; i < xSize; i++) {
                for (int j = 0; j < ySize; j++) {
                    values [i] [j] = player2Values [i] [j] + (player1Values [i] [j] * 3);
                }
            }
        }

        for (int i = 0; i < xSize; i++) {
            for (int j = 0; j < ySize; j++) {

                List <Integer> sum = new ArrayList<>();
                
                if (cell [i] [j] == " ") {
                    switch (locateCell(i, j)) {

                        case 1:
                        sum.add(values [0] [0] + values [1] [1] + values [2] [2]);
                        break;

                        case 2:
                        sum.add(values [0] [2] + values [1] [1] + values [2] [0]);
                        break;

                        case 3:
                        sum.add(values [0] [0] + values [1] [1] + values [2] [2]);
                        sum.add(values [0] [2] + values [1] [1] + values [2] [0]);
                        break;

                        default:
                        break;
                    }

                    sum.add(values [i] [0] + values [i] [1] + values [i] [2]);
                    sum.add(values [0] [j] + values [1] [j] + values [2] [j]);

                    int alignIA = 0;
                    int alignEmpty = 0;

                    for (int value : sum) {

                        switch (value) {

                            case 2:
                            cellValue [i] [j] += 21;
                            break;

                            case 6:
                            cellValue [i] [j] += 12;
                            break;

                            case 1:
                            cellValue [i] [j] += 2;
                            alignIA ++;
                            break;

                            case 0:
                            cellValue [i] [j] += 1;
                            alignEmpty++;
                            break;

                            default:
                            break;
                        }

                        if (alignIA >= 2) {
                            cellValue [i] [j] += 3;
                            if (alignEmpty >= 1) {
                                cellValue [i] [j] += 1;
                            }
                        }

                        if (alignIA == 1 && alignEmpty >= 1) {
                            cellValue [i] [j] += 2;
                        }

                        if (alignEmpty >= 2) {
                            cellValue [i] [j] +=1;
                        }

                    }
                }
            }
        }

        int bestValue = 0;
        int bestX = 0;
        int bestY = 0;

        for (int i = 0; i < xSize; i++) {
            for (int j = 0; j < ySize; j++) {
                if (cellValue [i] [j] >= bestValue) {
                    bestValue = cellValue [i] [j];
                    bestX = i;
                    bestY = j;
                }
            }
        }

        //Only to debug the program.
        valuesDebug = values;
        cellValueDebug = cellValue;

        String result = bestX+" "+bestY;
        return result;
    }

    public boolean checkValues (Integer [] [] values) {

        int x = lastX;
        int y = lastY;

        locateCell(x, y);

        //int 
        countLine = 0;
        for (int i = 0; i < xSize && countLine < 3; i++)
        {
            countLine += values [i] [y];
            countLine *= values [i] [y];

        }
        if (countLine == 3) {
            return true;
        }

        //int 
        countColumn = 0;
        for (int i = 0; i < ySize && countColumn < 3; i++)
        {
            countColumn += values [x] [i];
            countColumn *= values [x] [i];
        }
        if (countColumn == 3) {
            return true;
        }

        //int 
        countDiag1 = 0;
        for (int i = x-nbCellDiag1Down; i < cellAlignD1 && countDiag1 < 3; i++)
        {
            countDiag1 += values [i] [i];
            countDiag1 *= values [i] [i];
        }
        if (countDiag1 == 3) {
            return true;
        }

        //int 
        countDiag2 = 0;
        int j = y+nbCellDiag2Up;
        for (int i = x-nbCellDiag2Up; i < cellAlignD2 && countDiag2 < 3; i++)
        {
            countDiag2 += values [i] [j];
            countDiag2 *= values [i] [j];
            j--;
        }
        if (countDiag2 == 3) {
            return true;
        }
        return false;
    }

    public void displayValues () {

        System.out.println ("nbCellRight : "+nbCellRight);
        System.out.println ("nbCellLeft : "+nbCellLeft);
        System.out.println ("nbCellUp : "+nbCellUp);
        System.out.println ("nbCellDown : "+nbCellDown);
        System.out.println ("nbCellDiag1Up : "+nbCellDiag1Up);
        System.out.println ("nbCellDiag1Down : "+nbCellDiag1Down);
        System.out.println ("nbCellDiag2Up : "+nbCellDiag2Up);
        System.out.println ("nbCellDiag2Down : "+nbCellDiag2Down);
        System.out.println ("cellAlignD1 : "+cellAlignD1);
        System.out.println ("cellAlignD2 : "+cellAlignD2);
        System.out.println ("lastX : "+lastX);
        System.out.println ("lastY : "+lastY);
        System.out.println ("countLine : "+countLine);
        System.out.println ("countColumn : "+countColumn);
        System.out.println ("countDiag1 : "+countDiag1);
        System.out.println ("countDiag2 : "+countDiag2);
        System.out.println ("cellAlignLine : "+cellAlignLine);
        System.out.println ("cellAlignColumn : "+cellAlignColumn);
        System.out.println ("maxX : "+maxX);
        System.out.println ("maxY : "+maxY);

        String v1 = "";
        String v2 = "";
        String v3 = "";
        String v4 = "";
        String v5 = "";
        String v6 = "";
        String v7 = "";
        String v8 = "";
        String v9 = "";

        for (int i = 0; i < xSize; i++) {
            for (int j = 0; j < ySize; j++) {

                end:
                switch (j) {

                    case 0:
                    switch (i) {

                        case 0:
                        v1 = valuesDebug [i] [j]+" "+cellValueDebug [i] [j];
                        break end;

                        case 1:
                        v2 = valuesDebug [i] [j]+" "+cellValueDebug [i] [j];
                        break end;

                        case 2:
                        v3 = valuesDebug [i] [j]+" "+cellValueDebug [i] [j];
                        break end;
                    }

                    case 1:
                    switch (i) {

                        case 0:
                        v4 = valuesDebug [i] [j]+" "+cellValueDebug [i] [j];
                        break end;

                        case 1:
                        v5 = valuesDebug [i] [j]+" "+cellValueDebug [i] [j];
                        break end;

                        case 2:
                        v6 = valuesDebug [i] [j]+" "+cellValueDebug [i] [j];
                        break end;
                    }

                    case 2:
                    switch (i) {

                        case 0:
                        v7 = valuesDebug [i] [j]+" "+cellValueDebug [i] [j];
                        break end;

                        case 1:
                        v8 = valuesDebug [i] [j]+" "+cellValueDebug [i] [j];
                        break end;

                        case 2:
                        v9 = valuesDebug [i] [j]+" "+cellValueDebug [i] [j];
                        break end;
                    }

                }
                
            }
        }

        System.out.println ("\t"+"\t"+"\t"+" +--------------------------------+");
        System.out.println ("\t"+"\t"+"Y-2"+"\t"+" |   "+v7+"   |   "+v8+"   |   "+v9+"   |");
        System.out.println ("\t"+"\t"+"\t"+" +--------------------------------+");
        System.out.println ("\t"+"\t"+"Y-1"+"\t"+" |   "+v4+"   |   "+v5+"   |   "+v6+"   |");
        System.out.println ("\t"+"\t"+"\t"+" +--------------------------------+");
        System.out.println ("\t"+"\t"+"Y-0"+"\t"+" |   "+v1+"   |   "+v2+"   |   "+v3+"   |");
        System.out.println ("\t"+"\t"+"\t"+" +--------------------------------+ \n");
        System.out.println ("\t"+"\t"+"\t"+"    X-0     X-1     X-2   "+"\n");

    }
}