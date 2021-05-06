package de.neuefische.springserver.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;


@Data
@AllArgsConstructor
@NoArgsConstructor
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
}
