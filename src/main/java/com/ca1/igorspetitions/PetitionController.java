package com.ca1.igorspetitions;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/petitions")
public class PetitionController {

    // In-memory list to store petitions
    private List<Petition> petitions = new ArrayList<>();

    // Start with some random data
    public PetitionController() {
        petitions.add(new Petition("Save the Forest", "A petition to save the rainforest."));
        petitions.add(new Petition("Support Animal Rights", "A petition to support animal rights globally."));
    }

    // Getter for the petitions list (for testing)
    public List<Petition> getPetitions() {
        return petitions;
    }

    // Page to view all petitions
    @GetMapping("/all")
    public String viewAllPetitions(Model model) {
        model.addAttribute("petitions", petitions);
        return "viewAll";
    }

    // Page to create a new petition
    @GetMapping("/create")
    public String showCreateForm() {
        return "createPetition";
    }

    @PostMapping("/create")
    public String createPetition(@RequestParam String title, @RequestParam String description) {
        petitions.add(new Petition(title, description));
        return "redirect:/petitions/all";  // Redirect to view all petitions after creation
    }

    // Page to search petitions
    @GetMapping("/search")
    public String showSearchForm() {
        return "searchPetition";
    }

    // Page to view search results
    @PostMapping("/search")
    public String searchPetitions(@RequestParam String searchTerm, Model model) {
        List<Petition> results = new ArrayList<>();
        System.out.println("Searching for: " + searchTerm); // Log the search term

        // Loop through all petitions and check if the title contains the search term
        for (Petition petition : petitions) {
            if (petition.getTitle().toLowerCase().contains(searchTerm.toLowerCase())) { // Ignore case for search
                results.add(petition);
            }
        }

        // If no results found, log it
        if (results.isEmpty()) {
            System.out.println("No petitions found matching the search term: " + searchTerm);
        }

        model.addAttribute("petitions", results);
        return "searchResults";  // Return the view to display search results
    }

    // Page to view a specific petition
    @GetMapping("/view")
    public String viewPetition(@RequestParam int index, Model model) {
        Petition petition = petitions.get(index);
        model.addAttribute("petition", petition);
        model.addAttribute("index", index);  // Pass the index separately
        return "viewPetition";
    }

    // Page to sign a petition
    @PostMapping("/sign")
    public String signPetition(@RequestParam int index, @RequestParam String name, @RequestParam String email) {
        Petition petition = petitions.get(index);
        petition.addSignatory(new Signatory(name, email));  // Add the new signatory
        return "redirect:/petitions/view?index=" + index;  // Redirect to the petition details page
    }

}
