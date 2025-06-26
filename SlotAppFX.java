import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

// JavaFX version of the Slot Machine App
public class SlotAppFX extends Application {
    // UI components for reels and message box
    private TextField reel1 = new TextField();
    private TextField reel2 = new TextField();
    private TextField reel3 = new TextField();
    private TextField msgbox = new TextField();
    private Button rollButton = new Button("ROLL");
    private int userScore = 0;
    // Reel faces
    private final String[] reelface = {"BAR", "(-)", "-3-", "@@@", "&7&", "*7*", "BAR", "CHR", "(-)", "@@@"};

    @Override
    public void start(Stage primaryStage) {
        // Set up text fields
        reel1.setPrefColumnCount(3);
        reel2.setPrefColumnCount(3);
        reel3.setPrefColumnCount(3);
        msgbox.setPrefColumnCount(15);
        msgbox.setText("PRESS ROLL");
        msgbox.setEditable(false);
        reel1.setEditable(false);
        reel2.setEditable(false);
        reel3.setEditable(false);

        // Set up button action
        rollButton.setOnAction(e -> onRoll());

        // Layout for reels and main window
        HBox reels = new HBox(5, reel1, reel2, reel3);
        VBox root = new VBox(10, msgbox, reels, rollButton);
        root.setStyle("-fx-padding: 10;");

        // Set up and show the scene
        Scene scene = new Scene(root, 220, 120);
        primaryStage.setTitle("SLOT FX");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    // Called when the ROLL button is pressed
    private void onRoll() {
        int[] reelinfo = new int[3];
        // Randomly select reel faces
        for (int i = 0; i < 3; i++) {
            reelinfo[i] = (int)(Math.random() * 9);
        }
        // Update UI with reel faces
        reel1.setText(reelface[reelinfo[0]]);
        reel2.setText(reelface[reelinfo[1]]);
        reel3.setText(reelface[reelinfo[2]]);
        // Judge and update score
        int win = judge(reelinfo);
        userScore += win;
        msgbox.setText("Your Score is " + userScore);
    }

    // Judge the result and return the score for this roll
    private int judge(int[] reelinfo) {
        if (reelinfo[0] == 5 && reelinfo[1] == 5 && reelinfo[2] == 5) {
            return 1000; // *7*
        } else if (reelinfo[0] == 4 && reelinfo[1] == 4 && reelinfo[2] == 4) {
            return 1000; // &7&
        } else if ((reelinfo[0] == 4 || reelinfo[0] == 5) &&
                   (reelinfo[1] == 4 || reelinfo[1] == 5) &&
                   (reelinfo[2] == 4 || reelinfo[2] == 5)) {
            return 200; // ALL 7
        } else if (reelinfo[0] == 2 && reelinfo[1] == 2 && reelinfo[2] == 2) {
            return 800; // -3-
        } else if ((reelinfo[0] == 0 || reelinfo[0] == 6) &&
                   (reelinfo[1] == 0 || reelinfo[1] == 6) &&
                   (reelinfo[2] == 0 || reelinfo[2] == 6)) {
            return 500; // BAR
        } else if ((reelinfo[0] == 3 || reelinfo[0] == 9) &&
                   (reelinfo[1] == 3 || reelinfo[1] == 9) &&
                   (reelinfo[2] == 3 || reelinfo[2] == 9)) {
            return 1; // @@@
        } else if (reelinfo[0] == 7 && reelinfo[1] == 7 && reelinfo[2] == 7) {
            return 100; // CHR
        } else if ((reelinfo[0] == 7 && reelinfo[1] == 7) ||
                   (reelinfo[0] == 7 && reelinfo[2] == 7) ||
                   (reelinfo[1] == 7 && reelinfo[2] == 7)) {
            return 10; // 2CHR
        } else if (reelinfo[0] == 7 || reelinfo[1] == 7 || reelinfo[2] == 7) {
            return 2; // 1CHR
        }
        return 0;
    }

    // Main entry point
    public static void main(String[] args) {
        launch(args);
    }
}
