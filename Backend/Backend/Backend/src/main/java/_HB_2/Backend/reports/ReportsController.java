package _HB_2.Backend.reports;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/reports")
public class ReportsController {

    @Autowired
    ReportsService reportsService;

    @PostMapping("/createReports")
    Reports createReports (@RequestParam int reporter_id,
                           @RequestParam int reported_id,
                           @RequestBody Reports reports){

        return reportsService.createReport(reporter_id, reported_id, reports);
    }

    @GetMapping("/getUserReports")
    List<Reports> getUserReports(@RequestParam int userId){
        return reportsService.getUserReports(userId);
    }

}
