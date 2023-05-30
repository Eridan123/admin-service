//package kg.ecommerce.admin_service.config;
//
//import com.fasterxml.jackson.annotation.JsonInclude;
//import com.fasterxml.jackson.databind.DeserializationFeature;
//import com.fasterxml.jackson.databind.ObjectMapper;
//import com.fasterxml.jackson.databind.SerializationFeature;
//import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
//import com.veon.eurasia.onecfacade.config.properties.BasicConfigOneCPrice;
//import com.veon.eurasia.onecfacade.config.properties.BasicConfigOneCStock;
//import java.util.List;
//import feign.Client;
//import feign.codec.Decoder;
//import feign.okhttp.OkHttpClient;
//import lombok.RequiredArgsConstructor;
//import okhttp3.Protocol;
//import org.springframework.beans.factory.ObjectFactory;
//import org.springframework.boot.autoconfigure.http.HttpMessageConverters;
//import org.springframework.boot.context.properties.EnableConfigurationProperties;
//import org.springframework.cloud.openfeign.support.PageJacksonModule;
//import org.springframework.cloud.openfeign.support.ResponseEntityDecoder;
//import org.springframework.cloud.openfeign.support.SortJacksonModule;
//import org.springframework.cloud.openfeign.support.SpringDecoder;
//import org.springframework.context.annotation.Bean;
//import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
//
//
///**
// * FeignConfiguration.
// *
// * @author Petkov Vyacheslav (vpetkov@beeline.kz)
// */
//@RequiredArgsConstructor
//public class FeignDefaultConfiguration {
//
//  private static ObjectMapper objectMapper;
//
//  /**
//   * Feign client.
//   *
//   * @return Client
//   */
//  @Bean
//  public Client client() {
//    final okhttp3.OkHttpClient okHttp3Client = new okhttp3.OkHttpClient.Builder()
//            .protocols(List.of(Protocol.HTTP_1_1))
//            .build();
//    return new OkHttpClient(okHttp3Client);
//  }
//
//  /**
//   * FeignDecoder.
//   * @return Decoder
//   */
//  @Bean
//  public Decoder feignDecoder() {
//    final ObjectFactory<HttpMessageConverters> objectFactory = () ->
//            new HttpMessageConverters(new MappingJackson2HttpMessageConverter(feignObjectMapper()));
//    return new ResponseEntityDecoder(new SpringDecoder(objectFactory, new EmptyObjectProvider<>()));
//  }
//
//  protected synchronized ObjectMapper feignObjectMapper() {
//    if (objectMapper != null) {
//      return objectMapper;
//    }
//
//    objectMapper = new ObjectMapper()
//            .registerModule(new JavaTimeModule())
//            .registerModule(new PageJacksonModule())
//            .registerModule(new SortJacksonModule())
//            .enable(DeserializationFeature.ACCEPT_EMPTY_STRING_AS_NULL_OBJECT)
//            .enable(DeserializationFeature.ACCEPT_EMPTY_ARRAY_AS_NULL_OBJECT)
//            .disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES)
//            .disable(DeserializationFeature.FAIL_ON_IGNORED_PROPERTIES)
//            .disable(SerializationFeature.WRITE_DATE_TIMESTAMPS_AS_NANOSECONDS)
//            .disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS)
//            .disable(DeserializationFeature.FAIL_ON_MISSING_CREATOR_PROPERTIES)
//            .setSerializationInclusion(JsonInclude.Include.NON_EMPTY)
//            .setSerializationInclusion(JsonInclude.Include.NON_NULL);
//
//    return objectMapper;
//  }
//}