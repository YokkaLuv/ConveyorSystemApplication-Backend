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
public class Chapter4 {
    //input
    private float torque; // from chap 1
    private int motorSpeed; // from chap 1
    private int motorShaftDiameter; 
    //output
    private float nominalTorque;
    private float minimalShaftDiameter;

    private boolean imported;

    public void calculateOutput()
    {
        this.nominalTorque = torque * 1.5f;
        double value = (16 * torque * 1000) / (Math.PI * 20);
        this.minimalShaftDiameter = (float)Math.cbrt(value);
    }
}
