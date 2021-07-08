/**
 * Program: EndScreen.java
 * 
 * Name: Tyler Blair
 * 
 * Purpose: Winning screen when player completes game
 * 
 * Good End Screen Music Source: https://soundimage.org/fantasywonder/
 */

import greenfoot.*; 

public class EndScreen extends World
{
    private GreenfootImage fancyMessage = new GreenfootImage("message2.png");
    private GreenfootSound titleMusic = new GreenfootSound("Our-Mountain_v003_Looping.mp3");

    public EndScreen()  //TYLER - Sets black backdrop and displays text
    {    
        super(1080, 580, 1); 
        GreenfootImage back = getBackground();
        back.setColor(Color.BLACK);
        back.fill();
        back.drawImage(fancyMessage, 220 ,75);
        message();
    }

    public void act(){  //TYLER - Music plays. Player presses enter to replay game or escape to stop Greenfoot
        titleMusic.play();
        if(Greenfoot.isKeyDown("enter")) {
            titleMusic.stop();
            Greenfoot.setWorld(new Title());
        }
        
        if (Greenfoot.isKeyDown("escape")) 
            Greenfoot.stop();   
    }
    
    private void message()  //TYLER - Shows instructions
    {
        showText("Press ENTER To Restart", getWidth()/2 + 40, getHeight()/2 + 180);
        showText("Press ESCAPE To Quit", getWidth()/2 + 40, getHeight()/2 + 220);   
    }
}
