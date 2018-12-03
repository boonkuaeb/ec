package explorecali.services;

import explorecali.domain.Region;
import explorecali.domain.Tour;
import explorecali.domain.TourPackage;
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

    public Tour createTore(
            String title,
            String description,
            String blurb,
            Integer price,
            String duration,
            String bullets,
            String keywords,
            String difficulty,
            Region region,
            String tourPackageCode
    ) {
        Optional<TourPackage> tourPackageOptional = tourPackageRepository.findById(tourPackageCode);
        if (!tourPackageOptional.isPresent()) {
            throw new RuntimeException(("Tour Package does not exists: " + tourPackageCode));
        }
        TourPackage tourPackage = tourPackageOptional.get();

        Tour tour = new Tour();
        tour.setTitle(title);
        tour.setDescription(description);
        tour.setBlurb(blurb);
        tour.setPrice(price);
        tour.setDuration(duration);
        tour.setBullets(bullets);
        tour.setKeywords(keywords);
        tour.setDifficulty(difficulty);
        tour.setRegion(region);
        tour.setTourPackage(tourPackage);

        return tourRepository.save(tour);
    }

    public Iterable<Tour> findAll()
    {
        return tourRepository.findAll();
    }

    public long Total()
    {
        return tourRepository.count();
    }
}
