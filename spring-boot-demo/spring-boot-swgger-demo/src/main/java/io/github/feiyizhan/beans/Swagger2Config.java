package io.github.feiyizhan.beans;

/**
 * Swagger2的配置
 * @author 徐明龙 XuMingLong 2020-02-25
 */
//@Configuration
//@EnableSwagger2
public class Swagger2Config {
//    @Value(value = "${spring.profiles.active}")
//    String environment;
//    @Value(value = "${spring.application.name}")
//    String applicationName;
//    @Value(value = "${spring.application.version}")
//    String applicationVersion;
//    @Value(value = "${spring.application.controller.packages}")
//    String[] controllerPackages;
//
//    String applicationDescription;
//
//    @Value(value = "${spring.application.description}")
//    private void setApplicationDescription(String applicationDescription ){
//        this.applicationDescription = "";
//        if(StringUtils.isBlank(applicationDescription)){
//            return;
//        }
//        try {
//            byte[] data = applicationDescription.getBytes("ISO-8859-1");
//            this.applicationDescription = new String(data,"UTF-8");
//        } catch (UnsupportedEncodingException e) {
//            e.printStackTrace();
//        }
//    }
//
//
//    /**
//     * 业务系统的token认证机制
//     * @author 徐明龙 XuMingLong
//     * @createDate 2018-04-17
//     * @return
//     */
//    public List<Parameter> getTokenPar() {
//        ParameterBuilder tokenPar = new ParameterBuilder();
//        List<Parameter> pars = new ArrayList<Parameter>();
//        tokenPar.name("authorization")
//                .description("认证信息")
//                .modelRef(new ModelRef("string"))
//                .parameterType("header")
//                .required(true)
//                .build();
//        pars.add(tokenPar.build());
//
//        return pars;
//    }
//
//
//    @Bean
//    public Docket createRestApi() {
//        ApiSelectorBuilder builder = new Docket(DocumentationType.SWAGGER_2)
////            .enable(environment != null && (environment.equals("dev") || environment.equals("test"))? true : false)
//            .apiInfo(apiInfo())
//            .globalOperationParameters(getTokenPar())
//            .select();
////        if(controllerPackages!=null && controllerPackages.length >0){
////            Stream.of(controllerPackages)
////                .map(RequestHandlerSelectors::basePackage).forEach(r->{
////                builder.apis(r);
////            });
////        }
//
//        if(controllerPackages!=null && controllerPackages.length >0){
//            Predicate<RequestHandler> selector = Stream.of(controllerPackages)
//                .map(RequestHandlerSelectors::basePackage)
//                .reduce((r1,r2)->r1.or(r2)).orElse(null);
//            builder.apis(selector);
//        }
//        return builder
//                .paths(PathSelectors.any())
//                .build();
//    }
//
//    private ApiInfo apiInfo() {
//        return new ApiInfoBuilder()
//                .title(applicationName)
//                .description(applicationDescription)
//                .termsOfServiceUrl("http://")
//                .version(applicationVersion).build();
//    }
}
