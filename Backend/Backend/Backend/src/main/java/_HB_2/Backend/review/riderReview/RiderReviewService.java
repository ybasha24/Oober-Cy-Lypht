package _HB_2.Backend.review.riderReview;

import _HB_2.Backend.driver.DriverRepository;
import _HB_2.Backend.rider.RiderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RiderReviewService {

    @Autowired
    RiderReviewRepository riderReviewRepository;

    @Autowired
    DriverRepository driverRepository;

    @Autowired
    RiderRepository riderRepository;

    public RiderReview addRiderReview(int driverId, int riderId, RiderReview review) {

        review.setReviewRider(riderRepository.findById(riderId));
        review.setReviewer(driverId);
        riderReviewRepository.save(review);

        return review;
    }

    public RiderReview getReview(int reviewId) {
        return riderReviewRepository.findById(reviewId);
    }

    public void deleteReview(int reviewId) {
        riderReviewRepository.deleteById(reviewId);
    }
}

