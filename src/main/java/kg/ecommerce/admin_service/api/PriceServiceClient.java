package kg.ecommerce.admin_service.api;

import kg.ecommerce.admin_service.models.dto.PriceDto;
import kg.ecommerce.admin_service.models.dto.StockDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

@FeignClient(value = "price-client", url = "${service.price.endpoint}")
public interface PriceServiceClient {

  @RequestMapping(
          method = {RequestMethod.GET},
          value = {"/api/v1/prices"},
          consumes = {"application/json"}
  )
  List<PriceDto> getPrices();

  @RequestMapping(
          method = {RequestMethod.GET},
          value = {"/api/v1/prices/{id}"},
          consumes = {"application/json"}
  )
  PriceDto getPrice(@PathVariable Long id);

  @RequestMapping(
          method = {RequestMethod.PUT},
          value = {"/api/v1/prices"},
          consumes = {"application/json"}
  )
  void savePrice(@RequestBody PriceDto priceDto);

  @RequestMapping(
          method = {RequestMethod.DELETE},
          value = {"/api/v1/prices/{id}"},
          consumes = {"application/json"}
  )
  Boolean deletePrice(@PathVariable Long id);
}
