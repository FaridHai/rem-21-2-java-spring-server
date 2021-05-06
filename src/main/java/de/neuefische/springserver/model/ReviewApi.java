package de.neuefische.springserver.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ReviewApi {

    private String postId;
    private String name;
    private String id;
    private String email;
    private String body;

}
