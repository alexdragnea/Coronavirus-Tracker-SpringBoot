package net.dg.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import net.dg.model.LocationStats;
import net.dg.service.CoronavirusService;

@Controller
public class MainController {

	@Autowired
    CoronavirusService coronavirusService;

    @GetMapping("/")
    public String home(Model model) {
        List<LocationStats> allStats = coronavirusService.getAllStats();
        int totalReportedCases = allStats.stream().mapToInt(stat -> stat.getTotalCases()).sum();
        int totalNewCases = allStats.stream().mapToInt(stat -> stat.getChangesFromPrevDay()).sum();
        model.addAttribute("locationStats", allStats);
        model.addAttribute("totalReportedCases", totalReportedCases);
        model.addAttribute("totalNewCases", totalNewCases);

        return "home";
    }
}
