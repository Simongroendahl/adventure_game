import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.FloatControl;
import java.net.URL;

public class Audio {

    private Clip enterRoomOne = load("Enter_Room_1.wav");
    private Clip enterRoomTwo = load("Enter_Room_2.wav");
    private Clip alarmSiren = load("Alarm_Siren.wav");

    private Clip itemPickup = load("Item_Pickup.wav");
    private Clip terminalInput = load("Terminal_Input.wav");
    private Clip enterTerminal = load("Enter_Terminal.wav");
    private Clip error = load("Error.wav");

    // Våben lydeffekter
    private Clip meleeHitOne = load("Melee_Hit_1.wav");
    private Clip laserShotOne = load("Laser_Shot_1.wav");
    private Clip laserShotTwo = load("Laser_Shot_2.wav");

    protected Clip mainMenu = load("Main_Menu_BG.wav");
    protected Clip ambientBG = load("Ambient_BG.wav");
    private static float AMBIENT_VOLUME = -12.0f;

    public Audio() {
        if(ambientBG != null) {
            setVolume(ambientBG, AMBIENT_VOLUME);
        }
    }

    private boolean enabled = true;

    public void play(Sound sound) {
        Clip clip = switch (sound) {
            case ENTER_ROOM_1 -> enterRoomOne;
            case ENTER_ROOM_2 -> enterRoomTwo;
            case ITEM_PICKUP -> itemPickup;
            case ERROR -> error;
            case ALARM_SIREN -> alarmSiren;
            case TERMINAL_INPUT -> terminalInput;
            case ENTER_TERMINAL -> enterTerminal;
            case MELEE_HIT_1 -> meleeHitOne;
            case LASER_SHOT_1 -> laserShotOne;
            case LASER_SHOT_2 -> laserShotTwo;
            case MAIN_MENU -> mainMenu;
            case AMBIENT_BG -> ambientBG;
        };

        if (!enabled || clip == null) {
            return;
        }

        clip.stop();
        clip.setFramePosition(0);
        clip.start();
    }

    // Starter baggrundsmusikken
    public void startAmbient() {
        if(!enabled || ambientBG == null || ambientBG.isRunning()) {
            return;
        }
        ambientBG.loop(Clip.LOOP_CONTINUOUSLY);
    }

    public void startAmbientMainMenu() {
        if(!enabled || mainMenu == null || mainMenu.isRunning()) {
            return;
        }
        mainMenu.loop(Clip.LOOP_CONTINUOUSLY);
    }

    // Stopper baggrundsmusikken
    public void stopAmbient() {
        if(ambientBG != null) {
            ambientBG.stop();
        }
    }

    public void stopAmbientMainMenu() {
        if(mainMenu != null) {
            mainMenu.stop();
        }
    }


    /*public void setEnabled(boolean enabled) {
        this.enabled = enabled;

        if(enabled) {
            startAmbient();
        } else {
            stopAmbient();
        }
    }*/

    public void close() {
        Clip[] allClips = {};

        for (Clip clip : allClips) {
            if (clip != null) {
                clip.stop();
                clip.close();
            }
        }
    }

    private void setVolume(Clip clip, float decibel) {
        if(clip.isControlSupported(FloatControl.Type.MASTER_GAIN)) {
            FloatControl gain = (FloatControl) clip.getControl(FloatControl.Type.MASTER_GAIN);
            gain.setValue(decibel);
        }
    }



    private Clip load(String fileName) {
        try {
            URL url = Audio.class.getResource("/sounds/" + fileName);
            AudioInputStream stream = AudioSystem.getAudioInputStream(url);
            Clip clip = AudioSystem.getClip();
            clip.open(stream);
            return clip;
        } catch (Exception e) {
            System.out.println("Could not load sound: " + fileName);
            return null;
        }
    }
}
