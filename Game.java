public class Game {

   Player player1;
   IA player2;
   boolean gameIsFinished = false;

   Morpion myMorpion;

   Game (Player player1, IA player2, Morpion myMorpion) {
    this.player1 = player1;
    this.player2 = player2;
    this.myMorpion = myMorpion;
   }

   //Fonction qui permet de jouer le jeu.
   public void run () {

    Player player = player1;

    do {

        if (myMorpion.checkEndGame()) 
        {
            gameIsFinished = true;
            break;
        }

        Player currentPlayer = player1;
        if (player1.turn > player2.turn) currentPlayer = player2;

        //**Only to debug the program**
        //myMorpion.selectCell(currentPlayer);
        //myMorpion.displayValues();
        //System.out.println("L'id de "+currentPlayer.name+" est : "+currentPlayer.id);

        //Début du tour du joueur.
        currentPlayer.play(myMorpion, currentPlayer);
        player = currentPlayer;
        
    }
    while (!myMorpion.checkWinner(player));

    if (gameIsFinished) {
        myMorpion.display();
        System.out.println ("\t"+"La partie est terminée : égalité."+"\n \n");
    }
    else {
        myMorpion.display();
        Player winner = player;
        System.out.println("\t"+"Le gagnant est "+winner.name+"\n \n");
    }
   }
}
