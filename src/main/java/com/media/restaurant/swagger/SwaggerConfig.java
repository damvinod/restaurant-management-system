package com.media.restaurant.swagger;

import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.RequestMethod;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.builders.ResponseMessageBuilder;
import springfox.documentation.schema.ModelRef;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.service.ResponseMessage;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;
import com.media.restaurant.constant.CommonConstant;

@Configuration
@EnableSwagger2
public class SwaggerConfig {

  /**
   * This method will display the request details.
   *
   * @return
   */
  @Bean
  public Docket apiInformation() {
    return new Docket(DocumentationType.SWAGGER_2).groupName(CommonConstant.GROUP_NAME)
        .apiInfo(generalInformation()).useDefaultResponseMessages(false)
        .globalResponseMessage(RequestMethod.GET, responseMessageList())
        .globalResponseMessage(RequestMethod.POST, responseMessageList())
        .globalResponseMessage(RequestMethod.PUT, responseMessageList()).select()
        .apis(RequestHandlerSelectors.basePackage(CommonConstant.CONTROLLER_PACKAGE))
        .paths(PathSelectors.any())
        .build();
  }

  private ApiInfo generalInformation() {
    Contact contact = new Contact(CommonConstant.API_INFO_CONTACT_NAME,
        CommonConstant.API_INFO_CONTACT_URL, CommonConstant.API_INFO_CONTACT_EMAIL);

    return new ApiInfoBuilder().title(CommonConstant.TITLE).description(CommonConstant.DESCRIPTION)
        .termsOfServiceUrl(CommonConstant.API_INFO_TERMS_OF_SERVICE_URL).contact(contact)
        .license(CommonConstant.API_INFO_LICENSE).licenseUrl(CommonConstant.API_INFO_LICENSE_URL)
        .version(CommonConstant.API_INFO_VERSION).build();
  }



  private List<ResponseMessage> responseMessageList() {
    final List<ResponseMessage> responseMessageList = new ArrayList<ResponseMessage>();
    responseMessageList
    .add(new ResponseMessageBuilder().code(HttpStatus.INTERNAL_SERVER_ERROR.value())
        .message(HttpStatus.INTERNAL_SERVER_ERROR.getReasonPhrase())
        .responseModel(new ModelRef(CommonConstant.ERROR)).build());
    responseMessageList.add(new ResponseMessageBuilder().code(HttpStatus.FORBIDDEN.value())
        .message(HttpStatus.FORBIDDEN.getReasonPhrase()).build());
    responseMessageList.add(new ResponseMessageBuilder().code(HttpStatus.NOT_FOUND.value())
        .message(HttpStatus.NOT_FOUND.getReasonPhrase()).build());
    responseMessageList.add(new ResponseMessageBuilder().code(HttpStatus.OK.value())
        .message(HttpStatus.OK.getReasonPhrase()).build());
    responseMessageList.add(new ResponseMessageBuilder().code(HttpStatus.BAD_REQUEST.value())
        .message(HttpStatus.BAD_REQUEST.getReasonPhrase()).build());
    return responseMessageList;
  }
}
