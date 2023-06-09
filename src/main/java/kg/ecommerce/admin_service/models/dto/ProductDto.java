package kg.ecommerce.admin_service.models.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProductDto {
  private Long id;
  private String sku;
  private String name;
  private String description;
  private String image;
  private String category;
  private LocalDateTime createdDate = LocalDateTime.now();
  private LocalDateTime lastModifiedDate = LocalDateTime.now();
}
