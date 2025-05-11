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
public class Chapter7 {
    //input
    private float workingShaftPower; // from chap 1
    private float slowTorque; // from chap 6
    private float contactStress; // from chap 6
    private int ratedLoad; // 20000-50000
    private float pitchDiametersprocket3; // from chap 6
    
    //output
    private int equivalentDynamic;
    private float bearingLifespan;
    private float housingThickness;
    private float boltDiameter;
    private float oilViscosity;

    private boolean imported;

    public void calculateOutput() {
        if (pitchDiametersprocket3 == 0 || ratedLoad == 0) {
            throw new IllegalArgumentException("Invalid input: pitch diameter or rated load cannot be zero.");
        }

        equivalentDynamic = Math.round((slowTorque * 1000) / pitchDiametersprocket3);
        double lifespan = Math.pow((double) ratedLoad / equivalentDynamic, 3) * 1_000_000 / (60 * 290);
        bearingLifespan = (float) lifespan;
        housingThickness = (float) (0.03 * Math.cbrt(workingShaftPower * 1000));
        boltDiameter = (float) Math.sqrt((4 * 5000.0) / (Math.PI * 100));
        oilViscosity = contactStress * boltDiameter;
    }
}
