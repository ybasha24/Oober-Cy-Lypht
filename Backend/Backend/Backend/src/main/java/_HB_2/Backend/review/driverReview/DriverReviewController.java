package _HB_2.Backend.review.driverReview;

import _HB_2.Backend.driver.Driver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping( "/driverReview")
public class DriverReviewController {

    @Autowired
    DriverReviewService driverReviewService;

    @PostMapping("/postDriverReview")
    DriverReview createDriverReview(
            @RequestParam int driverId, int riderId,
            @RequestBody DriverReview review) {

        return driverReviewService.addDriverReview(driverId, riderId, review);
    }

    @GetMapping("/getDriverReviewByReviewId")
    DriverReview getDriverReview(@RequestParam int reviewId) {
        return driverReviewService.getReview(reviewId);
    }

    @DeleteMapping("/deleteDriverReviewByReviewId")
    String deleteDriverReview(@RequestParam int reviewId) {
        driverReviewService.deleteReview(reviewId);
        return "You have deleted driver review " + reviewId;
    }

    @GetMapping("getAllDriverReviewsByDriverId")
    List<DriverReview> getAllDriverReviewsByDriverid(@RequestParam int driverId) {
        return driverReviewService.getAllReviewsByDriverid(driverId);
    }
}
