package com.vladsch.clionarduinoplugin.generators.cmake;

import com.vladsch.flexmark.util.data.DataHolder;
import com.vladsch.flexmark.util.data.MutableDataHolder;
import com.vladsch.flexmark.util.data.MutableDataSetter;

public class CMakeParserOptions implements MutableDataSetter {
    public boolean autoConfig;
    public boolean blockComments;
    public boolean lineContinuation;
    public boolean astLineEndEol;
    public boolean astComments;
    public boolean astCommandBlocks;
    public boolean astBlankLines;
    public boolean astArgumentSeparators;
    public boolean astCommentedOutCommands;
    public int maxErrorLineRecoveries;

    CMakeParserOptions() {
        this((DataHolder) null);
    }

    CMakeParserOptions(CMakeParserOptions other) {
        autoConfig = other.autoConfig;
        blockComments = other.blockComments;
        lineContinuation = other.lineContinuation;
        astLineEndEol = other.astLineEndEol;
        astComments = other.astComments;
        astCommandBlocks = other.astCommandBlocks;
        astBlankLines = other.astBlankLines;
        astArgumentSeparators = other.astArgumentSeparators;
        astCommentedOutCommands = other.astCommentedOutCommands;
        maxErrorLineRecoveries = other.maxErrorLineRecoveries;
    }

    CMakeParserOptions(DataHolder options) {
        autoConfig = CMakeParser.AUTO_CONFIG.getFrom(options);
        blockComments = CMakeParser.BRACKET_COMMENTS.getFrom(options);
        lineContinuation = CMakeParser.LINE_CONTINUATION.getFrom(options);
        astLineEndEol = CMakeParser.AST_LINE_END_EOL.getFrom(options);
        astComments = CMakeParser.AST_COMMENTS.getFrom(options);
        astCommandBlocks = CMakeParser.AST_COMMAND_BLOCKS.getFrom(options);
        astBlankLines = CMakeParser.AST_BLANK_LINES.getFrom(options);
        astArgumentSeparators = CMakeParser.AST_ARGUMENT_SEPARATORS.getFrom(options);
        astCommentedOutCommands = CMakeParser.AST_COMMENTED_OUT_COMMANDS.getFrom(options);
        maxErrorLineRecoveries = CMakeParser.MAX_ERROR_LINE_RECOVERIES.getFrom(options);
    }

    @Override
    public MutableDataHolder setIn(final MutableDataHolder dataHolder) {
        dataHolder.set(CMakeParser.AUTO_CONFIG, autoConfig);
        dataHolder.set(CMakeParser.BRACKET_COMMENTS, blockComments);
        dataHolder.set(CMakeParser.LINE_CONTINUATION, lineContinuation);
        dataHolder.set(CMakeParser.AST_LINE_END_EOL, astLineEndEol);
        dataHolder.set(CMakeParser.AST_COMMENTS, astComments);
        dataHolder.set(CMakeParser.AST_COMMAND_BLOCKS, astCommandBlocks);
        dataHolder.set(CMakeParser.AST_BLANK_LINES, astBlankLines);
        dataHolder.set(CMakeParser.AST_ARGUMENT_SEPARATORS, astArgumentSeparators);
        dataHolder.set(CMakeParser.AST_COMMENTED_OUT_COMMANDS, astCommentedOutCommands);
        dataHolder.set(CMakeParser.MAX_ERROR_LINE_RECOVERIES, maxErrorLineRecoveries);
        return dataHolder;
    }
}
