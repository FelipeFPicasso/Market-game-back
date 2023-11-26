package com.marketgame.config

import com.fasterxml.jackson.databind.ObjectMapper
import com.marketgame.Security.AuthenticationFilter
import com.marketgame.Security.AuthorizationFilter
import com.marketgame.Security.JwtUtil
import com.marketgame.enums.Role
import com.marketgame.repository.CustomerRepository
import com.marketgame.service.UserDetailsCustomService
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.http.HttpMethod
import org.springframework.security.authentication.AuthenticationManager
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.builders.WebSecurity
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.security.config.http.SessionCreationPolicy
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.security.web.SecurityFilterChain
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter
import org.springframework.web.bind.annotation.CrossOrigin
import org.springframework.web.servlet.config.annotation.CorsRegistry

import org.springframework.web.servlet.config.annotation.WebMvcConfigurer





@Configuration
@EnableWebSecurity
@CrossOrigin("*")
@EnableGlobalMethodSecurity(prePostEnabled = true)
class SecurityConfig(


    private val customerRepository: CustomerRepository,
    private val authenticationConfiguration: AuthenticationConfiguration,
    private val userDetails: UserDetailsCustomService,
    private val jwtUtil: JwtUtil
){


    private val PUBLIC_MATCHERS = arrayOf<String>()

    private val PUBLIC_POST_MATCHERS = arrayOf(
        "/customer",
        "/login",
        "/loginteste"
    )

    private val ADMIN_MATCHERS = arrayOf(
        "/admin/**"
    )


    @Configuration
    class CorsConfiguration : WebMvcConfigurer {
        override fun addCorsMappings(registry: CorsRegistry) {
            registry.addMapping("/**")
                .allowedOrigins("*")
                .allowedMethods("GET", "POST", "PUT", "DELETE", "OPTIONS", "HEAD", "TRACE", "CONNECT")
        }
    }


    @Bean
    @Throws(Exception::class)
    fun authenticationManager(): AuthenticationManager {
        return authenticationConfiguration.authenticationManager
    }


     fun filterChain(auth: AuthenticationManagerBuilder){
        auth.userDetailsService(userDetails).passwordEncoder(bCryptPasswordEncoder())
    }

    @Bean
    fun filterChain(http: HttpSecurity): SecurityFilterChain {
        http.csrf().disable().cors()
        http.authorizeHttpRequests()
            .requestMatchers(*PUBLIC_MATCHERS).permitAll()
            .requestMatchers(HttpMethod.POST, *PUBLIC_POST_MATCHERS).permitAll()
            .requestMatchers(*ADMIN_MATCHERS).hasAnyAuthority(Role.ADMIN.description)
            .anyRequest().authenticated()

        http.addFilter(AuthenticationFilter(authenticationManager(),customerRepository, jwtUtil))
        http.addFilter(AuthorizationFilter(authenticationManager(), userDetails, jwtUtil))
        http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
        //print(http)
        return http.build()

    }

     fun configure(web: WebSecurity) {
        web.ignoring()
            .requestMatchers(
                "/v2/api-docs",
                "/v3/api-docs",
                "/configuration/ui",
                "/swagger-resources/**",
                "/configuration/security",
                "/swagger-ui/**",
                "/webjars/**",
                "/csrf/**"
            )
    }


    /*@Bean
    fun filterChain(http: HttpSecurity): SecurityFilterChain {
        http {
            authorizeRequests {
                authorize(anyRequest, authenticated)
            }
            formLogin { }
            httpBasic { }
        }
        return http.build()
    }*/



    @Bean
    fun bCryptPasswordEncoder(): BCryptPasswordEncoder{
        return BCryptPasswordEncoder()
    }


}