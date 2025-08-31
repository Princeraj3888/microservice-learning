package com.explorertech.entity;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Book {

    private Long id;
    private String isbnNo;
    private String name;
    private String coverPhotoUrl;
    private byte[] barCode;

}
