package com.dx.service.task;

import com.dx.mapper.task.TaskMapper;
import com.dx.model.Task.TaskModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class TaskServeceImpl implements TaskServece {

    @Autowired
    private TaskMapper taskMapper;
    @Override
    public List<TaskModel> queryTask(TaskModel taskModel) {
        return taskMapper.queryTask(taskModel);
    }

    public void updateTask(TaskModel task) {
        taskMapper.updateTask(task);
    }
}
