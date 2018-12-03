package explorecali.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RatingResponseDto {
    private Integer score;
    private String comment;
    private Integer customerId;
}
