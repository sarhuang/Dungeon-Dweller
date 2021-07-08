/**
 * Program: DungeonDialogue5.java
 * 
 * Name: Sarah Huang
 * 
 * Purpose: Dialogue screen in dungeon #5.
 * 
 * Dungeon Image source: https://www.pinterest.com/pin/735564551619109850/?autologin=true
 */

import greenfoot.*; 
public class DungeonDialogue5 extends World
{
    private GreenfootImage slimeRight = new GreenfootImage("CROP_green_right1.png");
    private GreenfootImage bg = new GreenfootImage("dungeon_dialogue5.png");
    public DungeonDialogue5() //SARAH - Sets dialogue image as background and adds slime
    {    
        super(1080, 580, 1); 
        GreenfootImage bg = getBackground();
        slimeRight.scale(150, 130);
        bg.drawImage(slimeRight, 460, 380);
        //Player player = new Player();
        //addObject(player, 125, 500);

    }
    
    public void act() //SARAH - Player presses enter to continue to the dungeon level
    {
        if (Greenfoot.isKeyDown("enter")) Greenfoot.setWorld(new DungeonWorld());
    }
}
