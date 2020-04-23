import javax.swing.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

// main class
public class GUIRomanApp extends JFrame {
    private JPanel mainPanel; // main panel of the swing form
    private JTextField txt1;
    private JLabel lbl1;
    private JButton btn;

    // constructor
    private GUIRomanApp(String title) {
        super(title);

        // adjust the project window or frame
        this.setContentPane(mainPanel);
        this.setLocationRelativeTo(null);
        this.setSize(500, 600);
        this.setResizable(false);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        lbl1.setSize(300, 100);
        //this.pack();

        // Action Listener for Button Action Click
        btn.addActionListener(e -> {
            ConvertLogic2();
        });

        // Key Typing Action Listener
        txt1.addKeyListener(new KeyAdapter() {
            @Override
            public void keyTyped(KeyEvent e) {
                super.keyTyped(e);
                if ((e.getKeyChar() >= '0' && e.getKeyChar() <= '9' ) ) {
                    txt1.setBorder(javax.swing.BorderFactory.createLineBorder(null));
                } else {
                    e.consume();
                    txt1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 0, 0), 2));
                }

                if (e.getKeyChar() == com.sun.glass.events.KeyEvent.VK_BACKSPACE) {
                    txt1.setBorder(javax.swing.BorderFactory.createLineBorder(null));
                }

                if (e.getKeyCode() == KeyEvent.VK_ENTER){
                    txt1.setBorder(javax.swing.BorderFactory.createLineBorder(null));
                }
            }
        }); // end of key typed action listener

        // Enter Keypress Action Listener
        txt1.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                super.keyPressed(e);
                if(e.getKeyCode() == KeyEvent.VK_ENTER){
                    if(txt1.isFocusOwner()){
                        ConvertLogic2();
                        txt1.setBorder(javax.swing.BorderFactory.createLineBorder(null));
                    }
                }
            }
        }); // end of enter keypress action listener

    } // end of constructor

    // main method
    public static void main(String[] args) {
        JFrame frame = new GUIRomanApp("My Roman Numeral Converter");
        frame.setVisible(true);
        frame.setLocationRelativeTo(null);
        frame.setSize(500, 300);
        //frame.pack();
    }
    // end of main method

    // roman numeral logic
    private String FindRomanNumber(int num) {
        String romanNum = "";
        if (num < 1) {
            romanNum = "the number you entered must be greater than 0";
            JOptionPane.showMessageDialog(null, "Empty Field!!", "Error Message!", JOptionPane.ERROR_MESSAGE);
        } else {
            String[] romanWords = {"M", "CM", "D", "CD", "C", "XC", "L", "XL", "X", "IX", "V", "IV", "I"};
            int[] romanInts = {1000, 900, 500, 400, 100, 90, 50, 40, 10, 9, 5, 4, 1};
            for (int i = 0; i < romanInts.length; i++) {
                while (num >= romanInts[i]) {
                    romanNum += romanWords[i];
                    num -= romanInts[i];
                }
            }
            JOptionPane.showMessageDialog(null, "The Roman Numeral for " + txt1.getText() + " is " + romanNum, "Number Converted!", JOptionPane.INFORMATION_MESSAGE);
        }
        return romanNum;
    }
    // end of roman numeral logic

    // Conversion helper function for both "Enter" button keypress and Submit btn click action listeners
    private void ConvertLogic2(){
        // throw an error message if text is empty
        if (txt1.getText().equals("")) {
            JOptionPane.showMessageDialog(null, "The input fields cannot be empty!", "Error Message!", JOptionPane.ERROR_MESSAGE);
        }
        // else perform roman numeral logic
        else {
            int numConversion = Integer.parseInt(txt1.getText()); // convert the text in the input to an int
            FindRomanNumber(numConversion);
            txt1.setText(""); // after conversion set the text back to be empty
        }
    }
    // end of helper function logic
}
