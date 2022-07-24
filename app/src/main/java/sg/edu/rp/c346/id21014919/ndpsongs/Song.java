package sg.edu.rp.c346.id21014919.ndpsongs;

import java.io.Serializable;

public class Song implements Serializable {

    private 	int id;
    private 	String title;
    private 	String singer;
    private 	int year;
    private 	int star;

    public Song(int id, String title, String songContent, int year, int star) {
        this.id = id;
        this.title = this.title;
        this.singer = singer;
        this.year = this.year;
        this.star = this.star;
    }

    public int getId() {  return id;  }

    public String getTitle() { return title; }

    public String getSinger() { return singer; }

    public int getYear() {  return year;  }

    public int getStar() { return star; }

    public void setSinger(String singer) {
        this.singer = singer;
    }

    public void setTitle(String title) {this.title = title;}

    public void setYear(int year) {this.year = year;}

    public void setStar(int star) {this.star = star;}

    @Override
    public String toString() { return " " + getStar(star);  }

    private String getStar(int star) {
        switch(star) {
            case 1: return "*";

            case 2: return "**";

            case 3: return "*";

            case 4: return "**";

            case 5: return "***";

            default: return "";
        }
    }


}
