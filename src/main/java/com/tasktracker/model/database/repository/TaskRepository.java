package com.tasktracker.model.database.repository;

import com.tasktracker.model.database.entity.Task;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TaskRepository extends JpaRepository<Task, Integer> {

    // Задачи конкретного пользователя
    List<Task> findByUserId(Integer userId);

    // Задачи в конкретной группе
    List<Task> findByGroupId(Integer groupId);

    // Все задачи в системе (для админа) - сортировка по дате создания
    List<Task> findAllByOrderByCreatedAtDesc();

    // Задачи конкретного пользователя (для админа)
    List<Task> findByUserIdOrderByCreatedAtDesc(Integer userId);

    // JPQL запрос для статистики по статусам (ТРЕБОВАНИЕ ТЗ)
    @Query("SELECT t.status, COUNT(t) FROM Task t GROUP BY t.status")
    List<Object[]> countTasksByStatus();

    // JPQL запрос для статистики по группам (ТРЕБОВАНИЕ ТЗ)
    @Query("SELECT t.group.name, COUNT(t) FROM Task t WHERE t.group IS NOT NULL GROUP BY t.group.name")
    List<Object[]> countTasksByGroup();
}