package com.echo.taask.controller;


import com.echo.taask.dto.SnapShotDTO;
import com.echo.taask.helper.SnapShotHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RequestMapping("api/snapshot")
@RestController
@CrossOrigin(origins = "*", maxAge = 3600)
public class SnapShotController {

    private SnapShotHelper snapShotHelper;

    @Autowired
    public SnapShotController(SnapShotHelper snapShotHelper)
    {
        this.snapShotHelper = snapShotHelper;
    }

    @GetMapping("/getSnapShot")
    public SnapShotDTO getSnapShotDTO(@RequestParam String userId)
    {
        return snapShotHelper.getSnapShot();
    }
}
