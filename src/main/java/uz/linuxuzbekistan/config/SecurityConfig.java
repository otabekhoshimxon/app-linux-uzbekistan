package uz.linuxuzbekistan.config;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.client.RestTemplate;
import uz.linuxuzbekistan.util.MD5PasswordGenerator;

@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter {


    @Autowired
    private JwtTokenFilter jwtTokenFilter;
    @Autowired
    private CustomUserDetailServise customUserDetailServise;



    private static final String[] AUTH_WHITELIST = {
            "/v2/api-docs",
            "/configuration/ui",
            "/configuration/security",
            "/swagger-ui.html",
            "/webjars/**",
            "/v3/api-docs/**",
            "/swagger-ui/**",
            "/swagger-resources",
            "/swagger-resources/**"
    };


    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                .antMatchers(AUTH_WHITELIST).permitAll()
                .antMatchers("/api/v1/auth/*").permitAll();
        http.authorizeRequests().anyRequest().authenticated();
        http.addFilterBefore(jwtTokenFilter, UsernamePasswordAuthenticationFilter.class);
        http.formLogin();
        http.csrf().disable();
        http.cors().disable();
    }
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(customUserDetailServise).passwordEncoder(passwordEncoder());
    }
    @Bean
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }
    @Bean
    public PasswordEncoder passwordEncoder(){
        return new PasswordEncoder() {
            @Override
            public String encode(CharSequence rawPassword) {
                return rawPassword.toString();
            }
            @Override
            public boolean matches(CharSequence rawPassword, String encodedPassword) {
                String md5Password = MD5PasswordGenerator.getMd5Password(rawPassword.toString());
                return md5Password.equals(encodedPassword);
            }

        };
    }
    @Bean
    public RestTemplate restTemplate(){
        return new RestTemplate();
    }




}
