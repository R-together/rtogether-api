package com.rtogether.api.entity;


import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import java.sql.Timestamp;

@Data
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "applications")
public class Application {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "application_id")
    private Long application_id;

    @Column(name = "session_id", nullable = false)
    private Integer session_id;

    @ManyToOne
    @JoinColumn(name = "mentee_id", nullable = false)
    private User mentee;

    @Column(name = "slot_id")
    private Integer slot_id;

    @Column(name = "application_reason", nullable = false)
    private String application_reason;

    @CreationTimestamp
    @Column(name = "created_at", updatable = false)
    private Timestamp created_at;

    @CreationTimestamp
    @Column(name = "updated_at", updatable = false)
    private Timestamp updated_at;

    @JsonProperty("mentee")
    public Long getMenteeId() {
        return mentee != null ? mentee.getUserId() : null;
    }
}