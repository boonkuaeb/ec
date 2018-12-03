package explorecali.services;

import explorecali.domain.Tour;
import explorecali.domain.TourRating;
import explorecali.domain.TourRatingPk;
import explorecali.dto.request.RatingDto;
import explorecali.exception.TourRatingNotFoundException;
import explorecali.repository.TourRatingRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TourRatingService {

    private TourRatingRepository tourRatingRepository;

    public TourRatingService(TourRatingRepository tourRatingRepository) {
        this.tourRatingRepository = tourRatingRepository;
    }

    public void save(Tour tour, Integer score, String comment, Integer customerId) {
        TourRatingPk tourRatingPk = new TourRatingPk(tour, customerId);
        TourRating tourRating = new TourRating(tourRatingPk, score, comment);
        tourRatingRepository.save(tourRating);
    }

    public TourRating update(Tour tour, Integer score, String comment, Integer customerId) {
        TourRating tourRating = this.verifyRating(tour.getId(), customerId);
        TourRatingPk tourRatingPk = new TourRatingPk(tour, customerId);
        tourRating.setScore(score);
        tourRating.setComment(comment);
        tourRating.setPk(tourRatingPk);
        tourRatingRepository.save(tourRating);
        return  tourRating;
    }

    public TourRating updatePatch(Tour tour, RatingDto ratingDto) {
        TourRating tourRating = this.verifyRating(tour.getId(), ratingDto.getCustomerId());
        TourRatingPk tourRatingPk = new TourRatingPk(tour, ratingDto.getCustomerId());
        if (ratingDto.getComment()!=null)
        {
            tourRating.setComment(ratingDto.getComment());
        }
        if(ratingDto.getScore() != null)
        {
            tourRating.setScore(ratingDto.getScore());

        }

        tourRating.setPk(tourRatingPk);
        tourRatingRepository.save(tourRating);
        return  tourRating;
    }

    public List<TourRating> getRatings(Long tourId) {
        return tourRatingRepository.findByPkTourId(tourId);
    }

    public Page<TourRating> getRatings(Long tourId, Pageable pageable)
    {
        return tourRatingRepository.findByPkTourId(tourId, pageable);
    }

    public Double getAverage(Long tourId) {
        return tourRatingRepository.getAverage(tourId);
    }

    public TourRating verifyRating(Long tourId, Integer customerId) {
        TourRating tourRating = tourRatingRepository.findByPkTourIdAndPkCustomerId(tourId, customerId);
        if (tourRating != null) {
            return tourRating;
        }
        throw new TourRatingNotFoundException("Rating not found");
    }
}
