import javax.sound.sampled.*;
import java.io.*;
import java.nio.file.*;

/**
 * Enhanced Sound Generator for Las Vegas Style Slot Machine
 * Creates authentic casino slot machine sound effects
 */
public class SoundGenerator {
    
    public static void main(String[] args) {
        System.out.println("🎰 Generating Las Vegas Slot Machine Sound Effects...");
        
        // Create sounds directory
        try {
            Files.createDirectories(Paths.get("sounds"));
            System.out.println("✅ Created sounds directory");
        } catch (IOException e) {
            System.out.println("❌ Could not create sounds directory: " + e.getMessage());
            return;
        }
        
        // Generate all Las Vegas style sound effects
        generateVegasSpinSound();
        generateVegasWinSound();
        generateVegasLoseSound();
        generateVegasJackpotSound();
        generateVegasToggleSound();
        generateCoinDropSound();
        generateReelStopSound();
        
        System.out.println("\n🎉 All Las Vegas slot machine sounds generated successfully!");
        System.out.println("📁 Check the 'sounds' folder for your authentic casino audio files.");
        System.out.println("🎮 Run your slot machine to experience the Vegas atmosphere!");
    }
    
    /**
     * Generate authentic Las Vegas reel spinning sound
     */
    private static void generateVegasSpinSound() {
        try {
            System.out.println("🎰 Generating Las Vegas spin sound...");
            
            int sampleRate = 44100;
            int duration = 2500; // 2.5 seconds for authentic feel
            byte[] audioData = new byte[sampleRate * duration / 500];
            
            int index = 0;
            for (int i = 0; i < duration; i += 500) {
                // Create mechanical whirring with multiple frequencies
                double baseFreq = 150 + (i * 30.0 / duration); // 150-180 Hz base
                double harmonic1 = baseFreq * 2; // Second harmonic
                double harmonic2 = baseFreq * 3; // Third harmonic
                
                // Combine multiple frequencies for mechanical sound
                byte[] chunk = generateMechanicalTone(baseFreq, harmonic1, harmonic2, 500, sampleRate);
                System.arraycopy(chunk, 0, audioData, index, chunk.length);
                index += chunk.length;
            }
            
            saveAudioFile(audioData, "sounds/spin.wav", sampleRate);
            System.out.println("✅ Las Vegas spin sound generated: sounds/spin.wav");
            
        } catch (Exception e) {
            System.out.println("❌ Error generating Vegas spin sound: " + e.getMessage());
        }
    }
    
    /**
     * Generate Las Vegas win sound (casino bells and chimes)
     */
    private static void generateVegasWinSound() {
        try {
            System.out.println("🎉 Generating Las Vegas win sound...");
            
            int sampleRate = 44100;
            int duration = 2000; // 2 seconds
            byte[] audioData = new byte[sampleRate * duration / 500];
            
            // Create casino bell sequence (similar to real slot machines)
            double[] bellFrequencies = {523.25, 659.25, 783.99, 1046.50}; // C, E, G, C
            int index = 0;
            
            for (int i = 0; i < duration; i += 400) { // Faster bell sequence
                double frequency = bellFrequencies[i / 400 % bellFrequencies.length];
                byte[] chunk = generateBellTone(frequency, 400, sampleRate);
                System.arraycopy(chunk, 0, audioData, index, chunk.length);
                index += chunk.length;
            }
            
            saveAudioFile(audioData, "sounds/win.wav", sampleRate);
            System.out.println("✅ Las Vegas win sound generated: sounds/win.wav");
            
        } catch (Exception e) {
            System.out.println("❌ Error generating Vegas win sound: " + e.getMessage());
        }
    }
    
    /**
     * Generate Las Vegas lose sound (soft disappointment)
     */
    private static void generateVegasLoseSound() {
        try {
            System.out.println("😔 Generating Las Vegas lose sound...");
            
            int sampleRate = 44100;
            int duration = 1200; // 1.2 seconds
            byte[] audioData = new byte[sampleRate * duration / 500];
            
            // Create descending "wah-wah" sound
            double startFreq = 350;
            int index = 0;
            
            for (int i = 0; i < duration; i += 400) {
                double currentFreq = startFreq - (i * 150.0 / duration); // Descend to 200 Hz
                byte[] chunk = generateWahWahTone(currentFreq, 400, sampleRate);
                System.arraycopy(chunk, 0, audioData, index, chunk.length);
                index += chunk.length;
            }
            
            saveAudioFile(audioData, "sounds/lose.wav", sampleRate);
            System.out.println("✅ Las Vegas lose sound generated: sounds/lose.wav");
            
        } catch (Exception e) {
            System.out.println("❌ Error generating Vegas lose sound: " + e.getMessage());
        }
    }
    
