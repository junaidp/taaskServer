package com.echo.taask.customer;

import com.echo.taask.customer.dto.CustomerDto;
import com.echo.taask.customer.dto.CustomerLinkDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.security.Principal;
import java.util.List;

@RequestMapping("customer")
@RestController
@RequiredArgsConstructor
@CrossOrigin(origins = "*", maxAge = 3600)
public class CustomerController {
    private final CustomerHelper customerHelper;
    @PostMapping("/saveCustomer")
    public ResponseEntity<?> savecustomer(Principal principal
            , @RequestPart(value = "image", required = false) MultipartFile image
            , @RequestPart("customer") CustomerDto customer
            , @RequestPart(value = "file", required = false) List<MultipartFile> file
            , @RequestPart("link") List<CustomerLinkDto> customerLinkDto) {
        try {
            String authenticatedUser = principal.getName();
            return customerHelper.saveCustomer(authenticatedUser, customer, image, file, customerLinkDto);
        } catch (Exception ex) {
            return new ResponseEntity("Please Contact Help Center!", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/list")
    public ResponseEntity<?> getAllCustomer(Principal principal,
                                            @RequestHeader("Authorization") String Authorizaion) {
        try {
            String authenticatedUser = principal.getName();
            return customerHelper.getCustomersList(authenticatedUser);
        } catch (Exception e) {
            return new ResponseEntity("Please Contact Help Center!", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/getCustomer")
    public ResponseEntity<?> getCustomer(Principal principal,
                                         @RequestParam String userid) {
        try {
            String authenticatedUser = principal.getName();
            return customerHelper.findCustomerById(authenticatedUser, userid);
        } catch (Exception ex) {
            return ResponseEntity.internalServerError().body("Please Contact Help Center");
        }
    }

    @DeleteMapping("DeleteCustomer")
    public ResponseEntity<?> deleteCustomer(Principal principal,
                                            @RequestParam String userid) {
        try {
            String authenticatedUser = principal.getName();
            return customerHelper.deleteCustomerById(authenticatedUser, userid);
        } catch (Exception ex) {
            return ResponseEntity.internalServerError().body("Please Contact Help Center");
        }
    }

    @PutMapping("updateCustomer")
    public ResponseEntity<?> updateCustomer(Principal principal,
                                            @RequestPart(value = "image", required = false) MultipartFile image,
                                            @RequestPart("customer") CustomerDto customerDto,
                                            @RequestParam String userid) {
        try {
            String authenticatedUser = principal.getName();
            return customerHelper.updateCustomer(authenticatedUser, customerDto, userid, image);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return ResponseEntity.internalServerError().body("Please Contact Help Center");
        }
    }
}
