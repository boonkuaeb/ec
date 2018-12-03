package explorecali.services;

import explorecali.domain.TorePackage;
import explorecali.repository.TorePackageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TorePackageService {

    private TorePackageRepository torePackageRepository;

    @Autowired
    public TorePackageService(TorePackageRepository torePackageRepository) {
        this.torePackageRepository = torePackageRepository;
    }

    public TorePackage createTorePackage(String code, String name) {
        if (torePackageRepository.existsById(code)) {
            torePackageRepository.save(new TorePackage(code, name));
        }
        return null;
    }

    public Iterable<TorePackage> findAll()
    {
        return torePackageRepository.findAll();
    }

    public Long totalTorePackageRepository()
    {
        return torePackageRepository.count();
    }

}
