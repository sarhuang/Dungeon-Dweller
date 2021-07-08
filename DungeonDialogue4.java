/**
 * Program: DungeonDialogue4.java
 * 
 * Name: Sarah Huang
 * 
 * Purpose: Dialogue screen in dungeon #4.
 * 
 * Dungeon Image source: https://www.pinterest.com/pin/735564551619109850/?autologin=true
 */
import greenfoot.*; 
public class DungeonDialogue4 extends World
{
    private GreenfootImage slimeRight = new GreenfootImage("CROP_green_right1.png");
    private GreenfootImage bg = new GreenfootImage("dungeon_dialogue4.png");
    public DungeonDialogue4() //SARAH - Sets the dialogue image as the background and adds slime
    {    
        super(1080, 580, 1); 
        GreenfootImage bg = getBackground();
        slimeRight.scale(150, 130);
        bg.drawImage(slimeRight, 460, 380);
        //Player player = new Player();
        //addObject(player, 125, 500);

    }
    
    public void act() //SARAH - Player presses enter to continue to next dialogue screen
    {
        if (Greenfoot.isKeyDown("enter")) Greenfoot.setWorld(new DungeonDialogue5());
    }
}
