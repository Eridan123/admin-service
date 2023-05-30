package kg.ecommerce.admin_service.controller;

import kg.ecommerce.admin_service.api.StockServiceClient;
import kg.ecommerce.admin_service.models.dto.PriceDto;
import kg.ecommerce.admin_service.models.dto.StockDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Repository;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/stocks")
@RequiredArgsConstructor
public class StockController {
  private final StockServiceClient stockServiceClient;

  @GetMapping
  public String list(Model model)
  {
    List<StockDto> response = stockServiceClient.getStocks();
    System.out.println(response);
    model.addAttribute("stocks", response);
    model.addAttribute("hh", "hh");
    return "stocks/list";
  }

  @GetMapping("/add")
  public String add(Model model) {
    model.addAttribute("stock", new StockDto());
    return "stocks/add";
  }

  @PostMapping("/save")
  public String save(@ModelAttribute("stock") StockDto stockDto) {
    stockServiceClient.saveStocks(stockDto);
    return "redirect:/stocks";
  }

  @GetMapping("/update/{id}")
  public String update(Model model, @PathVariable Long id) {
    StockDto stock = stockServiceClient.getStock(id);
    model.addAttribute("stock", stock);
    return "stocks/add";
  }

  @PostMapping("/delete/{id}")
  public String delete(@PathVariable Long id) {
    stockServiceClient.deleteStock(id);
    return "redirect:/stocks";
  }

}
