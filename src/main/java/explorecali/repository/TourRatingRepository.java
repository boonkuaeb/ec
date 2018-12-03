package explorecali.repository;

import explorecali.domain.TourRating;
import explorecali.domain.TourRatingPk;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import java.util.List;

@RepositoryRestResource(exported = false)
public interface TourRatingRepository extends CrudRepository<TourRating, TourRatingPk> {

    List<TourRating> findByPkTourId(Integer tourId);

    TourRating findByPkTourIdAndPkCustomerId(Integer tourId, Integer customerId);
}
