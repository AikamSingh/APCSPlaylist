
import java.io.File;
import java.net.URL;
import java.util.ArrayList;
import java.util.Scanner;

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

    /**
     * @param song the song to like
     */
    public void likeSong(Song song) {
        song.setLiked(true);
    }

    /**
     * @param song the song to remove
     */
    public void removeSong(Song song) {
        songs.remove(song);
        System.out.printf("Removed " + song);
    }

    /**
     * @return a string of all songs
     */
    public String examineAllSongs() {
        String allSongs = "";
        for (Song song : songs) {
            allSongs += song.toString();
            allSongs += "\n";
        }
        return allSongs.strip();// I know this is lazy but its cleaner than if statements

    }

    /**
     * @return a string of all liked songs
     */
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

    /**
     * This method will return the total duration of all songs in the playlist in minutes
     *
     * @return duration in minutes
     */
    public String totalDurationFormatted() {
        double totalDuration = totalDurationSeconds();
        int hours = (int) totalDuration / 3600;
        int minutes = (int) totalDuration / 60;
        int seconds = (int) totalDuration % 60;
        return String.format("%d:%02d:%02d", hours, minutes, seconds);// java will never be python
    }

    /**
     * This method will remove all unliked songs from the playlist
     */
    public void removeUnlikedSongs() {
        for (int i = 0; i < songs.size(); i++) {
            if (!songs.get(i).getLiked()) {
                songs.remove(i);
            }
        }
    }

    /**
     * @param position the index of the song to return
     * @return the song object at index position
     */
    public Song getSong(int position) {
        return songs.get(position);
    }

    /**
     * @return a csv of the entire playlist data
     */
    private String dumpData() {
        String data = "";
        for (Song song : songs) {
            data += song.getArtist() + "," + song.getName() + "," + song.getLength() + "," + song.getLiked() + "\n";
        }
        return data.strip();
    }

    /**
     * This method will save the playlist to a file
     *
     * @param filename the name of the file to save to
     */
    public void saveToFile(String filename) {
        try {

            java.io.FileWriter file = new java.io.FileWriter(filename);
            file.write(dumpData());
            file.close();
            System.out.println("Successfully wrote to the file to " + new File(filename).getAbsolutePath());
        }
        catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }

    }
    /**
     * This method will read a csv file and add all songs to the playlist. DESTRUCTIVE WILL CLEAR CURRENT PLAYLIST
     * @param filepath the name of the file to read
     */
    public void loadFromFile(String filepath) {
        try {
            //We need to use an absolute filepath or else java attempts to get file from JVM execution directory
            URL url = getClass().getResource(filepath);
            File file = new File(url.toURI());
            Scanner scanner = new Scanner(file);
            songs = new ArrayList<Song>();

            while (scanner.hasNextLine()) {
                try{
                String line = scanner.nextLine();
                String[] data = line.split(",");
                Song song = new Song(data[0], data[1], Double.parseDouble(data[2]), Boolean.parseBoolean(data[3]));
                songs.add(song);
                }
                catch (Exception e){
                    System.out.println("Playlist currupted! Go use spotify");
                }

            }

            scanner.close();
            System.out.println("Successfully loaded " + songs.size() + " songs from " + file.getAbsolutePath());
        }
        catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }


    /**
     * HIGHLY EXPERIMENTAL file player. Precurser to being able to play songs in the playlist by streaming from yt
     *
     * @param filepath the path to the file to play
     */
    public void playFile(String filepath) {// experimental. need some way to download music form soundcloud/youtube first
        System.out.print("Playing music");
        try {
            //using fully qualified names so imports arent as ugly I know.. it looks bad but this is an experimental feature and not ment for production
            URL url = getClass().getResource(filepath);
            File file = new File(url.toURI());

            javax.sound.sampled.AudioInputStream audioInput = javax.sound.sampled.AudioSystem.getAudioInputStream(file);
            javax.sound.sampled.Clip clip = javax.sound.sampled.AudioSystem.getClip();
            clip.open(audioInput);
            clip.start();
            javax.swing.JOptionPane.showMessageDialog(null, "Press OK to stop playing");// https://stackoverflow.com/questions/6870816/why-this-code-doesnt-play-the-sound-file apparenly we need to suspend currenty thread so deamon thread which plays music doesnt die so it can acutally play
            //note this will stop working if you run in a headless server environment
        }
        catch (Exception e) {
            System.out.println("Unknown Error");
        }
    }

}
