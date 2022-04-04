package _HB_2.Backend.rating;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping( "/rating")
public class RatingController {

    @Autowired
    private RatingService ratingService;

    @PostMapping("/createRating")
    Rating createRating(@RequestParam int raterId,
                        @RequestParam int ratedId,
                        @RequestBody Rating rating) {
        return ratingService.createRating(raterId, ratedId, rating);
    }

    @GetMapping("/getUserRating")
    Float getUserRating(@RequestParam int userId) {
        return ratingService.getUserRating(userId);
    }
}
