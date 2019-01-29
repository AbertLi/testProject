package one.example.com.myapplication3.sample.main.ui;

/**
 * item data for ui
 */
public class SubjectConverter {

    public static String titles(String[] title) {
        StringBuilder sb = new StringBuilder();
        for (String s : title) {
            sb.append(s).append("/");
        }
        return sb.toString();
    }

    public static String directorsAndCasts(String[] directors, String[] casts) {
        StringBuilder sb = new StringBuilder();
        if (directors != null && directors.length > 0) {
            sb.append("导演：");
            for (String director : directors) {
                sb.append(director).append(" ");
            }
        }
        if (casts != null && casts.length > 0) {
            sb.append("主演：");
            for (String cast : casts) {
                sb.append(cast).append(" ");
            }
        }
        return sb.toString();
    }

    public static String yearAndGenres(String year, String[] genres) {
        StringBuilder sb = new StringBuilder();
        sb.append(year).append("/");
        if (genres != null) {
            for (String genre : genres) {
                sb.append(genre).append(" ");
            }
        }
        return sb.toString();
    }


}