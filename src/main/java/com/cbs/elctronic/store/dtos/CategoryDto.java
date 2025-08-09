package com.cbs.elctronic.store.dtos;

import jakarta.persistence.Column;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CategoryDto {

    private String categoryId;

    @NotBlank
    @Size(min = 4,message = "Title must be minimum of 4 characters")
    private String title;

    @NotBlank(message = "Description required!!")
    private String description;

    private String coverImage;

}
