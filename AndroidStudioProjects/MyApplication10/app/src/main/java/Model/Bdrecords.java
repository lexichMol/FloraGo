package Model;

public class Bdrecords {
    private  int id;
    private  int plant_id;
    private String image;
    private String time;
    private String title;

    public Bdrecords(){

    }
    public Bdrecords(int plant_id, String image, String time, String title){
        this.plant_id = plant_id;
        this.image = image;
        this.time = time;
        this.title = title;

    }
    public Bdrecords(int id, int plant_id, String image, String time, String title){
        this.id = id;
        this.plant_id = plant_id;
        this.image = image;
        this.time = time;
        this.title = title;

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getPlant_id() {
        return plant_id;
    }

    public void setPlant_id(int plant_id) {
        this.plant_id = plant_id;
    }

    public String getimage() {
        return image;
    }

    public void setimage(String image) {
        this.image = image;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
