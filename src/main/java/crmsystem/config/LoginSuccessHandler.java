package crmsystem.config;

import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.SavedRequestAwareAuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
public class LoginSuccessHandler extends SavedRequestAwareAuthenticationSuccessHandler {

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
                                        Authentication authentication) throws ServletException, IOException {

/*        boolean hasUserRole = authentication.getAuthorities().stream()
                .anyMatch(r -> r.getAuthority().equals("ROLE_ADMIN") || r.getAuthority().equals("ROLE_USER"));*/

        if (authentication.getAuthorities().stream().anyMatch(r -> r.getAuthority().equals("ROLE_ADMIN"))){
            response.sendRedirect("mainform");
        }else if (authentication.getAuthorities().stream().anyMatch(r -> r.getAuthority().equals("ROLE_USER"))){
            response.sendRedirect("mainform");
        }else if (authentication.getAuthorities().stream().anyMatch(r -> r.getAuthority().equals("ROLE_RISK"))){
            response.sendRedirect("risk");
        }else if (authentication.getAuthorities().stream().anyMatch(r -> r.getAuthority().equals("ROLE_CORP"))){
            response.sendRedirect("corp");
        }

/*
        Collection<? extends GrantedAuthority> authorities = authentication.getAuthorities();

        if (authorities.contains(new SimpleGrantedAuthority("ROLE_ADMIN").getAuthority().equals("ROLE_ADMIN"))) {
                 response.sendRedirect("mainform");
        }else if(authorities.contains(new SimpleGrantedAuthority("ROLE_USER").getAuthority().equals("ROLE_USER"))){
                 response.sendRedirect("mainform");
        }else if(authorities.contains(new SimpleGrantedAuthority("ROLE_RISK").getAuthority().equals("ROLE_RISK"))){
                 response.sendRedirect("risk");
        }else if(authorities.contains(new SimpleGrantedAuthority("ROLE_CORP").getAuthority().equals("ROLE_CORP"))){
            response.sendRedirect("corp");
        }
*/


/*        String role = authentication.getAuthorities()
                .stream()
                .filter(r -> r.getAuthority().equals("ROLE_ADMIN") ||
                        r.getAuthority().equals("ROLE_USER") ||
                        r.getAuthority().equals("ROLE_RISK") ||
                        r.getAuthority().equals("ROLE_CORP")).findFirst()
                         .get().getAuthority();*/

/*        System.out.println(role);
        String redirectURL = null;

        if (role.equals("ROLE_ADMIN")  || role.equals("ROLE_USER")) {
            redirectURL = "mainform";
        }
        if (role.equals("ROLE_RISK")) {
            redirectURL = "risk";
        }
        if (role.equals("ROLE_CORP")) {
            redirectURL = "corp";
        }*/

/*
        if (hasUserRole) {
            redirectURL = "mainform";
        }else{
            redirectURL = "risk";
        }
*/

       // response.sendRedirect(redirectURL);

    }

}
