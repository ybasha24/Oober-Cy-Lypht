package _HB_2.Backend.review.driverReview;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping( "/review")
public class DriverReviewController {

    @Autowired
    DriverReviewService driverReviewService;

    @PostMapping("/driverReview")
    DriverReview createDriverReview(
            @RequestParam int driverId, int riderId,
            @RequestBody DriverReview review) {

        return driverReviewService.addDriverReview(driverId, riderId, review);
    }
}
