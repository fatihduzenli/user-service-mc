package com.cydeo.client;

import com.cydeo.dto.ProjectResponse;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(value = "project-service")
public interface ProjectClient {

    @GetMapping("/api/v1/project/count/manager/{assignedManager}")
    @CircuitBreaker(name = "project-service")
    ResponseEntity<ProjectResponse> getNonCompletedCountByAssignedManager(@PathVariable("assignedManager") String assignedManager);

}
