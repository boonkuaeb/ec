package explorecali.exception;

public class TourRatingNotFoundException extends RuntimeException {

    public TourRatingNotFoundException(String message) {
        super(message);
    }

    public TourRatingNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }
}
