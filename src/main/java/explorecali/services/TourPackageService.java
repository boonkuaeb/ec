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
        if (!tourPackageRepository.existsById(code)) {
            tourPackageRepository.save(new TourPackage(code, name));
        }
        return null;
    }

    public TourPackage findByName(String name)
    {
        TourPackage tourPackage =  tourPackageRepository.findByName(name);
        if (tourPackage != null)
        {
            return tourPackage;
        }
        throw new RuntimeException("Tore Package does not exits:"+ name);
    }

    public Iterable<TourPackage> findAll()
    {
        return tourPackageRepository.findAll();
    }

    public Long total()
    {
        return tourPackageRepository.count();
    }

}
