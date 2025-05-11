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
public class Chapter2 {
    // input
    private float workingShaftPower; // from chap 1
    private float chainVelocity; // from chap 1
    private int chainPitch; // 100-130
    private int sprocketTeeth1; // 13-21
    private int sprocketTeeth2; // 30-60
    // output
    private float chainForce;
    private float sprocketRotateSpd;
    private float pitchDiameterSprocket1;
    private float chainRatio;

    private boolean imported;

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
