/**
 * Program: Counter.java
 * 
 * Name: Sarah Huang, Tyler Blair
 * 
 * Purpose: Displays and updates timer and scoreboard.
 */

import greenfoot.*; 
public class Counter extends Actor
{
    int score;
    private int time = 3600;    //60 seconds = 3600

   public Counter(){    //SARAH - Adds the score and time on the screen
        setImage(new GreenfootImage("Score: " + score + "\n Time: " + (time/60), 40, Color.WHITE, new Color (0, 0, 0, 0)));
    }
    
   public void act() //SARAH - Counts the time down and updates the score and time
    {
        countTime();
        setImage(new GreenfootImage("Score: " + score + "\n Time: " + (time/60), 40, Color.WHITE, new Color (0, 0, 0, 0)));
    }    
    
    
   private void countTime(){    //TYLER - Stops it when it gets to zero
        time--;
        if (time == 0)
        {
            Greenfoot.setWorld(new ForestDialogue1());
        }
    }
    
   public int getTime(){    //SARAH - Allows dungeon world to access time
       return time;
   }

 }
    
    
