package explorecali.exception;

public class TourPackageNotFoundException extends RuntimeException {

    public TourPackageNotFoundException(String message) {
        super(message);
    }

    public TourPackageNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }
}
