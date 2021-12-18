import java.util.ArrayList;
import java.util.Random;
import java.util.concurrent.CopyOnWriteArrayList;

class CoOrd{
    public int x;
    public int y;


    CoOrd(){
        x = -99;
        y = -99;
    }

    CoOrd(int newx, int newy){
        x = newx;
        y = newy;
    }
}

/*class Monster{
    int MonsterName;
    int MonsterBoard;

    Monster(int name, int board){
        MonsterBoard = name;
    }
}*/

class Ship{
    int ShipName;
    int ShipLength;
    boolean ShipAlive;
    int shipBoard;

    //Ship(){}

    Ship(int name, int l, int board){
        ShipName = name;
        ShipLength = l;
        ShipAlive = true;
        shipBoard = board;
    }
}

public class gameClass {
    public int winner;
    private String[][] playerBoardMatrix;
    private String[][] EnemyBoardMatrix;
    private int playerPoint;
    private int EnemyPoint;
    //private CoOrds[] occupiedPositions;
    private ArrayList<Ship> playerShips;
    private ArrayList<CoOrd> occupiedPositionsForCarrierPlayer;
    private ArrayList<CoOrd> occupiedPositionsForBattleShipPlayer;
    private ArrayList<CoOrd> occupiedPositionsForSubmarinePlayer;
    private ArrayList<CoOrd> occupiedPositionsForDestroyerPlayer;
    private ArrayList<CoOrd> occupiedPositionsForPatrolboatPlayer;
    private ArrayList<CoOrd> occupiedPositionsForKrakenPlayer;
    private ArrayList<CoOrd> occupiedPositionsForCetusPlayer;
    private ArrayList<CoOrd> allOccupiedPosPlayer;
    Ship KrakenPlayer;
    Ship CetusPlayer;
    Ship carrierPlayer;
    Ship BSPlayer;
    Ship SubmarinePlayer;
    Ship DestroyerPlayer;
    Ship PatrolboatPlayer;

    private ArrayList<Ship> EnemyShips;
    private ArrayList<CoOrd> occupiedPositionsForCarrierEnemy;
    private ArrayList<CoOrd> occupiedPositionsForBattleShipEnemy;
    private ArrayList<CoOrd> occupiedPositionsForSubmarineEnemy;
    private ArrayList<CoOrd> occupiedPositionsForDestroyerEnemy;
    private ArrayList<CoOrd> occupiedPositionsForPatrolboatEnemy;
    private ArrayList<CoOrd> occupiedPositionsForKrakenEnemy;
    private ArrayList<CoOrd> occupiedPositionsForCetusEnemy;
    private ArrayList<CoOrd> allOccupiedPosEnemy;
    Ship KrakenEnemy;
    Ship CetusEnemy;
    Ship carrierEnemy;
    Ship BSEnemy;
    Ship SubmarineEnemy;
    Ship DestroyerEnemy;
    Ship PatrolboatEnemy;






