package songlist.model.features.region.dto;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

public class NewRegionDTO {

    @NotNull
    @NotBlank
    private String name;

    private String parentRegionId;

    public String getName() {
        return name;
    }

    public String getParentRegionId() {
        return parentRegionId;
    }
}
