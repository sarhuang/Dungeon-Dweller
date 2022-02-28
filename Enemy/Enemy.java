/**
 * Program: Enemy.java
 * 
 * Name: Sarah Huang
 * 
 * Purpose: The class definition for the enemy knight. The knight can move left and right.
 * Health and hitboxes are located here.
 * 
 * Drop Sword FX Source: http://soundbible.com/906-Drop-Sword.html
 * Slime Hit FX Source: http://soundbible.com/1081-Splat-And-Squirt.html
 */

import greenfoot.*;
public class Enemy extends Actor
{
    private GreenfootImage knightLeft1 = getImage();    //Instantiating the knight looking left images for the walking animation
    private GreenfootImage knightLeft2 = new GreenfootImage("CROP_knight_left2.png");
    private GreenfootImage knightLeft3 = new GreenfootImage("CROP_knight_left3.png");
    private GreenfootImage knightLeft4 = new GreenfootImage("CROP_knight_left4.png");
    private GreenfootImage knightGuardLeft = new GreenfootImage("CROP_knight_attack_left.png"); //Images for the "guard up" animation
    private GreenfootImage knightGuardRight = new GreenfootImage("CROP_knight_attack_right.png");
    
    private GreenfootImage knightDefeatLeft = new GreenfootImage("CROP_knight_left_GREENdead.png");
    private GreenfootImage knightDefeatRight = new GreenfootImage("CROP_knight_right_GREENdead.png");
    
    private boolean facingRight;    //Is knight facing right?
    private boolean facingLeft; //Is knight facing left?
   
    private int speed = 2;      //How fast the knights walk
    private int walkTime = 0;   //How long knights walk for
    private int randomWalkTime; //The total random time the knights walk for before turning
    private int frame = 0;      //Used to cycle through images for animation
    private int delay = 8;      //How long each image lasts for animation
    
    private int health = 3;             //Knight's health
    private boolean damaged = false;    //Double check the knight has been hit
    private int deathDelay = 10;

    Counter counter;    //Initialize Counter object to use to add score
    
    GreenfootSound dropSword = new GreenfootSound("Drop Sword-SoundBible.com-768774345.wav");
    GreenfootSound slimeballHit = new GreenfootSound("Splat And Squirt-SoundBible.com-2136633229.wav");
    
    
    public Enemy(Counter counter){  //SARAH - Instantiates the counter as it is dependant on enemy and randomizes 
                                    //walking distance
        this.counter = counter; 
        randomWalkTime = Greenfoot.getRandomNumber(700);    //Sets limit for how long knight can walk for before turning
    }
    
    public void act() //SARAH - Enemy walks as long as health is zero, activates "guard up" animation, and detects collision
    {
        if(health > 0)
            walk();
        else
            move(0);
            
        guardUp();
        hitByPlayer();
    }    
   
    
    public void walk(){
        /*SARAH - There is a timer that sets how long he walks for before walking the other way.
         *As the knight walks, the frame variable increments to determine which image should be set
         *to simulate walking. Once the last image is set, the frame count returns back to one and repeats
         *until the timer reaches half of randomWalkTime. After that, the knight walks right by setting the image
         *variables to the flipped versions of the sprite. Once the timer hits beyond randomWalkTime, it resets and the images
         *are set back the left sprites.*/
         
        walkTime++;
        frame++;    
        
        //Walk left
        if(walkTime < randomWalkTime/2){
            facingLeft = true;
            facingRight = false;
            setLocation(getX() - speed, getY());
            if(frame < 1 * delay)
                setImage(knightLeft1);
            else if(frame < 2 * delay)
                setImage(knightLeft2);
            else if(frame < 3 * delay)
                setImage(knightLeft3);
            else if(frame < 4 * delay){
                setImage(knightLeft4);
                frame = 1;
            }
        }
        //Walk right
        else if (walkTime < randomWalkTime){
            facingLeft = false;
            facingRight = true;
            knightLeft1 = new GreenfootImage("CROP_knight_right1.png");
            knightLeft2 = new GreenfootImage("CROP_knight_right2.png");
            knightLeft3 = new GreenfootImage("CROP_knight_right3.png");
            knightLeft4 = new GreenfootImage("CROP_knight_right4.png");
            setLocation(getX() + speed, getY());
            
            if(frame < 1 * delay)
                setImage(knightLeft1);
            else if(frame < 2 * delay)
                setImage(knightLeft2);
            else if(frame < 3 * delay)
                setImage(knightLeft3);
            else if(frame < 4 * delay){
                setImage(knightLeft4);
                frame = 1;
            }
        }
        //Back to left and repeats process
        else{
            knightLeft1 = new GreenfootImage("CROP_knight_left1.png");
            knightLeft2 = new GreenfootImage("CROP_knight_left2.png");
            knightLeft3 = new GreenfootImage("CROP_knight_left3.png");
            knightLeft4 = new GreenfootImage("CROP_knight_left4.png");
            walkTime = 0;
        }
    }
   
    public void guardUp(){ //SARAH - If the player is within 100 units, the animation for the knight will change 
                            //depending on which direction it was walking.
        
        Actor player = getOneObjectAtOffset(100, 0, Player.class);
        if(player != null){
            if(facingLeft){
                setImage(knightGuardLeft);
            }
            else if(facingRight){
                setImage(knightGuardRight);
            }
        }
        
    }
    
    public void hitByPlayer(){  //SARAH - When a slimeball intersects the enemy's image, the enemy loses health.
                                //If health is zero, the enemy has a dying image facing in the direction it was facing
                                //earlier and adds to score.
                                
        hitOnLeft();
        hitOnRight();
        
        //Double check that no slimeballs hitting the enemy means it's not hit
        if(!isTouching(LeftSlimeball.class) || !isTouching(RightSlimeball.class)){
            damaged = false;
        }
        
        //Erase object when it is defeated
        if(health <= 0){
            if(facingLeft){
                setImage(knightDefeatLeft);
                dropSword.play();
                deathDelay--;
            }
            else if(facingRight){
                setImage(knightDefeatRight);
                dropSword.play();
                deathDelay--;
            }
            
            if(deathDelay == 0)
                getWorld().removeObject(this);
        }
    }
    
    private void hitOnLeft(){   //SARAH - Support method to hitByPlayer(), specifically checking for left slimeballs
        //Basically isTouching but more precise
        Actor leftProjectile = getOneIntersectingObject(LeftSlimeball.class);   //Look out for left slimeball object intersecting enemy
        //Enemy is hit with left slimeball
        if((leftProjectile != null) && !damaged){
            slimeballHit.play();
            health--;
            counter.score++;    //Hits = increase score
            damaged = true;
            getWorld().removeObject(leftProjectile);    //Remove slimeball
        }
    }
    
    
    private void hitOnRight(){  //SARAH - Support method to hitByPlayer(), specifically checking for right slimeballs
        //Basically isTouching but more precise
        Actor rightProjectile = getOneIntersectingObject(RightSlimeball.class); //Look out for right slimeball object intersecting enemy
        //Enemy is hit with right slimeball
        if((rightProjectile != null) && !damaged){
            slimeballHit.play();
            health--;
            counter.score++;
            damaged = true;
            getWorld().removeObject(rightProjectile);
        }   
    }
}
