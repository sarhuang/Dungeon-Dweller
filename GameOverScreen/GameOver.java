/**
 * Program: GameOver.java
 * 
 * Name: Tyler Blair
 * 
 * Purpose: Game over screen when the player dies in the dungeon level
 * 
 * Game Over Music Source: https://opengameart.org/content/game-over-theme
 */

import greenfoot.*; 
public class GameOver extends World
{
    private GreenfootImage gameOver = new GreenfootImage("game_over.png");
    private GreenfootImage dead = new GreenfootImage("CROP_green_right_dead.png");
    private GreenfootSound gameOverMusic = new GreenfootSound("No Hope.mp3");
    
    public GameOver()   //TYLER - Sets a black backdrop, dead slime, and text
    {    
        super(1080, 580, 1); 
        GreenfootImage back = getBackground();
        back.setColor(Color.BLACK);
        back.fill();
        back.drawImage(gameOver, 170 ,-70);
        dead.scale(150, 130);
        back.drawImage(dead, getWidth()/3 + 100, 250);
        message();
    }
    
    private void message()  //TYLER - Displays instructions on how to proceed
    {
        showText("Press ENTER To Continue", getWidth()/2, getHeight()/2 + 140);
        showText("Press ESCAPE To Quit", getWidth()/2, getHeight()/2 + 180);   
    }
    
    public void act()   //Player presses enter to try again or escape to quit. Music playing in background.
    {
        gameOverMusic.play();
        if (Greenfoot.isKeyDown("enter")) {
            gameOverMusic.stop();
            Greenfoot.setWorld(new DungeonWorld());
        }
        if (Greenfoot.isKeyDown("escape")){
            Greenfoot.setWorld(new EndScreen2());
        }
    }
}
