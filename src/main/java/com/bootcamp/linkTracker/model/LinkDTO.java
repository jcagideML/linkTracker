package com.bootcamp.linkTracker.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class LinkDTO {

    private Integer linkId;
    private String url;
    private String state;
    private Integer visites;
    private String pass;

}