    /**
     * Generate Las Vegas jackpot sound (exciting celebration)
     */
    private static void generateVegasJackpotSound() {
        try {
            System.out.println("🏆 Generating Las Vegas jackpot sound...");
            
            int sampleRate = 44100;
            int duration = 4000; // 4 seconds for epic jackpot
            byte[] audioData = new byte[sampleRate * duration / 500];
            
            // Create exciting ascending arpeggio with casino bells
            double[] jackpotFrequencies = {523.25, 659.25, 783.99, 1046.50, 1318.51, 1567.98}; // C major scale
            int index = 0;
            
            for (int i = 0; i < duration; i += 300) { // Fast exciting sequence
                double frequency = jackpotFrequencies[i / 300 % jackpotFrequencies.length];
                byte[] chunk = generateJackpotTone(frequency, 300, sampleRate);
                System.arraycopy(chunk, 0, audioData, index, chunk.length);
                index += chunk.length;
            }
            
            saveAudioFile(audioData, "sounds/jackpot.wav", sampleRate);
            System.out.println("✅ Las Vegas jackpot sound generated: sounds/jackpot.wav");
            
        } catch (Exception e) {
            System.out.println("❌ Error generating Vegas jackpot sound: " + e.getMessage());
        }
    }
    
    /**
     * Generate Las Vegas toggle sound (casino button click)
     */
    private static void generateVegasToggleSound() {
        try {
            System.out.println("🔊 Generating Las Vegas toggle sound...");
            
            int sampleRate = 44100;
            int duration = 300; // 0.3 seconds for quick click
            byte[] audioData = new byte[sampleRate * duration / 500];
            
            // Create casino button click sound
            double frequency = 1200; // High frequency click
            byte[] chunk = generateClickTone(frequency, duration, sampleRate);
            System.arraycopy(chunk, 0, audioData, 0, chunk.length);
            
            saveAudioFile(audioData, "sounds/toggle.wav", sampleRate);
            System.out.println("✅ Las Vegas toggle sound generated: sounds/toggle.wav");
            
        } catch (Exception e) {
            System.out.println("❌ Error generating Vegas toggle sound: " + e.getMessage());
        }
    }
    
    /**
     * Generate coin drop sound (authentic casino coin sound)
     */
    private static void generateCoinDropSound() {
        try {
            System.out.println("🪙 Generating coin drop sound...");
            
            int sampleRate = 44100;
            int duration = 800; // 0.8 seconds
            byte[] audioData = new byte[sampleRate * duration / 500];
            
            // Create metallic coin drop sound
            double frequency = 800; // Metallic frequency
            byte[] chunk = generateCoinTone(frequency, duration, sampleRate);
            System.arraycopy(chunk, 0, audioData, 0, chunk.length);
            
            saveAudioFile(audioData, "sounds/coin.wav", sampleRate);
            System.out.println("✅ Coin drop sound generated: sounds/coin.wav");
            
        } catch (Exception e) {
            System.out.println("❌ Error generating coin drop sound: " + e.getMessage());
        }
    }
    
    /**
     * Generate reel stop sound (mechanical stop)
     */
    private static void generateReelStopSound() {
        try {
            System.out.println("🛑 Generating reel stop sound...");
            
            int sampleRate = 44100;
            int duration = 600; // 0.6 seconds
            byte[] audioData = new byte[sampleRate * duration / 500];
            
            // Create mechanical stop sound
            double frequency = 400; // Mechanical frequency
            byte[] chunk = generateStopTone(frequency, duration, sampleRate);
            System.arraycopy(chunk, 0, audioData, 0, chunk.length);
            
            saveAudioFile(audioData, "sounds/stop.wav", sampleRate);
            System.out.println("✅ Reel stop sound generated: sounds/stop.wav");
            
        } catch (Exception e) {
            System.out.println("❌ Error generating reel stop sound: " + e.getMessage());
        }
    }
    
    /**
     * Generate mechanical tone with harmonics for authentic reel sound
     */
    private static byte[] generateMechanicalTone(double baseFreq, double harmonic1, double harmonic2, int durationMs, int sampleRate) {
        int numSamples = (sampleRate * durationMs) / 1000;
        byte[] audioData = new byte[numSamples];
        
        for (int i = 0; i < numSamples; i++) {
            double time = (double) i / sampleRate;
            
            // Combine base frequency with harmonics for mechanical sound
            double sample = Math.sin(2.0 * Math.PI * baseFreq * time) * 0.4 +
                           Math.sin(2.0 * Math.PI * harmonic1 * time) * 0.2 +
                           Math.sin(2.0 * Math.PI * harmonic2 * time) * 0.1;
            
            // Add some randomness for authentic mechanical feel
            sample += (Math.random() - 0.5) * 0.1;
            
            audioData[i] = (byte) (sample * 127);
        }
        
        return audioData;
    }
    
