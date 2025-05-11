package com.server.mechacal.entities;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import com.server.mechacal.entities.Chapter.*;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Document(collection = "DesignInputs")
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class DesignInput {
    @Id
    private String id;
    
    @Field("chap1")
    private Chapter1 chap1;
    
    @Field("chap2")
    private Chapter2 chap2;
    
    @Field("chap3")
    private Chapter3 chap3;
    
    @Field("chap4")
    private Chapter4 chap4;
    
    @Field("chap5")
    private Chapter5 chap5;
    
    @Field("chap6")
    private Chapter6 chap6;
    
    @Field("chap7")
    private Chapter7 chap7;
}
