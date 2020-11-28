package com.projeto.changebookbooks.domain;

import com.projeto.changebookbooks.config.Messages;
import com.projeto.changebookbooks.integration.user.response.User;
import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Document
public class Book {

    @Transient
    public static final String SEQUENCE_NAME = "users_sequence";

    @Id
    private String id;

    @NotBlank(message = Messages.NAME_IS_REQUIRED)
    private String name;

    @NotBlank(message = Messages.AUTHOR_NAME_IS_REQUIRED)
    private String authorName;

    @NotBlank(message = Messages.EDITOR_NAME_IS_REQUIRED)
    private String editorName;

    @NotBlank(message = Messages.DESCRIPTION_IS_REQUIRED)
    private String description;

    @NotBlank(message = Messages.CATEGORY_IS_REQUIRED)
    private String category;

    @NotNull(message = Messages.IS_FOR_TRADE_IS_REQUIRED)
    private Boolean isForTrade;

    @NotBlank(message = Messages.TRADE_DESCRIPTION_IS_REQUIRED)
    private String tradeDescription;

    @NotNull(message = Messages.IS_FOR_SELL_IS_REQUIRED)
    private Boolean isForSell;

    @NotNull(message = Messages.IMAGE_IS_REQUIRED)
    private String image;

    @DBRef(db = "user")
    private User user;
}
