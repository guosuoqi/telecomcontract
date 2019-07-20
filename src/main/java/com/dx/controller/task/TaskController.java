package com.dx.controller.task;

import com.dx.model.Task.TaskModel;
import com.dx.service.task.TaskServece;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import java.util.List;

@Controller
public class TaskController {

    @Autowired
    private TaskServece taskServece;
    public List<TaskModel> queryTask(TaskModel taskModel){
        return taskServece.queryTask(taskModel);
    }

}
