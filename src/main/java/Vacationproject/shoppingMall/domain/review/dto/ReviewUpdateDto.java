package Vacationproject.shoppingMall.domain.review.dto;

public class ReviewUpdateDto {
    private int rating;
    private String comment;
    private String title;

    public String getTitle() {
        this.title = title;
        return title;
    }

    public String getComment() {
        this.comment = comment;
        return comment;
    }

    public int getRating() {
        this.rating = rating;
        return rating;
    }
}
