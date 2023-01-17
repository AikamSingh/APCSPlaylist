/**
 * Sample of a tester file for the Playlist class. This file should demonstrate all the capability of your
 * playlist in the main method. You don't need to follow the testing specifications of this exactly
 * if you want to write your own separate tester instead.
 * Note that there's no need for a Scanner in this project-- all of the playlist can be 'hardcoded' into main.
 * @author
 * @version
 */
public class PlaylistTester {
    public static void main(String[] args) {
        System.out.println("Initializing a Playlist...\n");
        //Make your playlist here
        Playlist playlist = new Playlist();
        System.out.println("Adding songs to the Playlist...\n");
        /**
         * Add some songs here. Note that the format for adding a Song to a Playlist p
         * is something like...
         * p.addSong(new Song(..., ..., ...))
         */
        playlist.addSong(new Song("The Beatles", "Hey Jude", 250));
        playlist.addSong(new Song("The Beatles", "Let It Be", 200));
        playlist.addSong(new Song("The Beatles", "Yesterday", 150));
        playlist.addSong(new Song("The Beatles", "In My Life", 300));




        //Print out all the songs in the playlist to verify it's working correctly
        System.out.println("Printing out all songs in the Playlist...\n");
        System.out.println(playlist.examineAllSongs());




        System.out.println("\nLiking the songs in position X, Y, Z, etc....\n");
        //Once your songs are 'liked', this should be reflected in the next printout
        playlist.likeSong(playlist.getSong(0));
        playlist.likeSong(playlist.getSong(1));
        playlist.likeSong(playlist.getSong(2));


        System.out.println("Printing the songs...\n");
        System.out.println(playlist.examineAllSongs());


        System.out.println("\nRemoving the song in position A, B, C, etc...\n");
        playlist.removeSong(playlist.getSong(0));
        playlist.removeSong(playlist.getSong(1));
        playlist.removeSong(playlist.getSong(2));


        System.out.println("Printing the songs...\n");
        System.out.println(playlist.examineAllSongs());

        System.out.println("\nPrinting only the liked songs...\n");
        //Your Playlist should be able to do this without looping while in main!
        System.out.println(playlist.examineLikedSongs());
        System.out.println("--------------------");
        System.out.println("expected: Hey Jude by The Beatles (0.06944444444444445:4.166666666666667:10.0)Let It Be by The Beatles (0.05555555555555555:3.3333333333333335:0.0)Yesterday by The Beatles (0.041666666666666664:2.5:0.0)");



        System.out.println("\nPrinting the total duration of all songs...\n");
        //Note that the format should look something like minutes:second
        System.out.println(playlist.totalDuration());

        System.out.println("\nRemoving all unliked songs from the playlist...\n");
        //This should be doable with a single method call


        System.out.println("Printing all songs...\n");
        //This should now look like only the liked songs list from before
    }
}
