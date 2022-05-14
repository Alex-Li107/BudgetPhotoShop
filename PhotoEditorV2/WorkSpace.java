import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.io.File;
import javax.swing.JOptionPane;
import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.awt.Graphics2D;
import java.awt.image.ColorModel;
import java.awt.image.WritableRaster;
import java.util.ArrayList;
import java.io.FileNotFoundException;
import java.io.IOException;

/**
 * :)
 * 
 * @author Alex Li 
 * @version May 14 2020
 */
public class WorkSpace extends World
{
    private final String STARTING_FILE = "Default.jpg";
    private String fileName = "Default.jpg";
    private GreenfootImage canvas;
    private ImageHolder image;
    private Menu m = new Menu(); 
    private MouseInfo mouse;
    private int shiftX, shiftY;
    private ButtonMenu fileMenu, editMenu, filterMenu, colourAdjustMenu;
    
    private Button test = new Button("Ignore All But Red", 14);
    private Button file,edit,filter; 
    private Button fileArr[] = new Button[3];
    /**
     * Constructor for objects of class MyWorld.
     * 
     */
    public WorkSpace()
    {    
        super(1153, 684, 1); 
        image = new ImageHolder(STARTING_FILE);
        addObject(image,576,342);
        addObject(m,575,12);
        addObject(test,100,13);
        file = new Button("File",15);       
        fileArr[0] = new Button("Open (Ctrl + o)","file",14,true);
        fileArr[1] = new Button("Save (Ctrl + s)","file",14,true);
        fileArr[2] = new Button("Save As       ","file",14,true);
        fileMenu = new ButtonMenu(fileArr); 
        addObject(file, 15, 12);
        addObject(fileMenu,0,0);
    }
    public void act ()
    {
        //checks for mouse input
        checkMouse();
    }
    private void checkMouse ()
    {
        //for button presses only
        if (Greenfoot.mouseClicked(null)){
            if(Greenfoot.mouseClicked(test)){
                image.setImage(Processor.redOnly(image.getBufferedImage())); 
            }
            if(Greenfoot.mouseClicked(fileArr[0]))
            {
                openFile ();
            }
            //checks if the user pressed the save button
            else if(Greenfoot.mouseClicked(fileArr[1]))
            {
                save();
            }
            //checks if the user pressed the save as button
            else if(Greenfoot.mouseClicked(fileArr[2]))
            {
                saveAs();
            }
        }
        if(Greenfoot.mousePressed(image)){
            //Gets the information of the mouse when the user clicks on the image so that the image does not jump to the mouse 
            mouse = Greenfoot.getMouseInfo(); 
            shiftX = mouse.getX();
            shiftY = mouse.getY();
        }
        //Checks if the user is dragging the image
        if(Greenfoot.mouseDragged(image)){
             //Gets the information of the mouse when the user drags the image
             mouse = Greenfoot.getMouseInfo();        
             //Moves the image relative to where the mouse is
             image.setLocation(mouse.getX() - shiftX + image.getX(), mouse.getY() - shiftY + image.getY());
             //Finds where the mouse is after moving the image to prevent the image from jumping
             shiftX = mouse.getX();
             shiftY = mouse.getY();
        }
        if(Greenfoot.mouseMoved(file)){ 
            //Shows all of its sub-buttons via a pop down menu
            fileMenu.showButtons(0);
        }
        //Checks if the cursor is not on file or any of its sub-buttons
        if(Greenfoot.mouseMoved(null) && !Greenfoot.mouseMoved(file) && !Greenfoot.mouseMoved(fileArr[0]) && !Greenfoot.mouseMoved(fileArr[1]) && !Greenfoot.mouseMoved(fileArr[2])){
            //Removes the pop down menu
            fileMenu.removeButtons();
        }
    }
    /**
     * Allows the user to open a new image file.
     */
    private void openFile()
    {
        // Use a JOptionPane to get file name from user
        String fileName = JOptionPane.showInputDialog("Please input a file name with extension");
        // If the file opening operation is successful, update the text in the open file button
        if(image.openFile (fileName)){
            //resets everything
            /*pointer = 0;
            pointer2 = 0;
            undoRedo.clear(); SliderValue.clear();
            SliderValue.add(0);
            undoRedo.add(createGreenfootImageFromBI(makeCopy(image.getBufferedImage())));*/
            JOptionPane.showMessageDialog(null, "Success!");
        }
        //if the file name is invalid, show an error pop up
        else JOptionPane.showMessageDialog(null, "Invalid File Name", "File Not Found", JOptionPane.ERROR_MESSAGE);
    }
    /**
     * Saves the image with its current name and file extension
     */
    private void save()
    {
        try{
            //if the image is a png, save it as a png 
            if(fileName.substring(fileName.length()-3,fileName.length()).equals("png")){
                File f = new File (fileName);
                ImageIO.write(image.getImage().getAwtImage(), "png", f); // need to do some imports
                //shows a success pop up
                JOptionPane.showMessageDialog(null, "Success!");
            }
            //if the image is a jpg, save it as a jpg
            else if(fileName.substring(fileName.length()-3,fileName.length()).equals("jpg")){
                File f = new File (fileName);
                //removes the alpha layer
                BufferedImage bi = makeJpg(image.getImage().getAwtImage());
                ImageIO.write(bi, "jpg", f); // need to do some imports
                //shows a success pop up
                JOptionPane.showMessageDialog(null, "Success!");
            }
        }
        //shows an error pop up
        catch(java.io.IOException e){
            JOptionPane.showMessageDialog(null, "File Could Not Be Saved", "Error", JOptionPane.ERROR_MESSAGE);         
        }
    }
    /**
     * Allows the user to save the image as a new file with a custom name as either a png or jpg.
     */
    private void saveAs()
    {
        // Use a JOptionPane to get file name from user
        String fileName = JOptionPane.showInputDialog("Input file name with either \".png\" or \".jpg\""); 
        File f;
        try{
            // If the file saving operation is successful, save it as a png
            if(fileName.substring(fileName.length()-3,fileName.length()).equals("png")){
                f = new File (fileName);
                ImageIO.write(image.getImage().getAwtImage(), "png", f); // need to do some imports
                //shows a success pop up
                JOptionPane.showMessageDialog(null, "Success!");
            }
            // If the file saving operation is successful, save it as a jpg
            else if(fileName.substring(fileName.length()-3,fileName.length()).equals("jpg")){
                f = new File (fileName);
                //removes the alpha layer
                BufferedImage bi = makeJpg(image.getImage().getAwtImage());
                ImageIO.write(bi,"jpg", f); // need to do some imports
                //shows a success pop up
                JOptionPane.showMessageDialog(null, "Success!");
            }
        }
        //why isnt this catching?
        catch(FileNotFoundException e){
            JOptionPane.showMessageDialog(null, "Invalid File Name", "File Not Found", JOptionPane.ERROR_MESSAGE);    
        }
        catch(IOException e){
            JOptionPane.showMessageDialog(null, "Invalid File Name", "File Not Found", JOptionPane.ERROR_MESSAGE);    
        }      
        catch(NullPointerException e){
            //if the user presses Esc, just close the box and do not error. Other programs do this to
            //JOptionPane.showMessageDialog(null, "Please enter something", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
    /**
     * Removes the alpha layer of a BufferedImage
     * @param bi                The image that you want the alpha layer to be removed from
     * @return BufferedImage    The BufferedImage without its alpha layer
     */
    private BufferedImage makeJpg(BufferedImage bi){
        //Code from: MadProgrammer
        BufferedImage copy = new BufferedImage(bi.getWidth(), bi.getHeight(), BufferedImage.TYPE_INT_RGB);
        Graphics2D g2d = copy.createGraphics();
        g2d.setColor(java.awt.Color.WHITE); 
        g2d.fillRect(0, 0, copy.getWidth(), copy.getHeight());
        g2d.drawImage(bi, 0, 0, null);
        g2d.dispose();
        return copy;
    }
}
