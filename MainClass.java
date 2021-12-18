import java.util.InputMismatchException;
import java.util.Random;
import java.util.Scanner;

public class MainClass {
    //int playerWins;
    static int playerWins = 0;
    static int EnemyWins = 0;
    public static void main(String[] args) {
        while (true){
            gameClass gameObject = new gameClass();
            gameObject.displayBoards(Constants.player);
            gameObject.displayBoards(Constants.enemy);
            //gameObject.changeBoardContent(Constants.enemy,1,1,"M");
            //gameObject.changeBoardContent(Constants.enemy, 1,2,"K");
            //gameObject.changeBoardContent(Constants.enemy, 10,10, "C");
            //gameObject.displayBoards();
            Scanner scn = new Scanner(System.in);
            while (true){
                System.out.println("ENTER COMMAND:");
                System.out.println("P : Play On, Q : Quit Match ");
                String command = scn.next();
                if(command.equalsIgnoreCase("p")){
                    try{
                        System.out.println("ENTER X Y TO ATTACK ENEMY: ");
                        int x = scn.nextInt();
                        int y = scn.nextInt();
                        gameObject.Attack(x, y, Constants.enemy);
                        if(gameObject.winner == Constants.player){
                            System.out.println("PLAYER WINS!");
                            //Constants.playerWins+=1;
                            playerWins+=1;
                            break;
                        }

                        System.out.println("NOW ENEMY WILL ATTACK YOU: ");
                        Random rand = new Random();
                        int enemyx = rand.nextInt(10);
                        int enemyy = rand.nextInt(10);
                    /*int enemyx = scn.nextInt();
                    int enemyy = scn.nextInt();*/
                        System.out.println("ENEMY GENERATED X : "+ enemyx + " " + " Y : "+ enemyy);
                        gameObject.Attack(enemyx, enemyy, Constants.player);
                        if(gameObject.winner == Constants.enemy){
                            System.out.println("ENEMY WINS!");
                            EnemyWins+=1;
                            break;
                        }
                    }catch (Exception e){
                        System.out.println("Enter valid Co-ordinates.");
                    }
                }
                else if(command.equalsIgnoreCase("q")){
                    System.out.println("YOU CHOSE TO QUIT THIS MATCH.");
                    break;
                }
                else{
                    System.out.println("Enter Valid Command.");
                    continue;
                }

            }
            System.out.println("STANDING :");
            System.out.println("PLAYER WINS: " +playerWins + " --- "+ " ENEMY WINS: "+ EnemyWins);
            System.out.println("Press E to exit console, any other key to play another match");
            String comm = scn.next();
            if(comm.equalsIgnoreCase("e")){
                break;
            }
            else{
                continue;
            }
        }
        System.out.println("FINAL STANDING : ");
        System.out.println("PLAYER WINS: " +playerWins + " --- "+ " ENEMY WINS: "+ EnemyWins);



        //System.out.println("ENDED");
    }
}