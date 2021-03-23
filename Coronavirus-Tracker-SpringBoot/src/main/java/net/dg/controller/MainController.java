package net.dg.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import net.dg.model.RecoveryStats;
import net.dg.model.DeathStats;
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
    
    @GetMapping("/deaths")
    public String viewHomeDeaths(Model model) {
        List<DeathStats> deathStats = coronavirusService.getAllDeaths();
        int totalDeaths = deathStats.stream().mapToInt(stat -> stat.getTotalDeaths()).sum();
        int totalNewDeaths = deathStats.stream().mapToInt(stat -> stat.getChangesFromPrevDay()).sum();
        model.addAttribute("deathStats", deathStats);
        model.addAttribute("totalReportedDeaths", totalDeaths);
        model.addAttribute("totalNewDeaths", totalNewDeaths);
  
        return "deaths";
    }
    
    @GetMapping("/recoveries")
    public String viewHomeRecoveries(Model model) {
        List<RecoveryStats> recoveryStats = coronavirusService.getAllRecovery();
        int totalRecoveries = recoveryStats.stream().mapToInt(stat -> stat.getTotalRecoveries()).sum();
        int totalNewRecoveries = recoveryStats.stream().mapToInt(stat -> stat.getChangesFromPrevDay()).sum();
        model.addAttribute("recoveryStats", recoveryStats);
        model.addAttribute("totalReportedRecoveries", totalRecoveries);
        model.addAttribute("totalNewRecoveries", totalNewRecoveries);
  
        return "recoveries";
    }
}
