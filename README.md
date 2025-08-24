# 🎰 Enhanced Slot Machine

A modern slot machine game implemented in JavaFX. Features a "Classic Casino" theme, a betting system, engaging gameplay, and authentic Las Vegas sound effects.

**Status**: ✅ **Phase 2 Complete** - Enhanced with animations, save/load, Las Vegas sound effects, and high score tracking

---

## ✨ New Features

### 🎮 Phase 1: Enhanced Gameplay ✅ COMPLETE
- **Betting System**: Place bets from $1 to $100
- **Balance Management**: Start with $1000, track your wins and losses
- **Multiple Bet Options**: Quick bet buttons (1, 5, 10, MAX)
- **Game Statistics**: Track total wins and number of spins
- **Auto Reset**: Game automatically resets when you run out of money

### 🎨 Phase 1: Visual Improvements ✅ COMPLETE
- **Classic Casino Theme**: A professional UI with a deep green background, and red and gold accents for an authentic casino feel.
- **Emoji Symbols**: Colorful slot symbols (🍒, 🍋, 🍊, 💎, 7️⃣, 🎰)
- **Dynamic Messages**: Color-coded win/loss messages
- **Better Layout**: Organized game information display
- **Responsive Design**: Fixed window size for consistent experience

### 🎯 Phase 1: Enhanced Payout System ✅ COMPLETE
- **7️⃣ 7️⃣ 7️⃣**: Jackpot! (100x bet)
- **🎰 🎰 🎰**: Big Win! (50x bet)
- **💎 💎 💎**: Diamond Win! (25x bet)
- **🍒 🍒 🍒**: Cherry Win! (15x bet)
- **🍋 🍋 🍋**: Lemon Win! (10x bet)
- **🍊 🍊 🍊**: Orange Win! (8x bet)
- **Two of a Kind**: Partial wins (2-5x bet)
- **Single High Symbols**: Small wins (1x bet)

### 🎬 Phase 2: Animations & Effects ✅ COMPLETE
- **Reel Spinning Animation**: 3D rotation effects during spins
- **Message Animations**: Pulsing effects for win/loss messages
- **Button Feedback**: Visual confirmation for save/load actions
- **Smooth Transitions**: Professional 2.5-second spinning delay
- **Spinning State**: Prevents multiple spins during animation

### 💾 Phase 2: Save/Load System ✅ COMPLETE
- **Auto-Save**: Automatically saves after each spin
- **Manual Save**: Save button with visual feedback
- **Load Game**: Restore previous game state
- **Persistent Data**: Saves balance, wins, spins, high scores, sound settings
- **File Management**: Uses `slot_save.dat` for data storage

### 🏆 Phase 2: High Score Tracking ✅ COMPLETE
- **High Score**: Tracks highest balance achieved (gold color)
- **Best Win**: Records biggest single win amount (orange color)
- **Persistent Records**: High scores saved between sessions
- **Visual Indicators**: Color-coded labels for achievements
- **Achievement Tracking**: Monitor progress over time

### 🔊 Phase 2: Authentic Las Vegas Sound System ✅ COMPLETE
- **Generated Las Vegas Sound Effects**: 7 authentic casino sound files
- **Sound Toggle**: Enable/disable sound effects with visual feedback
- **Sound Categories**: 
  - 🎰 **Spin Sound**: Authentic mechanical reel spinning (2.5 seconds)
  - 🎉 **Win Sound**: Casino bell victory chimes (2 seconds)
  - 😔 **Lose Sound**: Wah-wah disappointment sound (1.2 seconds)
  - 🏆 **Jackpot Sound**: Epic celebration sequence (4 seconds)
  - 🔊 **Toggle Sound**: Casino button click (0.3 seconds)
  - 🪙 **Coin Drop Sound**: Metallic coin drop (0.8 seconds)
  - 🛑 **Reel Stop Sound**: Mechanical reel stop (0.6 seconds)
- **Visual Feedback**: Button shows current sound state
- **Persistent Settings**: Sound preference saved between sessions
- **Las Vegas Authenticity**: Multi-harmonic frequencies, mechanical realism, casino bell quality

### 🎮 Phase 2: Enhanced Controls ✅ COMPLETE
- **💾 SAVE**: Save current game state with confirmation
- **📂 LOAD**: Load previous game state with confirmation
- **🔄 RESET**: Reset game to starting state
- **🔊 SOUND**: Toggle sound effects on/off
- **Auto-Save**: Automatic saving when closing game

