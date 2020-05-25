
// The runner of this game
public class GameRunner {
    // the size of the screen
    int width = 1100;
    int height = 583;
    // defining
    Frog frog = new Frog(new CartPt(width/ 2, height - 25));
    Integer score = 0; 
    Integer lives = 3;
    
    
    // To run the game
    void run() {
        Playground firstWorld = new Playground(frog, score, lives);
        firstWorld.bigBang(width, height, 0.2);
    }
    
    // main function
    public static void main(String[] argv) {
        GameRunner aor = new GameRunner();
        aor.run();
    }
    
}
