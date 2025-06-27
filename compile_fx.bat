@echo off
REM Compile SlotAppFX.java with JavaFX
set FXPATH=C:\javafx-sdk-24.0.1\lib
javac --module-path "%FXPATH%" --add-modules javafx.controls,javafx.fxml SlotAppFX.java
if %errorlevel% neq 0 (
    echo Compile failed.
    exit /b %errorlevel%
)
echo Compile successful.
