package com.example.MarkDownNoteTakingApp.Service.Impl;

import org.languagetool.JLanguageTool;
import org.languagetool.language.*;
import org.languagetool.rules.RuleMatch;
import org.languagetool.Language;
import org.languagetool.Languages;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;

@Service
public class GrammarCheckService {

    private final JLanguageTool langTool;

    public GrammarCheckService() throws IOException {
        langTool = new JLanguageTool(Languages.getLanguageForShortCode("en-US"));
    }

    public List<RuleMatch> checkGrammar(String text) throws IOException {
        return langTool.check(text);
    }
}
