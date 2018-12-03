package explorecali.web;

import explorecali.domain.Tour;
import explorecali.domain.TourRating;
import explorecali.dto.request.RatingDto;
import explorecali.services.TourRatingService;
import explorecali.services.TourService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("tours/{tourId}/ratings")
public class TourRatingController {

    private TourRatingService tourRatingService;
    private TourService tourService;

    @Autowired
    public TourRatingController(TourRatingService tourRatingService, TourService tourService) {
        this.tourRatingService = tourRatingService;
        this.tourService = tourService;
    }


    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void createTourRating(@PathVariable(value = "tourId") Long tourId, @RequestBody @Validated RatingDto rating) {
        Tour tour = tourService.verify(tourId);
        tourRatingService.save(tour, rating);
    }

    @GetMapping
    public List<RatingDto> getTourRating(@PathVariable(name = "tourId") Long tourId) {
        Tour tour = tourService.verify(tourId);
        List<TourRating> tourRatings = tourRatingService.getRatings(tourId);
        return tourRatings.stream()
                .map(this::toDto)
                .collect(Collectors.toList());
    }

    private RatingDto toDto(TourRating rating) {
        return new RatingDto(
                rating.getScore(),
                rating.getComment(),
                rating.getPk().getCustomerId()
        );
    }

}
