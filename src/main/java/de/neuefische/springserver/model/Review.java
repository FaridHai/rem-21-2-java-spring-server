package de.neuefische.springserver.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.Objects;

public class Review {
    private Product product;

    @NotBlank(message = "enter a review id")
    private String id;

    @NotBlank(message = "enter a title")
    private String title;

    private String email;

    @NotBlank(message = "write your review!!!!")
    @Size(min=10, message = "Your review is not long enough!")
    private String content;


    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
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
        return Objects.equals(product, review.product) && Objects.equals(id, review.id) && Objects.equals(title, review.title) && Objects.equals(email, review.email) && Objects.equals(content, review.content);
    }

    @Override
    public int hashCode() {
        return Objects.hash(product, id, title, email, content);
    }

    @Override
    public String toString() {
        return "Review{" +
                "product=" + product +
                ", id='" + id + '\'' +
                ", title='" + title + '\'' +
                ", email='" + email + '\'' +
                ", content='" + content + '\'' +
                '}';
    }
}
