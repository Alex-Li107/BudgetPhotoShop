import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
/**
 * Button Menu is an object that you can add to the world. It accepts an array of buttons and displays them as a drop down menu. Different menus should be 
 * created for different drop down menus
 * 
 * @author Alex Li
 * @version 1.0
 */
public class ButtonMenu extends Actor
{
    //The array to hold all the buttons in the drop down menu
    private Button buttonArr[];
    //an image so that ButtonMenu can be added to the world have not have a bunch of little greenfoots
    private GreenfootImage i = new GreenfootImage(1,1);
    //a boolean to control whether or not the buttons should be showing or not
    private boolean isShowing = false;
    /**
     * Creates a ButtonMenu out of an array of buttons
     * @param buttons       The array of buttons to be added to the ButtonMenu
     */
    public ButtonMenu(Button buttons[]){
        //copies all the buttons into buttonArr
        buttonArr = buttons.clone();
        setImage(i);
    }
    /**
     * Shows the buttons in the buttonArr as a drop down menu at a specific x-coordinate
     * @param Xpos          The Xpos where you want the buttonMenu to start
     */
    public void showButtons(int Xpos){
        for(int i = 0; i < buttonArr.length; i++){
            //adds the buttons to the world
            getWorld().addObject(buttonArr[i], Xpos + buttonArr[i].getSpaces(buttonArr[i].getbuttonGroup())/2, 37  + i * 25);
        }
        isShowing = true;
    }
    /**
     * Shows the buttons in the buttonArr as a drop down menu at a specific x and y coordinate
     * @param Xpos          The x-coordinate where you want the buttonMenu to start
     * @param Ypos          The y-coordinate where you want the buttonMenu to start
     */
    public void showButtons(int Xpos, int Ypos){
        for(int i = 0; i < buttonArr.length; i++){
            //adds the buttons to the world
            getWorld().addObject(buttonArr[i], Xpos + buttonArr[i].getSpaces(buttonArr[i].getbuttonGroup())/2, 37 + Ypos  + i * 25);
        }
        isShowing = true;
    }
    /**
     * Removes the buttons from the world
     */
    public void removeButtons(){
        for(int i = 0; i < buttonArr.length; i++){
            getWorld().removeObject(buttonArr[i]);
        }
        isShowing = false;
    }
}
