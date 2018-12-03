package explorecali.repository;

import explorecali.domain.TourRating;
import explorecali.domain.TourRatingPk;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import java.util.List;

@RepositoryRestResource(exported = false)
public interface TourRatingRepository extends CrudRepository<TourRating, TourRatingPk> {

    List<TourRating> findByPkTourId(Long tourId);

    Page<TourRating> findByPkTourId(Long tourId, Pageable pageable);

    TourRating findByPkTourIdAndPkCustomerId(Long tourId, Integer customerId);

    @Query(value = "SELECT AVG (Cast(SCORE as FLOAT)) from TOUR_RATING where TOUR_ID = ?1", nativeQuery = true)
    Double getAverage(Long tourId);
}
