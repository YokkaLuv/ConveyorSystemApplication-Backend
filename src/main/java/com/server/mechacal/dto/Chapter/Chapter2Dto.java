package com.server.mechacal.dto.Chapter;

import com.server.mechacal.entities.Chapter.Chapter2;

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
public class Chapter2Dto {
    // input
    private float workingShaftPower;
    private float chainVelocity;
    private int chainPitch;
    private int sprocketTeeth1; 
    private int sprocketTeeth2; 
    // workingShaftPower = 13, chainVelocity = 0.5, chainPitch = 115, sprocketTeeth1 = 13, sprocketTeeth2 = 39
    // output
    private float chainForce;
    private float sprocketRotateSpd;
    private float pitchDiameterSprocket1;
    private float chainRatio;

    private boolean imported;

    public static Chapter2Dto from(Chapter2 chapter2) {
        if (chapter2 == null) return null;

        return Chapter2Dto.builder()
            .workingShaftPower(chapter2.getWorkingShaftPower())
            .chainVelocity(chapter2.getChainVelocity())
            .chainPitch(chapter2.getChainPitch())
            .sprocketTeeth1(chapter2.getSprocketTeeth1())
            .sprocketTeeth2(chapter2.getSprocketTeeth2())
            .chainForce(chapter2.getChainForce())
            .sprocketRotateSpd(chapter2.getSprocketRotateSpd())
            .pitchDiameterSprocket1(chapter2.getPitchDiameterSprocket1())
            .chainRatio(chapter2.getChainRatio())
            .imported(chapter2.isImported())
            .build();
    }

    public void calculateOutput() {
        if (chainVelocity != 0f) {
            this.chainForce = (workingShaftPower * 1000) / chainVelocity;
        }

        if (workingShaftPower != 0f && sprocketTeeth1 != 0) {
            this.sprocketRotateSpd = (chainVelocity * 60 * 1000) / (chainPitch * sprocketTeeth1);
        }
        if (sprocketTeeth1 != 0) {
            double angle = Math.toRadians(180.0 / sprocketTeeth1);  // convert to radians
            double sinAngle = Math.sin(angle);
            if (sinAngle != 0) {
                this.pitchDiameterSprocket1 = (float)(chainPitch / sinAngle);
            }
        }
        if (sprocketTeeth1 != 0) {
            this.chainRatio = (float)sprocketTeeth2 / (float)sprocketTeeth1;
        }
    }
}
