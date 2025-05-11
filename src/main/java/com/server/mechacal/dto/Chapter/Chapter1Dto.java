package com.server.mechacal.dto.Chapter;

import com.server.mechacal.entities.Chapter.Chapter1;

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
public class Chapter1Dto {
    private float chainForce;
    private float chainVelocity;
    private float efficiency;
    private float loadFactor;
    private int motorSpeed;
    // chainForce = 26000, chainVelocity = 0.5, efficiency = 0.9, loadFactor = 1.2, motorSpeed = 1450

    private float workingShaftPower;
    private float essentialPower;
    private float torque;

    public static Chapter1Dto from(Chapter1 chapter1) 
    {
        if (chapter1 == null) return null;
        return Chapter1Dto.builder()
            .chainForce(chapter1.getChainForce())
            .chainVelocity(chapter1.getChainVelocity())
            .efficiency(chapter1.getEfficiency())
            .loadFactor(chapter1.getLoadFactor())
            .motorSpeed(chapter1.getMotorSpeed())
            .workingShaftPower(chapter1.getWorkingShaftPower())
            .essentialPower(chapter1.getEssentialPower())
            .torque(chapter1.getTorque())
            .build();
    }


    public void calculateOutput()
    {
        this.setWorkingShaftPower((float)(this.getChainForce() * this.getChainVelocity()) / 1000);
        this.setEssentialPower((this.getWorkingShaftPower() / this.getEfficiency()) * this.getLoadFactor());
        this.setTorque((this.getEssentialPower() * 9550) / this.getMotorSpeed());
    }
}