    public gameClass(){
        winner = 0;
        System.out.println("WELCOME TO BATTLESHIP VARIANT B.");
        System.out.println("FIGHT AGAINST KRAKEN AND CETUS TO WIN THE GAME. LETS GO!");
        playerBoardMatrix = new String[10][10];
        EnemyBoardMatrix = new String[10][10];

        playerPoint = 0;
        EnemyPoint = 0;

        playerShips = new ArrayList<>();
        occupiedPositionsForCarrierPlayer = new ArrayList<>();
        occupiedPositionsForBattleShipPlayer = new ArrayList<>();
        occupiedPositionsForSubmarinePlayer = new ArrayList<>();
        occupiedPositionsForPatrolboatPlayer = new ArrayList<>();
        occupiedPositionsForDestroyerPlayer = new ArrayList<>();
        occupiedPositionsForKrakenPlayer = new ArrayList<>();
        occupiedPositionsForCetusPlayer = new ArrayList<>();
        allOccupiedPosPlayer = new ArrayList<>();




        carrierPlayer = new Ship(Constants.carrier,5, Constants.player);
        playerShips.add(carrierPlayer);
        BSPlayer = new Ship(Constants.battleship, 4, Constants.player);
        playerShips.add(BSPlayer);
        SubmarinePlayer = new Ship(Constants.submarine, 3, Constants.player);
        playerShips.add(SubmarinePlayer);
        DestroyerPlayer = new Ship(Constants.destroyer, 3, Constants.player);
        playerShips.add(DestroyerPlayer);
        PatrolboatPlayer = new Ship(Constants.patrolBoat, 2, Constants.player);
        playerShips.add(PatrolboatPlayer);

        KrakenPlayer = new Ship(Constants.kraken, 1, Constants.player);
        CetusPlayer = new Ship(Constants.cetus, 1, Constants.player);

        playerShips.add(KrakenPlayer);
        playerShips.add(CetusPlayer);



        EnemyShips = new ArrayList<>();
        occupiedPositionsForCarrierEnemy = new ArrayList<>();
        occupiedPositionsForBattleShipEnemy = new ArrayList<>();
        occupiedPositionsForSubmarineEnemy = new ArrayList<>();
        occupiedPositionsForPatrolboatEnemy = new ArrayList<>();
        occupiedPositionsForDestroyerEnemy = new ArrayList<>();
        occupiedPositionsForKrakenEnemy = new ArrayList<>();
        occupiedPositionsForCetusEnemy = new ArrayList<>();
        allOccupiedPosEnemy = new ArrayList<>();




        carrierEnemy = new Ship(Constants.carrier,5, Constants.enemy);
        EnemyShips.add(carrierEnemy);
        BSEnemy = new Ship(Constants.battleship, 4, Constants.enemy);
        EnemyShips.add(BSEnemy);
        SubmarineEnemy = new Ship(Constants.submarine, 3, Constants.enemy);
        EnemyShips.add(SubmarineEnemy);
        DestroyerEnemy = new Ship(Constants.destroyer, 3, Constants.enemy);
        EnemyShips.add(DestroyerEnemy);
        PatrolboatEnemy = new Ship(Constants.patrolBoat, 2, Constants.enemy);
        EnemyShips.add(PatrolboatEnemy);

        KrakenEnemy = new Ship(Constants.kraken, 1, Constants.enemy);
        CetusEnemy = new Ship(Constants.cetus, 1, Constants.enemy);

        EnemyShips.add(KrakenEnemy);
        EnemyShips.add(CetusEnemy);



        for(int i =0; i<10; i++){

            for(int j = 0; j<10; j++){
                playerBoardMatrix[i][j] = "<> ";
                EnemyBoardMatrix[i][j] = ">< ";

            }
        }
        //System.out.println("VISUAL READY");

        setupBoard(Constants.player,true);

        setupBoard(Constants.enemy,true);



        //System.out.println("BOARD READY");

    }
    public void displayBoards(int board){


        String playerBoardLine = "";
        String enemyBoardLine = "";
        for(int i =0; i<10; i++){

            for(int j = 0; j<10; j++){

                playerBoardLine = playerBoardLine + playerBoardMatrix[i][j];
                enemyBoardLine = enemyBoardLine + EnemyBoardMatrix[i][j];

            }
            playerBoardLine = playerBoardLine + "\n";
            enemyBoardLine = enemyBoardLine + "\n";


        }
        if(board==Constants.player){
            System.out.println("----------POINTS-----------");
            System.out.println("Enemy" + EnemyPoint);

            System.out.println("------Ship Health : Player-------");
            System.out.println("Carrier: " + occupiedPositionsForCarrierPlayer.size());
            System.out.println("BattleShip: "+ occupiedPositionsForBattleShipPlayer.size());
            System.out.println("Submarine: "+ occupiedPositionsForSubmarinePlayer.size());
            System.out.println("Destroyer: "+ occupiedPositionsForDestroyerPlayer.size());
            System.out.println("Patrol Boat: "+ occupiedPositionsForPatrolboatPlayer.size());
            System.out.println("PLAYER BOARD: ");
            System.out.println(playerBoardLine);
        }

        else{
            System.out.println("----------POINTS----------");
            System.out.println("PLAYER"+ playerPoint);
            System.out.println("------Ship Health : Enemy-------");
            System.out.println("Carrier: " + occupiedPositionsForCarrierEnemy.size());
            System.out.println("BattleShip: "+ occupiedPositionsForBattleShipEnemy.size());
            System.out.println("Submarine: "+ occupiedPositionsForSubmarineEnemy.size());
            System.out.println("Destroyer: "+ occupiedPositionsForDestroyerEnemy.size());
            System.out.println("Patrol Boat: "+ occupiedPositionsForPatrolboatEnemy.size());
            System.out.println("ENEMY BOARD: ");
            System.out.println(enemyBoardLine);
        }

    }

