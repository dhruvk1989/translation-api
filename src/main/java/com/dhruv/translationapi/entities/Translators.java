package com.dhruv.translationapi.entities;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Translators {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long translatorId;
    private String username;
    private String emailAddress;
    private String priority;

}
