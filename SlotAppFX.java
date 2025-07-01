import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;
import javafx.scene.paint.Color;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.animation.Animation;
import javafx.animation.RotateTransition;
import javafx.animation.Timeline;
import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.util.Duration;
import javafx.scene.transform.Rotate;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import java.io.*;
import java.nio.file.*;
import java.util.Properties;
import java.util.concurrent.atomic.AtomicInteger;

// Enhanced JavaFX version of the Slot Machine App with Phase 2 features
public class SlotAppFX extends Application {
    // UI components for reels and message box
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
    
    // Sound variables (placeholder - will be implemented with actual sound files)
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

    @Override
    public void start(Stage primaryStage) {
        // Load saved game data
        loadGameData();
        
        // Set up text fields with better styling
        setupReels();
        setupButtons();
        setupLabels();
        setupAnimations();
        
        // Layout for betting controls
        HBox betControls = new HBox(5);
        betControls.setAlignment(Pos.CENTER);
        betControls.getChildren().addAll(bet1Button, bet5Button, bet10Button, maxBetButton);
        
        // Layout for game controls
        HBox gameControls = new HBox(5);
        gameControls.setAlignment(Pos.CENTER);
        gameControls.getChildren().addAll(saveButton, loadButton, resetButton, soundButton);
        
        // Layout for reels
        HBox reels = new HBox(10);
        reels.setAlignment(Pos.CENTER);
        reels.getChildren().addAll(reel1, reel2, reel3);
        
        // Layout for game info
        GridPane gameInfo = new GridPane();
        gameInfo.setHgap(20);
        gameInfo.setVgap(5);
        gameInfo.setAlignment(Pos.CENTER);
        gameInfo.add(balanceLabel, 0, 0);
        gameInfo.add(betLabel, 1, 0);
        gameInfo.add(winsLabel, 0, 1);
        gameInfo.add(spinsLabel, 1, 1);
        gameInfo.add(highScoreLabel, 0, 2);
        gameInfo.add(bestWinLabel, 1, 2);
        
        // Main layout
        VBox root = new VBox(15);
        root.setAlignment(Pos.CENTER);
        root.setPadding(new Insets(20));
        root.getChildren().addAll(gameInfo, msgbox, reels, betControls, gameControls, rollButton);
        
        // Apply styling
        root.setStyle("-fx-background-color: linear-gradient(to bottom, #2c3e50, #34495e); -fx-padding: 20;");
        
        // Set up and show the scene
        Scene scene = new Scene(root, 450, 600);
        primaryStage.setTitle("🎰 ENHANCED SLOT MACHINE - PHASE 2 🎰");
        primaryStage.setScene(scene);
        primaryStage.setResizable(false);
        primaryStage.show();
        
        // Auto-save when closing
        primaryStage.setOnCloseRequest(e -> saveGameData());
    }
    
    private void setupReels() {
        reel1.setPrefColumnCount(3);
        reel2.setPrefColumnCount(3);
        reel3.setPrefColumnCount(3);
        
        reel1.setEditable(false);
        reel2.setEditable(false);
        reel3.setEditable(false);
        
        // Style the reels
        String reelStyle = "-fx-font-size: 24px; -fx-font-weight: bold; -fx-alignment: center; " +
                          "-fx-background-color: #ecf0f1; -fx-border-color: #bdc3c7; -fx-border-width: 2;";
        reel1.setStyle(reelStyle);
        reel2.setStyle(reelStyle);
        reel3.setStyle(reelStyle);
        
        // Initialize with placeholder
        reel1.setText("🎰");
        reel2.setText("🎰");
        reel3.setText("🎰");
    }
    
