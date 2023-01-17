import java.util.ArrayList;

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
    public Playlist()
    {
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
    public void addSong(Song song)
    {
        songs.add(song);
        System.out.println("Added "+ song);
    }
    public void likeSong(Song song)
    {
        song.setLiked(true);
    }
    public void removeSong(Song song)
    {
        songs.remove(song);
        System.out.printf("Removed "+ song);
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
}
