public class App {
    public static void main(String[] args) {
        Game myGame = new Game(new Player("Player 1", "X"), new IA("IA Player", "O"), new Morpion());
        myGame.run();
    }
}