    /**
     * Generate bell tone for casino win sounds
     */
    private static byte[] generateBellTone(double frequency, int durationMs, int sampleRate) {
        int numSamples = (sampleRate * durationMs) / 1000;
        byte[] audioData = new byte[numSamples];
        
        for (int i = 0; i < numSamples; i++) {
            double time = (double) i / sampleRate;
            
            // Create bell-like sound with multiple harmonics
            double sample = Math.sin(2.0 * Math.PI * frequency * time) * 0.5 +
                           Math.sin(2.0 * Math.PI * frequency * 2.5 * time) * 0.3 +
                           Math.sin(2.0 * Math.PI * frequency * 4.0 * time) * 0.2;
            
            // Add decay for bell effect
            double decay = Math.exp(-time * 2.0);
            sample *= decay;
            
            audioData[i] = (byte) (sample * 127);
        }
        
        return audioData;
    }
    
    /**
     * Generate wah-wah tone for lose sound
     */
    private static byte[] generateWahWahTone(double frequency, int durationMs, int sampleRate) {
        int numSamples = (sampleRate * durationMs) / 1000;
        byte[] audioData = new byte[numSamples];
        
        for (int i = 0; i < numSamples; i++) {
            double time = (double) i / sampleRate;
            
            // Create wah-wah effect with frequency modulation
            double wahFreq = frequency + Math.sin(2.0 * Math.PI * 2.0 * time) * 50;
            double sample = Math.sin(2.0 * Math.PI * wahFreq * time) * 0.3;
            
            audioData[i] = (byte) (sample * 127);
        }
        
        return audioData;
    }
    
    /**
     * Generate jackpot tone with excitement
     */
    private static byte[] generateJackpotTone(double frequency, int durationMs, int sampleRate) {
        int numSamples = (sampleRate * durationMs) / 1000;
        byte[] audioData = new byte[numSamples];
        
        for (int i = 0; i < numSamples; i++) {
            double time = (double) i / sampleRate;
            
            // Create exciting jackpot sound with multiple harmonics
            double sample = Math.sin(2.0 * Math.PI * frequency * time) * 0.6 +
                           Math.sin(2.0 * Math.PI * frequency * 2.0 * time) * 0.3 +
                           Math.sin(2.0 * Math.PI * frequency * 3.0 * time) * 0.1;
            
            // Add some excitement with amplitude modulation
            sample *= (1.0 + Math.sin(2.0 * Math.PI * 8.0 * time) * 0.2);
            
            audioData[i] = (byte) (sample * 127);
        }
        
        return audioData;
    }
    
    /**
     * Generate click tone for UI interactions
     */
    private static byte[] generateClickTone(double frequency, int durationMs, int sampleRate) {
        int numSamples = (sampleRate * durationMs) / 1000;
        byte[] audioData = new byte[numSamples];
        
        for (int i = 0; i < numSamples; i++) {
            double time = (double) i / sampleRate;
            
            // Create sharp click sound
            double sample = Math.sin(2.0 * Math.PI * frequency * time) * 0.4;
            
            // Add quick attack and decay
            double envelope = Math.exp(-time * 10.0);
            sample *= envelope;
            
            audioData[i] = (byte) (sample * 127);
        }
        
        return audioData;
    }
    
    /**
     * Generate coin drop tone
     */
    private static byte[] generateCoinTone(double frequency, int durationMs, int sampleRate) {
        int numSamples = (sampleRate * durationMs) / 1000;
        byte[] audioData = new byte[numSamples];
        
        for (int i = 0; i < numSamples; i++) {
            double time = (double) i / sampleRate;
            
            // Create metallic coin sound
            double sample = Math.sin(2.0 * Math.PI * frequency * time) * 0.5 +
                           Math.sin(2.0 * Math.PI * frequency * 1.5 * time) * 0.3;
            
            // Add metallic resonance
            sample += Math.sin(2.0 * Math.PI * frequency * 2.5 * time) * 0.2;
            
            audioData[i] = (byte) (sample * 127);
        }
        
        return audioData;
    }
    
    /**
     * Generate reel stop tone
     */
    private static byte[] generateStopTone(double frequency, int durationMs, int sampleRate) {
        int numSamples = (sampleRate * durationMs) / 1000;
        byte[] audioData = new byte[numSamples];
        
        for (int i = 0; i < numSamples; i++) {
            double time = (double) i / sampleRate;
            
            // Create mechanical stop sound
            double sample = Math.sin(2.0 * Math.PI * frequency * time) * 0.4;
            
            // Add mechanical resonance
            sample += Math.sin(2.0 * Math.PI * frequency * 1.8 * time) * 0.2;
            
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