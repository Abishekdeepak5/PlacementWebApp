package com.PLACEMENTWEBAPP.PlacementWebApp.Controller;

import com.PLACEMENTWEBAPP.PlacementWebApp.Dto.LoginDto;
import com.PLACEMENTWEBAPP.PlacementWebApp.Dto.OtpVerificationRequest;
import com.PLACEMENTWEBAPP.PlacementWebApp.Dto.ResponseDto;
import com.PLACEMENTWEBAPP.PlacementWebApp.Entity.Staff;
import com.PLACEMENTWEBAPP.PlacementWebApp.Entity.StudentDTO;
import com.PLACEMENTWEBAPP.PlacementWebApp.Repository.StaffRepository;
import com.PLACEMENTWEBAPP.PlacementWebApp.Service.AuthService;
import com.PLACEMENTWEBAPP.PlacementWebApp.Entity.Student;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin("*")
@RequestMapping("/api/auth")
public class AuthController {

    @Autowired
    private AuthService authService;

    @Autowired
    private StaffRepository staffRepository;

    @PostMapping("/signup")
    public ResponseEntity<?> signUp(@RequestBody Student student){
        try{
            authService.createNewStudent(student);
            return ResponseEntity.ok(Map.of("message","otp sent to mail!"));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(Map.of("message",e.getMessage()));
        }
    }

    @PostMapping("/signup/verify")
    public ResponseEntity<?>verifyOtp(@RequestBody Student student){
            try{
                int otp= Integer.valueOf(student.message);
                if(authService.verifyOtp(otp, student)){
                    return ResponseEntity.ok(Map.of("message","verified successfully"));
                }
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(Map.of("message","OTP invalid"));
            }catch(Exception e){
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(Map.of("message",e.getMessage()));
            }
    }
    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginDto loginDto) throws Exception {
        System.out.println("Welcome to login page");
        try{
          return ResponseEntity.ok( authService.Login(loginDto));
        }
        catch (Exception e) {
            return ResponseEntity.status(500).body(Map.of("msg","Error : " + e.getMessage()));
        }

    }
    @PostMapping("/staffRegister")
    public Staff createStaff(@RequestBody Staff staff){
        System.out.println(staff.getEmail());
        System.out.println(staff.getPassword());
        return staffRepository.save(staff);
    }

}
