@echo off
echo 🎵 Generating Slot Machine Sound Effects...
echo.

REM Compile the sound generator
echo Compiling SoundGenerator...
javac SoundGenerator.java
if %ERRORLEVEL% NEQ 0 (
    echo ❌ Compilation failed!
    pause
    exit /b 1
)

echo ✅ Compilation successful!
echo.

REM Run the sound generator
echo Running SoundGenerator...
java SoundGenerator

echo.
echo 🎉 Sound generation complete!
echo 📁 Check the 'sounds' folder for your audio files.
echo 🎮 You can now run your slot machine with sound effects!
pause 