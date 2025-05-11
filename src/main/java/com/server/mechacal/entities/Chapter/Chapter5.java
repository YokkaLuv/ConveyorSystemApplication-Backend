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
public class Chapter5 {
    //input
    private float chainForce; // from chap 1
    private float chainVelocity; // from chap 1
    private int serviceLife; // 6-10
    //output
    private float conveyorForce;
    private float conveyorPower; // 6-10
    private int lifespan; 

    private boolean imported;

    public void calculateOutput() {
        this.conveyorForce = chainForce;
        this.conveyorPower = ((conveyorForce * chainVelocity) / 1000);
        this.lifespan = serviceLife * 8760;
    }
}
