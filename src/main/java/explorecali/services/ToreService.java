package explorecali.services;

import explorecali.domain.Region;
import explorecali.domain.Tore;
import explorecali.domain.TorePackage;
import explorecali.repository.TorePackageRepository;
import explorecali.repository.ToreRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ToreService {

    private ToreRepository toreRepository;
    private TorePackageRepository torePackageRepository;


    @Autowired
    public ToreService(ToreRepository toreRepository, TorePackageRepository torePackageRepository) {
        this.toreRepository = toreRepository;
        this.torePackageRepository = torePackageRepository;
    }

    public Tore createTore(
            String title,
            String description,
            String blurb,
            Integer price,
            String duration,
            String bullets,
            String keywords,
            String difficulty,
            Region region,
            String torePackageCode
    ) {
        Optional<TorePackage> torePackageOptional = torePackageRepository.findById(torePackageCode);
        if (!torePackageOptional.isPresent()) {
            throw new RuntimeException(("Tore Package does not exists: " + torePackageCode));
        }
        TorePackage torePackage = torePackageOptional.get();

        Tore tore = new Tore();
        tore.setTitle(title);
        tore.setDescription(description);
        tore.setBlurb(blurb);
        tore.setPrice(price);
        tore.setDuration(duration);
        tore.setBullets(bullets);
        tore.setKeywords(keywords);
        tore.setDifficulty(difficulty);
        tore.setRegion(region);
        tore.setTorePackage(torePackage);

        return toreRepository.save(tore);
    }

    public Iterable<Tore> findAll()
    {
        return toreRepository.findAll();
    }

    public long Total()
    {
        return toreRepository.count();
    }
}
