package ru.prevent.config;

import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import ru.prevent.service.UserService;

@Configuration
@EnableWebSecurity
@AllArgsConstructor
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
    private final UserService userService;
    private final Environment env;
    private final CustomAuthenticationSuccessHandler successHandler;
    private final PasswordEncoder bCryptPasswordEncoder;

    @Override
    public void configure(WebSecurity web) throws Exception {
        web
                .ignoring()
                .antMatchers("/css/**", "/js/**", "/img/**");
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .authorizeRequests()
                .antMatchers("/static/favicon.ico").permitAll()
                .antMatchers("/login", "/signup").permitAll()
                .antMatchers("/*", "/showResult/*").hasAnyRole("USER", "ADMIN")
                .antMatchers("/**").hasRole("ADMIN")
                .anyRequest().authenticated()
                .and()
                .formLogin()
                .loginPage("/login")
                .successHandler(successHandler)
                .permitAll()
                .and()
                .logout()
                .logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
                .logoutSuccessUrl("/login").deleteCookies("JSESSIONID")
                .invalidateHttpSession(true)
                .permitAll();
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        String admimUsername = env.getProperty("admin.username");
        String admimPassword = env.getProperty("admin.password");
        if (!admimUsername.isEmpty() && !admimPassword.isEmpty()) {
            auth.inMemoryAuthentication()
                    .passwordEncoder(NoOpPasswordEncoder.getInstance())
                    .withUser(admimUsername)
                    .password(admimPassword)
                    .roles("ADMIN");
        }

        auth.userDetailsService(userService)
                .passwordEncoder(bCryptPasswordEncoder);
    }
}
