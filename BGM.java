import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import java.io.File;

public class BGM {
    //playing the background music
    //Copied from https://stackoverflow.com/questions/6045384/playing-mp3-and-wav-in-java
    public static void playMusic(){
        try
        {
            String soundName = "Snake_Game.wav";
            AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(new File(soundName).getAbsoluteFile());
            Clip clip = AudioSystem.getClip();
            clip.open(audioInputStream);
            clip.start(); //start to play the clip
        }
catch (Exception e)
        {
        }
    }
}
