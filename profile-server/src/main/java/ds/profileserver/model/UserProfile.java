package ds.profileserver.model;

import java.util.Arrays;
import java.util.HashMap;

public class UserProfile {

    private int id;
    private int [] movieRatings = new int[]{7,4,4,2,4,5,6,8,9,7,4,5,6,1,2,3,6,5,4,7,8,9,6,5,4,2};

    public UserProfile(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int[] getMovieRatings() {
        return movieRatings;
    }

    public void setMovieRatings(int[] movieRatings) {
        this.movieRatings = movieRatings;
    }

    @Override
    public String toString() {
        return "UserProfile{" +
                "id=" + id +
                ", movieRatings=" + Arrays.toString(movieRatings) +
                '}';
    }
}
