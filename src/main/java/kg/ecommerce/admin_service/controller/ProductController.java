package kg.ecommerce.admin_service.controller;

import jakarta.servlet.http.HttpServletRequest;
import kg.ecommerce.admin_service.models.dto.ProductDto;
import kg.ecommerce.admin_service.service.FilesStorageService;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import kg.ecommerce.admin_service.api.ProductServiceClient;
import kg.ecommerce.admin_service.models.dto.ProductDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.List;

@Controller
@RequestMapping("/products")
@RequiredArgsConstructor
public class ProductController {

  private final ProductServiceClient productServiceClient;
  private final FilesStorageService storageService;

  @GetMapping
  public String list(Model model)
  {
    List<ProductDto> response = productServiceClient.getProducts();
    model.addAttribute("products", response);
    return "products/list";
  }

  @GetMapping("/add")
  public String add(Model model) {
    model.addAttribute("product", new ProductDto());
    return "products/add";
  }

  @GetMapping("/update/{id}")
  public String update(Model model, @PathVariable Long id) {
    ProductDto product = productServiceClient.getProduct(id);
    model.addAttribute("product", product);
    return "products/add";
  }

  @PostMapping(value = "/save",  consumes = { MediaType.MULTIPART_FORM_DATA_VALUE })
  public String save(@ModelAttribute("product") ProductDto productDto,@ModelAttribute("category") String category, HttpServletRequest request,
                     @RequestPart MultipartFile file) throws IOException {
    productDto.setCategory(category);
    try {
      storageService.save(file);

      String message = "Uploaded the file successfully: " + file.getOriginalFilename();
      productDto.setImage(file.getOriginalFilename());
      productServiceClient.saveProduct(productDto);
    } catch (Exception e) {
      e.printStackTrace();
    }
    return "redirect:/products";
  }

  @GetMapping("/files/{filename:.+}")
  @ResponseBody
  public ResponseEntity<Resource> getFile(@PathVariable String filename) {
    Resource file = storageService.load(filename);
    return ResponseEntity.ok()
            .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + file.getFilename() + "\"").body(file);
  }

  @PostMapping("/delete/{id}")
  public String delete(@PathVariable Long id) {
    productServiceClient.deleteProduct(id);
    return "redirect:/products";
  }
}
