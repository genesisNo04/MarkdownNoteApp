package com.example.MarkDownNoteTakingApp.Service.Impl;

//flexmark is a library used to parse the markdown text
import com.vladsch.flexmark.ext.autolink.AutolinkExtension;
import com.vladsch.flexmark.ext.gfm.strikethrough.StrikethroughExtension;
import com.vladsch.flexmark.ext.tables.TablesExtension;
import com.vladsch.flexmark.html.HtmlRenderer;
import com.vladsch.flexmark.parser.Parser;
import com.vladsch.flexmark.util.ast.Node;
import com.vladsch.flexmark.util.data.MutableDataSet;
import org.springframework.stereotype.Service;

import java.util.Arrays;

@Service
public class MarkdownService {

    private final Parser parser;
    private final HtmlRenderer renderer;

    public MarkdownService() {
        //create a config object to set options for parser and renderer.
        //for flexmark only
        MutableDataSet options = new MutableDataSet();
        //Parser.EXTENSIONS a key to tell what extension to use
        //AutolinkExtension: auto converts URL into clickable link
        //StrikethroughExtension: support strikethrough text using ~~text~~
        //TablesExtension: support table
        options.set(Parser.EXTENSIONS, Arrays.asList(
                AutolinkExtension.create(),
                StrikethroughExtension.create(),
                TablesExtension.create()
        ));

        //build parser with the options
        this.parser = Parser.builder(options).build();
        //build renderer with the options
        this.renderer = HtmlRenderer.builder(options).build();
    }

    public String renderToHtml(String markdown) {
        //take the input markdown string and parse it into a document tree(node)
        //Every markdown element(heading, paragraph, list, table) becomes a node in the tree
        Node document = parser.parse(markdown);
        //convert AST tree into HTML string
        return renderer.render(document);
    }
}
