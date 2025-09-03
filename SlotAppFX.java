import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;
import javafx.scene.paint.Color;
import javafx.scene.control.Alert.AlertType;
import javafx.animation.*;
import javafx.util.Duration;
import javafx.scene.transform.Rotate;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.shape.Rectangle;
import java.io.*;
import java.nio.file.*;
import java.util.Properties;

// Enhanced JavaFX version of the Slot Machine App with Phase 3 features
public class SlotAppFX extends Application {
    // UI components
    private TextField reel1 = new TextField();
    private TextField reel2 = new TextField();
    private TextField reel3 = new TextField();
    private Label msgbox = new Label("PRESS ROLL TO START");
    private Button rollButton = new Button("ROLL");
    private Button bet1Button = new Button("BET 1");
    private Button bet5Button = new Button("BET 5");
    private Button bet10Button = new Button("BET 10");
    private Button maxBetButton = new Button("MAX BET");
    private Button saveButton = new Button("💾 SAVE");
    private Button loadButton = new Button("📂 LOAD");
    private Button resetButton = new Button("🔄 RESET");
    private ChoiceBox<String> themeChoiceBox = new ChoiceBox<>();
    private ListView<String> spinHistoryListView = new ListView<>();

    // Game state variables
    private int userScore = 1000; // Starting balance
    private int currentBet = 1;
    private int totalWins = 0;
    private int totalSpins = 0;
    private int highScore = 0;
    private int bestWin = 0;

    // Animation variables
    private RotateTransition[] reelAnimations = new RotateTransition[3];
    private Timeline messageAnimation;
    private boolean isSpinning = false;

    // Sound variables
    private boolean soundEnabled = true;
    private Button soundButton = new Button("🔊 SOUND ON");

    // Reel faces with better symbols
    private final String[] reelface = {"🍒", "🍋", "🍊", "💎", "7️⃣", "🎰", "🍒", "💎", "🍋", "💎"};

    // Labels for display
    private Label balanceLabel = new Label("Balance: $1000");
    private Label betLabel = new Label("Bet: $1");
    private Label winsLabel = new Label("Total Wins: $0");
    private Label spinsLabel = new Label("Spins: 0");
    private Label highScoreLabel = new Label("High Score: $0");
    private Label bestWinLabel = new Label("Best Win: $0");

    private Scene scene;
    private StackPane rootStack;
    private ObservableList<String> spinHistory = FXCollections.observableArrayList();

    @Override
    public void start(Stage primaryStage) {
        // Load saved game data
        loadGameData();

        // Set up UI elements
        setupReels();
        setupButtons();
        setupLabels();
        setupAnimations();
        setupThemeChoiceBox();
        setupSpinHistory();

        // Main layout
        GridPane root = new GridPane();
        root.setAlignment(Pos.CENTER);
        root.setPadding(new Insets(10));
        root.getStyleClass().add("root");

        // Responsive layout
        ColumnConstraints col1 = new ColumnConstraints();
        col1.setPercentWidth(30);
        ColumnConstraints col2 = new ColumnConstraints();
        col2.setPercentWidth(40);
        ColumnConstraints col3 = new ColumnConstraints();
        col3.setPercentWidth(30);
        root.getColumnConstraints().addAll(col1, col2, col3);

        // Column 1: Game Info and Theme Selector
        VBox leftPane = new VBox(20);
        leftPane.setAlignment(Pos.CENTER);
        GridPane gameInfo = new GridPane();
        gameInfo.setHgap(20);
        gameInfo.setVgap(10);
        gameInfo.setAlignment(Pos.CENTER);
        gameInfo.add(balanceLabel, 0, 0);
        gameInfo.add(betLabel, 1, 0);
        gameInfo.add(winsLabel, 0, 1);
        gameInfo.add(spinsLabel, 1, 1);
        gameInfo.add(highScoreLabel, 0, 2);
        gameInfo.add(bestWinLabel, 1, 2);
        leftPane.getChildren().addAll(gameInfo, themeChoiceBox);
        root.add(leftPane, 0, 0);

        // Column 2: Reels, Bet Controls, and Roll Button
        VBox centerPane = new VBox(20);
        centerPane.setAlignment(Pos.CENTER);
        HBox reels = new HBox(10);
        reels.setAlignment(Pos.CENTER);
        reels.getChildren().addAll(reel1, reel2, reel3);
        HBox betControls = new HBox(10);
        betControls.setAlignment(Pos.CENTER);
        betControls.getChildren().addAll(bet1Button, bet5Button, bet10Button, maxBetButton);
        centerPane.getChildren().addAll(reels, msgbox, betControls, rollButton);
        root.add(centerPane, 1, 0);

        // Column 3: Controls and Spin History
        VBox rightPane = new VBox(10);
        rightPane.setAlignment(Pos.CENTER);
        HBox gameControls = new HBox(10);
        gameControls.setAlignment(Pos.CENTER);
        gameControls.getChildren().addAll(saveButton, loadButton, resetButton, soundButton);
        rightPane.getChildren().addAll(gameControls, new Label("Spin History:"), spinHistoryListView);
        root.add(rightPane, 2, 0);

        rootStack = new StackPane(root);

        // Set up and show the scene
        scene = new Scene(rootStack, 1000, 600);
        switchTheme("Classic");

        primaryStage.setTitle("🎰 ENHANCED SLOT MACHINE - PHASE 3 🎰");
        primaryStage.setScene(scene);
        primaryStage.setMinWidth(1000);
        primaryStage.setMinHeight(600);
        primaryStage.show();

        // Auto-save when closing
        primaryStage.setOnCloseRequest(e -> saveGameData());
    }

