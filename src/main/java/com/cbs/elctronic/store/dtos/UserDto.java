package com.cbs.elctronic.store.dtos;

import ch.qos.logback.core.joran.spi.NoAutoStart;
import com.cbs.elctronic.store.entities.Order;
import com.cbs.elctronic.store.validate.ImageNameValid;
import jakarta.persistence.Column;
import jakarta.persistence.Id;
import jakarta.validation.constraints.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserDto {

    private String userId;

    @Size(min = 3,max = 15,message = "Invalid name!")
    private String name;

    @Pattern(regexp = "^[a-z0-9][-a-z0-9]+@([-a-z0-9]+\\.)+[a-z]{2,5}$",message = "Invalid User Email!")
    @Email(message = "Invalid User Email!")
    private String email;

    @NotBlank(message = "Password is required.")
    private String password;

    @Size(min = 4,max = 6,message = "Invalid Gender")
    private String gender;

    @NotBlank(message = "Write something about yourself.")
    private String about;

    @ImageNameValid
    private String imageName;

    private List<OrderDto> orderDtos=new ArrayList<>();
}
