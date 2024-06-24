package tp.app.gestiontickets.configuration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.provisioning.JdbcUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;
import tp.app.gestiontickets.model.Role;
import tp.app.gestiontickets.model.UserEntity;
import tp.app.gestiontickets.repositories.RoleRepository;
import tp.app.gestiontickets.repositories.UserRepository;

import javax.sql.DataSource;

import java.util.Collections;

import static org.springframework.http.HttpMethod.GET;

@Configuration
@EnableWebSecurity
public class SpringSecurityConfig {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleRepository roleRepository;

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http.csrf(AbstractHttpConfigurer::disable).authorizeHttpRequests(auth -> {
            auth.requestMatchers("/swagger-ui/index.html#/").permitAll();
            auth.requestMatchers(GET,"/users").hasAuthority("ADMIN"); //Donne accès qu'aux Admins sur l'endPoint "/users"
            auth.requestMatchers("/apprenant/**").hasAuthority("APPRENANT");
            auth.requestMatchers("/formateur/**").hasAuthority("FORMATEUR");
            auth.requestMatchers("/admin/**").hasAuthority("ADMIN").anyRequest().authenticated();
        }).httpBasic(Customizer.withDefaults()).formLogin(Customizer.withDefaults());
        System.out.println(http.toString());
        return http.build();
    }

    @Bean
    public AuthenticationManager authenticationManager(
            AuthenticationConfiguration authenticationConfiguration) throws Exception {
        return authenticationConfiguration.getAuthenticationManager();
    }

    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    CommandLineRunner adminPardefaut() {
        return args -> {
            boolean adminExist = this.userRepository.existsByEmail("harouna.hkdk@gmail.com");
            if (adminExist == false) {
                UserEntity adminPardefaut = new UserEntity();
                adminPardefaut.setNom("DIALLO");
                adminPardefaut.setPrenom("Harouna");
                adminPardefaut.setEmail("harouna.hkdk@gmail.com");
                adminPardefaut.setMdp(passwordEncoder().encode("nessmdp"));
                Role roles = roleRepository.findRoleByLibelle("ADMIN");
                if (roles==null){
                    roles = new Role();
                    roles.setLibelle("ADMIN");
                    Role role1 = this.roleRepository.save(roles);
                    //adminPardefaut.setRoles(Collections.singletonList(role1));
                    System.out.println(adminPardefaut.toString());
                    userRepository.save(adminPardefaut);
                }

            }
            UserEntity user = this.userRepository.findByEmail("harouna.hkdk@gmail.com");
            user.setRoles(Collections.singletonList(roleRepository.findRoleByLibelle("ADMIN")));
            this.userRepository.save(user);
        };
    }

    /*@Bean
    public JdbcUserDetailsManager jdbcUserDetailsManager(DataSource dataSource) {
        return new JdbcUserDetailsManager(dataSource);
    }

    @Bean
    public CommandLineRunner commandLineRunner(JdbcUserDetailsManager jdbcUserDetailsManager) {
        PasswordEncoder passwordEncoder = passwordEncoder();
        return args -> {

            UserDetails uNess = jdbcUserDetailsManager.loadUserByUsername("userNess");
            if (uNess == null) {
                jdbcUserDetailsManager.createUser(
                        User.withUsername("userNess").password(passwordEncoder().encode("nessmdp")).roles("SupperAdmin").build()
                );
            }
            /*

            UserDetails uNess1 = jdbcUserDetailsManager.loadUserByUsername("userNess1");
            if (uNess == null) {
                jdbcUserDetailsManager.createUser(
                        UserEntity.withUsername("userNess1").password(passwordEncoder().encode("nessmdp")).roles("USER").build()
                );
            }
            UserDetails adminNess = jdbcUserDetailsManager.loadUserByUsername("adminNess");
            if (uNess == null) {
                jdbcUserDetailsManager.createUser(
                        UserEntity.withUsername("adminNess").password(passwordEncoder().encode("nessmdp")).roles("USER").build()
                );
            }*/
//        };
//    }

    /*/@Bean
    public UserDetailsService userDetailsService() {
        UserDetails admin = UserEntity.builder()
                .username("Ness")
                .password(passwordEncoder().encode("ness"))
                .roles("Admin").build();
        UserDetails user = UserEntity.builder()
                .username("user")
                .password(passwordEncoder().encode("user"))
                .roles("USER").build();
        System.out.println(passwordEncoder().encode("user"));
        return new InMemoryUserDetailsManager(admin, user);
    }*/



    /*@Bean
    public DaoAuthenticationProvider authenticationProvider(UserDetailsService userDetailsService) {
        DaoAuthenticationProvider auth = new DaoAuthenticationProvider();
        auth.setUserDetailsService(userDetailsService);
        auth.setPasswordEncoder(passwordEncoder());
        return auth;
    }

    /*@Bean
    public AuthenticationManager authenticationManager(HttpSecurity http, BCryptPasswordEncoder bCryptPasswordEncoder) throws Exception {
        AuthenticationManagerBuilder authenticationManagerBuilder = http.getSharedObject(AuthenticationManagerBuilder.class);
        authenticationManagerBuilder.userDetailsService(customUserDetailsService).passwordEncoder(bCryptPasswordEncoder);
        return authenticationManagerBuilder.build();
    }*/
}
