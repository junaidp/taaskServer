package com.echo.taask.other.helper;

import com.echo.taask.other.dto.CustomerSnapShotDTO;
import com.echo.taask.other.dto.ProjectSnapShotDTO;
import com.echo.taask.other.dto.SnapShotDTO;
import com.echo.taask.other.dto.TaskSnapShotDTO;
import org.springframework.stereotype.Component;

@Component
public class SnapShotHelper {

    SnapShotDTO snapShotDTO;

    public SnapShotDTO getSnapShot() {
        TaskSnapShotDTO taskSnapShotDTO = new TaskSnapShotDTO();
        taskSnapShotDTO.setAssignTasks(5);
        taskSnapShotDTO.setActiveTasks(4);
        taskSnapShotDTO.setCompletedTasks(2);
        taskSnapShotDTO.setRecievedTasks(7);

        CustomerSnapShotDTO customerSnapShotDTO;
        customerSnapShotDTO = new CustomerSnapShotDTO();
        customerSnapShotDTO.setContract(5);
        customerSnapShotDTO.setAdoption(2);
        customerSnapShotDTO.setGrowth(4);
        customerSnapShotDTO.setRenewal(9);
        customerSnapShotDTO.setOnboarding(3);
        customerSnapShotDTO.setTier1(9);

        ProjectSnapShotDTO projectSnapShotDTO = new ProjectSnapShotDTO();
        projectSnapShotDTO.setTotalProjects(20);
        projectSnapShotDTO.setCompletedProjects(12);

        snapShotDTO = new SnapShotDTO();

        snapShotDTO.setTaskSnapShotDTO(taskSnapShotDTO);
        snapShotDTO.setCustomerSnapShotDTO(customerSnapShotDTO);
        snapShotDTO.setProjectSnapShotDTO(projectSnapShotDTO);

        return snapShotDTO;
    }
}
