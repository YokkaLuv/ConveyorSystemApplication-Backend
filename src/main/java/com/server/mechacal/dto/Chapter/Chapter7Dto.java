package com.server.mechacal.dto.Chapter;

import com.server.mechacal.entities.Chapter.Chapter7;

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
public class Chapter7Dto {
    //input
    private float workingShaftPower;
    private float slowTorque;
    private float contactStress;
    private int ratedLoad;
    //output
    private int equivalentDynamic;
    private float bearingLifespan;
    private float housingThickness;
    private float boltDiameter;
    private float oilViscosity;

    private boolean imported;

    public static Chapter7Dto from(Chapter7 chapter7) {
        if (chapter7 == null) return null;

        return Chapter7Dto.builder()
            .workingShaftPower(chapter7.getWorkingShaftPower())
            .slowTorque(chapter7.getSlowTorque())
            .contactStress(chapter7.getContactStress())
            .ratedLoad(chapter7.getRatedLoad())
            .equivalentDynamic(chapter7.getEquivalentDynamic())
            .bearingLifespan(chapter7.getBearingLifespan())
            .housingThickness(chapter7.getHousingThickness())
            .boltDiameter(chapter7.getBoltDiameter())
            .oilViscosity(chapter7.getOilViscosity())
            .imported(chapter7.isImported())
            .build();
    }
}
