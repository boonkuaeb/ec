package explorecali.services;

import explorecali.domain.TourPackage;
import explorecali.repository.TourPackageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TourPackageService {

    private TourPackageRepository tourPackageRepository;

    @Autowired
    public TourPackageService(TourPackageRepository tourPackageRepository) {
        this.tourPackageRepository = tourPackageRepository;
    }

    public TourPackage createTourPackage(String code, String name) {
        if (tourPackageRepository.existsById(code)) {
            tourPackageRepository.save(new TourPackage(code, name));
        }
        return null;
    }

    public Iterable<TourPackage> findAll()
    {
        return tourPackageRepository.findAll();
    }

    public Long totalTorePackageRepository()
    {
        return tourPackageRepository.count();
    }

}
