package explorecali.dto.response;

import lombok.Data;

@Data
public class RatingDto {
    private Integer score;
    private String comment;
    private Integer customerId;
}
