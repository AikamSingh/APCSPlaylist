/**
 * File for a Song class to be used in the Playlist Project
 * @author Aikam Jeremy Max
 * @version 1/17/22
 */
public class Song {
    //Fields-- what information do we want each Song to store?
    public String artist;
    public String name;
    public double length;
    public boolean liked;



    /**
     * Constructor-- what information needs to be given to make a Song?
     * How will you handle 'liked' songs? It makes sense for a Song not be 'liked' by default
     */

    public Song(String artist, String name, double length)
    {
        this.artist = artist;
        this.name = name;
        this.length = length;
        liked = false;
    }
    public Song(String artist, String name, double length, boolean liked)
    {
        this.artist = artist;
        this.name = name;
        this.length = length;
        this.liked = liked;
    }


     /**
      * Methods-- what will you want each Song to do?
      * Consider all the getter methods (getName, getArtist, etc.)
      * You should probably have a toString method to be able to get the information for the full
      * song easily as well!
      * What kind of mutator (setter) methods will you need?
      */
    public String getName() 
    {
        return name;
    }
    /**
     * @return the artist
     */
    public String getArtist() 
    {
        return artist;
    }
    /**
     * @return the length
     */
    public double getLength() 
    {
        return length;
    }
    /**
     * @return the liked
     */
    public boolean getLiked()
    {
        return liked;
    }
    /**
     * @param liked the liked to set
     */
    public void setLiked(boolean liked)
    {
        this.liked = liked;
    }
    /**
     * @return a string of the song full title
     */
    public String toString()
    {
        //round the duration to 2 decimal places and convert from seconds to minutes and seconds
        int minutes = (int) length / 60;
        int seconds = (int) length % 60;
        String duration = String.format("%d:%02d", minutes, seconds);
        String likedConcat = liked ? " -- liked" : "";

        return "\""+ name +"\"" + " by " + artist + " (" + duration + ")" + likedConcat;
    }
}