    private void setupSpinHistory() {
        spinHistoryListView.setItems(spinHistory);
    }

    private void setupThemeChoiceBox() {
        themeChoiceBox.getItems().addAll("Classic", "Dark");
        themeChoiceBox.setValue("Classic");
        themeChoiceBox.getSelectionModel().selectedItemProperty().addListener((obs, oldTheme, newTheme) -> {
            if (newTheme != null) {
                switchTheme(newTheme);
            }
        });
    }

    private void switchTheme(String theme) {
        scene.getStylesheets().clear();
        String cssFile = theme.equalsIgnoreCase("Dark") ? "dark.css" : "classic.css";
        scene.getStylesheets().add(getClass().getResource(cssFile).toExternalForm());
    }

    private void setupReels() {
        reel1.setPrefColumnCount(4);
        reel2.setPrefColumnCount(4);
        reel3.setPrefColumnCount(4);

        reel1.setEditable(false);
        reel2.setEditable(false);
        reel3.setEditable(false);

        reel1.getStyleClass().add("text-field");
        reel2.getStyleClass().add("text-field");
        reel3.getStyleClass().add("text-field");

        // Initialize with placeholder
        reel1.setText("🎰");
        reel2.setText("🎰");
        reel3.setText("🎰");
    }

    private void setupButtons() {
        rollButton.getStyleClass().add("button");
        rollButton.setId("roll-button");

        bet1Button.getStyleClass().addAll("button", "bet-button");
        bet5Button.getStyleClass().addAll("button", "bet-button");
        bet10Button.getStyleClass().addAll("button", "bet-button");
        maxBetButton.getStyleClass().addAll("button", "bet-button");

        saveButton.getStyleClass().addAll("button", "game-control-button");
        loadButton.getStyleClass().addAll("button", "game-control-button");
        resetButton.getStyleClass().addAll("button", "game-control-button");
        soundButton.getStyleClass().addAll("button", "game-control-button");

        updateBetButtonStyles();

        // Set button actions
        rollButton.setOnAction(e -> onRoll());
        bet1Button.setOnAction(e -> setBet(1));
        bet5Button.setOnAction(e -> setBet(5));
        bet10Button.setOnAction(e -> setBet(10));
        maxBetButton.setOnAction(e -> setBet(100));
        saveButton.setOnAction(e -> saveGameData());
        loadButton.setOnAction(e -> loadGameData());
        resetButton.setOnAction(e -> resetGame());
        soundButton.setOnAction(e -> toggleSound());
    }

    private void updateBetButtonStyles() {
        bet1Button.getStyleClass().remove("bet-button-active");
        bet5Button.getStyleClass().remove("bet-button-active");
        bet10Button.getStyleClass().remove("bet-button-active");
        maxBetButton.getStyleClass().remove("bet-button-active");

        if (currentBet == 1) {
            bet1Button.getStyleClass().add("bet-button-active");
        } else if (currentBet == 5) {
            bet5Button.getStyleClass().add("bet-button-active");
        } else if (currentBet == 10) {
            bet10Button.getStyleClass().add("bet-button-active");
        } else if (currentBet == 100) {
            maxBetButton.getStyleClass().add("bet-button-active");
        }
    }

