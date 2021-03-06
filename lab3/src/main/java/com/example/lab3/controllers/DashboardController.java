package com.example.lab3.controllers;

import com.example.lab3.models.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/dashboard")
public class DashboardController {

    @Autowired
    InventoryRepository inventoryRepository;

    @GetMapping("/catalog/form")
    public String getQueryForm(Model model)	{
        model.addAttribute("catalogQuery", new CatalogQueryDTO());
        return "dashboard/catalog/query-form";
    }

    @PostMapping("/catalog/query")
    public String getQueryResults(CatalogQueryDTO query, Model model) {
        List<PlantInventoryEntry> plants = inventoryRepository.findAvailablePlants(query.getName(),
                query.getRentalPeriod().getStartDate(),
                query.getRentalPeriod().getEndDate());
        model.addAttribute("plants", plants);

        PurchaseOrderDTO po = new PurchaseOrderDTO();
        po.setRentalPeriod(query.getRentalPeriod());
        model.addAttribute("po", po);

        return "dashboard/catalog/query-result";
    }
}
