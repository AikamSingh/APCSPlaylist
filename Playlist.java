
import java.util.ArrayList;
// all the garbage needed to play a file


/**
 * The Playlist class, which will keep track of a playlist of Song objects
 * Refer to the project description to make sure you have access to all available methods
 */
public class Playlist {
    /**
     * Fields-- This will likely just need to be the ArrayList of Songs. Reference our previous problems
     * (CarDealership, Zoo) for structure on how this will look
     */
    private ArrayList<Song> songs;

    /**
     * Constructor-- this doesn't need any parameters. You should just initialize the ArrayList and
     * then use additional methods to add Songs in one-by-one
     */
    public Playlist() {
        songs = new ArrayList<>();
    }

    /**
     * Methods-- Remember that you need to be able to complete all of the following:
     * Adding a song
     * 'liking' a song
     * Removing a specific song
     * Examining all Songs (a String return or void print makes sense here)
     * Examining a sublist of all liked songs
     * Determining the total duration of all songs
     * Removing all unliked songs from the playlist (careful with this one!)
     */
    public void addSong(Song song) {
        songs.add(song);
        System.out.println("Added " + song);
    }

    public void likeSong(Song song) {
        song.setLiked(true);
    }

    public void removeSong(Song song) {
        songs.remove(song);
        System.out.printf("Removed " + song);
    }

    public String examineAllSongs() {
        String allSongs = "";
        for (Song song : songs) {
            allSongs += song.toString();
            allSongs += "\n";
        }
        return allSongs.strip();// I know this is lazy but its cleaner than if statements

    }

    public String examineLikedSongs() {
        String likedSongs = "";
        for (Song song : songs) {
            if (song.getLiked()) {
                likedSongs += song.toString();
                likedSongs += "\n";
            }
        }
        return likedSongs.strip();
    }

    /**
     * This method will return the total duration of all songs in the playlist in seconds
     *
     * @return duration in seconds
     */
    public double totalDurationSeconds() {
        double totalDuration = 0;
        for (Song song : songs) {
            totalDuration += song.getLength();
        }
        return totalDuration;
    }

    public String totalDurationFormatted() {
        double totalDuration = totalDurationSeconds();
        int hours = (int) totalDuration / 3600;
        int minutes = (int) totalDuration / 60;
        int seconds = (int) totalDuration % 60;
        return String.format("%d:%02d:%02d", hours, minutes, seconds);// java will never be python
    }

    public void removeUnlikedSongs() {
        for (int i = 0; i < songs.size(); i++) {
            if (!songs.get(i).getLiked()) {
                songs.remove(i);
            }
        }
    }

    public Song getSong(int position) {
        return songs.get(position);
    }

    public void playFile(String filepath) {// experimental. need some way to download music form soundcloud/youtube first
        System.out.print("Playing music");
        try {
            //using fully qualified names so imports arent as ugly I know.. it looks bad but this is an experimental feature and not ment for production
            java.net.URL url = getClass().getResource(filepath);
            java.io.File file = new java.io.File(url.toURI());

            javax.sound.sampled.AudioInputStream audioInput = javax.sound.sampled.AudioSystem.getAudioInputStream(file);
            javax.sound.sampled.Clip clip = javax.sound.sampled.AudioSystem.getClip();
            clip.open(audioInput);
            clip.start();
            javax.swing.JOptionPane.showMessageDialog(null, "Press OK to stop playing");// https://stackoverflow.com/questions/6870816/why-this-code-doesnt-play-the-sound-file apparenly we need to suspend currenty thread so deamon thread which plays music doesnt die so it can acutally play
        }
        catch (Exception e) {
            System.out.println("Unknown Error");
        }
    }

}
