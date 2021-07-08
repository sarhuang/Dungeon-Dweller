/**
 * Program: DungeonDialogue1.java
 * 
 * Name: Sarah Huang
 * 
 * Purpose: Cutscene #1 in the dungeon level.
 * 
 * Image source: https://www.pinterest.com/pin/735564551619109850/?autologin=true
 * 
 */
import greenfoot.*; 
public class DungeonDialogue1 extends World
{
    
    private GreenfootImage slimeRight = new GreenfootImage("CROP_green_right1.png");
    private GreenfootImage bg = new GreenfootImage("dungeon_dialogue1.png");
    public DungeonDialogue1() //SARAH - Sets up the background, slime, and text box.
    {    
        super(1080, 580, 1); 
        GreenfootImage bg = getBackground();
        slimeRight.scale(150, 130);
        bg.drawImage(slimeRight, 460, 380);
        //Player player = new Player();
        //addObject(player, 125, 500);

    }
    
    public void act() //SARAH - Enter is pressed and transitions to the next cutscene.
    {
        if (Greenfoot.isKeyDown("enter")) Greenfoot.setWorld(new DungeonDialogue2());
    }
}
