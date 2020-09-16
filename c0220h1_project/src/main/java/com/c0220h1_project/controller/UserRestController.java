package com.c0220h1_project.controller;

import com.c0220h1_project.model.Exam;
import com.c0220h1_project.model.UserPrincipal;
import com.c0220h1_project.model.login_msg.request.Login;
import com.c0220h1_project.model.login_msg.response.JwtResponse;
import com.c0220h1_project.model.user.User;
import com.c0220h1_project.model.user.UserDto;
import com.c0220h1_project.payload.UpdatePasswordToken;
import com.c0220h1_project.payload.response.ApiResponse;
import com.c0220h1_project.security.JwtProvider;
import com.c0220h1_project.service.exam.ExamService;
import com.c0220h1_project.service.test.TestService;
import com.c0220h1_project.service.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@CrossOrigin(origins = "*", allowedHeaders = "*")
//@RequestMapping(path = "/users")
public class UserRestController {
    @Autowired
    UserService userService;

    /////////// THIEN ///////////
    @Autowired
    private AuthenticationManager authManager;

    @Autowired
    private JwtProvider jwtProvider;

    @Autowired
    TestService testService;

    @Autowired
    ExamService examService;

    private final String NOT_FOUND_USER = "Cannot find this user!";

    private final String NOT_FOUND_EXAMS = "Cannot find exam list!";

    private PasswordEncoder encoder;

    @Autowired
    public void setEncoder(PasswordEncoder encoder) {
        this.encoder = encoder;
    }

    @GetMapping("/listUser")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<List<User>> getListUser() {
        List<User> memberList = userService.findAllMember();
        if (memberList.isEmpty()) {
            return new ResponseEntity<>((List<User>) null, HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(memberList, HttpStatus.OK);
    }

    @PostMapping(value = "/register", produces = MediaType.APPLICATION_JSON_VALUE, consumes =MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> registerUser(@RequestBody UserDto userDto){
        if (Boolean.TRUE.equals(userService.save(userService.parseDto(userDto)))){
            return new ResponseEntity<>(HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.CONFLICT);
    }

    @DeleteMapping("/delete-user/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<String> deleteUser(@PathVariable Integer id) {
        if (Boolean.TRUE.equals(userService.deleteUser(id))) {
            return new ResponseEntity<>(HttpStatus.OK);
        }else return new ResponseEntity<>(HttpStatus.CONFLICT);
    }

    @GetMapping("/new-user")
    public ResponseEntity<User> findUserNew() {
        return new ResponseEntity<>(userService.findTopByOrderByIdDesc(), HttpStatus.OK);
    }

    /////////// THIEN ///////////
    @PostMapping("/login")
    public ResponseEntity<?> authenticateUser(@Valid @RequestBody Login loginRequest) throws AuthenticationException {

        Authentication authentication = authManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        loginRequest.getUsername(),
                        loginRequest.getPassword()
                )
        );

        SecurityContextHolder.getContext().setAuthentication(authentication);
        String token = jwtProvider.generatingJwt(authentication);
        System.out.println("Token is generated: " + token);

        UserPrincipal userPrincipal = (UserPrincipal) authentication.getPrincipal();
        System.out.println("UserDetails: " + userPrincipal.getUsername());

        JwtResponse response = new JwtResponse(
                token,
                userPrincipal.getUsername(),
                userPrincipal.getEmail(),
                userPrincipal.getAvatar(),
                userPrincipal.getAuthorities()
        );
        System.out.println(response);
        return ResponseEntity.ok(response);
    }

    //    tinh - get user by id

    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Object> findUserById(@PathVariable("id") Integer id) {
        User user = userService.findById(id);
        if (user == null) {
            return new ResponseEntity<>(new ApiResponse(false, NOT_FOUND_USER), HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(user, HttpStatus.OK);
    }

    @GetMapping(value = "/getTotalUser")
    public ResponseEntity<Integer> findTotalUser() {
        List<User> userList = userService.findAll();
        return new ResponseEntity<>(userList.size(), HttpStatus.OK);
    }

//    tinh - update user

    @PatchMapping(value = "update-user/{id}")
    public ResponseEntity<Object> updateUser(@PathVariable Integer id, @RequestBody User user) {
        User currentUser = userService.findById(id);
        if (currentUser == null) {
            return new ResponseEntity<>(new ApiResponse(false, NOT_FOUND_USER), HttpStatus.NOT_FOUND);
        }
        currentUser.setUsername(user.getUsername());
        currentUser.setFullName(user.getFullName());
        currentUser.setEmail(user.getEmail());
        currentUser.setAddress(user.getAddress());
        currentUser.setPhoneNumber(user.getPhoneNumber());
        userService.edit(currentUser);
        return new ResponseEntity<>(currentUser, HttpStatus.OK);
    }

//    tinh - update password

    @PatchMapping("/update-password/{id}")
    public ResponseEntity<Object> changePassword(@PathVariable Integer id, @RequestBody UpdatePasswordToken updatePasswordToken) {
        User user = userService.findById(id);
        if (user == null) {
            return new ResponseEntity<>(new ApiResponse(false, NOT_FOUND_USER), HttpStatus.NOT_FOUND);
        }
        if (!encoder.matches(updatePasswordToken.getCurrentPassword(), user.getUserPassword())) {
            return new ResponseEntity<>(new ApiResponse(false, "The password is incorrect!"), HttpStatus.BAD_REQUEST);
        }
        user.setUsername(encoder.encode(updatePasswordToken.getNewPassword()));
        userService.edit(user);
        return new ResponseEntity<>(user, HttpStatus.OK);
    }

//    tinh - test history

    @GetMapping(value = "/history/{id}")
    public ResponseEntity<Object> getTestHistory(@PathVariable("id") Integer id) {
        List<Exam> exams = examService.findByUserId(id);
        if (exams == null) {
            return new ResponseEntity<>(new ApiResponse(false, NOT_FOUND_EXAMS), HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(exams, HttpStatus.OK);
    }


}

