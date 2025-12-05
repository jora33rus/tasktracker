package com.tasktracker.model.service;

import com.tasktracker.model.database.entity.TaskStatus;
import com.tasktracker.model.dto.request.TaskRequest;
import com.tasktracker.model.dto.request.TaskUpdateRequest;
import com.tasktracker.model.dto.response.entity.TaskResponse;
import com.tasktracker.model.dto.response.exception.NotFoundException;

import java.util.List;

public interface TaskService {

    // Получить все задачи текущего пользователя
    List<TaskResponse> getUserTasks(Integer userId);

    // Получить задачу по ID (с проверкой, что задача принадлежит пользователю)
    TaskResponse getTaskById(Integer taskId, Integer userId) throws NotFoundException;

    // Создать задачу
    TaskResponse createTask(TaskRequest request, Integer userId);

    // Обновить задачу
    TaskResponse updateTask(Integer taskId, TaskUpdateRequest request, Integer userId) throws NotFoundException;

    // Изменить статус задачи
    TaskResponse updateTaskStatus(Integer taskId, TaskStatus status, Integer userId) throws NotFoundException;

    // Удалить задачу
    void deleteTask(Integer taskId, Integer userId) throws NotFoundException;

    // АДМИНСКИЕ МЕТОДЫ (добавим позже)
    // List<TaskResponse> getAllTasks();
    // List<TaskResponse> getUserTasksAdmin(Integer userId);
}