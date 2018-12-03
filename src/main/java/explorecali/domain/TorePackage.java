package explorecali.domain;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.io.Serializable;

@Entity
@Data
public class TorePackage implements Serializable {
    @Id
    @GeneratedValue
    private Long code;

    private String name;

    public TorePackage() {
    }
}
