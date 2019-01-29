package one.example.com.myapplication3.sample.main.webservice;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * webservice所使用的数据结构，根据数据类型添加对应后缀
 * 如TopMovieJson, TopMoviePb
 */
public class TopMovieJson {

    @SerializedName("count")
    @Expose
    private Integer count;
    @SerializedName("start")
    @Expose
    private Integer start;
    @SerializedName("total")
    @Expose
    private Integer total;
    @SerializedName("subjects")
    @Expose
    private List<Subject> subjects = null;
    @SerializedName("title")
    @Expose
    private String title;

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }

    public Integer getStart() {
        return start;
    }

    public void setStart(Integer start) {
        this.start = start;
    }

    public Integer getTotal() {
        return total;
    }

    public void setTotal(Integer total) {
        this.total = total;
    }

    public List<Subject> getSubjects() {
        return subjects;
    }

    public void setSubjects(List<Subject> subjects) {
        this.subjects = subjects;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public static class Subject {

        @SerializedName("rating")
        @Expose
        private Rating rating;
        @SerializedName("genres")
        @Expose
        private List<String> genres = null;
        @SerializedName("title")
        @Expose
        private String title;
        @SerializedName("casts")
        @Expose
        private List<Cast> casts = null;
        @SerializedName("collect_count")
        @Expose
        private Integer collectCount;
        @SerializedName("original_title")
        @Expose
        private String originalTitle;
        @SerializedName("subtype")
        @Expose
        private String subtype;
        @SerializedName("directors")
        @Expose
        private List<Director> directors = null;
        @SerializedName("year")
        @Expose
        private String year;
        @SerializedName("images")
        @Expose
        private Images images;
        @SerializedName("alt")
        @Expose
        private String alt;
        @SerializedName("id")
        @Expose
        private String id;

        public Rating getRating() {
            return rating;
        }

        public void setRating(Rating rating) {
            this.rating = rating;
        }

        public List<String> getGenres() {
            return genres;
        }

        public void setGenres(List<String> genres) {
            this.genres = genres;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public List<Cast> getCasts() {
            return casts;
        }

        public void setCasts(List<Cast> casts) {
            this.casts = casts;
        }

        public Integer getCollectCount() {
            return collectCount;
        }

        public void setCollectCount(Integer collectCount) {
            this.collectCount = collectCount;
        }

        public String getOriginalTitle() {
            return originalTitle;
        }

        public void setOriginalTitle(String originalTitle) {
            this.originalTitle = originalTitle;
        }

        public String getSubtype() {
            return subtype;
        }

        public void setSubtype(String subtype) {
            this.subtype = subtype;
        }

        public List<Director> getDirectors() {
            return directors;
        }

        public void setDirectors(List<Director> directors) {
            this.directors = directors;
        }

        public String getYear() {
            return year;
        }

        public void setYear(String year) {
            this.year = year;
        }

        public Images getImages() {
            return images;
        }

        public void setImages(Images images) {
            this.images = images;
        }

        public String getAlt() {
            return alt;
        }

        public void setAlt(String alt) {
            this.alt = alt;
        }

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

    }

    public static class Images {

        @SerializedName("small")
        @Expose
        private String small;
        @SerializedName("large")
        @Expose
        private String large;
        @SerializedName("medium")
        @Expose
        private String medium;

        public String getSmall() {
            return small;
        }

        public void setSmall(String small) {
            this.small = small;
        }

        public String getLarge() {
            return large;
        }

        public void setLarge(String large) {
            this.large = large;
        }

        public String getMedium() {
            return medium;
        }

        public void setMedium(String medium) {
            this.medium = medium;
        }

    }

    public static class Director {

        //    @SerializedName("alt")
//    @Expose
//    private String alt;
//    @SerializedName("avatars")
//    @Expose
//    private Avatars avatars;
        @SerializedName("name")
        @Expose
        private String name;
        @SerializedName("id")
        @Expose
        private String id;

//    public String getAlt() {
//        return alt;
//    }
//
//    public void setAlt(String alt) {
//        this.alt = alt;
//    }
//
//    public Avatars getAvatars() {
//        return avatars;
//    }
//
//    public void setAvatars(Avatars avatars) {
//        this.avatars = avatars;
//    }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

    }

    public static class Cast {

        //    @SerializedName("alt")
//    @Expose
//    private String alt;
//    @SerializedName("avatars")
//    @Expose
//    private Avatars avatars;
        @SerializedName("name")
        @Expose
        private String name;
        @SerializedName("id")
        @Expose
        private String id;

//    public String getAlt() {
//        return alt;
//    }
//
//    public void setAlt(String alt) {
//        this.alt = alt;
//    }
//
//    public Avatars getAvatars() {
//        return avatars;
//    }
//
//    public void setAvatars(Avatars avatars) {
//        this.avatars = avatars;
//    }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

    }

    public static class Rating {

        @SerializedName("max")
        @Expose
        private Integer max;
        @SerializedName("average")
        @Expose
        private Double average;
        @SerializedName("stars")
        @Expose
        private String stars;
        @SerializedName("min")
        @Expose
        private Integer min;

        public Integer getMax() {
            return max;
        }

        public void setMax(Integer max) {
            this.max = max;
        }

        public Double getAverage() {
            return average;
        }

        public void setAverage(Double average) {
            this.average = average;
        }

        public String getStars() {
            return stars;
        }

        public void setStars(String stars) {
            this.stars = stars;
        }

        public Integer getMin() {
            return min;
        }

        public void setMin(Integer min) {
            this.min = min;
        }

    }
}