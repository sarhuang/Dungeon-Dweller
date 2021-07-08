/**
 * Program: DungeonWorld.java
 * 
 * Name: Sarah Huang
 * 
 * Purpose: The class definition for the first level. 
 * Music, score, health, and character placement is stored here.
 * 
 * Image source: https://www.pinterest.com/pin/735564551619109850/?autologin=true
 * Background Music source: https://wingless-seraph.net/en/material-music.html
 */

import greenfoot.*; 
public class DungeonWorld extends World
{
   //Variables for spawnKnights()
   private int spawnTime = 80;    //How fast the enemies should appear in the world
   private int time = 0;           //Total time program has been running
   private int randomSide;         //Used to decide which side the enemy will spawn from
   
   Counter counter = new Counter(); //Initialize Counter object so that Enemy class can use it
   public Player slime = new Player();
   HealthBarDungeon healthbarDungeon = new HealthBarDungeon();

   GreenfootSound dungeonMusic = new GreenfootSound("SNES-Dungeon02_loop (online-audio-converter.com).mp3");
   
   public DungeonWorld()    //SARAH - Adds the player, score, time, and healthbar on screen
   {    
       super(1080, 580, 1); //Creates dimensions for world
        addObject(slime, 100, 450);  //Adds the Player on screen
        addObject(counter, 100, 75);       //Adds the time and score counter on screen
        addObject(healthbarDungeon, slime.getX(), slime.getY() - 70);
   }
    
   public void act(){   //SARAH - The timer increments, background scrolls, knights spawn, music plays, and the world
                        //the game over screen when the player dies
       if(counter.getTime() <= 3)   //Music stops once the timer reaches zero and transitions to next level
            dungeonMusic.stop();
       else
            dungeonMusic.play();
       scrollBackground();
       time++;
       spawnKnights();
       showGameOver();
   }
    
   private void scrollBackground()  //SARAH - Scrolls the background by redrawing it constantly
   {
        GreenfootImage background = new GreenfootImage(getBackground());
        getBackground().drawImage(background, -1, 0);
        getBackground().drawImage(background, getWidth()-11, 0);
   }
   
   public void spawnKnights(){  //SARAH - At certain time intervals, an enemy is added on screen either on the left or 
                                //right side of the world.
        if(time % spawnTime == 0){
            randomSide = Greenfoot.getRandomNumber(2);
            switch(randomSide){
                case 0: addObject(new Enemy(counter), 1080, 415); break;
                case 1: addObject(new Enemy(counter), 0, 415); break;
            }
        }
   }
   
   public Player getPlayer(){   //SARAH - DungeonWorld has access to the Player class
       return slime;
    }
    
   public HealthBarDungeon getHealthBarDungeon(){   //SARAH - DungeonWorld has access to the player's dungeon health bar
       return healthbarDungeon;
    }
   
   public Counter getCounter(){ //SARAH - DungeonWorld has access to the Counter class
       return counter;
    }
    
   public void showGameOver(){  //SARAH - Sets world to the game over screen when the player loses all health
       if(getHealthBarDungeon().getHealth() == 0){
           dungeonMusic.stop();
           Greenfoot.setWorld(new GameOver());
        }
    }
}