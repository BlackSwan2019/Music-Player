import javax.swing.*;

public class GameMusic {
    public static void main(String[] args) {
        UserInterface frame = new UserInterface();

        MusicCollector collector = new MusicCollector();

        collector.collectMusic();
    }
}
