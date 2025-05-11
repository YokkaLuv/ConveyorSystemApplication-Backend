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
    private int minimalShaftDiameter;
    private int sprocketTeeth1;
    private int sprocketTeeth2;
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
            .outputTorque(chapter6.getOutputTorque())
            .imported(chapter6.isImported())
            .build();
    }
}
