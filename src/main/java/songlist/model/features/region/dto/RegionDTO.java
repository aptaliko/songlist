package songlist.model.features.region.dto;

public class RegionDTO {

    private String id;

    private String name;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public RegionDTO(String id, String name) {
        this.id = id;
        this.name = name;
    }
}
