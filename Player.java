import java.util.Scanner;

public class Player {
    
    String name;
    String token;
    int turn;
    int id;

    Player (String name, String token) {
        this.name = name;
        this.token = token;
        this.turn = 0;
        this.id = 0;
        
    }
    

    //Méthode permettant de jouer dans une case.
    public boolean putToken (Morpion morpion, Player player, String X, String Y) {

        //Vérification de la disponibilité de la case.
        if (morpion.checkCell(X, Y, token, player)) {
        turn++;
        return true;
        }
            return false;

    }

    Scanner inputUser = new Scanner(System.in);

    //Fonction permettant à un joueur de jouer.
    public void play (Morpion morpion, Player player) {

        
        morpion.display();
        System.out.println("\t"+name+" doit jouer."+"\n\t");
        System.out.println ("\t"+"Tapez la coordonnée X, puis ESPACE, puis la coordonnée Y."+"\n"+"\n");
        
        String X = inputUser.next();
        String Y = inputUser.next();
         
        while (!putToken (morpion, player, X, Y))
        {
            //Affichage en cas de case occupée (en boucle jusqu'à ce que la case soit libre).
            morpion.display();
            System.out.println ("La saisie est incorrecte ou la case est occupée.");
            System.out.println("\t"+name+" doit jouer."+"\n\t");
            System.out.println ("\t"+"Tapez la coordonnée X, puis ESPACE, puis la coordonnée Y."+"\n"+"\n");
                   
            X = inputUser.next();
            Y = inputUser.next();
        }
    }
}

//Sous-classe pour les IA.
class IA extends Player {

    
    IA (String name, String token) {
        super(name, token);
        this.id = 1;
    }

    //Fonction play en override pour que l'IA puisser jouer.
    public void play (Morpion morpion, Player player) {

        morpion.display();

        //Initialisation d'un Scanner pour lire les coordonnées de la case (x et y reviennent dans un seul String).
        Scanner readSelectCell = new Scanner(morpion.selectCell(player));

        String X = readSelectCell.next();
        String Y = readSelectCell.next();

        System.out.println(player.name+" is playing on x "+X+", y "+Y);
         
        //Repêchage d'une possible erreur de positionnement de la part de l'IA.
        if (!putToken (morpion, player, X, Y))
        {
            System.out.println ("Erreur d'IA");
        }
        
    }
    }