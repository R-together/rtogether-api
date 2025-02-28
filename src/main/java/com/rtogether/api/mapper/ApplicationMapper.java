package com.rtogether.api.mapper;

import com.rtogether.api.dto.ApplicationDTO;
import com.rtogether.api.entity.Application;
import com.rtogether.api.entity.User;
import org.springframework.stereotype.Component;

@Component
public class ApplicationMapper {

    public Application toEntity(ApplicationDTO dto, User mentee) {
        return Application.builder()
                .session_id(dto.getSession_id())
                .slot_id(dto.getSlot_id())
                .application_reason(dto.getApplication_reason())
                .mentee(mentee)
                .build();
    }
}
