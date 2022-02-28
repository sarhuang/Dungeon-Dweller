/**
 * Program: EndScreen2.java
 * 
 * Name: Tyler Blair
 * 
 * Purpose: Losing screen when player refuses to complete game
 */

import greenfoot.*;  

public class EndScreen2 extends World
{
    private GreenfootImage message = new GreenfootImage("message3.png");
    
    public EndScreen2() //TYLER - Sets black backdrop and adds text
    {    
        super(1080, 580, 1); 
        GreenfootImage back = getBackground();
        back.setColor(Color.BLACK);
        back.fill();
        back.drawImage(message, 100 ,75);
    }
    
    public void act(){  //TYLER - Greenfoot stops game
        Greenfoot.stop();
    }
}
