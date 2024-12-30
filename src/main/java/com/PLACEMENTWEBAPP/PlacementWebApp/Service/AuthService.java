package com.PLACEMENTWEBAPP.PlacementWebApp.Service;

import com.PLACEMENTWEBAPP.PlacementWebApp.Configuration.SecurityConfig;
import com.PLACEMENTWEBAPP.PlacementWebApp.Dto.LoginDto;
import com.PLACEMENTWEBAPP.PlacementWebApp.Dto.ResponseDto;
import com.PLACEMENTWEBAPP.PlacementWebApp.Entity.Student;
import com.PLACEMENTWEBAPP.PlacementWebApp.Entity.StudentDTO;
import com.PLACEMENTWEBAPP.PlacementWebApp.Repository.StudentRepository;
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
    private OTPService otpService;

    @Autowired
    private TokenGenerator tokenGenerator;

    public void createNewStudent(Student student) throws Exception {
        System.out.println("Received Student: " + student.getName()); // Debugging
        if (studentRepository.existsByEmail(student.getEmail())) {
            throw new Exception("Email is already taken!");
        }
        if (studentRepository.existsByRegisterNumber(student.getRegisterNumber())) {
            throw new Exception("Register number is already taken!");
        }
        int otpValue=otpService.optGenerator();
        student.setOtp(otpValue);
        otpService.sendEmail(student.getEmail(),otpValue);
        studentRepository.save(student);
    }
    public boolean verifyOtp(int otp,Student student)throws Exception{
        Student student1=studentRepository.findByEmail(student.getEmail());
        if(otp==student1.getOtp()){
            student1.setVerified(true);
            studentRepository.save(student1);
            return true;
        }
        return false;
    }
    public ResponseDto Login(LoginDto loginDto) throws Exception {
        Student student=studentRepository.findByRegisterNumber(loginDto.getRegisterNumber());
        if(!student.isVerified()){
            throw new Exception("Please verify your email!");
        }

        ResponseDto responseDto=new ResponseDto();
        if(student.getPassword().equals(loginDto.getPassword())){
//            String accessToken=tokenGenerator.generateAccessToken(student.getEmail(),student.getId(),student.getRole());
            String accessToken=tokenGenerator.generateAccessToken(student);
            String refreshToken=tokenGenerator.generateRefreshToken(student.getEmail(),student.getId(),student.getRole());
            responseDto.setStudent(student);
            responseDto.setAccessToken(accessToken);
            responseDto.setRefreshToken(refreshToken);
        }
        else{
            throw new Exception("not a valid password");
        }
        return responseDto;
    }

}