    public void changeBoardContent(int whichBoard, int x, int y, String content){
        int actualX = x;
        int actualY = y;
        if(whichBoard == Constants.player){
            playerBoardMatrix[actualX][actualY] = " "+content+" ";
        }
        else if(whichBoard == Constants.enemy){
            EnemyBoardMatrix[actualX][actualY] = " "+content+" ";
        }

        //displayBoards();
    }

    private boolean isOccupied(int x, int y, int len, int direction, int whichBoard){



        boolean isOccupied = false;



        ArrayList<CoOrd> allOccupiedPos = new ArrayList<>();

        if(whichBoard == Constants.player){
            allOccupiedPos = allOccupiedPosPlayer;
        }
        else if(whichBoard == Constants.enemy){
            allOccupiedPos = allOccupiedPosEnemy;
        }



        if(direction== Constants.horizontal){
            for (int i =0; i< len; i++) {
                for (CoOrd ts : allOccupiedPos) {
                    if (ts.x == x+i && ts.y == y) {
                        isOccupied = true;
                        return isOccupied;
                    }
                }
            }
        }
        else if(direction == Constants.vertical){
            for (int i =0; i< len; i++) {
                for (CoOrd ts : allOccupiedPos) {
                    if (ts.x == x && ts.y == y+i) {
                        isOccupied = true;
                        return isOccupied;
                    }
                }
            }
        }

        return isOccupied;
    }

    private void occupyPosition(int x, int y, int direction,Ship ship, int whichBoard){



        if(whichBoard == Constants.player){
            if(direction == Constants.horizontal){
                for(int i =0; i<ship.ShipLength; i++){
                    CoOrd newOccupiedCoOrd = new CoOrd();
                    newOccupiedCoOrd.x = x + i;
                    newOccupiedCoOrd.y = y;
                    allocatePosition(newOccupiedCoOrd, ship, whichBoard);
                    allOccupiedPosPlayer.add(newOccupiedCoOrd);
                }
            }
            else if(direction == Constants.vertical){
                for (int i =0; i<ship.ShipLength; i++){
                    CoOrd newOccupiedCoOrd = new CoOrd();
                    newOccupiedCoOrd.x = x;
                    newOccupiedCoOrd.y = y + i;
                    //occupiedPositions.add(newOccupiedCoOrd);
                    allocatePosition(newOccupiedCoOrd, ship, whichBoard);
                    allOccupiedPosPlayer.add(newOccupiedCoOrd);
                }
            }
        }
        else if(whichBoard == Constants.enemy){
            if(direction == Constants.horizontal){
                for(int i =0; i<ship.ShipLength; i++){
                    CoOrd newOccupiedCoOrd = new CoOrd();
                    newOccupiedCoOrd.x = x + i;
                    newOccupiedCoOrd.y = y;
                    allocatePosition(newOccupiedCoOrd, ship, whichBoard);
                    allOccupiedPosEnemy.add(newOccupiedCoOrd);
                    //number of squares used by all the boats on the enemy board
                }
            }
            else if(direction == Constants.vertical){
                for (int i =0; i<ship.ShipLength; i++){
                    CoOrd newOccupiedCoOrd = new CoOrd();
                    newOccupiedCoOrd.x = x;
                    newOccupiedCoOrd.y = y + i;
                    //occupiedPositions.add(newOccupiedCoOrd);
                    allocatePosition(newOccupiedCoOrd, ship, whichBoard);
                    allOccupiedPosEnemy.add(newOccupiedCoOrd);
                }
            }
        }

    }

