package com.server.mechacal.dto;

import com.server.mechacal.dto.Chapter.*;
import com.server.mechacal.entities.DesignInput;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class DesignInputDto {
    private String id;
    private Chapter1Dto chap1;
    private Chapter2Dto chap2;
    private Chapter3Dto chap3;
    private Chapter4Dto chap4;
    private Chapter5Dto chap5;
    private Chapter6Dto chap6;
    private Chapter7Dto chap7;

    public void update(DesignInput designInput) {
    if (designInput.getId() != null) id = designInput.getId();
    
    if (designInput.getChap1() != null) chap1 = Chapter1Dto.from(designInput.getChap1());
    if (designInput.getChap2() != null) chap2 = Chapter2Dto.from(designInput.getChap2());
    if (designInput.getChap3() != null) chap3 = Chapter3Dto.from(designInput.getChap3());
    if (designInput.getChap4() != null) chap4 = Chapter4Dto.from(designInput.getChap4());
    if (designInput.getChap5() != null) chap5 = Chapter5Dto.from(designInput.getChap5());
    if (designInput.getChap6() != null) chap6 = Chapter6Dto.from(designInput.getChap6());
    if (designInput.getChap7() != null) chap7 = Chapter7Dto.from(designInput.getChap7());
    }
}
