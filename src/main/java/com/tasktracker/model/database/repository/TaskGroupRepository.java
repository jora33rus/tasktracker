package com.tasktracker.model.database.repository;

import com.tasktracker.model.database.entity.TaskGroup;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TaskGroupRepository extends JpaRepository<TaskGroup, Integer> {

    // Группы конкретного пользователя
    List<TaskGroup> findByUserId(Integer userId);

    // Все группы в системе (для админа)
    List<TaskGroup> findAllByOrderByCreatedAtDesc();

    // Проверка, принадлежит ли группа пользователю
    boolean existsByIdAndUserId(Integer id, Integer userId);
}