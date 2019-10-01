package com.vladsch.clionarduinoplugin.generators.cmake;

import com.vladsch.flexmark.spec.SpecExample;
import com.vladsch.flexmark.test.ComboSpecTestCase;
import com.vladsch.flexmark.util.data.DataHolder;
import com.vladsch.flexmark.util.data.MutableDataSet;
import org.junit.runners.Parameterized;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CMakeFormatterSpecTest extends ComboSpecTestCase {
    private static final String SPEC_RESOURCE = "/cmake_formatter_spec.md";
    public static final String SPEC_FILE_URL = "/Users/vlad/src/projects/CLionArduinoPlugin/test-resources" + SPEC_RESOURCE;

    private static final DataHolder OPTIONS = new MutableDataSet()
            .set(CMakeParser.AUTO_CONFIG, false)
            //.set(FormattingRenderer.INDENT_SIZE, 2)
            //.set(HtmlRenderer.PERCENT_ENCODE_URLS, true)
            //.set(Parser.EXTENSIONS, Collections.singleton(FormatterExtension.create()))
            .set(CMakeParser.AST_BLANK_LINES, true)
            .set(CMakeParser.AST_LINE_END_EOL, true)
            .set(CMakeParser.AST_ARGUMENT_SEPARATORS, true);

    private static final Map<String, DataHolder> optionsMap = new HashMap<String, DataHolder>();
    static {
        optionsMap.put("auto-config", new MutableDataSet().set(CMakeParser.AUTO_CONFIG, true));
        optionsMap.put("bracket-comments", new MutableDataSet().set(CMakeParser.BRACKET_COMMENTS, true));
        optionsMap.put("line-cont", new MutableDataSet().set(CMakeParser.LINE_CONTINUATION, true));

        optionsMap.put("no-preserve-whitespace", new MutableDataSet().set(CMakeFormatter.PRESERVE_WHITESPACE, false));
        optionsMap.put("no-preserve-seps", new MutableDataSet().set(CMakeFormatter.PRESERVE_ARGUMENT_SEPARATOR, false));
        optionsMap.put("no-preserve-breaks", new MutableDataSet().set(CMakeFormatter.PRESERVE_LINE_BREAKS, false));
        optionsMap.put("split-seps", new MutableDataSet()
                .set(CMakeFormatter.ARGUMENT_LIST_PREFIX, "\n")
                .set(CMakeFormatter.ARGUMENT_LIST_SUFFIX, "\n")
                .set(CMakeFormatter.ARGUMENT_SEPARATOR, "\n")
        );
        optionsMap.put("collapse-whitespace", new MutableDataSet().set(CMakeFormatter.COLLAPSE_WHITESPACE, true));

        optionsMap.put("ast-line-eol", new MutableDataSet().set(CMakeParser.AST_LINE_END_EOL, true));
        optionsMap.put("ast-comments", new MutableDataSet().set(CMakeParser.AST_COMMENTS, true));
        optionsMap.put("ast-blank", new MutableDataSet().set(CMakeParser.AST_BLANK_LINES, true));
        optionsMap.put("ast-arg-seps", new MutableDataSet().set(CMakeParser.AST_ARGUMENT_SEPARATORS, true));
        optionsMap.put("commented-out", new MutableDataSet().set(CMakeParser.AST_COMMENTED_OUT_COMMANDS, true));
    }

    private static final CMakeIParser PARSER = new CMakeIParser(OPTIONS);

    // The spec says URL-escaping is optional, but the examples assume that it's enabled.
    private static final CMakeFormatter RENDERER = CMakeFormatter.build(OPTIONS);

    private static DataHolder optionsSet(String optionSet) {
        if (optionSet == null) return null;
        return optionsMap.get(optionSet);
    }

    public CMakeFormatterSpecTest(SpecExample example) {
        super(example);
    }

    @Parameterized.Parameters(name = "{0}")
    public static List<Object[]> data() {
        return getTestData(SPEC_RESOURCE, SPEC_FILE_URL);
    }

    @Override
    public DataHolder options(String optionSet) {
        return optionsSet(optionSet);
    }

    @Override
    public String getSpecResourceName() {
        return SPEC_RESOURCE;
    }

    @Override
    public CMakeIParser parser() {
        return PARSER;
    }

    @Override
    public CMakeFormatter renderer() {
        return RENDERER;
    }
}
