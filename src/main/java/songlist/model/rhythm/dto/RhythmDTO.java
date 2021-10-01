package songlist.model.rhythm.dto;

public class RhythmDTO {

    private String id;

    private String name;

    private String meter;

    public RhythmDTO(String id, String name, String meter) {
        this.id = id;
        this.name = name;
        this.meter = meter;
    }

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

    public String getMeter() {
        return meter;
    }

    public void setMeter(String meter) {
        this.meter = meter;
    }
}
