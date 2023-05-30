package kg.ecommerce.admin_service.api;

import kg.ecommerce.admin_service.models.dto.ProductDto;
import kg.ecommerce.admin_service.models.dto.ProductDto;
import kg.ecommerce.admin_service.models.dto.StockDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

@FeignClient(value = "product-client", url = "${service.product.endpoint}")
public interface ProductServiceClient {

  @RequestMapping(
          method = {RequestMethod.GET},
          value = {"/api/v1/products"},
          consumes = {"application/json"}
  )
  List<ProductDto> getProducts();

  @RequestMapping(
          method = {RequestMethod.GET},
          value = {"/api/v1/products/{id}"},
          consumes = {"application/json"}
  )
  ProductDto getProduct(@PathVariable Long id);

  @RequestMapping(
          method = {RequestMethod.PUT},
          value = {"/api/v1/products"},
          consumes = {"application/json"}
  )
  void saveProduct(@RequestBody ProductDto priceDto);

  @RequestMapping(
          method = {RequestMethod.POST},
          value = {"/api/v1/products/delete/{id}"},
          consumes = {"application/json"}
  )
  Boolean deleteProduct(@PathVariable Long id);
}
