package explorecali.services;

import explorecali.domain.Tour;
import explorecali.domain.TourRating;
import explorecali.domain.TourRatingPk;
import explorecali.dto.request.RatingDto;
import explorecali.dto.response.RagingAverateDto;
import explorecali.repository.TourRatingRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TourRatingService {

    private TourRatingRepository tourRatingRepository;

    public TourRatingService(TourRatingRepository tourRatingRepository) {
        this.tourRatingRepository = tourRatingRepository;
    }

    public void save(Tour tour, Integer score, String comment,Integer customerId) {
        TourRatingPk tourRatingPk = new TourRatingPk(tour, customerId);
        TourRating tourRating = new TourRating(tourRatingPk, score, comment);
        tourRatingRepository.save(tourRating);
    }

    public List<TourRating> getRatings(Long tourId){
        return tourRatingRepository.findByPkTourId(tourId);
    }

    public Long getAverage(Long tourId)
    {
        return tourRatingRepository.getAverage(tourId);

    }
}
