/**
 * Program: Player.java
 * 
 * Name: Sarah Huang
 * 
 * Purpose: The class definition for the player. You can move left and right, jump, and shoot slimeballs.
 *          The player's health and hit boxes are defined here too.
 *          
 * Sword Slice FX Source: http://soundbible.com/1008-Decapitation.html
 * Slimeball FX Source: http://soundbible.com/1097-Slime-Splash.html
 * Slime Jump FX Source: http://soundbible.com/1084-Slime.html
 */

import greenfoot.*;  
public class Player extends Actor
{
    //Initializing player sprites
    private GreenfootImage slimeRight = getImage();
    private GreenfootImage slimeLeft = new GreenfootImage("CROP_green_left1.png");
              
    private int yBoost = 0;                 //How high player can jump
    private int cooldownTime = 0;           //Timer for how often player can use slimeballs
    final int DUNGEON_GROUND_LEVEL = 450;   //The y-coordinate for the "ground"
    
    private boolean facingRight;
    private boolean facingLeft;
    
    GreenfootSound swordSlice = new GreenfootSound("Decapitation-SoundBible.com-800292304.wav");
    GreenfootSound slimeball = new GreenfootSound("Slime Splash-SoundBible.com-1894179558.wav");
    GreenfootSound slimeJump = new GreenfootSound("Slime-SoundBible.com-803762203.wav");
    
    
    public Player(){ //SARAH - Sets correct size of player when game starts
        slimeRight.scale(150, 130);
        setImage(slimeRight);
        facingRight = false;
        facingLeft = false;
    }

    public void act() //SARAH - Allows player to move and jump with arrow keys and spacebar and check for collisions
    {
        move();
        jump();
        hitByEnemy();
    }    
    
    
    public void move()  //Player can move slime depending on the arrow keys. Player must hold down arrow keys while
                        //shooting with spacebar to determine the direction the slimeball moves.
    {
        if (Greenfoot.isKeyDown("right")) {
            facingRight = true;
            facingLeft = false;
            
            setImage(slimeRight);
            setLocation(getX()+4, getY());
            
            //Hold down right key and space to shoot right unless cooldown activated
            if (cooldownTime > 0) 
                cooldownTime--;
                
            if ("space".equals(Greenfoot.getKey()) && cooldownTime == 0){
                //Slimeball facing right is created
                RightSlimeball rSlimeball = new RightSlimeball();
                getWorld().addObject(rSlimeball, getX() + 70, getY() + 20);
                slimeball.play();
                cooldownTime = 11;
            }
        }
        else if(Greenfoot.isKeyDown("left")) {
            facingRight = false;
            facingLeft = true;
            //Makes slime look left
            slimeLeft.scale(150, 130);
            setImage(slimeLeft);
            setLocation(getX()-4, getY());
            
            //Hold down left key and space to shoot left unless cooldown activated
            if (cooldownTime > 0) 
                cooldownTime--;

            if ("space".equals(Greenfoot.getKey()) && cooldownTime == 0)
            {
                //Slimeball facing right is created
                LeftSlimeball lSlimeball = new LeftSlimeball();
                getWorld().addObject(lSlimeball, getX() - 70, getY() + 20);
                slimeball.play();
                cooldownTime = 11;
            }
        }
    }
    
    
    public void jump(){ //SARAH - Player jumps by pressing up arrow key by moving the character up and back down
                        //to the ground level.
        if (getY() != DUNGEON_GROUND_LEVEL) // in middle of jump
        {
           //Falls faster due to gravity
           yBoost++;
           setLocation(getX(), getY() + yBoost);
           
           //Lands back down to the floor
           if (getY() >= DUNGEON_GROUND_LEVEL) {
                setLocation(getX(), DUNGEON_GROUND_LEVEL);
           }
        }
        else{
            //On ground and activates jump
            if(Greenfoot.isKeyDown("up")){
                slimeJump.play();
                yBoost = -29;
                setLocation(getX(), getY() + yBoost);
                
            }
        }
    }
    
    public boolean hitByEnemy() //SARAH - Returns true or false to the health bar if it intersects enemy
    {
        //Basically isTouching but better
        Actor knight = getOneObjectAtOffset(0, 0, Enemy.class); //If the enemy touches the center of the player
        //Enemy touches player
        if (knight != null) {
            swordSlice.play();
            return true;
        }
        else{
            return false;
        }
    }
    
    public boolean hitByBoss() //SARAH - Returns true or false to the health bar if it intersects boss
    {
        Actor captain = getOneObjectAtOffset(0, 0, Boss.class); //If the enemy touches the center of the player
        //Enemy touches player
        if (captain != null) {
            swordSlice.play();
            return true;
        }
        else{
            return false;
        }
    }
}
