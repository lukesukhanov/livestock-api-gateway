package com.livestock.apigateway.config;

import java.util.List;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.reactive.CorsWebFilter;
import org.springframework.web.cors.reactive.UrlBasedCorsConfigurationSource;

@Configuration
public class SecurityConfig {

  @Value("${app.security.cors-origins}")
  private String[] corsOrigins;

  @Bean
  CorsWebFilter corsWebFilter() {
    UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();

    CorsConfiguration loginConfig = new CorsConfiguration();
    loginConfig.setAllowedOrigins(List.of(this.corsOrigins));
    loginConfig.setAllowedMethods(List.of(HttpMethod.POST.toString()));
    loginConfig.setAllowedHeaders(List.of(HttpHeaders.AUTHORIZATION));
    loginConfig.setAllowCredentials(true);
    loginConfig.setMaxAge(3600L);
    source.registerCorsConfiguration("/login", loginConfig);

    CorsConfiguration oauth2AuthorizeConfig = new CorsConfiguration();
    oauth2AuthorizeConfig.setAllowedOrigins(List.of(this.corsOrigins));
    oauth2AuthorizeConfig.setAllowedMethods(List.of(HttpMethod.GET.toString()));
    oauth2AuthorizeConfig.setAllowCredentials(true);
    oauth2AuthorizeConfig.setMaxAge(3600L);
    source.registerCorsConfiguration("/oauth2/authorize", oauth2AuthorizeConfig);

    CorsConfiguration oauth2TokenConfig = new CorsConfiguration();
    oauth2TokenConfig.setAllowedOrigins(List.of(this.corsOrigins));
    oauth2TokenConfig.setAllowedMethods(List.of(HttpMethod.POST.toString()));
    oauth2TokenConfig.setAllowedHeaders(List.of(HttpHeaders.CONTENT_TYPE));
    oauth2TokenConfig.setAllowCredentials(true);
    oauth2TokenConfig.setMaxAge(3600L);
    source.registerCorsConfiguration("/oauth2/token", oauth2TokenConfig);

    CorsConfiguration usersConfig = new CorsConfiguration();
    usersConfig.setAllowedOrigins(List.of(this.corsOrigins));
    usersConfig.setAllowedMethods(List.of(HttpMethod.POST.toString()));
    usersConfig.setAllowedHeaders(List.of(HttpHeaders.CONTENT_TYPE, HttpHeaders.ACCEPT));
    usersConfig.setAllowCredentials(false);
    usersConfig.setMaxAge(3600L);
    source.registerCorsConfiguration("/users", usersConfig);

    CorsConfiguration productsInCartConfig = new CorsConfiguration();
    productsInCartConfig.setAllowedOrigins(List.of(this.corsOrigins));
    productsInCartConfig.setAllowedMethods(List.of(
        HttpMethod.GET.toString(),
        HttpMethod.POST.toString(),
        HttpMethod.DELETE.toString()));
    productsInCartConfig.setAllowedHeaders(List.of(
        HttpHeaders.AUTHORIZATION,
        HttpHeaders.CONTENT_TYPE,
        HttpHeaders.ACCEPT));
    productsInCartConfig.setAllowCredentials(true);
    productsInCartConfig.setMaxAge(3600L);
    source.registerCorsConfiguration("/productsInCart/**", productsInCartConfig);

    CorsConfiguration productsConfig = new CorsConfiguration();
    productsConfig.setAllowedOrigins(List.of(this.corsOrigins));
    productsConfig.setAllowedMethods(List.of(HttpMethod.GET.toString()));
    productsConfig.setAllowedHeaders(List.of(HttpHeaders.ACCEPT));
    productsConfig.setAllowCredentials(false);
    productsConfig.setMaxAge(3600L);
    source.registerCorsConfiguration("/products", productsConfig);

    CorsConfiguration productImagesConfig = new CorsConfiguration();
    productImagesConfig.setAllowedOrigins(List.of(this.corsOrigins));
    productImagesConfig.setAllowedMethods(List.of(HttpMethod.GET.toString()));
    productImagesConfig.setAllowedHeaders(List.of(HttpHeaders.ACCEPT));
    productImagesConfig.setAllowCredentials(false);
    productImagesConfig.setMaxAge(3600L);
    source.registerCorsConfiguration("/productImages/**", productImagesConfig);

    CorsConfiguration categoriesConfig = new CorsConfiguration();
    categoriesConfig.setAllowedOrigins(List.of(this.corsOrigins));
    categoriesConfig.setAllowedMethods(List.of(HttpMethod.GET.toString()));
    categoriesConfig.setAllowedHeaders(List.of(HttpHeaders.ACCEPT));
    categoriesConfig.setAllowCredentials(false);
    categoriesConfig.setMaxAge(3600L);
    source.registerCorsConfiguration("/categories", categoriesConfig);

    return new CorsWebFilter(source);
  }
}
