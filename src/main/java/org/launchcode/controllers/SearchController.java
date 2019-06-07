package org.launchcode.controllers;

import org.launchcode.models.JobData;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.HashMap;

import static org.launchcode.controllers.ListController.columnChoices;
import static org.launchcode.models.JobData.findByColumnAndValue;

/**
 * Created by LaunchCode
 */
@Controller
@RequestMapping("search")
public class SearchController {

    @RequestMapping(value = "")
    public String search(Model model) {
        model.addAttribute("columns", columnChoices);
        return "search";
    }

    @RequestMapping(value = "results")
    public String resultsByType(Model model, @RequestParam String searchType, String searchTerm) {

        model.addAttribute("column", searchType);
        model.addAttribute("searchTerm", searchTerm);
        model.addAttribute("columns", columnChoices);

        ArrayList<HashMap<String, String>> results;

        if (searchType.equals("all")) {
            results = JobData.findByValue(searchTerm);

        } else {
            results = JobData.findByColumnAndValue(searchType, searchTerm);
        }

        model.addAttribute("results", results);
        int numResults = results.size();
        model.addAttribute("numResults", numResults);


        return "search";
    }
}
