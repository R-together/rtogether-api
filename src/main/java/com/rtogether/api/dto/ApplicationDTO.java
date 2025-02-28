package com.rtogether.api.dto;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import lombok.Builder;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ApplicationDTO {
    private Long mentee_id;
    private Integer session_id;
    private Integer slot_id;
    private String application_reason;
}
