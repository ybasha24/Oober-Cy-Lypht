package _HB_2.Backend.review.driverReview;

import _HB_2.Backend.driver.DriverRepository;
import _HB_2.Backend.rider.RiderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DriverReviewService {

    @Autowired
    DriverReviewRepository driverReviewRepository;

    @Autowired
    DriverRepository driverRepository;

    @Autowired
    RiderRepository riderRepository;

    public DriverReview addDriverReview(int driverId, int riderId, DriverReview review) {

        review.setReviewDriver(driverRepository.findById(driverId));
        review.setReviewer(riderId);
        driverReviewRepository.save(review);

        return review;
    }

    public DriverReview getReview(int reviewId) {
        return driverReviewRepository.findById(reviewId);
    }
}
