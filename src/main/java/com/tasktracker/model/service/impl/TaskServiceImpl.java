package com.tasktracker.model.service.impl;

import com.tasktracker.model.database.entity.Task;
import com.tasktracker.model.database.entity.TaskGroup;
import com.tasktracker.model.database.entity.TaskStatus;
import com.tasktracker.model.database.entity.User;
import com.tasktracker.model.database.repository.TaskGroupRepository;
import com.tasktracker.model.database.repository.TaskRepository;
import com.tasktracker.model.database.repository.UserRepository;
import com.tasktracker.model.dto.request.TaskRequest;
import com.tasktracker.model.dto.request.TaskUpdateRequest;
import com.tasktracker.model.dto.response.entity.TaskResponse;
import com.tasktracker.model.dto.response.exception.NotFoundException;
import com.tasktracker.model.mapper.TaskMapper;
import com.tasktracker.model.service.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class TaskServiceImpl implements TaskService {

    private final TaskRepository taskRepository;
    private final TaskGroupRepository taskGroupRepository;
    private final UserRepository userRepository;
    private final TaskMapper taskMapper;

    @Autowired
    public TaskServiceImpl(TaskRepository taskRepository,
                           TaskGroupRepository taskGroupRepository,
                           UserRepository userRepository,
                           TaskMapper taskMapper) {
        this.taskRepository = taskRepository;
        this.taskGroupRepository = taskGroupRepository;
        this.userRepository = userRepository;
        this.taskMapper = taskMapper;
    }

    @Override
    public List<TaskResponse> getUserTasks(Integer userId) {
        List<Task> tasks = taskRepository.findByUserId(userId);
        return taskMapper.asListResponse(tasks);
    }

    @Override
    public TaskResponse getTaskById(Integer taskId, Integer userId) throws NotFoundException {
        Task task = taskRepository.findById(taskId)
                .orElseThrow(NotFoundException::new);

        // Проверяем, что задача принадлежит пользователю
        if (!task.getUser().getId().equals(userId)) {
            throw new NotFoundException();
        }

        return taskMapper.asResponse(task);
    }

    @Override
    @Transactional
    public TaskResponse createTask(TaskRequest request, Integer userId) {
        try {
            System.out.println("createTask called: userId=" + userId + ", request=" + request);

            User user = userRepository.findById(userId)
                    .orElseThrow(() -> new RuntimeException("User not found with ID: " + userId));
            System.out.println("Found user: " + user.getEmail());

            TaskGroup group = null;

            if (request.groupId() != null && request.groupId() > 0) {
                System.out.println("Looking for group ID: " + request.groupId());
                group = taskGroupRepository.findById(request.groupId())
                        .orElseThrow(() -> new RuntimeException("Group not found with ID: " + request.groupId()));

                // Проверяем, что группа принадлежит пользователю
                if (!group.getUser().getId().equals(userId)) {
                    throw new RuntimeException("Group does not belong to user");
                }
                System.out.println("Found group: " + group.getName());
            } else {
                System.out.println("No group specified or groupId = 0");
            }

            Task task = new Task(
                    request.title(),
                    request.description(),
                    user,
                    group
            );

            System.out.println("Saving task: " + task.getTitle());
            Task savedTask = taskRepository.save(task);
            System.out.println("Task saved with ID: " + savedTask.getId());

            return taskMapper.asResponse(savedTask);
        } catch (Exception e) {
            System.err.println("Error in createTask: " + e.getMessage());
            e.printStackTrace();
            throw e;
        }
    }

    @Override
    @Transactional
    public TaskResponse updateTask(Integer taskId, TaskUpdateRequest request, Integer userId)
            throws NotFoundException {
        Task task = taskRepository.findById(taskId)
                .orElseThrow(NotFoundException::new);

        // Проверяем, что задача принадлежит пользователю
        if (!task.getUser().getId().equals(userId)) {
            throw new NotFoundException();
        }

        TaskGroup group = null;
        if (request.groupId() != null) {
            group = taskGroupRepository.findById(request.groupId())
                    .orElseThrow(() -> new RuntimeException("Group not found"));

            // Проверяем, что группа принадлежит пользователю
            if (!group.getUser().getId().equals(userId)) {
                throw new RuntimeException("Group does not belong to user");
            }
        }

        task.setTitle(request.title());
        task.setDescription(request.description());
        task.setStatus(request.status());
        task.setGroup(group);

        Task updatedTask = taskRepository.save(task);
        return taskMapper.asResponse(updatedTask);
    }

    @Override
    @Transactional
    public TaskResponse updateTaskStatus(Integer taskId, TaskStatus status, Integer userId)
            throws NotFoundException {
        Task task = taskRepository.findById(taskId)
                .orElseThrow(NotFoundException::new);

        // Проверяем, что задача принадлежит пользователю
        if (!task.getUser().getId().equals(userId)) {
            throw new NotFoundException();
        }

        task.setStatus(status);
        Task updatedTask = taskRepository.save(task);
        return taskMapper.asResponse(updatedTask);
    }

    @Override
    @Transactional
    public void deleteTask(Integer taskId, Integer userId) throws NotFoundException {
        Task task = taskRepository.findById(taskId)
                .orElseThrow(NotFoundException::new);

        // Проверяем, что задача принадлежит пользователю
        if (!task.getUser().getId().equals(userId)) {
            throw new NotFoundException();
        }

        taskRepository.delete(task);
    }
}