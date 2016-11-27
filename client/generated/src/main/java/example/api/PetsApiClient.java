package example.api;

import org.springframework.cloud.netflix.feign.FeignClient;
import io.swagger.configuration.ClientConfiguration;

@FeignClient(name="${swaggerPetstore.name:swaggerPetstore}", url="${swaggerPetstore.url:http://petstore.swagger.io/v1}", configuration = ClientConfiguration.class)
public interface PetsApiClient extends PetsApi {
}