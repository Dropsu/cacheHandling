package ds.model;

import org.springframework.data.cassandra.core.mapping.Column;
import org.springframework.data.cassandra.core.mapping.PrimaryKey;
import org.springframework.data.cassandra.core.mapping.Table;

import java.io.Serializable;
import java.util.Arrays;

@Table
public class UserProfile implements Serializable {

    @PrimaryKey
    private String id;

    @Column
    private String [] movieRatings;

    public UserProfile(String id, String[] movieRatings) {
        this.id = id;
        this.movieRatings = movieRatings;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String[] getMovieRatings() {
        return movieRatings;
    }

    public void setMovieRatings(String[] movieRatings) {
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
