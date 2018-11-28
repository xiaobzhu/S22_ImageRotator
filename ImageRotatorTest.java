import javax.swing.*;

/**
 * @author xiaobzhu
 * This is the main class of the ImageRotator , that used to run this project
 */
public class ImageRotatorTest {
    /**
     * This the main method that used to run the project
     * @param args parameter in the main method
     */
    public static void main(String args[]){
        ImageRotator imageRotator = new ImageRotator();
        imageRotator.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        imageRotator.setVisible(true);
        imageRotator.setSize(500,600);
    }


}
