package com.echo.taask.recources;

import com.echo.taask.customer.dto.CustomerLinkDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.parameters.P;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.security.Principal;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/resources")
@CrossOrigin(origins = "*", maxAge = 3600)
public class ResourceController {
    private final ResourceService resourceService;

    @PostMapping
    public ResponseEntity<?> uploadResource(Principal principal,
                                            @RequestParam(value = "customerId", required = false) String customerId,
                                            @RequestPart(value = "file", required = false) List<MultipartFile> file,
                                            @RequestPart(value = "link", required = false) List<CustomerLinkDto> customerLinkDto) {
        try {
            String authenticatedUser = principal.getName();
            return resourceService.addResources(customerId, authenticatedUser, file, customerLinkDto);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Please Contact Help Center");
        }
    }

    @PutMapping
    public ResponseEntity<?> updateResource(Principal principal,
                                            @RequestPart(value = "file", required = false) MultipartFile file,
                                            @RequestPart (value = "fileUuid", required = false) String fileUuid,
                                            @RequestPart(value = "link", required = false) CustomerLinkDto customerLinkDto,
                                            @RequestPart (value = "linkUuid", required = false) String linkUuid){
        try {
            return resourceService.updateResources(fileUuid,linkUuid, principal.getName(), file, customerLinkDto);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Please Contact Help Center");
        }
    }


    @GetMapping
    public ResponseEntity<?> getUserResources(Principal principal) {
        try {
            String authenticatedUser = principal.getName();
            return resourceService.getUserResources(authenticatedUser);
        } catch (Exception e) {
            e.getStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Please Contact Help Center");
        }
    }


    @DeleteMapping
    public ResponseEntity<?> deleteResource(Principal principal,
                                            @RequestParam String resourceType,
                                            @RequestParam String resourceId) {
        try {
            String authenticatedUser = principal.getName();
            return resourceService.deleteResource(authenticatedUser,resourceId,resourceType);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Please Contact Help Center");
        }
    }

}
