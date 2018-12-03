package explorecali.web;

import explorecali.domain.Tour;
import explorecali.domain.TourRating;
import explorecali.dto.request.RatingDto;
import explorecali.dto.response.RagingAverageResponseDto;
import explorecali.dto.response.RatingResponseDto;
import explorecali.services.TourRatingService;
import explorecali.services.TourService;
import org.decimal4j.util.DoubleRounder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
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
        tourRatingService.save(tour, rating.getScore(), rating.getComment(), rating.getCustomerId());
    }

    @PutMapping
    public RatingResponseDto updateTourRating(@PathVariable(value = "tourId") Long tourId, @RequestBody @Validated RatingDto ratingDto) {
        Tour tour = tourService.verify(tourId);
        TourRating tourRating = tourRatingService.update(tour, ratingDto.getScore(), ratingDto.getComment(), ratingDto.getCustomerId());
        return toDto(tourRating);
    }

    @PatchMapping
    public RatingResponseDto updatePathcTourRating(@PathVariable(value = "tourId") Long tourId, @RequestBody @Validated RatingDto ratingDto) {
        Tour tour = tourService.verify(tourId);
        TourRating tourRating = tourRatingService.updatePatch(tour, ratingDto);
        return toDto(tourRating);
    }

    @GetMapping
    public List<RatingResponseDto> getTourRating(@PathVariable(name = "tourId") Long tourId) {
        Tour tour = tourService.verify(tourId);
        return tourRatingService.getRatings(tourId)
                .stream()
                .map(this::toDto)
                .collect(Collectors.toList());
    }

    @GetMapping("/page")
    public Page<RatingResponseDto> getTourRating(@PathVariable(name = "tourId") Long tourId, Pageable pageable) {
        Tour tour = tourService.verify(tourId);
        Page<TourRating> tourRatingPage = tourRatingService.getRatings(tourId, pageable);

        List<RatingResponseDto> ratingDtoList = tourRatingPage.getContent()
                .stream()
                .map(this::toDto)
                .collect(Collectors.toList());

        return new PageImpl<RatingResponseDto>(ratingDtoList, pageable, tourRatingPage.getTotalPages());
    }

    @GetMapping(path = "/average")
    public RagingAverageResponseDto getTourAverage(@PathVariable(name = "tourId") Long tourId) {
        Tour tour = tourService.verify(tourId);
        Double avg = tourRatingService.getAverage(tourId);
        return new RagingAverageResponseDto((avg != null) ? DoubleRounder.round(avg, 2) : 0);
    }

    private RatingResponseDto toDto(TourRating rating) {
        return new RatingResponseDto(
                rating.getScore(),
                rating.getComment(),
                rating.getPk().getCustomerId()
        );
    }

}
