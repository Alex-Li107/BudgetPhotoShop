import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Its the 1155 by 25 grey bar on top of the world. It is in its own class so that when the image is dragged over the grey bar, it will not cover it
 * 
 * @author Alex Li
 * @version 0.2
 */
public class Menu extends Actor
{
    //Instance variables to create the bar 
    private GreenfootImage menuBar;
    private Color menuBack;
    /**
     * Creates a 1155 by 25 grey bar
     */
    public Menu(){
        menuBar = new GreenfootImage(1155,25);
        menuBar.drawRect(0,0,1155,25);
        menuBack = new Color(88,88,88);
        menuBar.setColor(menuBack);
        menuBar.fillRect(0,0,1155,25);
        setImage(menuBar);
    }
}
