package explorecali.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Tour {
    @Id
    @GeneratedValue
    private Long id;

    private String title;
    private String description;
    private String blurb;
    private Integer price;
    private String duration;
    private String bullets;
    private String keywords;
    private String difficulty;
    private Region region;

    @ManyToOne
    private TourPackage tourPackage;
}
