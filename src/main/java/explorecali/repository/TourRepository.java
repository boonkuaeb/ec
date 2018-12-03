package explorecali.repository;

import explorecali.domain.Tour;
import org.springframework.data.repository.CrudRepository;

public interface TourRepository extends CrudRepository<Tour, Long> {
}
