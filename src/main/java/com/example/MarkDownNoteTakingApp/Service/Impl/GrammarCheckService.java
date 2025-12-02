package com.example.MarkDownNoteTakingApp.Service.Impl;

import org.languagetool.JLanguageTool;
import org.languagetool.rules.RuleMatch;
import org.springframework.stereotype.Service;
import org.languagetool.language.English;

import java.io.IOException;
import java.util.List;

@Service
public class GrammarCheckService {

    private final JLanguageTool langTool;

    public GrammarCheckService() throws IOException {
        langTool = new JLanguageTool(new English());
    }

    public List<RuleMatch> checkGrammar(String text) throws IOException {
        //run through all enabled rules for english
        // each rulematch will tell the start and end and the message show what the error is and suggestion
        return langTool.check(text);

        //after the list is return can run like this to get the info out
//        List<RuleMatch> matches = grammarCheckService.checkGrammar("She dont like it.");
//        for (RuleMatch match : matches) {
//            System.out.println(match.getMessage());
//            System.out.println("Suggestion: " + match.getSuggestedReplacements());
//        }
    }
}
