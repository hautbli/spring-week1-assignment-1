package com.codesoom.assignment;

import com.codesoom.exception.TaskNotFoundException;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

public class TaskRepository {
    private final static Map<Long, Task> tasks = new HashMap<>();
    private static Long id = 1L;

    public List<Task> findAll() {
        return new ArrayList<>(tasks.values());
    }

    public Task save(Task task) {
        task.setId(id++);
        tasks.put(task.getId(), task);
        return task;
    }

    public Task update(Task task) {
        if (task == null) {
            throw new IllegalArgumentException("task가 null입니다.");
        }

        Task findOne = findById(task.getId())
                .orElseThrow(TaskNotFoundException::new);
        findOne.setTitle(task.getTitle());
        return findOne;
    }

    public void delete(Long id) {
        tasks.remove(id);
    }

    public Optional<Task> findById(Long id) {
        if (isExist(id)) {
            return Optional.ofNullable(tasks.get(id));
        }
        return Optional.empty();
    }

    public boolean isExist(Long id) {
        if (id == null) {
            return false;
        }
        return tasks.containsKey(id);
    }
}