    private void setupButtons() {
        // Style the roll button
        rollButton.setStyle("-fx-font-size: 16px; -fx-font-weight: bold; -fx-background-color: #e74c3c; " +
                           "-fx-text-fill: white; -fx-padding: 10 20; -fx-cursor: hand;");
        rollButton.setPrefWidth(120);
        
        // Style bet buttons
        String betButtonStyle = "-fx-font-size: 12px; -fx-font-weight: bold; -fx-background-color: #3498db; " +
                               "-fx-text-fill: white; -fx-padding: 8 12; -fx-cursor: hand;";
        bet1Button.setStyle(betButtonStyle);
        bet5Button.setStyle(betButtonStyle);
        bet10Button.setStyle(betButtonStyle);
        maxBetButton.setStyle(betButtonStyle);
        
        // Style game control buttons
        String gameButtonStyle = "-fx-font-size: 11px; -fx-font-weight: bold; -fx-background-color: #27ae60; " +
                                "-fx-text-fill: white; -fx-padding: 6 10; -fx-cursor: hand;";
        saveButton.setStyle(gameButtonStyle);
        loadButton.setStyle(gameButtonStyle);
        resetButton.setStyle(gameButtonStyle);
        soundButton.setStyle(gameButtonStyle);
        
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
    
    private void setupLabels() {
        // Style the message box
        msgbox.setStyle("-fx-font-size: 18px; -fx-font-weight: bold; -fx-text-fill: #f39c12; " +
                       "-fx-background-color: #2c3e50; -fx-padding: 10; -fx-alignment: center;");
        msgbox.setPrefWidth(350);
        msgbox.setAlignment(Pos.CENTER);
        
        // Style info labels
        String labelStyle = "-fx-font-size: 14px; -fx-font-weight: bold; -fx-text-fill: #ecf0f1;";
        balanceLabel.setStyle(labelStyle);
        betLabel.setStyle(labelStyle);
        winsLabel.setStyle(labelStyle);
        spinsLabel.setStyle(labelStyle);
        highScoreLabel.setStyle(labelStyle + " -fx-text-fill: #f1c40f;");
        bestWinLabel.setStyle(labelStyle + " -fx-text-fill: #e67e22;");
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
        
        // Simulate spinning delay
        Timeline spinDelay = new Timeline(new KeyFrame(Duration.seconds(2), e -> {
            stopSpinning();
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
        msgbox.setStyle("-fx-font-size: 18px; -fx-font-weight: bold; -fx-text-fill: #3498db; " +
                       "-fx-background-color: #2c3e50; -fx-padding: 10; -fx-alignment: center;");
        
        playSound("spin");
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
        
        // Update message based on result
        if (win > 0) {
            if (win >= currentBet * 50) {
                msgbox.setText("🎉 JACKPOT! +$" + win + " 🎉");
                playSound("jackpot");
            } else {
                msgbox.setText("🎉 WIN! +$" + win + " 🎉");
                playSound("win");
            }
            msgbox.setStyle("-fx-font-size: 18px; -fx-font-weight: bold; -fx-text-fill: #27ae60; " +
                           "-fx-background-color: #2c3e50; -fx-padding: 10; -fx-alignment: center;");
        } else {
            msgbox.setText("Better luck next time! Bet: $" + currentBet);
            msgbox.setStyle("-fx-font-size: 18px; -fx-font-weight: bold; -fx-text-fill: #e74c3c; " +
                           "-fx-background-color: #2c3e50; -fx-padding: 10; -fx-alignment: center;");
            playSound("lose");
        }
        
        // Auto-save after each spin
        saveGameData();
    }
    
    private void resetGame() {
        userScore = 1000;
        totalWins = 0;
        totalSpins = 0;
        currentBet = 1;
        updateLabels();
        msgbox.setText("Game Reset! Press ROLL to start");
        msgbox.setStyle("-fx-font-size: 18px; -fx-font-weight: bold; -fx-text-fill: #f39c12; " +
                       "-fx-background-color: #2c3e50; -fx-padding: 10; -fx-alignment: center;");
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
                msgbox.setStyle("-fx-font-size: 18px; -fx-font-weight: bold; -fx-text-fill: #27ae60; " +
                               "-fx-background-color: #2c3e50; -fx-padding: 10; -fx-alignment: center;");
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