    private void allocatePosition(CoOrd pos, Ship ship, int whichBoard){
        if(whichBoard == Constants.player){
            if(ship.ShipName== Constants.carrier){
                occupiedPositionsForCarrierPlayer.add(pos);
            }
            else if(ship.ShipName== Constants.battleship){
                occupiedPositionsForBattleShipPlayer.add(pos);
            }
            else if(ship.ShipName== Constants.submarine){
                occupiedPositionsForSubmarinePlayer.add(pos);
            }
            else if(ship.ShipName== Constants.destroyer){
                occupiedPositionsForDestroyerPlayer.add(pos);
            }
            else if(ship.ShipName== Constants.patrolBoat){
                occupiedPositionsForPatrolboatPlayer.add(pos);
            }
            else if(ship.ShipName == Constants.kraken){
                occupiedPositionsForKrakenPlayer.add(pos);
            }
            else if(ship.ShipName == Constants.cetus){
                occupiedPositionsForCetusPlayer.add(pos);
            }
        }
        else if(whichBoard == Constants.enemy){
            if(ship.ShipName== Constants.carrier){
                occupiedPositionsForCarrierEnemy.add(pos);
            }
            else if(ship.ShipName== Constants.battleship){
                occupiedPositionsForBattleShipEnemy.add(pos);
            }
            else if(ship.ShipName== Constants.submarine){
                occupiedPositionsForSubmarineEnemy.add(pos);
            }
            else if(ship.ShipName== Constants.destroyer){
                occupiedPositionsForDestroyerEnemy.add(pos);
            }
            else if(ship.ShipName== Constants.patrolBoat){
                occupiedPositionsForPatrolboatEnemy.add(pos);
            }
            else if(ship.ShipName == Constants.kraken){
                occupiedPositionsForKrakenEnemy.add(pos);
            }
            else if(ship.ShipName == Constants.cetus){
                occupiedPositionsForCetusEnemy.add(pos);
            }
        }

    }
    private void setupBoard(int whichBoard, boolean firstTime){
        Random random = new Random();

        ArrayList<Ship> shipsOfABoard = new ArrayList<>();
        if(firstTime==false){
            if(whichBoard == Constants.player){
                for(int i =0; i<playerShips.size()-2;i++){
                    shipsOfABoard.add(playerShips.get(i));
                }
            }
            else if (whichBoard == Constants.enemy){
                for(int i =0; i<EnemyShips.size()-2;i++){
                    shipsOfABoard.add(EnemyShips.get(i));
                }
            }
        }
        else{
            if(whichBoard == Constants.player){
                for(int i =0; i<playerShips.size();i++){
                    shipsOfABoard.add(playerShips.get(i));
                }
            }
            else if (whichBoard == Constants.enemy){
                for(int i =0; i<EnemyShips.size();i++){
                    shipsOfABoard.add(EnemyShips.get(i));
                }
            }
        }


        int finalX = 0;
        int finalY= 0;
        for(Ship eachShip : shipsOfABoard) {
            if(eachShip.ShipAlive == true){
                int direction = random.nextInt(2);

                while (true) {
                    boolean foundPos = false;
                    if (direction == Constants.vertical) {

                        int randX = random.nextInt(10);
                        int randY = random.nextInt(10);
                        if((randY + eachShip.ShipLength)<10 && !(isOccupied(randX, randY, eachShip.ShipLength, direction, whichBoard))){
                            finalX = randX;
                            finalY = randY;
                            foundPos = true;

                        }


                    }
                    else if(direction == Constants.horizontal){

                        int randX = random.nextInt(10);
                        int randY = random.nextInt(10);
                        if((randX + eachShip.ShipLength)<10 && !(isOccupied(randX, randY, eachShip.ShipLength, direction, whichBoard))){
                            finalX = randX;
                            finalY = randY;
                            foundPos = true;

                        }
                    }
                    if(foundPos){
                        break;
                    }
                }
                occupyPosition(finalX, finalY,direction, eachShip, whichBoard);
            }


        }

        /*seeBoatLocations(Constants.player);
        seeBoatLocations(Constants.enemy);*/
    }

