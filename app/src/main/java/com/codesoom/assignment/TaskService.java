package com.codesoom.assignment;

import com.codesoom.exception.TaskNotFoundException;

import java.io.IOException;
import java.util.List;

public class TaskService {

    private final TaskRepository taskRepository = new TaskRepository();

    public String getTasks() throws IOException {
        List<Task> tasks = taskRepository.findAll();
        return JsonParser.objectToJson(tasks);
    }

    /**
     * @param id 조회할 Task의 id이다.
     * @return 조회한 Task를 Json으로 변환한 값이다.
     * @throws TaskNotFoundException 조회할 Task가 존재하지 않는 경우이다.
     */
    public String getTask(Long id) throws IOException {
        Task task = taskRepository.findById(id)
                .orElseThrow(TaskNotFoundException::new);
        return JsonParser.objectToJson(task);
    }

    public String createTask(String body) throws IOException {
        Task task = JsonParser.requestBodyToObject(body, Task.class);
        Task savedTask = taskRepository.save(task);

        return JsonParser.objectToJson(savedTask);
    }

    /**
     * @param id   수정할 Task의 id이다.
     * @param body Task정보로 변환하려는 body이다.
     * @return 수정한 Task를 Json으로 변환한 값이다.
     * @throws TaskNotFoundException 조회할 Task가 존재하지 않는 경우이다.
     */
    public String updateTask(Long id, String body) throws IOException {
        if (taskRepository.isExist(id)) {
            Task task = JsonParser.requestBodyToObject(body, Task.class);
            task.setId(id);

            Task updateTask = taskRepository.update(task);

            return JsonParser.objectToJson(updateTask);
        }
        throw new TaskNotFoundException();
    }

    /**
     * @param id 조회할 Task의 id이다.
     * @throws TaskNotFoundException 조회할 Task가 존재하지 않는 경우이다.
     */
    public void deleteTask(Long id) {
        if (taskRepository.isExist(id)) {
            taskRepository.delete(id);
            return;
        }
        throw new TaskNotFoundException();
    }
}
