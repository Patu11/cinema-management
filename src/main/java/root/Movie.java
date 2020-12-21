package root;

import java.io.Serializable;

//for future use
public class Movie implements Serializable {
    private String title;
    private int ageCategory;
    private int length;
    private String genre;
    private double price;

    public Movie(String title, int ageCategory, int length, String genre, double price) {
        this.title = title;
        this.ageCategory = ageCategory;
        this.length = length;
        this.genre = genre;
        this.price = price;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getAgeCategory() {
        return ageCategory;
    }

    public void setAgeCategory(int ageCategory) {
        this.ageCategory = ageCategory;
    }

    public int getLength() {
        return length;
    }

    public void setLength(int length) {
        this.length = length;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    @Override
    public String toString() {
        return "Movie{" +
                "title='" + title + '\'' +
                ", ageCategory=" + ageCategory +
                ", length=" + length +
                ", genre='" + genre + '\'' +
                ", price=" + price +
                '}';
    }
}

