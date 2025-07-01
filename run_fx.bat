@echo off
REM Run Enhanced SlotAppFX with JavaFX (Phase 2)
set FXPATH=C:\javafx-sdk-24.0.1\lib
echo Starting Enhanced Slot Machine with Phase 2 features...
java --module-path "%FXPATH%" --add-modules javafx.controls,javafx.fxml,javafx.graphics,javafx.media SlotAppFX
if %ERRORLEVEL% NEQ 0 (
    echo Application failed to start!
    pause
)
