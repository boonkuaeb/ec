package explorecali.repository;

import explorecali.domain.Tour;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface TourRepository extends CrudRepository<Tour, Long> {
    List<Tour> findByTourPackageCode(@Param("code") String code);
}
