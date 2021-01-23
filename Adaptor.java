// In this example, our MediaAdaptor is the Adaptor class which lets
// AudioPlayer play other types of files: mp4 and Vlc


// ======================================================================

interface MediaPlayer {
    public void play(String audioType, String fileName);
}

interface AdvancedMediaPlayer {
    public void playVlc(String filename);
    public void playMp4(String filename);
}

// The concrete class or interfaces for the adaptor to target
class VlcPlayer implements AdvancedMediaPlayer {
    @Override
    public void playVlc(String filename) {
        System.out.println("Playing vlc file. Name: " + filename);
    }

    @Override
    public void playMp4(String filename) {
        // nothing 
    }
}

class Mp4Player implements AdvancedMediaPlayer {
    @Override
    public void playVlc(String filename) {
        // do nothing
    }

    @Override
    public void playMp4(String filename) {
        System.out.println("Playing mp4 file. Name: " + filename);
    }
}

class MediaAdapter implements MediaPlayer {
    AdvancedMediaPlayer advancedMediaPlayer;
    
    public MediaAdapter(String audioType) {
        if (audioType.equalsIgnoreCase("vlc") {
            advancedMediaPlayer = new VlcPlayer();
        } else {
            advancedMediaPlayer = new Mp4Player();
        }
    }

    @Override 
    public void play(String audioType, String filename) {
        if (audioType.equalsIgnoreCase("vlc")) {
            advancedMediaPlayer.playVlc(filename);
        } else if (audioType.equalsIgnoreCase("mp4")) {
            advancedMediaPlayer.playMp4(filename);
        }
    }
}

class AudioPlayer implements MediaPlayer {
    MediaAdapter mediaAdapter;

    @Override
    public void play(String audioType, String filename) {
        if (audioType.equalsIgnoreCase("mp3")) {
            System.out.println("Playing mp3 file. Name " + filename);
        } else if (audioType.equalsIgnoreCase("vlc") || audioType.equalsIgnoreCase("mp4")) {
            mediaAdapter = new MediaAdapter((audioType));
            mediaAdapter.play(audioType, filename);
        } else {
            System.out.print("Invalid audio type");
        }
    }
}

public class Adaptor {
    public static void main(String[] args) {
        AudioPlayer audioPlayer = new AudioPlayer();
  
        audioPlayer.play("mp3", "beyond the horizon.mp3");
        audioPlayer.play("mp4", "alone.mp4");
        audioPlayer.play("vlc", "far far away.vlc");
        audioPlayer.play("avi", "mind me.avi");
     }
}
