package com.baar.springbootcrud.dto;

import lombok.*;

@Data
@RequiredArgsConstructor
@Getter
@Setter
@ToString
public class PersonDto {
    private Integer id;
    private String firstName;
    private String lastName;
    private String email;
    private String gender;
    private String ipAddress;


}
