import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.awt.FontMetrics;
import java.awt.Graphics2D;
import java.util.HashMap; 
import java.util.Map;
/**
 * A button without a background that can have a custom font, font size, and text colour. It will highlight a different colour when the user mouses
 * over the button, this colour can be customized too. Multiple buttons can be added to the world. 
 * 
 * @author Alex Li
 * @version May 14 2020
 */
public class Button extends Actor
{
    //Declare private variables
    private GreenfootImage img, temp;
    //text is the text the button will display
    private String text, buttonGroup;
    //The font of the button
    private Font TextFont;
    //The colours the button will have
    private Color textColour, highlightColour;
    //The font size and button width of the button
    private int fontSize, textWidth = 0, actCount = 0;
    //Helper class to find the width of height of the button
    private TextSizeFinder finder = new TextSizeFinder();
    //Information about the mouse
    private MouseInfo mouse;
    private boolean isDropDown; 
    private static HashMap<String, Integer> ButtonSizes = new HashMap<String, Integer>();  
    /**
     * Constructs a Button with a given String and a specified font size. It will have a default text colour of light gray and a default font of calibri 
     * @param text              String value to display
     * @param fontSize          The font size, as an integer
     */
    public Button (String text, int fontSize){
        isDropDown = false; 
        //sets the colours
        textColour = new Color(255,255,255);
        highlightColour = new Color(158,158,159);
        //sets the font
        TextFont = new Font ("calibri",fontSize);
        this.text = text;
        this.fontSize = fontSize;
        //creates a greenfoot image with the correct width and height to store the text
        img = new GreenfootImage(finder.getTextWidth(text,fontSize), finder.getTextHeight(text,fontSize));
        img.setColor(textColour);
        img.setFont(TextFont);
        //draws the text
        img.drawString(text,0,img.getHeight() - (img.getHeight()/3));
        //sets the image of the button
        setImage(img);
    }
    /**
     * Constructs a Button with a given String, a specified font size. 
     * @param text              String value to display
     * @param fontSize          The font size, as an integer
     */
    public Button (String text, String buttonGroup, int fontSize, boolean isDropDown){
        //saves the button text
        this.text = text;
        //sets the font
        TextFont = new Font ("Calibri", fontSize);
        this.fontSize = fontSize;
        this.isDropDown = isDropDown;
        this.buttonGroup = buttonGroup;
        //creates a temporary image to find how big the text will be relative to the button
        temp = new GreenfootImage (text, fontSize, Color.WHITE, Color.BLACK);
        //if the key already exists and the size of the text to be displayed it larger than the others, replace the key
        if(ButtonSizes.containsKey(buttonGroup) && temp.getWidth()+5 > ButtonSizes.get(buttonGroup)) ButtonSizes.put(buttonGroup, temp.getWidth()+5);
        //else, add the key
        else ButtonSizes.put(buttonGroup, temp.getWidth()+5);
        img = new GreenfootImage(ButtonSizes.get(buttonGroup), temp.getHeight()+5);
        //sets the backgroud colour to white
        img.setColor(Color.WHITE);
        img.fill();
        //if it is a subButton, the text colour will be black
        if(isDropDown) img.setColor(Color.BLACK);
        //else, it will be white
        else img.setColor(Color.WHITE);
        //creates the button
        img.drawString(text, 5, 15);
        setImage(img);
    }
    /**
     * Act - do whatever Button wants to do. This method is called whenever the 'Act' or 'Run' button gets pressed in the environment.
     * Checks whether or not the mouse is hovering over the button, if it is highlight it, otherwise don't
     */
    public void act(){
        if(actCount % 5 == 0){
            if(Greenfoot.mouseMoved(this)){ 
                //Highlights the button
                this.hoverOver();
            }
            //resets the button to its normal state
            if(Greenfoot.mouseMoved(null) &&!Greenfoot.mouseMoved(this)) this.reset();
        }
    }
    /**
     * Highlights the button if the mouse is hoving over a button
     */
    private void hoverOver(){
        //clears the image to prevent the images from overlapping 
        img.clear();
        if(isDropDown){
            img.setColor(highlightColour);
            img.fill();
            img.setColor(textColour);
            img.setFont(TextFont);
        }
        else{
            img.setColor(highlightColour);
            img.setFont(TextFont);
        }
        //draws the text, but with the highlighted colour
        img.drawString(text,0,img.getHeight() - (img.getHeight()/3));
        setImage(img);
    }
    /**
     * Resets the button if the mouse moves away from it
     */
    private void reset()
    {
        /*//clears the image to prevent the images from overlapping 
        img.clear();
        if(isDropDown){
            img.setColor(Color.WHITE);
            img.fill();
            img.setColor(textColour);
            img.setFont(TextFont);
        }
        else{
            img.setColor(textColour);
            img.setFont(TextFont);
        }
        //redraws the text
        img.drawString(text,0,img.getHeight() - (img.getHeight()/3));
        setImage(img);*/
        //new
        temp = new GreenfootImage (text, fontSize, Color.BLACK, Color.WHITE);
        if(ButtonSizes.containsKey(buttonGroup)) img = new GreenfootImage (ButtonSizes.get(buttonGroup), temp.getHeight()+5);
        else img = new GreenfootImage (temp.getWidth()+5, temp.getHeight()+5);
        if(isDropDown){
            img.setColor(Color.WHITE);
            img.fill();
        }
        //if it is a subButton, the text colour will be black
        img.setColor(textColour);
        img.drawString(text, 5, 15);
        setImage(img);
    }
    /**
     * Returns the largest button, in terms of length, in a specified group
     * @param buttonGroup       A String that specifies which group the button is in
     * @return int              The largest button in the group
     */
    public int getSpaces(String buttonGroup){
        return ButtonSizes.get(buttonGroup);
    }
    /**
     * Returns the group a button belongs to
     * @return String           The group the button belongs to
     */
    public String getbuttonGroup(){
        return this.buttonGroup;
    }
}
