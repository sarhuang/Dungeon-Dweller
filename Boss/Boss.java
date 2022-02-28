/**
 * Program: Boss.java
 * 
 * Name: Sarah Huang
 * 
 * Purpose: The class definition for the boss captain. The captain moves left and right randomly.
 * Health and hitboxes are located here.
 * 
 * Drop Sword FX Source: http://soundbible.com/906-Drop-Sword.html
 * Slime Hit FX Source: http://soundbible.com/1081-Splat-And-Squirt.html
 * Defeated Grunt FX Source: http://soundbible.com/462-Male-Grunt.html
 */

import greenfoot.*;
public class Boss extends Actor
{
    private GreenfootImage captainLeft1 = getImage(); 
    private GreenfootImage captainLeft2 = new GreenfootImage("captain_left2.png");
    private GreenfootImage captainLeft3 = new GreenfootImage("captain_left2.png");
    private GreenfootImage captainRight = new GreenfootImage("captain_right1.png");
    private GreenfootImage captainDefeatedLeft = new GreenfootImage("captain_left_dead.png");
    private GreenfootImage captainDefeatedRight = new GreenfootImage("captain_right_dead.png");
    
    private boolean facingRight;    //Is boss facing right?
    private boolean facingLeft; //Is boss facing left?
   
    private int speed;
    private int walkTime = 0;   //How long the boss walk for
    private int maxWalkTime = 500;
    private int frame = 0;      //Used to cycle through images for animation
    private int delay = 8;      //How long each image lasts for animation
    
    private int health = 30;             //Boss's health
    private boolean damaged = false;    //Double check the boss has been hit
    private int deathDelay = 40;        //For dramatic effect and his sprite won't disappear instantly
    
    GreenfootSound dropSword = new GreenfootSound("Drop Sword-SoundBible.com-768774345.wav");
    GreenfootSound slimeballHit = new GreenfootSound("Splat And Squirt-SoundBible.com-2136633229.wav");
    GreenfootSound painedGrunt = new GreenfootSound("Male Grunt-SoundBible.com-68178715.mp3");
    
    public Boss(){  //SARAH - Scales the boss to the correct size, and the boss is set with a random initial speed
        captainLeft1.scale(300, 300);
        captainDefeatedLeft.scale(300, 300);
        captainDefeatedRight.scale(300, 300);
        speed = Greenfoot.getRandomNumber(11);  
    }
    
    public void act()  //SARAH - The boss will walk unless its health is zero and check for collisions
    {
        if(health > 0)
            walk();
        else
            move(0);
            
        hitByPlayer();
    }    
    
    public void walk(){ //SARAH - The knight cycles through three images to simulate animation and will walk at random speeds
                        //after it walks right and left with the previous speed
        walkTime++;
        frame++;    
        
        //Walk right
        if (walkTime < maxWalkTime/2){
            facingLeft = false;
            facingRight = true;
            captainLeft1 = new GreenfootImage("captain_right1.png");
            captainLeft2 = new GreenfootImage("captain_right2.png");
            captainLeft3 = new GreenfootImage("captain_right2.png");
            captainLeft1.scale(300, 300);
            captainLeft2.scale(300, 300);
            captainLeft3.scale(300, 300);
            setLocation(getX() + speed, getY());
            
            if(frame < 1 * delay)
                setImage(captainLeft1);
            else if(frame < 2 * delay){
                setImage(captainLeft2);
            }
            else if(frame < 3 * delay){
                setImage(captainLeft3);
                frame = 1;
            }
        }
        
        //Walk left
        else if(walkTime < maxWalkTime){
            facingLeft = true;
            facingRight = false;
            captainLeft1 = new GreenfootImage("captain_left1.png");
            captainLeft2 = new GreenfootImage("captain_left2.png");
            captainLeft3 = new GreenfootImage("captain_left2.png");
            captainLeft1.scale(300, 300);
            captainLeft2.scale(300, 300);
            captainLeft3.scale(300, 300);
            setLocation(getX() - speed, getY());
            if(frame < 1 * delay)
                setImage(captainLeft1);
            else if(frame < 2 * delay){
                setImage(captainLeft2);
            }
            else if(frame < 3 * delay){
                setImage(captainLeft3);
                frame = 1;
            }
        }
        
        else{
            captainLeft1 = new GreenfootImage("captain_right1.png");
            captainLeft2 = new GreenfootImage("captain_right2.png");
            captainLeft3 = new GreenfootImage("captain_right2.png");
            walkTime = 0;
            speed = Greenfoot.getRandomNumber(11);  //The boss will charge at another random speed
        }
    }

    
    public void hitByPlayer(){  //SARAH - When a slimeball intersects the boss's image, the boss loses health.
                                //If health is zero, the boss has a dying image facing in the direction it was facing
                                //earlier, and the world changes to EndScreen.
                                
        //Basically isTouching but more precise
        Actor leftProjectile = getOneIntersectingObject(LeftSlimeball.class);   //Look out for left slimeball object intersecting enemy
        Actor rightProjectile = getOneIntersectingObject(RightSlimeball.class); //Look out for right slimeball object intersecting enemy
        
        //Enemy is hit with left slimeball
        if((leftProjectile != null) && !damaged){
            slimeballHit.play();
            health--;
            damaged = true;
            getWorld().removeObject(leftProjectile);    //Remove slimeball
        }
        //Enemy is hit with right slimeball
        else if((rightProjectile != null) && !damaged){
            slimeballHit.play();
            health--;
            damaged = true;
            getWorld().removeObject(rightProjectile);
        }
        //Double check that no slimeballs hitting the enemy means it's not hit
        else if(!isTouching(LeftSlimeball.class) || !isTouching(RightSlimeball.class)){
            damaged = false;
        }

        //Erase object when it is defeated
        if(health <= 0){
            dropSword.play();
            painedGrunt.play();
            
            if(facingLeft){
                setImage(captainDefeatedLeft);
                deathDelay--;
            }
            else if(facingRight){
                setImage(captainDefeatedRight);
                deathDelay--;
            }
            
            if(deathDelay == 0)
                Greenfoot.setWorld(new EndScreen());
        }
    }
    
    public int getBossHealth(){ //Allows ForestWorld to access the boss's health.
        return health;
    }
}
