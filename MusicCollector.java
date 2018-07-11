import java.io.File;
import java.util.ArrayList;

public class MusicCollector {
    Object[][] tracks = new Object[3][1];

    String trackName = "";

    public Object[][] collectMusic() {
        File f = new File("C:\\Users\\Ben\\IdeaProjects\\Sandbox\\Music");
        File l[] = f.listFiles();

        int i = 0;

        for (File track : l) {
            trackName = "<html>" + track.getName().substring(0, track.getName().length() - 4) + "<br> Artist - Album";

            tracks[i][0] = trackName;

            i++;
        }

        return tracks;
    }
}
