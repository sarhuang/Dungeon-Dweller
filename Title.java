/**
 * Program: Title.java
 * 
 * Name: Tyler Blair
 * 
 * Purpose: Title screen introducing game to the player
 * 
 * Title Screen Music Source: https://soundimage.org/fantasywonder/
 */

import greenfoot.*;

public class Title extends World
{
    private GreenfootImage title = new GreenfootImage("title.png");
    private GreenfootSound titleMusic = new GreenfootSound("Our-Mountain_v003_Looping.mp3");

    public Title()  //TYLER - Sets black background and adds the game name image and instructions to advance
    {    
        super(1080, 599, 1); 
        GreenfootImage back = getBackground();
        back.setColor(Color.BLACK);
        back.fill();
        Greenfoot.start();
        back.drawImage(title, 200 ,0);
        message();
    }
    
    public void act()   //TYLER - Music playing in background. Player presses space to go to directions page.
    {
        titleMusic.play();
        if (Greenfoot.isKeyDown("space")) {
            titleMusic.stop();
            Greenfoot.setWorld(new Start());
        }
    }
    
    private void message()  //TYLER - Shows instructions to advance forward
    {
        showText("PRESS SPACE TO CONTINUE", 540, 550);
    }
}
