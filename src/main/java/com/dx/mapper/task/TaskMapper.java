package com.dx.mapper.task;

import com.dx.model.Task.TaskModel;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TaskMapper {

    List<TaskModel> queryTask(TaskModel taskModel);

    void updateTask(TaskModel task);

    void addTask(List<TaskModel> taskModels);
}
