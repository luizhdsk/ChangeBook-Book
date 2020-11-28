package com.projeto.changebookbooks.integration.user.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.TypeAlias;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Document
@TypeAlias("user")
public class User {

    @JsonProperty("user_name")
    private String userName;

    @Id
    private String cpf;

    private String city;

    @Indexed(unique = true)
    private String email;

    private String phone;

}
