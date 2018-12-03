package explorecali.web;

import explorecali.domain.Tour;
import explorecali.dto.RatingDto;
import explorecali.services.TourRatingService;
import explorecali.services.TourService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

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
    public void createTourRating(@PathVariable(value = "tourId") Long tourId, @RequestBody @Validated RatingDto rating)
    {
        Tour tour = tourService.verify(tourId);
        tourRatingService.save(tour, rating);
    }


}
