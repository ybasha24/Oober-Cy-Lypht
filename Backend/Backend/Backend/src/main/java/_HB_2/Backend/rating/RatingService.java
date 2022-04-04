package _HB_2.Backend.rating;

import _HB_2.Backend.user.User;
import _HB_2.Backend.user.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RatingService {

    @Autowired
    UserRepository userRepository;

    @Autowired
    RatingRepository ratingRepository;

    public Rating createRating(int raterId, int ratedId, Rating rating) {

        User rater = userRepository.findById(raterId);
        User rated = userRepository.findById(ratedId);

        rating.setRater(rater);
        rating.setRated(rated);

        ratingRepository.save(rating);
        return rating;
    }
}
