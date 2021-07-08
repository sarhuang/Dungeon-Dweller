/**
 * Program: Start.java
 * 
 * Name: Tyler Blair
 * 
 * Purpose: The directions page before the player plays the game.
 */
import greenfoot.*; 

public class Start extends World
{
    private GreenfootImage directions = new GreenfootImage("directions.png");
    private GreenfootImage message = new GreenfootImage("message1.png");
    
    public Start()  //TYLER - Sets black backdrop and adds the directions
    {    
        super(1080, 599, 1); 
        GreenfootImage back = getBackground();
        back.setColor(Color.BLACK);
        back.fill();
        message();
        prepare();
        back.drawImage(directions, 410 ,0);
        back.drawImage(message, 100 ,75);
    }
    public void act()   //TYLER - If the player presses enter, the screen will change to the dialgoue.
    {
        if (Greenfoot.isKeyDown("enter")) {
            Greenfoot.setWorld(new DungeonDialogue1());
        }
    }
    
    private void message()  //TYLER - Shows text
    {
        showText("PRESS ENTER TO START", 540, 450);
    }
    
    private void prepare()  //TYLER - Adds the player on screen
    {
        Player player = new Player();
        addObject(player, 125, 500);
        
    }
}
