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
public class Chapter6 {
    //input
    private float workingShaftPower; // from chap 1
    private int motorSpeed; // from chap 1
    private float fastRatio; // from chap 3
    private float slowRatio; // from chap 3
    private int minimalShaftDiameter; // from chap 4
    private int sprocketTeeth1; // from chap 2
    private int sprocketTeeth2; // from chap 2
    private int z1;
    private int z3;
    //output
    private float fastTorque;
    private int fastTeeth;
    private int module1;
    private float pitchDiametersprocket1;
    private float pitchDiametersprocket2;
    private float slowTorque;
    private int slowTeeth;
    private int module2;
    private float pitchDiametersprocket3;
    private float pitchDiametersprocket4;
    private float bendingStress;
    private float contactStress;
    private float outputTorque;

    private boolean imported;

    // public void calculateOutput() {
    //     // z1 = 20, z3 = 25
    //     // fastTorque = (workingShaftPower * 9550)/motorSpeed 
    //     // fastTeeth = fastRatio * z1
    //     // module1 = cbrt((2*fastTorque*1000*1.3)/(z1*0.3*600^2))
    //     // 
    // }
}
