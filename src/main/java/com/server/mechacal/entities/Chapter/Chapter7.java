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
    //output
    private int equivalentDynamic;
    private float bearingLifespan;
    private float housingThickness;
    private float boltDiameter;
    private float oilViscosity;

    private boolean imported;
}
