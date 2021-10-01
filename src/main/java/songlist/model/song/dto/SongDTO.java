package songlist.model.song.dto;

public class SongDTO {

    private String id;
    private String name;
    private String rhythmName;

    public SongDTO(String id, String name, String rhythmName) {
        this.id = id;
        this.name = name;
        this.rhythmName = rhythmName;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getRhythm() {
        return rhythmName;
    }

    public void setRhythm(String rhythm) {
        this.rhythmName = rhythm;
    }
}
