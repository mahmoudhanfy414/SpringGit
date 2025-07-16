package com.spring.wev.dto;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class ExceptionDto {
    private String arMessage;
    private String enMessage;

    public ExceptionDto(String arMessage,String enMessage) {
        this.arMessage=arMessage;
        this.enMessage=enMessage;
    }

}
