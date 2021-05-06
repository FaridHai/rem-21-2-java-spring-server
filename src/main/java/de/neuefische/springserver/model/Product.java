package de.neuefische.springserver.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Product {

    @NotBlank(message="Name not null!")
    @Size(min=2, message="Name must be have atleast 2 Characters!")
    private String name;
    @NotBlank(message="Id not null!")
    private String id;

}
