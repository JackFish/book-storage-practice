package kr.bookstorage.security.filter;

import com.fasterxml.jackson.databind.ObjectMapper;
import kr.bookstorage.domain.User;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AbstractAuthenticationProcessingFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by 권성봉 on 8/10/16.
 */
@Slf4j
public class StatelessLoginFilter extends AbstractAuthenticationProcessingFilter {

    private AuthenticationProvider customAuthProvider;

    public StatelessLoginFilter(String defaultFilterProcessesUrl, AuthenticationProvider customAuthProvider) {
        super(defaultFilterProcessesUrl);
        this.customAuthProvider = customAuthProvider;
    }

//    @Override
//    public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain) throws IOException, ServletException {
//        HttpServletRequest request = (HttpServletRequest) req;
//        HttpServletResponse response = (HttpServletResponse) res;
//
//        if ("OPTIONS".equalsIgnoreCase(request.getMethod())) {
//            response.setHeader("Access-Control-Allow-Origin", "*");
//            response.setHeader("Access-Control-Allow-Methods", "*");
//            response.setHeader("Access-Control-Allow-Headers", "*");
//            response.setStatus(HttpServletResponse.SC_OK);
//        } else {
//            chain.doFilter(req, res);
//        }
//        /*if(requestMatcher.matches(request)){
//            final UserDto.SocialLogin socialLogin = objectMapper.readValue(req.getInputStream(), UserDto.SocialLogin.class);
//            log.debug("Social Param : {}", socialLogin);
//
//            HttpServletRequestHelper requestWrapper = new HttpServletRequestHelper(request);
//
//            String uri = request.getRequestURI();
//            int pathParamIndex = uri.indexOf(';');
//
//            if (pathParamIndex > 0) {
//                // strip everything after the first semi-colon
//                uri = uri.substring(0, pathParamIndex);
//            }
//
//            // uri must start with context path
//            uri = uri.substring(request.getContextPath().length());
//
//            String filterProcessUrl = FILTER_PROCESS_URL.replace("*//**", "").replace("*//*", "").replace("*", "");
//
//            log.debug("filterProcessUrl : {}", filterProcessUrl);
//
//            uri = uri.substring(filterProcessUrl.length());
//            log.debug("uri : {}", uri);
//
//            if(uri.startsWith("/")){
//                requestWrapper.setParameter("providerId", uri.substring(1));
//            }
//
//            if(socialLogin.getExpiresIn() != 0){
//                requestWrapper.setParameter("expiresIn", String.valueOf(socialLogin.getExpiresIn()));
//            }
//
//            requestWrapper.setParameter("accessToken", socialLogin.getAccessToken());
//            requestWrapper.setParameter("refreshToken", socialLogin.getRefreshToken());
//
//            if(socialLogin.isRememberMe()){
//                requestWrapper.setParameter("remember-me", "yes");
//            }
//            super.doFilter(requestWrapper, res, chain);
//        }else{
//            super.doFilter(req, res, chain);
//        }*/
//    }

    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException, IOException, ServletException {
        final User user = new ObjectMapper().readValue(request.getInputStream(), User.class);

        log.debug("StatelessLoginFilter User Email : {}", user.getEmail());

        final UsernamePasswordAuthenticationToken userToken = new UsernamePasswordAuthenticationToken(user.getEmail(), user.getPassword());
        return customAuthProvider.authenticate(userToken);
    }
}
