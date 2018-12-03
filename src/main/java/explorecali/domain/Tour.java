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
    @Column(length = 2000)
    private String description;
    @Column(length = 2000)
    private String blurb;
    private Integer price;
    private String duration;
    @Column(length = 2000)
    private String bullets;
    private String keywords;
    private String difficulty;
    private Region region;

    @ManyToOne
    private TourPackage tourPackage;
}
