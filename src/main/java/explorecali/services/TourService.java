package explorecali.services;

import explorecali.domain.Difficulty;
import explorecali.domain.Region;
import explorecali.domain.Tour;
import explorecali.domain.TourPackage;
import explorecali.exception.TourNotFoundException;
import explorecali.exception.TourPackageNotFoundException;
import explorecali.repository.TourPackageRepository;
import explorecali.repository.TourRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class TourService {

    private TourRepository tourRepository;
    private TourPackageRepository tourPackageRepository;


    @Autowired
    public TourService(TourRepository tourRepository, TourPackageRepository tourPackageRepository) {
        this.tourRepository = tourRepository;
        this.tourPackageRepository = tourPackageRepository;
    }

    public Tour createTour(
            String title,
            String description,
            String blurb,
            Integer price,
            String duration,
            String bullets,
            String keywords,
            Difficulty difficulty,
            Region region,
            String tourPackageName
    ) {
        TourPackage tourPackage = tourPackageRepository.findByName(tourPackageName);
        if (tourPackage == null) {
            throw new TourPackageNotFoundException(("Tour Package does not exists: " + tourPackageName));
        }
        Tour tour = new Tour();
        tour.setTitle(title);
        tour.setDescription(description);
        tour.setBlurb(blurb);
        tour.setPrice(price);
        tour.setDuration(duration);
        tour.setBullets(bullets);
        tour.setKeywords(keywords);
        tour.setDifficulty(difficulty.toString());
        tour.setRegion(region);
        tour.setTourPackage(tourPackage);

        return tourRepository.save(tour);
    }

    public Iterable<Tour> findAll()
    {
        return tourRepository.findAll();
    }

    public long total()
    {
        return tourRepository.count();
    }

    public Tour verify(Long tourId)
    {
        Optional<Tour> tour = tourRepository.findById(tourId);
        if (!tour.isPresent()) {
            throw new TourNotFoundException("Tour " + tourId + " Not Found");
        }
        return tour.get();
    }

}
