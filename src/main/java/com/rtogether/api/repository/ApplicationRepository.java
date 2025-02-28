package com.rtogether.api.repository;

import com.rtogether.api.entity.Application;
import com.rtogether.api.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ApplicationRepository extends JpaRepository<Application, Long> {
    List<Application> findByMentee(User mentee);
}
