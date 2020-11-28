package com.projeto.changebookbooks.integration.user.response;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.projeto.changebookbooks.config.Messages;
import com.projeto.changebookbooks.domain.Book;
import lombok.*;
import org.dom4j.tree.AbstractEntity;
import org.hibernate.validator.constraints.br.CPF;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import java.io.Serializable;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "user")
public class User {

    @JsonProperty("user_name")
    private String userName;

    @Id
    private String cpf;

    private String city;

    @Column(unique = true)
    private String email;

    private String phone;

}
