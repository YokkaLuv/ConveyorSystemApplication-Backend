package com.server.mechacal.entities;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import com.server.mechacal.auth.entities.User;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Document(collection = "WorkSessions")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class WorkSession {
    @Id
    private String id;
    
    private String title;

    private LocalDateTime createdAt;

    @DBRef
    private User creator;

    @DBRef
    private List<User> participants;

    @DBRef
    private DesignInput designInput;
// 
    // @DBRef
    // private List<CalculationResult> calculationResults;
// 
    // @DBRef
    // private List<Material> materials;
// 
    // @DBRef
    // private ChainSpecification chainSpecification;

    private String status; //  IN_PROGRESS, COMPLETED


}
