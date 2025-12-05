package com.tasktracker.model.service.impl;

import com.tasktracker.model.database.entity.TaskGroup;
import com.tasktracker.model.database.entity.User;
import com.tasktracker.model.database.repository.TaskGroupRepository;
import com.tasktracker.model.database.repository.UserRepository;
import com.tasktracker.model.dto.request.TaskGroupRequest;
import com.tasktracker.model.dto.response.entity.TaskGroupResponse;
import com.tasktracker.model.dto.response.exception.NotFoundException;
import com.tasktracker.model.mapper.TaskGroupMapper;
import com.tasktracker.model.service.TaskGroupService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class TaskGroupServiceImpl implements TaskGroupService {

    private final TaskGroupRepository taskGroupRepository;
    private final UserRepository userRepository;
    private final TaskGroupMapper taskGroupMapper;

    @Autowired
    public TaskGroupServiceImpl(TaskGroupRepository taskGroupRepository,
                                UserRepository userRepository,
                                TaskGroupMapper taskGroupMapper) {
        this.taskGroupRepository = taskGroupRepository;
        this.userRepository = userRepository;
        this.taskGroupMapper = taskGroupMapper;
    }

    @Override
    @Transactional
    public TaskGroupResponse createGroup(TaskGroupRequest request, Integer userId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));

        TaskGroup group = new TaskGroup(request.name(), user);
        TaskGroup savedGroup = taskGroupRepository.save(group);

        return taskGroupMapper.asResponse(savedGroup);
    }

    @Override
    public List<TaskGroupResponse> getUserGroups(Integer userId) {
        List<TaskGroup> groups = taskGroupRepository.findByUserId(userId);
        return taskGroupMapper.asListResponse(groups);
    }

    @Override
    public TaskGroupResponse getGroupById(Integer groupId, Integer userId) throws NotFoundException {
        TaskGroup group = taskGroupRepository.findById(groupId)
                .orElseThrow(NotFoundException::new);

        if (!group.getUser().getId().equals(userId)) {
            throw new NotFoundException();
        }

        return taskGroupMapper.asResponse(group);
    }

    @Override
    @Transactional
    public TaskGroupResponse updateGroup(Integer groupId, TaskGroupRequest request, Integer userId) throws NotFoundException {
        TaskGroup group = taskGroupRepository.findById(groupId)
                .orElseThrow(NotFoundException::new);

        if (!group.getUser().getId().equals(userId)) {
            throw new NotFoundException();
        }

        group.setName(request.name());
        TaskGroup updatedGroup = taskGroupRepository.save(group);

        return taskGroupMapper.asResponse(updatedGroup);
    }

    @Override
    @Transactional
    public void deleteGroup(Integer groupId, Integer userId) throws NotFoundException {
        TaskGroup group = taskGroupRepository.findById(groupId)
                .orElseThrow(NotFoundException::new);

        if (!group.getUser().getId().equals(userId)) {
            throw new NotFoundException();
        }

        taskGroupRepository.delete(group);
    }
}