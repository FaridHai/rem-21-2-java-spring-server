package de.neuefische.springserver.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Order {

    @NotBlank(message="Id not null!")
    private String id;
    @Size(min=1)
    private List<Product> products;

}
