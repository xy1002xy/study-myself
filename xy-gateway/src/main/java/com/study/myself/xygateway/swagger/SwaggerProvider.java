// package com.study.myself.xygateway.swagger;
//
// import lombok.AllArgsConstructor;
// import org.springframework.cloud.gateway.config.GatewayProperties;
// import org.springframework.cloud.gateway.route.RouteLocator;
// import org.springframework.cloud.gateway.support.NameUtils;
// import org.springframework.context.annotation.Primary;
// import org.springframework.stereotype.Component;
// import springfox.documentation.swagger.web.SwaggerResource;
// import springfox.documentation.swagger.web.SwaggerResourcesProvider;
//
// import java.util.ArrayList;
// import java.util.List;
//
// /**
//  * @program: xy-parent
//  * @description: swagegr
//  * @author: wxy
//  * @create: 2020-11-28 22:26
//  **/
// @Component
// @Primary
// @AllArgsConstructor
// public class SwaggerProvider implements SwaggerResourcesProvider {
//     public static final String API_URI = "/v2/api-docs";
//     private final RouteLocator routeLocator;
//     private final GatewayProperties gatewayProperties;
//
//
//     @Override
//     public List<SwaggerResource> get() {
//         List<SwaggerResource> resources = new ArrayList<>();
//         List<String> routes = new ArrayList<>();
//         routeLocator.getRoutes().subscribe(route -> routes.add(route.getId()));
//         gatewayProperties.getRoutes().stream().filter(routeDefinition -> routes.contains(routeDefinition.getId()))
//             .forEach(routeDefinition -> routeDefinition.getPredicates().stream()
//                 .filter(predicateDefinition -> ("Path").equalsIgnoreCase(predicateDefinition.getName()))
//                 .forEach(predicateDefinition -> resources.add(swaggerResource(routeDefinition.getId(),
//                     predicateDefinition.getArgs().get(NameUtils.GENERATED_NAME_PREFIX + "0")
//                         .replace("/**", API_URI)))));
//         return resources;
//     }
//
//     private SwaggerResource swaggerResource(String name, String location) {
//         SwaggerResource swaggerResource = new SwaggerResource();
//         swaggerResource.setName(name);
//         swaggerResource.setLocation(location);
//         swaggerResource.setSwaggerVersion("2.0");
//         return swaggerResource;
//     }
// }
//