    private void seeBoatLocations(int whichBoard){
        if(whichBoard== Constants.player){
            System.out.println("PLAYER BOARD :: ");
            for(CoOrd tempPos : occupiedPositionsForCarrierPlayer){
                System.out.print(tempPos.x + "," + tempPos.y + " ");
                //changeBoardContent(Constants.player,tempPos.x,tempPos.y,"C");
            }
            System.out.println(" ");
            for(CoOrd tempPos : occupiedPositionsForBattleShipPlayer){
                System.out.print(tempPos.x + "," + tempPos.y + " ");
                //changeBoardContent(Constants.player,tempPos.x,tempPos.y,"B");
            }
            System.out.println(" ");
            for(CoOrd tempPos : occupiedPositionsForSubmarinePlayer){
                System.out.print(tempPos.x + "," + tempPos.y + " ");
                //changeBoardContent(Constants.player,tempPos.x,tempPos.y,"S");
            }
            System.out.println(" ");
            for(CoOrd tempPos : occupiedPositionsForDestroyerPlayer){
                System.out.print(tempPos.x + "," + tempPos.y + " ");
                //changeBoardContent(Constants.player,tempPos.x,tempPos.y,"D");
            }
            System.out.println(" ");
            for(CoOrd tempPos : occupiedPositionsForPatrolboatPlayer){
                System.out.print(tempPos.x + "," + tempPos.y + " ");
                //changeBoardContent(Constants.player,tempPos.x,tempPos.y,"P");
            }
            System.out.println(" ");
            for(CoOrd tempPos : occupiedPositionsForKrakenPlayer){
                System.out.print(tempPos.x + "," + tempPos.y + " ");
                //changeBoardContent(Constants.player,tempPos.x,tempPos.y,"K");
            }
            System.out.println(" ");
            for(CoOrd tempPos : occupiedPositionsForCetusPlayer){
                System.out.print(tempPos.x + "," + tempPos.y + " ");
                //changeBoardContent(Constants.player,tempPos.x,tempPos.y,"T");
            }
            System.out.println(" ");
        }

        else if(whichBoard == Constants.enemy){
            System.out.println("ENEMY BOARD:");
            for(CoOrd tempPos : occupiedPositionsForCarrierEnemy){
                System.out.print(tempPos.x + "," + tempPos.y + " ");
                //changeBoardContent(Constants.cpu,tempPos.x,tempPos.y,"C");
            }
            System.out.println(" ");
            for(CoOrd tempPos : occupiedPositionsForBattleShipEnemy){
                System.out.print(tempPos.x + "," + tempPos.y + " ");
                //changeBoardContent(Constants.cpu,tempPos.x,tempPos.y,"B");
            }
            System.out.println(" ");
            for(CoOrd tempPos : occupiedPositionsForSubmarineEnemy){
                System.out.print(tempPos.x + "," + tempPos.y + " ");
                //changeBoardContent(Constants.cpu,tempPos.x,tempPos.y,"S");
            }
            System.out.println(" ");
            for(CoOrd tempPos : occupiedPositionsForDestroyerEnemy){
                System.out.print(tempPos.x + "," + tempPos.y + " ");
                //changeBoardContent(Constants.cpu,tempPos.x,tempPos.y,"D");
            }
            System.out.println(" ");
            for(CoOrd tempPos : occupiedPositionsForPatrolboatEnemy){
                System.out.print(tempPos.x + "," + tempPos.y + " ");
                //changeBoardContent(Constants.cpu,tempPos.x,tempPos.y,"P");
            }
            System.out.println(" ");
            for(CoOrd tempPos : occupiedPositionsForKrakenEnemy){
                System.out.print(tempPos.x + "," + tempPos.y + " ");
                //changeBoardContent(Constants.cpu,tempPos.x,tempPos.y,"K");
            }
            System.out.println(" ");
            for(CoOrd tempPos : occupiedPositionsForCetusEnemy){
                System.out.print(tempPos.x + "," + tempPos.y + " ");
                //changeBoardContent(Constants.cpu,tempPos.x,tempPos.y,"T");
            }
            System.out.println(" ");
        }
    }

