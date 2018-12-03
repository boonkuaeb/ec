package explorecali.domain;

import java.util.Arrays;
import java.util.Optional;

public enum Region {
    CENTRAL_COAST("Central Coast"),
    SOUTHERN_CALIFORNIA("Southern California"),
    NORTHERN_CALIFORNIA("Northern California"),
    VARIES("Varies");

    private final String label;

    Region(String label) {
        this.label = label;
    }

    public static Optional<Region> findByLabel(String byLabel) {
        return Arrays.stream(values())
                .filter(Region -> Region.label.equals(byLabel))
                .findFirst();
    }
}
