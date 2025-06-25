# Swing Slot & JavaFX SlotAppFX

This project contains two versions of a simple slot machine game:
- `swingSlot.java`: The original version using Java Swing.
- `SlotAppFX.java`: A modern version using JavaFX.

## Requirements

- **JavaFX version (`SlotAppFX.java`)**
  - Java 22 or newer (required for JavaFX 24+)
  - JavaFX SDK 24.0.1 or newer

- **Swing version (`swingSlot.java`)**
  - Java 8 or newer (Swing is included in standard JDK)

## How to Run the JavaFX Version

1. **Download JavaFX SDK**
   - Get it from: https://gluonhq.com/products/javafx/
   - Extract it (e.g., to `C:\javafx-sdk-24.0.1`)

2. **Compile**
   ```powershell
   javac --module-path "C:\javafx-sdk-24.0.1\lib" --add-modules javafx.controls,javafx.fxml SlotAppFX.java
   ```

3. **Run**
   ```powershell
   java --module-path "C:\javafx-sdk-24.0.1\lib" --add-modules javafx.controls,javafx.fxml SlotAppFX
   ```

4. **If you see an error about `major.minor version 66.0`**
   - You need to install Java 22 or newer and use it for compiling and running.

## How to Run the Swing Version

1. **Compile**
   ```powershell
   javac swingSlot.java
   ```
2. **Run**
   ```powershell
   java swingSlot
   ```

## Files

- `swingSlot.java` - Original Swing slot machine
- `SlotAppFX.java` - JavaFX slot machine (recommended for modern Java)

## Notes
- JavaFX is the recommended way for new desktop Java GUI apps.
- If you have any issues running the JavaFX version, check your Java version and module path.

---
Enjoy the slot machine game!

