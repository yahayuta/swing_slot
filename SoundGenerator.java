import javax.sound.sampled.*;
import java.io.*;
import java.nio.file.*;

/**
 * Sound Generator for Slot Machine
 * Creates basic sound effects programmatically
 */
public class SoundGenerator {
    
    public static void main(String[] args) {
        System.out.println("🎵 Generating Slot Machine Sound Effects...");
        
        // Create sounds directory
        try {
            Files.createDirectories(Paths.get("sounds"));
            System.out.println("✅ Created sounds directory");
        } catch (IOException e) {
            System.out.println("❌ Could not create sounds directory: " + e.getMessage());
            return;
        }
        
        // Generate all sound effects
        generateSpinSound();
        generateWinSound();
        generateLoseSound();
        generateJackpotSound();
        generateToggleSound();
        
        System.out.println("\n🎉 All sound effects generated successfully!");
        System.out.println("📁 Check the 'sounds' folder for your audio files.");
        System.out.println("🎮 Run your slot machine to hear them in action!");
    }
    
    /**
     * Generate spinning reel sound (mechanical whirring)
     */
    private static void generateSpinSound() {
        try {
            System.out.println("🎰 Generating spin sound...");
            
            // Create a mechanical spinning sound
            int sampleRate = 44100;
            int duration = 2000; // 2 seconds
            byte[] audioData = new byte[sampleRate * duration / 500]; // 500ms chunks
            
            int index = 0;
            for (int i = 0; i < duration; i += 500) {
                // Generate whirring sound with varying frequency
                double frequency = 200 + (i * 50.0 / duration); // 200-250 Hz
                byte[] chunk = generateTone(frequency, 500, sampleRate);
                System.arraycopy(chunk, 0, audioData, index, chunk.length);
                index += chunk.length;
            }
            
            saveAudioFile(audioData, "sounds/spin.wav", sampleRate);
            System.out.println("✅ Spin sound generated: sounds/spin.wav");
            
        } catch (Exception e) {
            System.out.println("❌ Error generating spin sound: " + e.getMessage());
        }
    }
    
    /**
     * Generate win sound (cheerful chime)
     */
    private static void generateWinSound() {
        try {
            System.out.println("🎉 Generating win sound...");
            
            int sampleRate = 44100;
            int duration = 1500; // 1.5 seconds
            byte[] audioData = new byte[sampleRate * duration / 500];
            
            // Create ascending chime (C major chord)
            double[] frequencies = {523.25, 659.25, 783.99}; // C, E, G
            int index = 0;
            
            for (int i = 0; i < duration; i += 500) {
                double frequency = frequencies[i / 500 % frequencies.length];
                byte[] chunk = generateTone(frequency, 500, sampleRate);
                System.arraycopy(chunk, 0, audioData, index, chunk.length);
                index += chunk.length;
            }
            
            saveAudioFile(audioData, "sounds/win.wav", sampleRate);
            System.out.println("✅ Win sound generated: sounds/win.wav");
            
        } catch (Exception e) {
            System.out.println("❌ Error generating win sound: " + e.getMessage());
        }
    }
    
    /**
     * Generate lose sound (soft disappointment)
     */
    private static void generateLoseSound() {
        try {
            System.out.println("😔 Generating lose sound...");
            
            int sampleRate = 44100;
            int duration = 1000; // 1 second
            byte[] audioData = new byte[sampleRate * duration / 500];
            
            // Create descending tone (sad sound)
            double frequency = 400; // Start at 400 Hz
            int index = 0;
            
            for (int i = 0; i < duration; i += 500) {
                double currentFreq = frequency - (i * 100.0 / duration); // Descend to 300 Hz
                byte[] chunk = generateTone(currentFreq, 500, sampleRate);
                System.arraycopy(chunk, 0, audioData, index, chunk.length);
                index += chunk.length;
            }
            
            saveAudioFile(audioData, "sounds/lose.wav", sampleRate);
            System.out.println("✅ Lose sound generated: sounds/lose.wav");
            
        } catch (Exception e) {
            System.out.println("❌ Error generating lose sound: " + e.getMessage());
        }
    }
    
    /**
     * Generate jackpot sound (exciting celebration)
     */
    private static void generateJackpotSound() {
        try {
            System.out.println("🏆 Generating jackpot sound...");
            
            int sampleRate = 44100;
            int duration = 3000; // 3 seconds
            byte[] audioData = new byte[sampleRate * duration / 500];
            
            // Create exciting ascending arpeggio
            double[] frequencies = {523.25, 659.25, 783.99, 1046.50, 1318.51}; // C major scale
            int index = 0;
            
            for (int i = 0; i < duration; i += 300) { // Faster notes
                double frequency = frequencies[i / 300 % frequencies.length];
                byte[] chunk = generateTone(frequency, 300, sampleRate);
                System.arraycopy(chunk, 0, audioData, index, chunk.length);
                index += chunk.length;
            }
            
            saveAudioFile(audioData, "sounds/jackpot.wav", sampleRate);
            System.out.println("✅ Jackpot sound generated: sounds/jackpot.wav");
            
        } catch (Exception e) {
            System.out.println("❌ Error generating jackpot sound: " + e.getMessage());
        }
    }
    
    /**
     * Generate toggle sound (UI click)
     */
    private static void generateToggleSound() {
        try {
            System.out.println("🔊 Generating toggle sound...");
            
            int sampleRate = 44100;
            int duration = 500; // 0.5 seconds
            byte[] audioData = new byte[sampleRate * duration / 500];
            
            // Create simple click sound
            double frequency = 800; // High frequency click
            byte[] chunk = generateTone(frequency, duration, sampleRate);
            System.arraycopy(chunk, 0, audioData, 0, chunk.length);
            
            saveAudioFile(audioData, "sounds/toggle.wav", sampleRate);
            System.out.println("✅ Toggle sound generated: sounds/toggle.wav");
            
        } catch (Exception e) {
            System.out.println("❌ Error generating toggle sound: " + e.getMessage());
        }
    }
    
    /**
     * Generate a tone at specified frequency and duration
     */
    private static byte[] generateTone(double frequency, int durationMs, int sampleRate) {
        int numSamples = (sampleRate * durationMs) / 1000;
        byte[] audioData = new byte[numSamples];
        
        for (int i = 0; i < numSamples; i++) {
            double angle = (2.0 * Math.PI * frequency * i) / sampleRate;
            // Generate sine wave and convert to byte
            double sample = Math.sin(angle) * 0.3; // Reduce volume to 30%
            audioData[i] = (byte) (sample * 127);
        }
        
        return audioData;
    }
    
    /**
     * Save audio data to WAV file
     */
    private static void saveAudioFile(byte[] audioData, String filename, int sampleRate) throws Exception {
        AudioFormat format = new AudioFormat(
            sampleRate,    // Sample rate
            8,            // Sample size in bits
            1,            // Channels (mono)
            true,         // Signed
            false         // Little endian
        );
        
        ByteArrayInputStream bais = new ByteArrayInputStream(audioData);
        AudioInputStream ais = new AudioInputStream(bais, format, audioData.length);
        
        AudioSystem.write(ais, AudioFileFormat.Type.WAVE, new File(filename));
    }
} 