    private void setupLabels() {
        msgbox.setId("msgbox");
        msgbox.setWrapText(true);

        balanceLabel.getStyleClass().add("label");
        betLabel.getStyleClass().add("label");
        winsLabel.getStyleClass().add("label");
        spinsLabel.getStyleClass().add("label");
        highScoreLabel.getStyleClass().add("label");
        highScoreLabel.setId("high-score-label");
        bestWinLabel.getStyleClass().add("label");
        bestWinLabel.setId("best-win-label");
    }

    private void setupAnimations() {
        // Setup reel spinning animations
        for (int i = 0; i < 3; i++) {
            reelAnimations[i] = new RotateTransition(Duration.millis(500), getReelByIndex(i));
            reelAnimations[i].setAxis(Rotate.Y_AXIS);
            reelAnimations[i].setByAngle(360);
            reelAnimations[i].setCycleCount(Animation.INDEFINITE);
            reelAnimations[i].setAutoReverse(false);
        }

        // Setup message animation
        messageAnimation = new Timeline();
        messageAnimation.setCycleCount(Animation.INDEFINITE);
        messageAnimation.setAutoReverse(true);
    }

    private TextField getReelByIndex(int index) {
        switch (index) {
            case 0: return reel1;
            case 1: return reel2;
            case 2: return reel3;
            default: return reel1;
        }
    }

    private void toggleSound() {
        soundEnabled = !soundEnabled;
        soundButton.setText(soundEnabled ? "🔊 SOUND ON" : "🔇 SOUND OFF");
        playSound("toggle");
    }

    private void playSound(String soundType) {
        if (!soundEnabled) return;

        try {
            String soundFile = "sounds/" + soundType + ".wav";
            File file = new File(soundFile);
            if (file.exists()) {
                Media sound = new Media(file.toURI().toString());
                MediaPlayer mediaPlayer = new MediaPlayer(sound);
                mediaPlayer.play();
            }
        } catch (Exception e) {
            // Silently fail if sound file not found or can't be played
        }
    }

    private void setBet(int betAmount) {
        if (betAmount <= userScore) {
            currentBet = betAmount;
            updateLabels();
            updateBetButtonStyles();
            playSound("toggle");
        } else {
            showAlert("Insufficient Balance", "You don't have enough money for this bet!");
        }
    }

    private void showAlert(String title, String content) {
        Alert alert = new Alert(AlertType.WARNING);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(content);
        alert.showAndWait();
    }

    // Called when the ROLL button is pressed
    private void onRoll() {
        if (isSpinning) return; // Prevent multiple spins

        if (currentBet > userScore) {
            showAlert("Insufficient Balance", "You don't have enough money to place this bet!");
            return;
        }

        if (userScore <= 0) {
            showAlert("Game Over", "You're out of money! Game will reset.");
            resetGame();
            return;
        }

        // Start spinning animation
        startSpinning();

        // Deduct bet from balance
        userScore -= currentBet;
        totalSpins++;

        // Play coin drop sound for betting
        playSound("coin");

        // Simulate spinning delay with reel stop sound
        Timeline spinDelay = new Timeline(new KeyFrame(Duration.seconds(2.5), e -> {
            stopSpinning();
            playSound("stop"); // Play reel stop sound
            completeSpin();
        }));
        spinDelay.play();
    }

    private void startSpinning() {
        isSpinning = true;
        rollButton.setDisable(true);
        rollButton.setText("SPINNING...");

        // Start reel animations
        for (RotateTransition animation : reelAnimations) {
            animation.play();
        }

        // Animate message
        messageAnimation.getKeyFrames().clear();
        messageAnimation.getKeyFrames().addAll(
            new KeyFrame(Duration.ZERO, new KeyValue(msgbox.scaleXProperty(), 1.0)),
            new KeyFrame(Duration.millis(500), new KeyValue(msgbox.scaleXProperty(), 1.1)),
            new KeyFrame(Duration.seconds(1), new KeyValue(msgbox.scaleXProperty(), 1.0))
        );
        messageAnimation.play();

        msgbox.setText("🎰 SPINNING... 🎰");
    }

    private void stopSpinning() {
        isSpinning = false;
        rollButton.setDisable(false);
        rollButton.setText("ROLL");

        // Stop reel animations
        for (RotateTransition animation : reelAnimations) {
            animation.stop();
        }

        // Stop message animation
        messageAnimation.stop();
        msgbox.setScaleX(1.0);
    }

