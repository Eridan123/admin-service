package kg.ecommerce.admin_service.api;

import kg.ecommerce.admin_service.models.dto.StockDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

@FeignClient(value = "stock-client", url = "${service.stock.endpoint}")
public interface StockServiceClient {

  @RequestMapping(
          method = {RequestMethod.GET},
          value = {"/api/v1/stocks"},
          consumes = {"application/json"}
  )
  List<StockDto> getStocks();

  @RequestMapping(
          method = {RequestMethod.GET},
          value = {"/api/v1/stocks/{id}"},
          consumes = {"application/json"}
  )
  StockDto getStock(@PathVariable Long id);

  @RequestMapping(
          method = {RequestMethod.PUT},
          value = {"/api/v1/stocks"},
          consumes = {"application/json"}
  )
  void saveStocks(@RequestBody StockDto stockDtos);

  @RequestMapping(
          method = {RequestMethod.DELETE},
          value = {"/api/v1/stocks/{id}"},
          consumes = {"application/json"}
  )
  Boolean deleteStock(@PathVariable Long id);
}
