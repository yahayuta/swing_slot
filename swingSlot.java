import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;

// Swing component sample
public class swingSlot {
    
    // Swing components
    private static JFrame mainFrame = null;
    private static JPanel mainPanel = null;
    private static JButton btnChange = null;
    private static JTextField reel1= null;
    private static JTextField reel2 = null;
    private static JTextField reel3 = null;
    private static JTextField msgbox = null;
    
    // Main function
    public static void main(String[] args) {
        
        mainLogic a = mainLogic.getInstance();
        mainLogic b = mainLogic.getInstance();
        
        if ( a == b)
        {
            System.out.println("Singleton Object");
        }
        
        // Frame initialization
        mainFrame = new JFrame();
        mainFrame.setTitle("SWING SLOT");

        // Textbox initialization
        reel1 = new JTextField("", 3);
        reel2 = new JTextField("", 3);
        reel3 = new JTextField("", 3);
        msgbox = new JTextField("", 15);
        msgbox.setText("PRESS ROLL");
        
        // Button initialization
        btnChange = new JButton("ROLL");
        btnChange.addActionListener(new rollAction());
        
        // Panel initialization
        mainPanel = new JPanel();
        mainPanel.add(msgbox);
        mainPanel.add(reel1);
        mainPanel.add(reel2);
        mainPanel.add(reel3);
        mainPanel.add(btnChange);
        
        // Frame settings
        mainFrame.getContentPane().add(mainPanel);
        mainFrame.setBounds( 10, 10, 200, 120);
        mainFrame.setVisible(true);
        mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
    	// Start slot thread
    	mainLoop loop = new mainLoop();
    	loop.start();
    }
    
    // Action class for roll button
    public static class rollAction implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            System.out.println("ROLL PRESSED");
            if (!mainLogic.getIsRoll()) {
                mainLogic.setIsRoll(true);
                msgbox.setText("ROLLING...");
            } else {
                mainLogic.setIsRoll(false);
                mainLogic.judge();
                msgbox.setText("Your Score is " + mainLogic.getOutScore());
            }
        }
    }
    
    // Slot thread class
    public static class mainLoop extends Thread {
        public void run() {
            System.out.println("**********THREAD START**********");
            while(true) {
                try {
                    sleep(100);
                } catch (Throwable th) {
                    th.printStackTrace();
                    System.out.println("run():SYSTEM ERROR: " + th.toString());
                    break;
                }
                mainLogic.mainLoop();
            }
        }
    }

	// Main logic class (Singleton)
	public static class mainLogic {
	    
	    private static mainLogic mainLogicInstance = null;
	    
	    private static final String reelface[] = {"BAR",
		                                             "(-)",
		                                             "-3-",
		                                             "@@@",
		                                             "&7&",
		                                             "*7*",
		                                             "BAR",
		                                             "CHR",
		                                             "(-)",
		                                             "@@@"};
	    private static boolean _isRoll = false;
	    private static int reelinfo[] = {1, 2, 3};
	    private static int winnerrate = 0;
	    private static int userscore = 0;
	    private static String outscore = null;
		
		// Constructor
	    private mainLogic() {
	        System.out.println("CALLED");
	        mainLogicInstance = new mainLogic();
	    }
	    
	    public static synchronized mainLogic getInstance() {
	        return mainLogicInstance;
	    }
	    
	    public static String getOutScore() {
	        return outscore;
	    }
	    
	    public static boolean getIsRoll() {
	        return _isRoll;
	    }
	    
	    public static void setIsRoll(boolean isRoll) {
	        _isRoll = isRoll;
	    }
	    
	    // Main loop function (called in loop)
	    public static void mainLoop() {
	    	// Roll the reels
	        rollReel();
	    }
	    
	    // Judge score
		public static void judge() {
			if (reelinfo[0] == 5 && reelinfo[1] == 5 && reelinfo[2] == 5) {
			    // *7*
				winnerrate=1000;
			} else if (reelinfo[0] == 4 && reelinfo[1] == 4 && reelinfo[2] == 4) {
			    // &7&
				winnerrate=1000;
			} else if ((reelinfo[0] == 4 || reelinfo[0] == 5) && 
			           (reelinfo[1] == 4 || reelinfo[1] == 5) &&
			           (reelinfo[2] == 4 || reelinfo[2] == 5)) {
			    // ALL 7
			    winnerrate=200; 
			} else if (reelinfo[0] == 2 && reelinfo[1] == 2 && reelinfo[2] == 2) {
			    // -3- 
			    winnerrate=800;
			} else if((reelinfo[0] == 0 || reelinfo[0] == 6)&&
			           (reelinfo[1] == 0 || reelinfo[1] == 6)&&
			           (reelinfo[2] == 0 || reelinfo[2] == 6)) {
            // BAR
			    winnerrate=500;
			} else if ((reelinfo[0] == 3 || reelinfo[0] == 9)&&
			           (reelinfo[1] == 3 || reelinfo[1] == 9)&&
			           (reelinfo[2] == 3 || reelinfo[2] == 9)) {
            // @@@ 
			    winnerrate=1;
			} else if(reelinfo[0] == 7 && reelinfo[1] == 7 && reelinfo[2] == 7) {
			    //CHR
				winnerrate=100;
			} else if ((reelinfo[0] == 7 &&reelinfo[1] == 7)||
			           (reelinfo[0] == 7 &&reelinfo[2] == 7)||
			           (reelinfo[1] == 7 &&reelinfo[2] == 7)) {
			    //2CHR
			    winnerrate=10;
			} else if (reelinfo[0] == 7 || reelinfo[1] == 7 || reelinfo[2] == 7) {
			    // 1CHR
				winnerrate=2;
			}
			
			userscore += winnerrate;
			outscore = "" + userscore;
			
			winnerrate = 0;
		}
		
	    // Reel rolling function
		public static void rollReel() {
		    if (_isRoll) {
				reelinfo[0] = (int)(Math.random() * 9);
				reel1.setText(reelface[reelinfo[0]]);
				
				reelinfo[1] = (int)(Math.random() * 9);
				reel2.setText(reelface[reelinfo[1]]);
				
				reelinfo[2] = (int)(Math.random() * 9);
				reel3.setText(reelface[reelinfo[2]]);  
		    }
		}
	}
}
