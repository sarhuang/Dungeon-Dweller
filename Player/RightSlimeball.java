/**
 * Program: RightSlimeball.java
 * 
 * Name: Sarah Huang
 * 
 * Purpose: The player's right projectile in the game. A slimeball moves right 
 * and will disappear when it reaches the edge of the world or the timer runs out.
 */

import greenfoot.*;
public class RightSlimeball extends Actor
{
    private int time = 50;  //How long the slimeball appears on screen

    public void act() //SARAH - Moves 10 units in 50 milliseconds and disappears if it touches the edge of the world
                      //or after 50 miliseconds.
    {
        move(10);
        time--;

        if(isAtEdge()){
            getWorld().removeObject(this);
        }
        else if(time == 0){
            getWorld().removeObject(this);
        }
    }   
}
