package com.PLACEMENTWEBAPP.PlacementWebApp.Service;

import com.PLACEMENTWEBAPP.PlacementWebApp.Configuration.SecurityConfig;
import com.PLACEMENTWEBAPP.PlacementWebApp.Dto.LoginDto;
import com.PLACEMENTWEBAPP.PlacementWebApp.Dto.ResponseDto;
import com.PLACEMENTWEBAPP.PlacementWebApp.Entity.Role;
import com.PLACEMENTWEBAPP.PlacementWebApp.Entity.Student;
import com.PLACEMENTWEBAPP.PlacementWebApp.Entity.StudentDTO;
import com.PLACEMENTWEBAPP.PlacementWebApp.Entity.User;
import com.PLACEMENTWEBAPP.PlacementWebApp.Repository.StudentRepository;
import com.PLACEMENTWEBAPP.PlacementWebApp.Repository.UserRepository;
import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public class AuthService {

    @Autowired
    private StudentRepository studentRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private OTPService otpService;

    @Autowired
    private TokenGenerator tokenGenerator;

    public User createNewStudent(StudentDTO student) throws Exception {
        System.out.println("Received Student: " + student);
        if (userRepository.existsByEmail(student.user.email)) {
            throw new Exception("Email is already taken!");
        }
        if (studentRepository.existsByRegisterNumber(student.registerNumber)) {
            throw new Exception("Register number is already taken!");
        }
        int otpValue=otpService.optGenerator();
        student.user.otp=otpValue;
//        otpService.sendEmail(student.user.email,otpValue);
        User user=student.getUser();
        userRepository.save(user);
        if(student.role==Role.STUDENT){
            Student studentDetails=student.getStudentDetail();
            studentDetails.setUser(user);
            user.setStudent(studentDetails);
            studentRepository.save(studentDetails);
        }
        return user;
    }
    public boolean verifyOtp(int otp,StudentDTO student)throws Exception{
        User user=userRepository.findByEmail(student.user.email);
        if(otp==user.getOtp()){
            user.setVerified(true);
            userRepository.save(user);
            return true;
        }
        return false;
    }
    public ResponseDto Login(LoginDto loginDto) throws Exception {
        User user=userRepository.findByEmail(loginDto.getEmail());
        System.out.print("user "+user);
        if(!user.isVerified()){
            throw new Exception("verify");
        }
        ResponseDto responseDto=new ResponseDto();
        if(user.getPassword().equals(loginDto.getPassword())){
//            String accessToken=tokenGenerator.generateAccessToken(student.getEmail(),student.getId(),student.getRole());
            String accessToken=tokenGenerator.generateAccessToken(user.getEmail());
            String refreshToken=tokenGenerator.generateRefreshToken(user.getEmail(),user.getId(),user.getRole());
            responseDto.setUser(user);
            responseDto.setAccessToken(accessToken);
            responseDto.setRefreshToken(refreshToken);
        }
        else{
            throw new Exception("invalid password");
        }
        return responseDto;
    }

    public void sendOtp(LoginDto loginDto) throws Exception{
        User user=userRepository.findByEmail(loginDto.getEmail());
        otpService.sendEmail(user.getEmail(),user.getOtp());
    }
}
