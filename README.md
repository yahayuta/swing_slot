# Swing Slot & JavaFX Slot Machine

A simple slot machine game implemented in both Java Swing and JavaFX.

---

## Features
- Classic slot machine UI and logic
- Two versions:
  - `swingSlot.java`: Java Swing (legacy, for reference)
  - `SlotAppFX.java`: JavaFX (modern, recommended)
- Easy compile and run scripts for Windows

---

## Requirements

### JavaFX Version (`SlotAppFX.java`)
- Java 22 or newer (required for JavaFX 24+)
- JavaFX SDK 24.0.1 or newer ([Download here](https://gluonhq.com/products/javafx/))

### Swing Version (`swingSlot.java`)
- Java 8 or newer (Swing is included in standard JDK)

---

## Getting Started

### 1. Clone the Repository
```sh
git clone https://github.com/yourusername/your-repo.git
cd your-repo
```

### 2. JavaFX Setup
- Download and extract JavaFX SDK (e.g., to `C:\javafx-sdk-24.0.1`)
- Make sure your `PATH` and `JAVA_HOME` point to Java 22+

---

## How to Run (JavaFX Version)

### Compile
Double-click or run:
```bat
compile_fx.bat
```

### Run
Double-click or run:
```bat
run_fx.bat
```

Or manually:
```sh
javac --module-path "C:\javafx-sdk-24.0.1\lib" --add-modules javafx.controls,javafx.fxml SlotAppFX.java
java --module-path "C:\javafx-sdk-24.0.1\lib" --add-modules javafx.controls,javafx.fxml SlotAppFX
```

---

## How to Run (Swing Version)

```sh
javac swingSlot.java
java swingSlot
```

---

## Project Structure

```
├── SlotAppFX.java      # JavaFX version
├── swingSlot.java      # Swing version
├── compile_fx.bat      # Compile JavaFX version
├── run_fx.bat          # Run JavaFX version
├── .gitignore          # Ignore .class and build files
├── README.md           # This file
└── ...
```

---

## .gitignore
- Ignores all `.class` files, build folders, and VS Code settings

---

## License
[MIT](LICENSE)

---

## Notes
- JavaFX is recommended for new desktop Java GUI apps.
- If you see errors about `major.minor version 66.0`, upgrade to Java 22 or newer.
- For VS Code, add JavaFX jars to referenced libraries and set VM arguments in `launch.json`.

---

Enjoy the slot machine game!

