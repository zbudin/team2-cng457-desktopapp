package desktopapp;

import java.text.SimpleDateFormat;
import java.util.Comparator;
import java.util.Date;

public class Comment{
    public static SimpleDateFormat df = new SimpleDateFormat("dd/MM/yy-HH:mm");
    public String comment;
    public int rating;
    public long timestamp;

    public Comment(){
        comment = null;
        rating = 0;
        timestamp = 0;
    }

    public Comment(String comment, int rating, long timestamp) {
        this.comment = comment;
        this.rating = rating;
        this.timestamp = timestamp;
    }

    public int getRating() {
        return rating;
    }

    public long getTimestamp() {
        return timestamp;
    }

    public String getComment() {
        return comment;
    }

    @Override
    public String toString(){
        Date date = new Date(timestamp);
        String dateText = df.format(date);
        String temp = "Rating: " + rating +
                "\nComment: " + comment +
                "\nDate-Time: " + dateText;
        return temp;
    }
}