package main.java.com;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.view.RedirectView;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;


@Controller
@CrossOrigin
public class HelloController {
    @Autowired
    @Qualifier("authenticationManagerBean")
    private AuthenticationManager authenticationManagerBean;
    private final JwtTokenUtil jwtTokenUtil = new JwtTokenUtil();
    private final JwtUserDetailsService userDetailsService = new JwtUserDetailsService();

    public HelloController() {
    }

    @RequestMapping("/")
    public String home(){
        System.out.println("Hello Home...");
        return "hello";
    }

    @GetMapping({"/", "/hello"})
    public String hello(Model model, @RequestParam(value="name", required=false, defaultValue="World") String name) {
        model.addAttribute("name", name);
        JwtRequest request = new JwtRequest("javainuse", "password");
        String token = this.getLoginKey();
        System.out.println(token);
        model.addAttribute("tokenString", token);
        return "hello";
    }

    @RequestMapping(value = "/authenticate", method = RequestMethod.POST)
    public ResponseEntity<?> createAuthenticationToken(@RequestBody JwtRequest authenticationRequest) throws Exception {
        authenticate(authenticationRequest.getUsername(), authenticationRequest.getPassword());
        final UserDetails userDetails = userDetailsService
                .loadUserByUsername(authenticationRequest.getUsername());
        final String token = jwtTokenUtil.generateToken(userDetails);
        return ResponseEntity.ok(new JwtResponse(token));
    }
    private void authenticate(String username, String password) throws Exception {
        try {
            authenticationManagerBean.authenticate(new UsernamePasswordAuthenticationToken(username, password));
        } catch (DisabledException e) {
            throw new Exception("USER_DISABLED", e);
        } catch (BadCredentialsException e) {
            throw new Exception("INVALID_CREDENTIALS", e);
        }
    }
    public String getLoginKey(){
         UserDetails userDetails = userDetailsService.loadUserByUsername("javainuse");
         String token = jwtTokenUtil.generateToken(userDetails);
        return  token;
    }
   @RequestMapping(value="/redirect", method = RequestMethod.GET)
    public RedirectView redirect(HttpServletResponse response){

        Cookie cookie = new Cookie("token", "testCookie");
        cookie.setHttpOnly(false);
        cookie.setPath("http://localhost:4200");
        response.addCookie(cookie);
        RedirectView view = new RedirectView("http://localhost:4200");

       return view;
       //        String token = "hello";
       // view.addStaticAttribute("Access-Control-Allow-Origin", "*");
       // view.setExposePathVariables(false);
       //  view.addStaticAttribute("token", "test");
       // request.setRequestHeader('Access-Control-Allow-Origin', 'true')
       // request.setRequestHeader('Access-Control-Allow-Credentials', 'true')
    }

}
