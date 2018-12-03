package explorecali.services;

import explorecali.domain.Tour;
import explorecali.domain.TourRating;
import explorecali.domain.TourRatingPk;
import explorecali.dto.request.RatingDto;
import explorecali.repository.TourRatingRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TourRatingService {

    private TourRatingRepository tourRatingRepository;

    public TourRatingService(TourRatingRepository tourRatingRepository) {
        this.tourRatingRepository = tourRatingRepository;
    }

    public void save(Tour tour, RatingDto rating) {
        TourRatingPk tourRatingPk = new TourRatingPk(tour, rating.getCustomerId());
        TourRating tourRating = new TourRating(tourRatingPk, rating.getScore(), rating.getComment());
        tourRatingRepository.save(tourRating);
    }

    public List<TourRating> getRatings(Long tourId){
        return tourRatingRepository.findByPkTourId(tourId);
    }
}
