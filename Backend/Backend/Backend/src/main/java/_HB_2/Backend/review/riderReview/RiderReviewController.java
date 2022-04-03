package _HB_2.Backend.review.riderReview;

import _HB_2.Backend.review.driverReview.DriverReview;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping( "/riderReview")
public class RiderReviewController {

    @Autowired
    RiderReviewService riderReviewService;

    @PostMapping("/postRiderReview")
    RiderReview createRiderReview(
            @RequestParam int driverId, int riderId,
            @RequestBody RiderReview review) {

        return riderReviewService.addRiderReview(driverId, riderId, review);
    }

    @GetMapping("/getRiderReviewByReviewId")
    RiderReview getRiderReview(@RequestParam int reviewId) {
        return riderReviewService.getReview(reviewId);
    }

    @DeleteMapping("/deleteRiderReviewByReviewId")
    String deleteRiderReview(@RequestParam int reviewId) {
        riderReviewService.deleteReview(reviewId);
        return "You have deleted rider review " + reviewId;
    }

    @GetMapping("getAllRiderReviewsByRiderId")
    List<RiderReview> getAllRiderReviewsByRiderid(@RequestParam int riderId) {
        return riderReviewService.getAllReviewsByRiderid(riderId);
    }
}

