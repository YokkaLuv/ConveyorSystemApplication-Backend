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
    private float pitchDiametersprocket3;
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
            .pitchDiametersprocket3(chapter7.getPitchDiametersprocket3())
            .equivalentDynamic(chapter7.getEquivalentDynamic())
            .bearingLifespan(chapter7.getBearingLifespan())
            .housingThickness(chapter7.getHousingThickness())
            .boltDiameter(chapter7.getBoltDiameter())
            .oilViscosity(chapter7.getOilViscosity())
            .imported(chapter7.isImported())
            .build();
    }

    public void calculateOutput() {
        if (pitchDiametersprocket3 == 0 || ratedLoad == 0) {
            throw new IllegalArgumentException("Invalid input: pitch diameter or rated load cannot be zero.");
        }

        equivalentDynamic = Math.round((slowTorque * 1000) / pitchDiametersprocket3);
        double lifespan = Math.pow((double) ratedLoad / equivalentDynamic, 3) * 1_000_000 / (60 * 290);
        bearingLifespan = (float) lifespan;
        housingThickness = (float) (0.03 * Math.cbrt(workingShaftPower * 1000));
        boltDiameter = (float) Math.sqrt((4 * 5000.0) / (Math.PI * 100));
        oilViscosity = contactStress * boltDiameter;
    }
}
