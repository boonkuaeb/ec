package explorecali.repository;

import explorecali.domain.TourRating;
import explorecali.domain.TourRatingPk;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import java.util.List;

@RepositoryRestResource(exported = false)
public interface TourRatingRepository extends CrudRepository<TourRating, TourRatingPk> {

    List<TourRating> findByPkTourId(Long tourId);

    TourRating findByPkTourIdAndPkCustomerId(Long tourId, Integer customerId);

    @Query(value = "SELECT AVG (SCORE) from TOUR_RATING where TOUR_ID = ?1", nativeQuery = true)
    Long getAverage(Long tourId);
}
