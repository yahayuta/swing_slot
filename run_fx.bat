@echo off
REM Run SlotAppFX with JavaFX
set FXPATH=C:\javafx-sdk-24.0.1\lib
java --module-path "%FXPATH%" --add-modules javafx.controls,javafx.fxml SlotAppFX
