package com.dhruv.translationapi.entities;

import javax.persistence.*;

@Entity
public class Requests {
    @Id
    @Column(name = "request_id", nullable = false)
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long requestId;

    public Long getRequestId() {
        return requestId;
    }

    public void setRequestId(Long requestId) {
        this.requestId = requestId;
    }



}
