package explorecali.web;

import explorecali.dto.response.ErrorDto;
import explorecali.exception.TourNotFoundException;
import explorecali.exception.TourPackageNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class BaseController {

    @ExceptionHandler(TourNotFoundException.class)
    public ResponseEntity<ErrorDto> handleTourNotFoundException(TourNotFoundException e) {
        return new ResponseEntity<ErrorDto>(new ErrorDto(e.getMessage(), HttpStatus.NOT_FOUND.value()), HttpStatus.NOT_FOUND);
    }


    @ExceptionHandler(TourPackageNotFoundException.class)
    public ResponseEntity<ErrorDto> handleTourPackageNotFoundException(TourPackageNotFoundException e) {
        return new ResponseEntity<ErrorDto>(new ErrorDto(e.getMessage(), HttpStatus.NOT_FOUND.value()), HttpStatus.NOT_FOUND);
    }
}
