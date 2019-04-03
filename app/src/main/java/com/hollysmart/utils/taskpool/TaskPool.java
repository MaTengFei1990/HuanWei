package com.hollysmart.utils.taskpool;


import java.util.LinkedList;
import java.util.List;

/**
 * Created by cai on 16/4/7
 */
public class TaskPool {

    private List<INetModel> taskQueue = new LinkedList<>();

    public void addTask(INetModel updateModel) {
        taskQueue.add(updateModel);
    }
    public int getTotal() {
        return taskQueue.size();
    }
    public void clear(){
        taskQueue.clear();
    }

    public void execute(final OnNetRequestListener onUpDateListener) {
        if (!taskQueue.isEmpty()) {
            INetModel updateModel = taskQueue.remove(0);
            updateModel.request();
        } else
            onUpDateListener.onSuccess();
    }
}
