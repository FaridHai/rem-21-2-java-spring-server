package de.neuefische.springserver.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.Objects;

public class Review {
    @NotBlank(message = "enter a review id")
    @JsonProperty("id")
    private String reviewId;
    @NotBlank(message = "enter a product id")
    @JsonProperty("postId")
    private String productId;
    private Product product;
    @NotBlank(message = "enter a title")
    @JsonProperty("name")
    private String title;
    private String email;
    @NotBlank(message = "write your review!!!!")
    @Size(min=10, message = "Your review is not long enough!")
    @JsonProperty("body")
    private String content;

    public String getReviewId() {
        return reviewId;
    }

    public void setReviewId(String reviewId) {
        this.reviewId = reviewId;
    }

    public String getProductId() {
        return productId;
    }

    public void setProductId(String productId) {
        this.productId = productId;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Review review = (Review) o;
        return Objects.equals(reviewId, review.reviewId) && Objects.equals(productId, review.productId) && Objects.equals(product, review.product) && Objects.equals(title, review.title) && Objects.equals(email, review.email) && Objects.equals(content, review.content);
    }

    @Override
    public int hashCode() {
        return Objects.hash(reviewId, productId, product, title, email, content);
    }

    @Override
    public String toString() {
        return "Review{" +
                "reviewId='" + reviewId + '\'' +
                ", productId='" + productId + '\'' +
                ", product=" + product +
                ", title='" + title + '\'' +
                ", email='" + email + '\'' +
                ", content='" + content + '\'' +
                '}';
    }
}
