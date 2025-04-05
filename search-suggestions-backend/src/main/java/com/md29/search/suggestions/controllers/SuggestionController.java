package com.md29.search.suggestions.controllers;

import com.md29.search.suggestions.services.SuggestionService;
import jakarta.websocket.server.PathParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/suggestion")
@CrossOrigin(origins = "http://localhost:3000")
public class SuggestionController {
    @Autowired
    private SuggestionService suggestionService;

    @GetMapping("/{searchWord}")
    @ResponseStatus(HttpStatus.OK)
    public List<String> getSuggestions(@PathVariable String searchWord) {
        return suggestionService.suggestedProducts(searchWord.toLowerCase());
    }
}
