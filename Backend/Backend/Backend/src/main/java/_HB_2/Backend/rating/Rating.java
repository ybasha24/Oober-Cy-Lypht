package _HB_2.Backend.rating;

import _HB_2.Backend.user.User;
import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.Set;

@Entity
public class Rating {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @ManyToOne
    @JoinColumn(name = "Rater_id")
    User rater;

    @ManyToOne
    @JoinColumn(name = "Rated_id")
    User rated;

    int rating;

    public Rating(){

    }

    public int getId() {
        return id;
    }

    public void setRater(User rater) {
        this.rater = rater;
    }

    public User getRater() {
        return rater;
    }

    public void setRatee(User rated) {
        this.rated = rated;
    }

    public User getRated() {
        return rated;
    }

    public int getRating() {
        return rating;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }

}