    private void completeSpin() {
        int[] reelinfo = new int[3];
        // Randomly select reel faces
        for (int i = 0; i < 3; i++) {
            reelinfo[i] = (int)(Math.random() * reelface.length);
        }

        // Update UI with reel faces
        reel1.setText(reelface[reelinfo[0]]);
        reel2.setText(reelface[reelinfo[1]]);
        reel3.setText(reelface[reelinfo[2]]);

        // Judge and update score
        int win = judge(reelinfo) * currentBet;
        userScore += win;
        totalWins += win;

        // Update high scores
        if (userScore > highScore) {
            highScore = userScore;
        }
        if (win > bestWin) {
            bestWin = win;
        }

        updateLabels();

        String spinResult = String.format("%s %s %s -> Win: $%d", reel1.getText(), reel2.getText(), reel3.getText(), win);
        spinHistory.add(0, spinResult);
        if (spinHistory.size() > 5) {
            spinHistory.remove(5);
        }

        // Update message based on result
        if (win > 0) {
            if (win >= currentBet * 50) {
                msgbox.setText("🎉 JACKPOT! +$" + win + " 🎉");
                playSound("jackpot");
                playJackpotAnimation();
            } else {
                msgbox.setText("🎉 WIN! +$" + win + " 🎉");
                playSound("win");
                playWinAnimation();
            }
        } else {
            msgbox.setText("Better luck next time! Bet: $" + currentBet);
            playSound("lose");
        }

        // Auto-save after each spin
        saveGameData();
    }

    private void playWinAnimation() {
        ScaleTransition st1 = new ScaleTransition(Duration.millis(200), reel1);
        st1.setByX(0.2);
        st1.setByY(0.2);
        st1.setCycleCount(4);
        st1.setAutoReverse(true);

        ScaleTransition st2 = new ScaleTransition(Duration.millis(200), reel2);
        st2.setByX(0.2);
        st2.setByY(0.2);
        st2.setCycleCount(4);
        st2.setAutoReverse(true);

        ScaleTransition st3 = new ScaleTransition(Duration.millis(200), reel3);
        st3.setByX(0.2);
        st3.setByY(0.2);
        st3.setCycleCount(4);
        st3.setAutoReverse(true);

        ParallelTransition pt = new ParallelTransition(st1, st2, st3);
        pt.play();
    }

    private void playJackpotAnimation() {
        Rectangle rect = new Rectangle(rootStack.getWidth(), rootStack.getHeight());
        rect.setFill(Color.GOLD);
        rect.setOpacity(0);
        rootStack.getChildren().add(rect);

        FillTransition ft = new FillTransition(Duration.millis(500), rect, Color.GOLD, Color.RED);
        ft.setCycleCount(6);
        ft.setAutoReverse(true);

        Timeline tl = new Timeline(
            new KeyFrame(Duration.ZERO, new KeyValue(rect.opacityProperty(), 0.5)),
            new KeyFrame(Duration.millis(2000), new KeyValue(rect.opacityProperty(), 0))
        );

        ScaleTransition st = new ScaleTransition(Duration.millis(500), reel1);
        st.setByX(0.4);
        st.setByY(0.4);
        st.setCycleCount(6);
        st.setAutoReverse(true);

        ParallelTransition pt = new ParallelTransition(ft, tl, st);
        pt.setOnFinished(e -> rootStack.getChildren().remove(rect));
        pt.play();
    }

    private void resetGame() {
        userScore = 1000;
        totalWins = 0;
        totalSpins = 0;
        currentBet = 1;
        updateLabels();
        updateBetButtonStyles();
        msgbox.setText("Game Reset! Press ROLL to start");
        spinHistory.clear();
        saveGameData();
    }

    private void updateLabels() {
        balanceLabel.setText("Balance: $" + userScore);
        betLabel.setText("Bet: $" + currentBet);
        winsLabel.setText("Total Wins: $" + totalWins);
        spinsLabel.setText("Spins: " + totalSpins);
        highScoreLabel.setText("High Score: $" + highScore);
        bestWinLabel.setText("Best Win: $" + bestWin);
    }

    private void saveGameData() {
        try {
            Properties props = new Properties();
            props.setProperty("userScore", String.valueOf(userScore));
            props.setProperty("totalWins", String.valueOf(totalWins));
            props.setProperty("totalSpins", String.valueOf(totalSpins));
            props.setProperty("highScore", String.valueOf(highScore));
            props.setProperty("bestWin", String.valueOf(bestWin));
            props.setProperty("soundEnabled", String.valueOf(soundEnabled));

            Path saveFile = Paths.get("slot_save.dat");
            props.store(new FileOutputStream(saveFile.toFile()), "Slot Machine Save Data");

            // Show save confirmation
            Timeline saveConfirm = new Timeline(new KeyFrame(Duration.seconds(1), e -> {
                saveButton.setText("💾 SAVED!");
                Timeline resetButton = new Timeline(new KeyFrame(Duration.seconds(1), ev -> {
                    saveButton.setText("💾 SAVE");
                }));
                resetButton.play();
            }));
            saveConfirm.play();

        } catch (IOException e) {
            showAlert("Save Error", "Could not save game data: " + e.getMessage());
        }
    }

