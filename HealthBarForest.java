/**
 * Program: HealthBarDungeon.java
 * 
 * Name: Sarah Huang
 * 
 * Purpose: The class definition for the player's health bar for the forest level.
 *          It draws the health bar and calculates the player's health.
 * 
 */

import greenfoot.*;  
public class HealthBarForest extends Actor
{   
    private GreenfootImage slimeDead = new GreenfootImage("CROP_green_left_dead.png");
    private int health = 100;
    private int deathDelay = 20;

    
    public HealthBarForest(){   //SARAH - Draws the health bar on screen
        setImage(new GreenfootImage(102, 12));
        getImage().drawRect(0, 0, 101, 11);
        getImage().setColor(Color.GREEN);
        getImage().fillRect(1, 1, health, 10);
        
    }
    
    public void act() //SARAH - Updates health bar and follow the player's location
    {
        setImage(new GreenfootImage(102, 12));
        getImage().drawRect(0, 0, 101, 11);
        getImage().setColor(Color.GREEN);
        getImage().fillRect(1, 1, health, 10);
        
        World world = getWorld();
        ForestWorld forest = (ForestWorld)world;
        setLocation(forest.getPlayer().getX(), forest.getPlayer().getY() - 70);
        loseHealth();
    }    


    public void loseHealth(){   //SARAH - Subtracts health and triggers game over screen if health reaches zero
        World world = getWorld();
        ForestWorld forest = (ForestWorld)world;
        setLocation(forest.getPlayer().getX(), forest.getPlayer().getY() - 70);
        if(forest.getPlayer().hitByBoss()){
            health--;
        }
        if(health <= 0){
            slimeDead.scale(150, 130);
            forest.getPlayer().setImage(slimeDead);
            deathDelay--;   //Just so the user can see the dead sprite for longer
        }
        if(deathDelay == 0){
           getWorld().removeObject(forest.getPlayer());
           getWorld().removeObject(this);
        }
    }
    
    public int getHealth(){ //Allows ForestWorld to access player's health
        return health;
    }
}
