package com.dhruv.translationapi.model.request;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class RequestModelUser {
    //private String q;
    private String source;
    private String target;
    private String format;
}
