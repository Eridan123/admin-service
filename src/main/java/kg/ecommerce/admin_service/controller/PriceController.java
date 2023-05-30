package kg.ecommerce.admin_service.controller;

import kg.ecommerce.admin_service.api.PriceServiceClient;
import kg.ecommerce.admin_service.api.StockServiceClient;
import kg.ecommerce.admin_service.models.dto.PriceDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/prices")
@RequiredArgsConstructor
public class PriceController {
  private final PriceServiceClient priceServiceClient;

  @GetMapping
  public String list(Model model)
  {
    List<PriceDto> response = priceServiceClient.getPrices();
    System.out.println(response);
    model.addAttribute("prices", response);
    return "prices/list";
  }

  @GetMapping("/add")
  public String add(Model model) {
    model.addAttribute("price", new PriceDto());
    return "prices/add";
  }

  @GetMapping("/update/{id}")
  public String update(Model model, @PathVariable Long id) {
    PriceDto price = priceServiceClient.getPrice(id);
    model.addAttribute("price", price);
    return "prices/add";
  }

  @PostMapping("/save")
  public String save(@ModelAttribute("price") PriceDto priceDto) {
    priceServiceClient.savePrice(priceDto);
    return "redirect:/prices";
  }

  @PostMapping("/{id}")
  public String delete(@PathVariable Long id) {
    priceServiceClient.deletePrice(id);
    return "redirect:/prices";
  }
}
