package com.dx.service.task;

import com.dx.model.Task.TaskModel;

import java.util.List;

public interface TaskServece {

    List<TaskModel> queryTask(TaskModel taskModel);
}
