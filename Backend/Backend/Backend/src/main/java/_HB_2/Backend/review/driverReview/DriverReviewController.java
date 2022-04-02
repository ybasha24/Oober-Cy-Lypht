package _HB_2.Backend.review.driverReview;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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

    @GetMapping("/getDriverReview")
    DriverReview getDriverReview(@RequestParam int reviewId) {
        return driverReviewService.getReview(reviewId);
    }
}
