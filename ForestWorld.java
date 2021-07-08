/**
 * Program: ForestWorld.java
 * 
 * Name: Sarah Huang
 * 
 * Purpose: The second level with a boss.
 * 
 * Forest Background Image Source: https://www.deviantart.com/pixel-keep/art/Deep-Forest-742215940
 * Battle Music Source: https://wingless-seraph.net/en/material-music.html
 */

import greenfoot.*;

public class ForestWorld extends World
{
    public Player slime = new Player();
    HealthBarForest healthbarForest = new HealthBarForest();
    Boss captain = new Boss();
    GreenfootSound battleMusic = new GreenfootSound("Battle-Sonic_loop (online-audio-converter.com).mp3");
    
    public ForestWorld()    //SARAH - Adds the player, its healthbar, and the boss on screen
    {    
        super(1080, 580, 1); 
        addObject(slime, getWidth()/2, 450);
        addObject(captain, 0, 380);
        addObject(healthbarForest, slime.getX(), slime.getY() - 70);
    }
    
    public void act(){  //SARAH - If the boss's health reaches zero, the music will stop and show the game over screen
        if(captain.getBossHealth() <= 0)
            battleMusic.stop();
        else
            battleMusic.play();
            
        battleMusic.setVolume(60);
        showGameOver();
        
    }
    
    public Player getPlayer(){  //SARAH - Enables ForestWorld to have access to the Player class
       return slime;
    }
    
    public HealthBarForest getHealthBarForest(){    //SARAH - Enables ForestWorld to have access to HealthBarForest
       return healthbarForest;
    }
    
    public void showGameOver(){ //SARAH - Sets the world to the boss game over screen
       if(getHealthBarForest().getHealth() == 0){
           Greenfoot.setWorld(new GameOver2());
        }
    }
}
