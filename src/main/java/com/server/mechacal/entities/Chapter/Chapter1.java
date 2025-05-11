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
public class Chapter1 {
    //input (can be used in other chapters)
    private float chainForce; // 20000 - 30000
    private float chainVelocity; // 0.4 - 0.6
    private float efficiency;
    private float loadFactor; // 1.1 - 1.5
    private int motorSpeed;
    
    private float workingShaftPower;
    private float essentialPower;
    private float torque;

    public void calculateOutput() {
        if (chainForce != 0f && chainVelocity != 0f) {
            this.workingShaftPower = (chainForce * chainVelocity) / 1000f;
        }

        if (workingShaftPower != 0f && efficiency != 0f && loadFactor != 0f) {
            this.essentialPower = (workingShaftPower / efficiency) * loadFactor;
        }

        if (essentialPower != 0f && motorSpeed != 0f && motorSpeed != 0) {
            this.torque = (essentialPower * 9550f) / motorSpeed;
        }
    }
}
