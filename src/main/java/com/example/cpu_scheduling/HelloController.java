package com.example.cpu_scheduling;

import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;

import static com.example.cpu_scheduling.sort.*;

public class HelloController {
    public TextField numbers;

    public TableView<PCB> table;

    private final ObservableList<PCB> data = FXCollections.observableArrayList();
    @FXML
    private TableColumn<PCB,String> nameCol;
    @FXML
    private TableColumn<PCB,Number> arrivalTimeCol;
    @FXML
    private TableColumn<PCB,Number> runTimeCol;
    @FXML
    private TableColumn<PCB,Number> priorityNumCol;
    @FXML
    private TableColumn<PCB,String> statusCol;
    @FXML
    public void newPCB(ActionEvent actionEvent) {
        nameCol.setCellValueFactory(new PropertyValueFactory<PCB, String>("name"));
        arrivalTimeCol.setCellValueFactory(new PropertyValueFactory<PCB, Number>("arrivalTime"));
        runTimeCol.setCellValueFactory(new PropertyValueFactory<PCB, Number>("runTime"));
        priorityNumCol.setCellValueFactory(new PropertyValueFactory<PCB, Number>("priorityNum"));
        statusCol.setCellValueFactory(new PropertyValueFactory<PCB, String>("status"));

        table.setEditable(false);

        int x = 0;
        try {
            x = Integer.valueOf(numbers.getText());
        } catch (Exception e) {
            numbers.setText("");
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText("错误");
            alert.setContentText("您输入的程序数目只能为数字，请重新输入");
            alert.show();
        }
        int finalX = x;
        for (int i = 0; i < finalX; i++) {
            int pcbNumber = 0;
            pcbNumber = i;
            String pcbname = "进程" + pcbNumber;
            data.add(new PCB(pcbname));
        }
        table.setItems(data);
    }
    @FXML
    public void Scheduling(ActionEvent actionEvent) {
        /*
           把优先级最高（优先级数小）的进程排到队首，并且对余下的进程排序，为什么不直接排序呢，这是由于我的排序算法不是很完善，
           它仅在优先级上排序，所以队列队首不一定是最应该running的进程，所以再用一遍getMax
        */
        if (data.size() > 1) {
            getMax(data);
        }
        if (!data.isEmpty()) {
            for (int i = 0; i < data.size(); i++) {
                if (i == 0) {
                    //队首
                    if (data.get(0).getStatus().equals("Ready"))
                        data.get(0).setStatus("Running");
                    else if (data.get(0).getStatus().equals("Suspending"))
                        data.get(0).setStatus("Ready");
                } else {
                    //非队首
                    if (data.get(i).getStatus().equals("Running")) {
                        data.get(i).setStatus("Suspending");
                    }
                }
            }
            //进程运行
            if (data.get(0).getStatus() == "Running")
            {
                data.get(0).run();
            }
        }
        for (PCB q : data) {
            if (q.getRunTime() == 0) {
                q.setStatus("End");
            }
            if (q.getStatus().equals("End")) {
                data.remove(data.get(0));
                break;
            }
        }
    }
}