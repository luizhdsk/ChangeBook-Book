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
@ToString
public class User {
    
    @Id
    private String cpf;

    @JsonProperty("user_name")
    private String userName;

    private String city;

    @Indexed(unique = true)
    private String email;

    private String phone;

}
