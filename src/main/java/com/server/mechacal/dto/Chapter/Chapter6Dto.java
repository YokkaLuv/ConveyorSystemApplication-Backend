package com.server.mechacal.dto.Chapter;

import com.server.mechacal.entities.Chapter.Chapter6;

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
public class Chapter6Dto {
    //input
    private float workingShaftPower; 
    private int motorSpeed;
    private float fastRatio;
    private float slowRatio;
    private float minimalShaftDiameter;
    private int sprocketTeeth1;
    private int sprocketTeeth2;
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

    public static Chapter6Dto from(Chapter6 chapter6) {
        if (chapter6 == null) return null;

        return Chapter6Dto.builder()
            .workingShaftPower(chapter6.getWorkingShaftPower())
            .motorSpeed(chapter6.getMotorSpeed())
            .fastRatio(chapter6.getFastRatio())
            .slowRatio(chapter6.getSlowRatio())
            .minimalShaftDiameter(chapter6.getMinimalShaftDiameter())
            .sprocketTeeth1(chapter6.getSprocketTeeth1())
            .sprocketTeeth2(chapter6.getSprocketTeeth2())
            .fastTorque(chapter6.getFastTorque())
            .fastTeeth(chapter6.getFastTeeth())
            .module1(chapter6.getModule1())
            .pitchDiametersprocket1(chapter6.getPitchDiametersprocket1())
            .pitchDiametersprocket2(chapter6.getPitchDiametersprocket2())
            .slowTorque(chapter6.getSlowTorque())
            .slowTeeth(chapter6.getSlowTeeth())
            .module2(chapter6.getModule2())
            .pitchDiametersprocket3(chapter6.getPitchDiametersprocket3())
            .pitchDiametersprocket4(chapter6.getPitchDiametersprocket4())
            .bendingStress(chapter6.getBendingStress())
            .contactStress(chapter6.getContactStress())
            .sharpDiameter(chapter6.getSharpDiameter())
            .sharpTorque(chapter6.getSharpTorque())
            .imported(chapter6.isImported())
            .build();
    }

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
