import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 *
 * @author Sjaak Smetsers & Renske Smetsers-Weeda
 * @version 3.0 -- 20-01-2017
 */
public class MyDodo extends Dodo
{
    private int myNrOfEggsHatched;
    
    public MyDodo() {
        super( EAST );
        myNrOfEggsHatched = 0;
    }

    public void act() {
    }

    /**
     * Move one cell forward in the current direction.
     * 
     * <P> Initial: Dodo is somewhere in the world
     * <P> Final: If possible, Dodo has moved forward one cell
     *
     */
    public void move() {
        if ( canMove() ) {
            step();
        } else {
            showError( "I'm stuck!" );
        }
    }

    /**
     * Test if Dodo can move forward, (there are no obstructions
     *    or end of world in the cell in front of her).
     * 
     * <p> Initial: Dodo is somewhere in the world
     * <p> Final:   Same as initial situation
     * 
     * @return boolean true if Dodo can move (no obstructions ahead)
     *                 false if Dodo can't move
     *                      (an obstruction or end of world ahead)
     */
    public boolean canMove() {
        if ( borderAhead() ){
            return false;
        } else {
            return true;
        }
    }

    /**
     * Hatches the egg in the current cell by removing
     * the egg from the cell.
     * Gives an error message if there is no egg
     * 
     * <p> Initial: Dodo is somewhere in the world. There is an egg in Dodo's cell.
     * <p> Final: Dodo is in the same cell. The egg has been removed (hatched).     
     */    
    public void hatchEgg () {
        if ( onEgg() ) {
            pickUpEgg();
            myNrOfEggsHatched++;
        } else {
            showError( "There was no egg in this cell" );
        }
    }
    
     public boolean grainAhead(){
        move();
        if(onGrain() == true) {
            stepOneCelBackwards();
            return true;
        }else{
        stepOneCelBackwards();
        return false;
    }
    }

    /**
     * Returns the number of eggs Dodo has hatched so far.
     * 
     * @return int number of eggs hatched by Dodo
     */
    public int getNrOfEggsHatched() {
        return myNrOfEggsHatched;
    }
    
    /**
     * Move given number of cells forward in the current direction.
     * 
     * <p> Initial:   
     * <p> Final:  
     * 
     * @param   int distance: the number of steps made
     */
    public void jump( int distance ) {
        int nrStepsTaken = 0;               // set counter to 0
        while ( nrStepsTaken < distance ) { // check if more steps must be taken  
            move();                         // take a step
            nrStepsTaken++;  
            System.out.println("moved " + nrStepsTaken);// increment the counter
        }
    }

   /**
    * Zet een stap tot de rande van de wereld
    */
    public void walkToWorldEdge() {
        while (!borderAhead()) {
            move();
        }
    }
    

    /**
     * Test if Dodo can lay an egg.
     *          (there is not already an egg in the cell)
     * 
     * <p> Initial: Dodo is somewhere in the world
     * <p> Final:   Same as initial situation
     * 
     * @return boolean true if Dodo can lay an egg (no egg there)
     *                 false if Dodo can't lay an egg
     *                      (already an egg in the cell)
     */

    public boolean canLayEgg( ){
       if( onEgg() ){
           return false;
       }else{
            return true;
        }
    }  
    
    public void turn180 () {
        turnRight();
        turnRight();
    }
    
    /**
     * Zolang er geen obstakels tussen dodo en ei zit,
     * zet hij een stap voor uit toto hij op een ei staat 
     */
    
    public void gotoEgg(){
        while (!onEgg()){
            move();
        
        }
    }
    
    /**
     * Draait zich om loopt naar de einde van de wereld
     */
    public void backToStartOfRowAndFaceBack() {
        turn180();
        walkToWorldEdge();
        turn180();
    }
    
    public void climbOverFence () {
        turnLeft();
        move();
        turnRight();
        move();
        move();
        turnRight();
        move();
        turnLeft();
    }
    
    /**
     * Loopt naar de rand van de wereld en klimt over hekken heen.
     */
    
    public void walkToWorldEdgeClimbingOverFences() {
    while (!borderAhead()) {
        if(!fenceAhead()) {
            move();
        }else{
            climbOverFence();
        }
    }
}

/**
 * Dodo zet een stap achteruit
 */
public void stepOneCelBackwards() {
    turn180();
    move();
    turn180();
}

/**
 * Terwijl dodo loopt pakt ze de grains op en laat de coordintes van de graains zien
 */
public void pickUpGrainsAndPrintCoordinates() {
    while(!borderAhead()) {
        if (onGrain()) {
            pickUpGrain();
            System.out.println("X:" + getX() + ", Y: "+ getY());
        }
        move();
    }
    if(onGrain()) {
        pickUpGrain();
        System.out.println("X:" + getX() + ", Y: "+ getY());
    }
}

/**
 * Dood springt door de hekken en legt een ei op de nest
 */
public void vulLegeNestenOp () {
    while (!borderAhead()) {
        if (onNest() && canLayEgg()) {
            layEgg();
        }
        move();
    }
    if(onNest() && canLayEgg()) {
        layEgg();
    }
}

public void walkToNestClimbingOverFences() {
    while(!onNest()) {
        if (fenceAhead()) {
            climbOverFence();
        }else{
            move();
        }
    }
    layEgg();
}

/**
 * Dodo loopt om de hekken heen
 */
public void walkAroundFencedArea() {
 while (!onEgg()) {
        if (fenceAhead()) {
            turnLeft();
        } else {
            move();
            turnRight();
            if (fenceAhead()) {
                turnLeft(); 
            }
        }
    }
}

/**
 * Dodo probeert de nest te vinden
 */

public void eggTrailToNest() {
    while (!onNest()) {
        turnRight();
        while (!eggAhead() && !nestAhead()) {
            turnLeft();
        }
        move();
    }
}


/**
 * Dodo probeert door de doolhod zijn neest te vinden
 */
public void doolhof() {
    while(!onNest()) {
        turnRight();
        while(fenceAhead()) {
            turnLeft();
        }
        move();
    }
}

public void doolhofLastig() {
    while (!onNest()) {
        turnRight();
        while (fenceAhead()) {
            turnLeft();
        }
        move();
    }
    System.out.println("Gefeliciteerd dodo heeft de nest gevonden");

}

public void faceEast() {
    while(getDirection() !=EAST) {
        turnLeft();
    }
}

}


