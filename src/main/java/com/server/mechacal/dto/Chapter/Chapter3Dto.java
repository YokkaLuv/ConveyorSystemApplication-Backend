package com.server.mechacal.dto.Chapter;

import com.server.mechacal.entities.Chapter.Chapter3;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Chapter3Dto {
    //input
    private int motorSpeed;
    private float sprocketRotateSpd;
    private float chainRatio;
    //output
    private float totalRatio; 
    private float fastRatio; 
    private float slowRatio; 

    private boolean imported;

    public static Chapter3Dto from(Chapter3 chapter3) {
        if (chapter3 == null) return null;

        return Chapter3Dto.builder()
            .motorSpeed(chapter3.getMotorSpeed())
            .sprocketRotateSpd(chapter3.getSprocketRotateSpd())
            .chainRatio(chapter3.getChainRatio())
            .totalRatio(chapter3.getTotalRatio())
            .fastRatio(chapter3.getFastRatio())
            .slowRatio(chapter3.getSlowRatio())
            .imported(chapter3.isImported())
            .build();
    }

    public void calculateOutput() {
        if (sprocketRotateSpd != 0 && chainRatio != 0f) {
            this.totalRatio = (float) motorSpeed / (sprocketRotateSpd * chainRatio);
            this.fastRatio = (float) Math.sqrt(totalRatio);
            this.slowRatio = totalRatio / fastRatio;
        }
    }
}