    public void Attack(int x, int y, int Board){
        //if(x)

        if(x<0 || x>9 || y<0 || y>9 ){
            throw new ArrayIndexOutOfBoundsException("INVALID!") ;
        }

        if(Board == Constants.enemy){
            int index = -9;
            Ship ship = null;

            int i =0;
            for(CoOrd tempPos : occupiedPositionsForCarrierEnemy){
                if(tempPos.x == x && tempPos.y == y){
                    index = i;
                    ship = new Ship(Constants.carrier,5,Constants.enemy) ;
                    break;
                }
                i = i +1;
            }
            i =0;
            for(CoOrd tempPos : occupiedPositionsForBattleShipEnemy){
                if(tempPos.x == x && tempPos.y == y){
                    index = i;
                    ship = new Ship(Constants.battleship, 4, Constants.enemy);
                    break;
                }
                i = i +1;
            }
            i = 0;
            for(CoOrd tempPos : occupiedPositionsForSubmarineEnemy){
                if(tempPos.x == x && tempPos.y == y){
                    index = i;
                    ship = new Ship(Constants.submarine, 3, Constants.enemy);
                    break;
                }
                i = i +1;
            }
            i = 0;
            for(CoOrd tempPos : occupiedPositionsForDestroyerEnemy){
                if(tempPos.x == x && tempPos.y == y){
                    index = i;
                    ship = new Ship(Constants.destroyer,3, Constants.enemy) ;
                    break;
                }
                i = i +1;
            }
            i = 0;
            for(CoOrd tempPos : occupiedPositionsForPatrolboatEnemy){
                if(tempPos.x == x && tempPos.y == y){
                    index = i;
                    ship = new Ship(Constants.patrolBoat,2, Constants.enemy);
                    break;
                }
                i = i +1;
            }
            i = 0;
            for(CoOrd tempPos : occupiedPositionsForKrakenEnemy){
                if(tempPos.x == x && tempPos.y == y){
                    index = i;
                    ship = new Ship(Constants.kraken,1, Constants.enemy);
                    break;
                }
                i = i +1;
            }
            i = 0;
            for(CoOrd tempPos : occupiedPositionsForCetusEnemy){
                if(tempPos.x == x && tempPos.y == y){
                    index = i;
                    ship = new Ship(Constants.cetus,1, Constants.enemy);
                    break;
                }
                i = i +1;
            }
            i = 0;
            System.out.println("<------------------------------------------>");
            if(index ==-9 || ship == null){
                System.out.println("YOU MISSED!");
                changeBoardContent(Constants.enemy,x,y,"M");
                playerPoint = playerPoint -1;
            }
            else{
                calculateDamage(index, ship);
                changeBoardContent(Constants.enemy,x,y,"H");
            }

            displayBoards(Constants.enemy);

        }

        if(Board == Constants.player){
            int index = -9;
            Ship ship = null;

            int i =0;
            for(CoOrd tempPos : occupiedPositionsForCarrierPlayer){
                if(tempPos.x == x && tempPos.y == y){
                    index = i;
                    ship = new Ship(Constants.carrier,5,Constants.player) ;
                    break;
                }
                i = i +1;
            }
            i =0;
            for(CoOrd tempPos : occupiedPositionsForBattleShipPlayer){
                if(tempPos.x == x && tempPos.y == y){
                    index = i;
                    ship = new Ship(Constants.battleship, 4, Constants.player);
                    break;
                }
                i = i +1;
            }
            i = 0;
            for(CoOrd tempPos : occupiedPositionsForSubmarinePlayer){
                if(tempPos.x == x && tempPos.y == y){
                    index = i;
                    ship = new Ship(Constants.submarine, 3, Constants.player);
                    break;
                }
                i = i +1;
            }
            i = 0;
            for(CoOrd tempPos : occupiedPositionsForDestroyerPlayer){
                if(tempPos.x == x && tempPos.y == y){
                    index = i;
                    ship = new Ship(Constants.destroyer,3, Constants.player) ;
                    break;
                }
                i = i +1;
            }
            i = 0;
            for(CoOrd tempPos : occupiedPositionsForPatrolboatPlayer){
                if(tempPos.x == x && tempPos.y == y){
                    index = i;
                    ship = new Ship(Constants.patrolBoat,2, Constants.player);
                    break;
                }
                i = i +1;
            }
            i = 0;
            for(CoOrd tempPos : occupiedPositionsForKrakenPlayer){
                if(tempPos.x == x && tempPos.y == y){
                    index = i;
                    ship = new Ship(Constants.kraken,1, Constants.player);
                    break;
                }
                i = i +1;
            }
            i = 0;
            for(CoOrd tempPos : occupiedPositionsForCetusPlayer){
                if(tempPos.x == x && tempPos.y == y){
                    index = i;
                    ship = new Ship(Constants.cetus,1, Constants.player);
                    break;
                }
                i = i +1;
            }
            i = 0;

            if(index ==-9 || ship == null){
                System.out.println("ENEMY MISSED!");
                changeBoardContent(Constants.player,x,y,"M");
                EnemyPoint = EnemyPoint -1;
            }
            else{
                calculateDamage(index, ship);
                changeBoardContent(Constants.player,x,y,"H");
            }

            displayBoards(Constants.player);


        }
    }

