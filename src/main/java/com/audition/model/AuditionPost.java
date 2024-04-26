package com.audition.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Data
@AllArgsConstructor @NoArgsConstructor
public class AuditionPost {
    private int userId;
    private int id;
    private String title;
    private String body;


}
