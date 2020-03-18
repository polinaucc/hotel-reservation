package ua.polina.hotel_reservation.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Primary
@Configuration
public class SpringSecurityWebAppConfig extends WebSecurityConfigurerAdapter {
    public SpringSecurityWebAppConfig() {
        super();
    }

    @Override
    protected void configure(HttpSecurity httpSecurity) throws Exception {
        httpSecurity
                .csrf()
                .disable();

        httpSecurity
                .authorizeRequests()
                .antMatchers("/", "auth/login", "/logout")
                .permitAll();

        httpSecurity
                .authorizeRequests()
                .antMatchers("/client/**")
                .access("hasAuthority(T(ua.polina.hotel_reservation.entity.Role).CLIENT.getAuthority())");

        httpSecurity
                .authorizeRequests()
                .antMatchers("/admin/**")
                .access("hasAuthority(T(ua.polina.hotel_reservation.entity.Role).ADMIN.getAuthority())");

        httpSecurity
                .authorizeRequests()
                .and()
                .exceptionHandling();

        httpSecurity
                .authorizeRequests()
                .and()
                .formLogin()
                .loginPage("/auth/login")
                .failureUrl("/auth/login?error")
                .defaultSuccessUrl("/auth/default-success", true)
                .and()
                .logout()
                .logoutUrl("/logout")
                .logoutSuccessUrl("/auth/login?logout");
    }
}
