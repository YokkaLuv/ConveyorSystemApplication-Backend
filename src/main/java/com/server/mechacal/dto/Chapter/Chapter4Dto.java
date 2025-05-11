package com.server.mechacal.dto.Chapter;

import com.server.mechacal.entities.Chapter.Chapter4;

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
public class Chapter4Dto {
    //input
    private float torque;
    private int motorSpeed;
    private int motorShaftDiameter; 
    //output
    private float nominalTorque;
    private float minimalShaftDiameter;

    private boolean imported;

    public static Chapter4Dto from(Chapter4 chapter4) {
        if (chapter4 == null) return null;

        return Chapter4Dto.builder()
            .torque(chapter4.getTorque())
            .motorSpeed(chapter4.getMotorSpeed())
            .motorShaftDiameter(chapter4.getMotorShaftDiameter())
            .nominalTorque(chapter4.getNominalTorque())
            .minimalShaftDiameter(chapter4.getMinimalShaftDiameter())
            .imported(chapter4.isImported())
            .build();
    }

    public void calculateOutput()
    {
        this.nominalTorque = torque * 1.5f;
        double value = (16 * torque * 1000) / (Math.PI * 20);
        this.minimalShaftDiameter = (float)Math.cbrt(value);
    }

}
