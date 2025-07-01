@echo off
REM Compile Enhanced SlotAppFX with JavaFX (Phase 2)
set FXPATH=C:\javafx-sdk-24.0.1\lib
javac --module-path "%FXPATH%" --add-modules javafx.controls,javafx.fxml,javafx.graphics,javafx.media SlotAppFX.java
if %ERRORLEVEL% EQU 0 (
    echo Compilation successful! Phase 2 features ready.
    echo Run with: run_fx.bat
) else (
    echo Compilation failed!
    pause
)
