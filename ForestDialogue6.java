/**
 * Program: ForesDialogue6.java
 * 
 * Name: Sarah Huang
 * 
 * Purpose: Dialogue in forest #6
 * 
 * Forest Background Image Source: https://www.deviantart.com/pixel-keep/art/Deep-Forest-742215940
 */

import greenfoot.*; 

public class ForestDialogue6 extends World
{
    private GreenfootImage slimeRight = new GreenfootImage("CROP_green_right1.png");

    public ForestDialogue6()    //SARAH - Sets forest background with dialogue and slime
    {    
        super(1080, 580, 1); 
        GreenfootImage bg = getBackground();
        slimeRight.scale(150, 130);
        bg.drawImage(slimeRight, 460, 380);
        showText("Press ENTER quickly",760, getHeight()/4);
    }
    
    public void act()   //SARHAH - Player continues by pressing enter to next dialogue screen
    {
        if (Greenfoot.isKeyDown("enter")) Greenfoot.setWorld(new ForestDialogue7());
    }
}
