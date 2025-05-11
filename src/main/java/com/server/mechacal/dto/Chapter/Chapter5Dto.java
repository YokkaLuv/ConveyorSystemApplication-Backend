package com.server.mechacal.dto.Chapter;

import com.server.mechacal.entities.Chapter.Chapter5;

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
public class Chapter5Dto {
    //input
    private float chainForce;
    private float chainVelocity;
    private int serviceLife;
    //output
    private float conveyorForce;
    private float conveyorPower;
    private int lifespan; 

    private boolean imported;

    public static Chapter5Dto from(Chapter5 chapter5) {
        if (chapter5 == null) return null;

        return Chapter5Dto.builder()
            .chainForce(chapter5.getChainForce())
            .chainVelocity(chapter5.getChainVelocity())
            .serviceLife(chapter5.getServiceLife())
            .conveyorForce(chapter5.getConveyorForce())
            .conveyorPower(chapter5.getConveyorPower())
            .lifespan(chapter5.getLifespan())
            .imported(chapter5.isImported()) // if you're using the imported flag
            .build();
    }

    public void calculateOutput() {
        this.conveyorForce = chainForce;
        this.conveyorPower = ((conveyorForce * chainVelocity) / 1000);
        this.lifespan = serviceLife * 8760;
    }
}
