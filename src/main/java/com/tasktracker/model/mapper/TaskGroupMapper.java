package com.tasktracker.model.mapper;

import com.tasktracker.model.database.entity.TaskGroup;
import com.tasktracker.model.dto.response.entity.TaskGroupResponse;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class TaskGroupMapper {

    public TaskGroupResponse asResponse(TaskGroup group) {
        return new TaskGroupResponse(
                group.getId(),
                group.getCreatedAt(),
                group.getName(),
                group.getUser().getId(),
                group.getUser().getFirstName() + " " + group.getUser().getLastName(),
                group.getTasks() != null ? group.getTasks().size() : 0
        );
    }

    public List<TaskGroupResponse> asListResponse(List<TaskGroup> groups) {
        List<TaskGroupResponse> response = new ArrayList<>();
        for (TaskGroup group : groups) {
            response.add(asResponse(group));
        }
        return response;
    }
}