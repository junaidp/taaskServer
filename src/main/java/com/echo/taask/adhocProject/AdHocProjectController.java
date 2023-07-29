package com.echo.taask.adhocProject;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.parameters.P;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.security.Principal;

@RequestMapping("adHocProject")
@RestController
@RequiredArgsConstructor
@CrossOrigin(origins = "*", maxAge = 3600)
public class AdHocProjectController {
    private final AdHocService adHocService;

    @PostMapping
    public ResponseEntity<?> saveAdHocProject(Principal principal,
                                              @RequestPart AdHocRequest adHocProject,
                                              @RequestPart(required = false) MultipartFile file) {
        try {
            return adHocService.saveProjectHoc(principal, adHocProject, file);
        } catch (Exception ex) {
            ex.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).contentType(MediaType.APPLICATION_JSON)
                    .body("Please Contact Help Center");
        }
    }

    @GetMapping
    public ResponseEntity<?> getAdHocProjects(Principal principal) {
        try {
            return adHocService.getAllAdHocProjects(principal);
        } catch (Exception exception) {
            exception.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).contentType(MediaType.APPLICATION_JSON)
                    .body("Please Contact Help Center");
        }
    }

    @GetMapping("/detail/view")
    public ResponseEntity<?> getAdHodDetails(Principal principal,
                                             @RequestParam String uuid) {
        try {
            return adHocService.getAdHocProjectDetails(principal,uuid);
        } catch (Exception exception) {
            exception.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).contentType(MediaType.APPLICATION_JSON)
                    .body("Please Contact Help Center");
        }
    }

    @DeleteMapping("delete/adHocProject")
    public ResponseEntity<?> deleteAdHocProject(Principal principal,
                                                @RequestParam String uuid){
        try {
            return adHocService.deleteAdHocData(principal,uuid);
        } catch (Exception exception) {
            exception.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).contentType(MediaType.APPLICATION_JSON)
                    .body("Please Contact Help Center");
        }
    }

}
