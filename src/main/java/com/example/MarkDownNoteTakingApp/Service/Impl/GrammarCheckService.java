package com.example.MarkDownNoteTakingApp.Service.Impl;

import org.languagetool.JLanguageTool;
import org.languagetool.language.AmericanEnglish;
import org.languagetool.rules.RuleMatch;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;

@Service
@Lazy
public class GrammarCheckService {

    public GrammarCheckService() {

    }

    public List<RuleMatch> checkGrammar(String text) throws IOException {
        JLanguageTool langTool = new JLanguageTool(new AmericanEnglish());
        return langTool.check(text);
    }
}
