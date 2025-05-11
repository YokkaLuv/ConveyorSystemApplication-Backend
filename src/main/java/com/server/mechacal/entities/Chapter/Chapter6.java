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
    private float minimalShaftDiameter; // from chap 4
    private int sprocketTeeth1; // from chap 2
    private int sprocketTeeth2; // from chap 2
    // not input and not output also
    @Builder.Default
    private int z1 = 20;
    @Builder.Default
    private int z3 = 25;
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
    private float sharpDiameter;
    private float sharpTorque;

    private boolean imported;

    public void calculateOutput() {
        fastTorque = (workingShaftPower * 9550f) / motorSpeed;

        // Fast stage gear
        fastTeeth = Math.round(fastRatio * z1);
        double module1Raw = Math.cbrt((2 * fastTorque * 1000 * 1.3) / (z1 * 0.3 * Math.pow(600, 2))) * 10;
        module1 = (int) Math.ceil(module1Raw);

        pitchDiametersprocket1 = module1 * z1;
        pitchDiametersprocket2 = module1 * fastTeeth;

        // Contact stress (simplified version)
        contactStress = (float) (190 * Math.sqrt(((2 * fastTorque * 1000) / pitchDiametersprocket1)
                / (0.3 * Math.pow(pitchDiametersprocket1, 2))
                * ((pitchDiametersprocket1 + 1) / pitchDiametersprocket1)));

        // Slow stage
        slowTorque = (float) (fastTorque * pitchDiametersprocket1 * 0.98);
        slowTeeth = Math.round(slowRatio * z3);
        double module2Raw = Math.cbrt((2 * slowTorque * 1000 * 1.3) / (z3 * 0.3 * Math.pow(600, 2))) * 10;
        module2 = (int) Math.ceil(module2Raw);

        pitchDiametersprocket3 = module2 * z3;
        pitchDiametersprocket4 = module2 * slowTeeth;

        // Bending stress
        bendingStress = (float) (((2 * slowTorque * 1000) / pitchDiametersprocket3)
                / (0.3 * pitchDiametersprocket1)) * 2.5f * 1.2f;

        // Sharp torque and diameter
        sharpTorque = (float) (slowTorque * slowRatio * 0.98);
        sharpDiameter = (float) Math.cbrt((16 * sharpTorque * 1000) / (Math.PI * 20));
    }
}
