package com.touchIon.backend.Auth;


import com.touchIon.backend.Auth.DTO.LoginRequest;
import com.touchIon.backend.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

   private final AuthService authService;

   @Autowired()
   public AuthController(AuthService authService) {
         this.authService = authService;
   }

   @PostMapping("/login")
   ResponseEntity<AuthResponse> signIn(@RequestBody LoginRequest credentials){
       if (Boolean.FALSE.equals(authService.checkCredentials(credentials))){
           throw new BadCredentialsException("Invalid credentials");
       }
       return ResponseEntity.ok(authService.createToken(credentials));
   }

    @PostMapping("/register")
    ResponseEntity<User> signUp(@RequestBody User user){
         User newUser = authService.register(user);
         return ResponseEntity.ok(newUser);
    }

}
