package com.server.mechacal.entities.Chapter;

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
public class Chapter3 {
    //input
    private int motorSpeed; // from chap 1
    private float sprocketRotateSpd; // from chap 2
    private float chainRatio; // from chap 2
    //output
    private float totalRatio; 
    private float fastRatio; 
    private float slowRatio; 

    private boolean imported;

    public void calculateOutput() {
        if (sprocketRotateSpd != 0 && chainRatio != 0f) {
            this.totalRatio = (float) motorSpeed / (sprocketRotateSpd * chainRatio);
            this.fastRatio = (float) Math.sqrt(totalRatio);
            this.slowRatio = totalRatio / fastRatio;
        }
    }

}