---

## 🚀 Getting Started

### Requirements
- Java 22 or newer
- JavaFX SDK 24.0.1 or newer ([Download here](https://gluonhq.com/products/javafx/))

### Quick Start
1. **Generate Las Vegas Sounds**: Double-click `generate_sounds.bat` (one-time setup)
2. **Compile**: Double-click `compile_fx.bat`
3. **Run**: Double-click `run_fx.bat`

### Manual Commands
```sh
# Generate Las Vegas sound effects (one-time)
javac SoundGenerator.java
java SoundGenerator

# Compile (Phase 2)
javac --module-path "C:\javafx-sdk-24.0.1\lib" --add-modules javafx.controls,javafx.fxml,javafx.graphics,javafx.media SlotAppFX.java

# Run (Phase 2)
java --module-path "C:\javafx-sdk-24.0.1\lib" --add-modules javafx.controls,javafx.fxml,javafx.graphics,javafx.media SlotAppFX
```

---

## 🎮 How to Play

1. **Set Your Bet**: Choose from $1, $5, $10, or $100 (MAX BET)
2. **Press ROLL**: Watch the reels spin with animations and authentic Las Vegas sounds!
3. **Watch Your Balance**: Keep track of your money and wins
4. **Aim for Jackpots**: Three 7️⃣s give you the biggest payout!
5. **Save Progress**: Use save/load buttons to preserve your game
6. **Experience Vegas**: Toggle sound effects for authentic casino atmosphere

### Betting Strategy
- Start with small bets to build your balance
- Use MAX BET when you're feeling lucky
- Watch your total wins and spins for statistics
- The game resets automatically if you run out of money

### Game Controls
- **BET 1**: Place $1 bet
- **BET 5**: Place $5 bet
- **BET 10**: Place $10 bet
- **MAX BET**: Place $100 bet (highest payout potential)
- **ROLL**: Spin the reels with current bet amount
- **💾 SAVE**: Save current game state
- **📂 LOAD**: Load previous game state
- **🔄 RESET**: Reset game to starting state
- **🔊 SOUND**: Toggle sound effects

### Save/Load Features
- **Auto-Save**: Game automatically saves after each spin
- **Manual Save**: Click SAVE button anytime
- **Load Game**: Restore your previous session
- **File Location**: `slot_save.dat` in game directory
- **Data Preserved**: Balance, wins, spins, high scores, sound settings

### Las Vegas Sound System
- **Auto-Generated**: Run `generate_sounds.bat` to create authentic casino sound files
- **7 Sound Effects**: Spin, win, lose, jackpot, toggle, coin drop, and reel stop sounds
- **Toggle Control**: Enable/disable sounds with 🔊 SOUND button
- **Persistent Settings**: Sound preference saved between sessions
- **Authentic Quality**: Multi-harmonic frequencies, mechanical realism, casino bell quality

---

## 📁 Project Structure

```
├── SlotAppFX.java          # Enhanced JavaFX version (Phase 2) ✅
├── SoundGenerator.java     # Las Vegas sound effects generator ✅
├── swingSlot.java          # Legacy Swing version (for reference)
├── compile_fx.bat          # Compile script (Phase 2) ✅
├── run_fx.bat              # Run script (Phase 2) ✅
├── generate_sounds.bat     # Sound generation script ✅
├── sounds/                 # Generated Las Vegas sound effects ✅
│   ├── spin.wav           # Authentic mechanical reel spinning
│   ├── win.wav            # Casino bell victory chimes
│   ├── lose.wav           # Wah-wah disappointment sound
│   ├── jackpot.wav        # Epic celebration sequence
│   ├── toggle.wav         # Casino button click
│   ├── coin.wav           # Metallic coin drop sound
│   └── stop.wav           # Mechanical reel stop sound
├── slot_save.dat          # Save file (created automatically)
├── sound_info.txt         # Las Vegas sound system documentation ✅
├── README.md              # This file ✅
└── LICENSE                # MIT License
```

---

## 🔧 Technical Details

### Phase 1 Features ✅
- **Betting System**: Full betting implementation with validation
- **State Management**: Proper game state tracking
- **Error Handling**: User-friendly alerts for insufficient funds
- **UI Components**: Modern JavaFX controls and styling
- **Game Logic**: Improved payout system with multiple winning combinations

### Phase 2 Features ✅
- **Animation System**: JavaFX animations for reels and messages
- **File I/O**: Properties-based save/load system
- **Las Vegas Sound System**: Complete authentic casino sound effects
- **High Score System**: Persistent achievement tracking
- **Enhanced UI**: Additional controls and feedback
- **Advanced Audio Synthesis**: Multi-harmonic frequencies, mechanical realism

### Code Improvements ✅
- Better separation of concerns
- Enhanced error handling
- Modern JavaFX styling and animations
- Improved user experience with visual feedback
- Comprehensive game statistics and persistence
- Professional animation timing and effects
- Complete Las Vegas sound system integration

### Performance
- **Fast Loading**: Optimized JavaFX application
- **Responsive UI**: Smooth button interactions and animations
- **Memory Efficient**: Clean state management
- **Cross-Platform**: Works on Windows, macOS, and Linux
- **Persistent Data**: Efficient file-based save system
- **Audio Optimized**: Generated Las Vegas sound files with authentic quality

---

## 🎯 Future Enhancements (Planned)

### Phase 3: Advanced Features 📋
- Multiple paylines
- Bonus rounds
- Progressive jackpots
- Tournament mode
- Multiple game themes
- Advanced sound customization
- Real casino background ambience

---

## 🐛 Troubleshooting

### Common Issues
- **"JavaFX not found"**: Download and install JavaFX SDK
- **"Major.minor version"**: Upgrade to Java 22+
- **"Module not found"**: Check JavaFX path in batch files
- **"Compilation failed"**: Ensure JavaFX SDK is properly installed
- **"Save file error"**: Check write permissions in game directory
- **"No sound"**: Run `generate_sounds.bat` to create Las Vegas sound files

### JavaFX Setup
1. Download JavaFX SDK from [Gluon](https://gluonhq.com/products/javafx/)
2. Extract to `C:\javafx-sdk-24.0.1\`
3. Update batch files if using different path
4. Ensure Java 22+ is installed and in PATH

### Las Vegas Sound Setup
1. Run `generate_sounds.bat` to create authentic casino sound effects
2. Ensure the `sounds` folder contains 7 WAV files
3. Use 🔊 SOUND button to toggle the Vegas atmosphere
4. Check `sound_info.txt` for detailed Las Vegas sound information

### Testing Your Setup
```sh
# Check Java version
java -version

# Check if JavaFX is accessible
java --module-path "C:\javafx-sdk-24.0.1\lib" --add-modules javafx.controls --version

# Generate Las Vegas sound effects
.\generate_sounds.bat
```

### File Locations
- **JavaFX SDK**: `C:\javafx-sdk-24.0.1\` (default)
- **Project**: `C:\Users\yasun\develop\swing_slot\`
- **Compiled Classes**: Same directory as source files
- **Save File**: `slot_save.dat` in project directory
- **Sound Files**: `sounds/` folder in project directory

---

## 📊 Game Statistics

The enhanced slot machine tracks:
- **Current Balance**: Real-time money display
- **Current Bet**: Active bet amount
- **Total Wins**: Cumulative winnings
- **Total Spins**: Number of games played
- **High Score**: Highest balance achieved
- **Best Win**: Biggest single win amount

### Save Data Format
The game saves the following data to `slot_save.dat`:
- User score (balance)
- Total wins
- Total spins
- High score
- Best win
- Sound settings

### Las Vegas Sound Files
Generated authentic casino sound effects in `sounds/` folder:
- **spin.wav** (215KB): Authentic mechanical reel spinning
- **win.wav** (172KB): Casino bell victory chimes
- **lose.wav** (103KB): Wah-wah disappointment sound
- **jackpot.wav** (345KB): Epic celebration sequence
- **toggle.wav** (26KB): Casino button click
- **coin.wav** (69KB): Metallic coin drop sound
- **stop.wav** (52KB): Mechanical reel stop sound

---

## 📄 License
[MIT](LICENSE) - Feel free to use and modify!

---

**Experience the authentic Las Vegas slot machine atmosphere with complete Phase 2 features! 🎰✨🎬🎵**

*Last Updated: Phase 2 Complete with Authentic Las Vegas Sound System - Ready for Phase 3*

