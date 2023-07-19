package com.echo.taask.user;

import com.echo.taask.customer.dto.ImageResponse;
import com.echo.taask.recources.Image;
import lombok.RequiredArgsConstructor;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.util.Date;

@Component
@RequiredArgsConstructor
public class UserHelper {
    @Autowired
    UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public ResponseEntity<?> updateUser(User user, UpdateUserRequest updateUser, MultipartFile image) {
        try {
            User saveUpdatedUser = new User();
            if (updateUser != null) {
                saveUpdatedUser.setId(user.getId());
                saveUpdatedUser.setFirstname(isNotBlank(updateUser.getFirstname()) ? updateUser.getFirstname() : user.getFirstname());
                saveUpdatedUser.setLastname(isNotBlank(updateUser.getLastname()) ? updateUser.getLastname() : user.getLastname());
                saveUpdatedUser.setPassword(isNotBlank(updateUser.getPassword()) ? passwordEncoder.encode(updateUser.getPassword()) : user.getPassword());
                saveUpdatedUser.setEmail(user.getEmail());
                saveUpdatedUser.setCreationDate(user.getCreationDate());
                saveUpdatedUser.setUpdateDate(new Date());
            }
            if (image != null && !image.isEmpty()) {
                String fileName = StringUtils.cleanPath(image.getOriginalFilename());
                String fileExtension = StringUtils.getFilenameExtension(fileName);
                if (fileExtension == null || !isSupportedImageFormat(fileExtension)) {
                    return ResponseEntity.badRequest().body("Invalid image file");
                } else {
                    Image uploadImage = new Image();
                    uploadImage.setName(image.getOriginalFilename());
                    uploadImage.setContentType(image.getContentType());
                    uploadImage.setData(image.getBytes());
                    saveUpdatedUser.setImage(uploadImage);
                }
            }
            userRepository.save(saveUpdatedUser);
            return ResponseEntity.status(HttpStatus.CREATED)
                    .contentType(MediaType.APPLICATION_JSON)
                    .body("User: " + saveUpdatedUser.getEmail() + " updated");
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error Occured In Server");
    }

    public ResponseEntity<?> getUserByEmail(String user) {
        try {
            User userProfile = userRepository.findByEmail(user);
            UserResponse userResponse = new UserResponse();
            userResponse.setFirstname(userProfile.getFirstname());
            userResponse.setLastname(userProfile.getLastname());
            userResponse.setEmail(userProfile.getEmail());


            ImageResponse imageResponse = new ImageResponse();
            imageResponse.setName(userProfile.getImage().getName());
            imageResponse.setContentType(userProfile.getImage().getContentType());
            imageResponse.setData(userProfile.getImage().getData());
            userResponse.setImage(imageResponse);
            return ResponseEntity.status(HttpStatus.OK)
                    .contentType(MediaType.APPLICATION_JSON)
                    .body(userResponse);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>("Server Error Contact Help Center!", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    private boolean isSupportedImageFormat(String fileExtension) {
        String supportedFormats = "jpg,jpeg,png,gif"; // Add more supported formats if needed
        return supportedFormats.contains(fileExtension.toLowerCase());
    }

    private boolean isNotBlank(String str) {
        return str != null && !str.trim().isEmpty();
    }
}