    private void calculateDamage(int index, Ship ship){

        //we are now on player's board
        //calculating damage of player's board
        if(ship.shipBoard == Constants.player){
            if(ship.ShipName == Constants.carrier){
                occupiedPositionsForCarrierPlayer.remove(index);
                if(occupiedPositionsForCarrierPlayer.size()==0){
                    System.out.println("Player's Carrier Sunk!");
                    EnemyPoint = EnemyPoint + 2*ship.ShipLength;
                    playerShips.get(0).ShipAlive = false;
                    //ship.ShipAlive = false;
                }
                else{
                    System.out.println("Player's Carried Is Damaged!");
                    EnemyPoint = EnemyPoint+1;
                }
            }
            if(ship.ShipName == Constants.battleship){
                occupiedPositionsForBattleShipPlayer.remove(index);
                if(occupiedPositionsForBattleShipPlayer.size()==0){
                    System.out.println("Player's Battleship Sunk!");
                    EnemyPoint = EnemyPoint + 2*ship.ShipLength;
                    playerShips.get(1).ShipAlive = false;
                    //ship.ShipAlive = false;
                }
                else{
                    System.out.println("Player's Battleship Is Damaged!");
                    EnemyPoint = EnemyPoint+1;
                }
            }
            if(ship.ShipName == Constants.submarine){
                occupiedPositionsForSubmarinePlayer.remove(index);
                if(occupiedPositionsForSubmarinePlayer.size()==0){
                    System.out.println("Player's Submarine Sunk!");
                    EnemyPoint = EnemyPoint + 2*ship.ShipLength;
                    playerShips.get(2).ShipAlive = false;
                    //ship.ShipAlive = false;
                }
                else{
                    System.out.println("Player's Submarine is damaged!");
                    EnemyPoint = EnemyPoint+1;
                }
            }
            if(ship.ShipName == Constants.destroyer){
                occupiedPositionsForDestroyerPlayer.remove(index);
                if(occupiedPositionsForDestroyerPlayer.size()==0){
                    System.out.println("Player's Destroyer Sunk!");
                    EnemyPoint = EnemyPoint + 2*ship.ShipLength;
                    playerShips.get(3).ShipAlive = false;
                    //playerShips.set(0).ShipAlive = false;
                    //ship.ShipAlive = false;
                }
                else{
                    System.out.println("Player's Destroyer Is Damaged!");
                    EnemyPoint = EnemyPoint+1;
                }
            }
            if(ship.ShipName == Constants.patrolBoat){
                occupiedPositionsForPatrolboatPlayer.remove(index);
                if(occupiedPositionsForPatrolboatPlayer.size()==0){
                    System.out.println("Player's Patrolboat Sunk!");
                    EnemyPoint = EnemyPoint + 2*ship.ShipLength;
                    playerShips.get(4).ShipAlive = false;
                    //ship.ShipAlive = false;
                }
                else{
                    System.out.println("Player's PatrolBoatIs Damaged!");
                    EnemyPoint = EnemyPoint+1;
                }
            }
            if(ship.ShipName == Constants.kraken){
                //occupiedPositionsForKrakenPlayer.remove(index);
                System.out.println("ENEMY HIT KRAKEN, ENEMY'S POINTS WILL BE 0!");
                EnemyPoint = 0;
            }
            if(ship.ShipName == Constants.cetus){
                System.out.println("ENEMY HIT CETUS, PLAYER BOARD HAS BEEN RESHUFFLED!");
                occupiedPositionsForCarrierPlayer.clear();
                occupiedPositionsForBattleShipPlayer.clear();
                occupiedPositionsForSubmarinePlayer.clear();
                occupiedPositionsForDestroyerPlayer.clear();
                occupiedPositionsForPatrolboatPlayer.clear();
                /*for(Ship es : playerShips){
                    System.out.println("ship :: " + es.ShipLength+  "unsunk: " + es.ShipAlive);
                }*/
                setupBoard(Constants.player,false);
            }

            if(occupiedPositionsForPatrolboatPlayer.size() == 0 && occupiedPositionsForDestroyerPlayer.size() ==0 && occupiedPositionsForSubmarinePlayer.size()==0 &&
            occupiedPositionsForBattleShipPlayer.size() == 0 && occupiedPositionsForCarrierPlayer.size() == 0){
                winner = Constants.enemy;
            }
        }



        if(ship.shipBoard == Constants.enemy){
            if(ship.ShipName == Constants.carrier){
                occupiedPositionsForCarrierEnemy.remove(index);
                if(occupiedPositionsForCarrierEnemy.size()==0){
                    System.out.println("YOU SANK ENEMY'S CARRIER!");
                    EnemyShips.get(0).ShipAlive = false;
                    playerPoint = playerPoint + 2*ship.ShipLength;
                    //ship.ShipAlive = false;
                }
                else{
                    System.out.println("YOU DAMAGED ENEMY'S CARRIER!");
                    playerPoint = playerPoint + 1;
                }
            }
            if(ship.ShipName == Constants.battleship){
                occupiedPositionsForBattleShipEnemy.remove(index);
                if(occupiedPositionsForBattleShipEnemy.size()==0){
                    System.out.println("YOU SANK ENEMY'S BattleShip!");
                    EnemyShips.get(1).ShipAlive = false;
                    playerPoint = playerPoint + 2*ship.ShipLength;
                    //ship.ShipAlive = false;
                }
                else{
                    System.out.println("YOU DAMAGED ENEMY'S BattleShip!");
                    playerPoint = playerPoint + 1;
                }
            }
            if(ship.ShipName == Constants.submarine){
                occupiedPositionsForSubmarineEnemy.remove(index);
                if(occupiedPositionsForSubmarineEnemy.size()==0){
                    System.out.println("YOU SANK ENEMY'S SUBMARINE!");
                    EnemyShips.get(2).ShipAlive = false;
                    playerPoint = playerPoint + 2*ship.ShipLength;
                    //ship.ShipAlive = false;
                }
                else{
                    System.out.println("YOU DAMAGED ENEMY'S SUBMARINE!");
                    playerPoint = playerPoint + 1;
                }
            }
            if(ship.ShipName == Constants.destroyer){
                occupiedPositionsForDestroyerEnemy.remove(index);
                if(occupiedPositionsForDestroyerEnemy.size()==0){
                    System.out.println("YOU SANK ENEMY'S Destroyer!");
                    EnemyShips.get(3).ShipAlive = false;
                    playerPoint = playerPoint + 2*ship.ShipLength;
                    //CPUShips.set(0).ShipAlive = false;
                    //ship.ShipAlive = false;
                }
                else{
                    System.out.println("YOU DAMAGED ENEMY'S Destroyer!");
                    playerPoint = playerPoint + 1;
                }
            }
            if(ship.ShipName == Constants.patrolBoat){
                occupiedPositionsForPatrolboatEnemy.remove(index);
                if(occupiedPositionsForPatrolboatEnemy.size()==0){
                    System.out.println("YOU SANK ENEMY'S PATROL!");
                    EnemyShips.get(4).ShipAlive = false;
                    playerPoint = playerPoint + 2*ship.ShipLength;
                    //ship.ShipAlive = false;
                }
                else{
                    System.out.println("YOU DAMAGED ENEMY'S PATROL!");
                    playerPoint = playerPoint + 1;
                }
            }
            if(ship.ShipName == Constants.kraken){
                System.out.println("PLAYER HIT KRAKEN, PLAYER'S POINTS WILL BE 0!");
                playerPoint = 0;
            }
            if(ship.ShipName == Constants.cetus){
                System.out.println("Player HIT CETUS, ENEMY BOARD HAS BEEN RESHUFFLED!");
                occupiedPositionsForCarrierEnemy.clear();
                occupiedPositionsForBattleShipEnemy.clear();
                occupiedPositionsForSubmarineEnemy.clear();
                occupiedPositionsForDestroyerEnemy.clear();
                occupiedPositionsForPatrolboatEnemy.clear();
                /*for(Ship es : EnemyShips){
                    System.out.println("ship ::" + es.ShipLength+  "unsunk: " + es.ShipAlive);
                }
                setupBoard(Constants.enemy,false);*/
            }

            if(occupiedPositionsForPatrolboatEnemy.size() == 0 && occupiedPositionsForDestroyerEnemy.size() ==0 && occupiedPositionsForSubmarineEnemy.size()==0 &&
                    occupiedPositionsForBattleShipEnemy.size() == 0 && occupiedPositionsForCarrierEnemy.size() == 0){
                winner = Constants.player;
            }
        }


    }

}