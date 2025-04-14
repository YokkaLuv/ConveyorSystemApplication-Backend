package com.server.mechacal.dto;

import com.server.mechacal.entities.WorkSession;
import com.server.mechacal.auth.entities.User;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class WorkSessionDto {
    private String id;

    private String title;

    private LocalDateTime createdAt;

    private String creatorId;

    private List<String> participantIds;

    private String status;

    public void update(WorkSession session) {
        if (session.getId() != null) id = session.getId();
        if (session.getTitle() != null) title = session.getTitle();
        if (session.getCreatedAt() != null) createdAt = session.getCreatedAt();
        if (session.getStatus() != null) status = session.getStatus();

        if (session.getCreator() != null) creatorId = session.getCreator().getUserId();

        if (session.getParticipants() != null) 
        {
            participantIds = new ArrayList<>();
            for (User user : session.getParticipants()) 
            {
                if (user != null && user.getEmail() != null) 
                {
                    participantIds.add(user.getEmail());
                }
            }
        }
    }
}
