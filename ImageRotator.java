
import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

/**
 * @author xiaobzhu
 * This is class is used to build up the GUI and set up the rotate function in this class, in this class it include two listener class
 * one is the Keylistener used to detect the user change on the time interval part, the other one is the class to build up the action
 * listener, used this action litener to get the user action on the start button
 */
public class ImageRotator extends JFrame {
    /**
     * this is the BufferedImage type value used to save the image information to let the image can do the change in the following code
     */
    private BufferedImage bufferedImage;
    /**
     * This is the JButton type value to set up the JButton and set the text for the JButton
     */
    private JButton angle = new JButton("Angle");
    /**
     * This is the JButton type value to set up the JButton and set the text for the JButton
     */
    private JButton start = new JButton("Start");
    /**
     * This is the JTextField type value to set up the textfield to get the user input to set the angle change
     */
    private JTextField setAngle = new JTextField();
    /**
     * This is the JTextField type value to set up the textfield to get the user input to set the time interval
     */
    private JTextField setTime = new JTextField();
    /**
     * Thie is the JLabel type value to set up the image in the GUI
     */
    public JLabel image;
    /**
     * This is the int value to save the angle change each time
     */
    private int num;
    /**
     * This is a String value to save the text in the textField
     */
    private String text;
    /**
     * This is the String value to save the text in the second textField
     */
    private String text1;
    /**
     * This is the Timer Type value used to save the timer information to do the time interval control and to decide the delay and something
     */
    private Timer timer;

    private BufferedImage NewImage;

    /**
     * This is the constructor of the ImageRotator class in this class it contain two actionlistener class, one is working for get the user keyreleased action
     * one is get the user click mouse action, keyrelease action is used to design the dynamic change on the image rotator, for the mouse action is used to design
     * to save the user input set angle change value
     */
    public ImageRotator(){
        super("Image Rotator");
        Buttonhandler buttonhandler = new Buttonhandler(); // call the Buttonhandle class to add the button handler to the button
        try{                                                                              //this try is used to read the image from the root directory
            bufferedImage = ImageIO.read(getClass().getResource("IG.png"));        //A class containing static convenience methods for locating ImageReaders and ImageWriters, and performing simple encoding and decoding.
        }catch(IOException e){
            System.out.println("error");
        }
        int position = (int) Math.sqrt(bufferedImage.getHeight()*bufferedImage.getHeight()+bufferedImage.getWidth()*bufferedImage.getWidth()); // this int value is used to calcualate the length of the diagonal
        Rectangle positon = new Rectangle(new Dimension(position, position)); //this call the rectangle class to draw a new retangle by the position calculate before
        NewImage = new BufferedImage(positon.width, positon.height,BufferedImage.TYPE_INT_ARGB); //this line is used to set a new buffered image to use the graphic two D
        image = new JLabel(new ImageIcon(bufferedImage));     //An implementation of the Icon interface that paints Icons from Images.
        JPanel panel;
        panel = new JPanel();
        FlowLayout layout1 = new FlowLayout();
        panel.setLayout(layout1);
        JPanel panel1 = new JPanel();
        FlowLayout layout = new FlowLayout();
        panel1.setLayout(layout);
        Dimension dimension = new Dimension(300, 50);
        setAngle.setPreferredSize(dimension);
        Dimension dimension1 = new Dimension(100, 50);
        angle.setPreferredSize(dimension1);
        angle.addActionListener(buttonhandler);
        panel1.add(setAngle);
        panel1.add(angle);

        JPanel panel2 = new JPanel();
        panel2.setLayout(layout);
        Dimension dimension3 = new Dimension(400, 50);
        setTime.setPreferredSize(dimension3);
        setTime.addKeyListener(new Keyhandler());
        panel2.add(setTime);


        JPanel panel3 = new JPanel();
        panel3.setLayout(layout);
        Dimension dimension2 = new Dimension(400, 50);
        start.setPreferredSize(dimension2);
        start.addActionListener(buttonhandler);
        panel3.add(start);


        panel.add(panel1);
        panel.add(panel2);
        panel.add(panel3);
        panel.add(image);
        add(panel);
    }

    /**
     * The listener interface for receiving action events. The class that is interested in processing an action event
     * implements this interface, and the object created with that class is registered with a component, using the component's
     * addActionListener method. When the action event occurs, that object's <code>actionPerformed</code> method is invoked.
     */
    public class Buttonhandler implements ActionListener {
        int i = 0;

        /**
         * This class will revoke when get the action
         * @param e action parameter
         */
        @Override
        public void actionPerformed(ActionEvent e) {
            if(e.getSource() == angle){
                text = setAngle.getText();
                angle.setEnabled(false);
                setAngle.setEnabled(false);
                if(text.length() == 0){
                    setAngle.setText("Please set value for rotate value");
                    throw new IllegalArgumentException("Please set value for rotate value");
                }
                for(int i = 0;i < text.length(); i++){
                    if(!Character.isDigit(text.charAt(i))){
                        setAngle.setText("Please in put digit");
                    }
                }
                if(Integer.parseInt(text) >= 360 || Integer.parseInt(text) <= 0){
                    setAngle.setText("Please input value between (0-360)");
                    angle.setEnabled(true);
                    setAngle.setEnabled(true);
                }
            }
            if(e.getSource() == start){
                if(start.getText().equals("Stop")){
                    timer.stop();
                    start.setText("Start");
                }else {
                    num = Integer.parseInt(text);
                    start.setText("Stop");
                    timer = new Timer(Integer.valueOf(setTime.getText()), i -> {          //This is used set the timer let it return the rotate method after the delay time setting
                        image.setIcon(new ImageIcon(Rotate(num)));         //reset the image label
                        num = num + Integer.valueOf(text);

                    });
                    timer.start();   //begin the timer to work
                }
            }

        }

    }

    /**
     * This class is working on to set the keylistener, to get the user input in the second text field to reach the dynamic change speed
     */
    public class Keyhandler implements KeyListener{
        /**
         * Method inplements from the Keylistener class, can not  be deleted
         * @param e the parament in the method
         */
        @Override
        public void keyTyped(KeyEvent e) {

        }
        /**
         * Method inplements from the Keylistener class, can not  be deleted
         * @param e the parament in the method
         */
        @Override
        public void keyPressed(KeyEvent e) {

        }
        /**
         * Method inplements from the Keylistener class, can not  be deleted
         * @param e the parament in the method
         */
        @Override
        public void keyReleased(KeyEvent e) {
            if(start.getText().equals("Stop")){
                if(setTime.getText().isEmpty()){
                    timer.stop();
                    start.setText("Start");
                    angle.setEnabled(true);
                    setAngle.setEnabled(true);
                }
                else{
                    text1 = setTime.getText();
                    timer.setDelay(Integer.valueOf(text1));
                }

            }
        }
    }

    /**
     * This method is working on to retote the image,
     * @param angle is the int value that decide how many degree to rotate
     * @return the image after rotate
     */
    public BufferedImage Rotate( int angle) {
        Graphics2D g = NewImage.createGraphics(); // call the Graphics2D abstract class to use the component
        g.translate((NewImage.getWidth()-bufferedImage.getWidth())/2,(NewImage.getHeight()-bufferedImage.getHeight())/2); // make the graph to be the center position
        g.rotate(Math.toRadians(angle),bufferedImage.getWidth()/2,bufferedImage.getHeight()/2); // make the graph rotate
        g.drawImage(bufferedImage,null,null); // draw the rotated image
        g.dispose();//delete the current image
        return NewImage;
    }


}