    private void loadGameData() {
        try {
            Path saveFile = Paths.get("slot_save.dat");
            if (Files.exists(saveFile)) {
                Properties props = new Properties();
                props.load(new FileInputStream(saveFile.toFile()));

                userScore = Integer.parseInt(props.getProperty("userScore", "1000"));
                totalWins = Integer.parseInt(props.getProperty("totalWins", "0"));
                totalSpins = Integer.parseInt(props.getProperty("totalSpins", "0"));
                highScore = Integer.parseInt(props.getProperty("highScore", "0"));
                bestWin = Integer.parseInt(props.getProperty("bestWin", "0"));
                soundEnabled = Boolean.parseBoolean(props.getProperty("soundEnabled", "true"));

                updateLabels();
                updateBetButtonStyles();
                soundButton.setText(soundEnabled ? "🔊 SOUND ON" : "🔇 SOUND OFF");

                // Show load confirmation
                Timeline loadConfirm = new Timeline(new KeyFrame(Duration.seconds(1), e -> {
                    loadButton.setText("📂 LOADED!");
                    Timeline resetButton = new Timeline(new KeyFrame(Duration.seconds(1), ev -> {
                        loadButton.setText("📂 LOAD");
                    }));
                    resetButton.play();
                }));
                loadConfirm.play();

                msgbox.setText("Game loaded successfully!");
            } else {
                showAlert("No Save File", "No saved game found. Starting fresh!");
            }
        } catch (IOException e) {
            showAlert("Load Error", "Could not load game data: " + e.getMessage());
        }
    }

    // Enhanced judge method with better payouts
    private int judge(int[] reelinfo) {
        // Check for three of a kind first (highest payouts)
        if (reelinfo[0] == 4 && reelinfo[1] == 4 && reelinfo[2] == 4) {
            return 100; // 7️⃣ 7️⃣ 7️⃣ - Jackpot!
        } else if (reelinfo[0] == 5 && reelinfo[1] == 5 && reelinfo[2] == 5) {
            return 50; // 🎰 🎰 🎰 - Big Win!
        } else if (reelinfo[0] == 3 && reelinfo[1] == 3 && reelinfo[2] == 3) {
            return 25; // 💎 💎 💎 - Diamond Win!
        } else if (reelinfo[0] == 0 && reelinfo[1] == 0 && reelinfo[2] == 0) {
            return 15; // 🍒 🍒 🍒 - Cherry Win!
        } else if (reelinfo[0] == 1 && reelinfo[1] == 1 && reelinfo[2] == 1) {
            return 10; // 🍋 🍋 🍋 - Lemon Win!
        } else if (reelinfo[0] == 2 && reelinfo[1] == 2 && reelinfo[2] == 2) {
            return 8; // 🍊 🍊 🍊 - Orange Win!
        }

        // Check for two of a kind
        if ((reelinfo[0] == 4 && reelinfo[1] == 4) || (reelinfo[0] == 4 && reelinfo[2] == 4) ||
            (reelinfo[1] == 4 && reelinfo[2] == 4)) {
            return 5; // Two 7️⃣s
        } else if ((reelinfo[0] == 5 && reelinfo[1] == 5) || (reelinfo[0] == 5 && reelinfo[2] == 5) ||
                   (reelinfo[1] == 5 && reelinfo[2] == 5)) {
            return 3; // Two 🎰s
        } else if ((reelinfo[0] == 3 && reelinfo[1] == 3) || (reelinfo[0] == 3 && reelinfo[2] == 3) ||
                   (reelinfo[1] == 3 && reelinfo[2] == 3)) {
            return 2; // Two 💎s
        }

        // Check for single high-value symbols
        if (reelinfo[0] == 4 || reelinfo[1] == 4 || reelinfo[2] == 4) {
            return 1; // Single 7️⃣
        } else if (reelinfo[0] == 5 || reelinfo[1] == 5 || reelinfo[2] == 5) {
            return 1; // Single 🎰
        }

        return 0; // No win
    }

    // Main entry point
    public static void main(String[] args) {
        launch(args);
    }
}
