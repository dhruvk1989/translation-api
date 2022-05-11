package com.dhruv.translationapi.model.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class RequestModel {
    private String q;
    private String source;
    private String target;
    private String format;

}
