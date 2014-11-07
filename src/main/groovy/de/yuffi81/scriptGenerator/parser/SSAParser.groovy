package de.yuffi81.scriptGenerator.parser

import de.yuffi81.scriptGenerator.model.DialogLine


class SSAParser implements ISubtitleParser {

    private static String concatTextTokens (List<String> tokens) {
        tokens.join(",")
    }

    @Override
    List<DialogLine> parseLines(String[] lines) {
        List<DialogLine> dialogLines = []

        lines.each {String line ->

            if (line.startsWith("Dialogue")){
                DialogLine dialogLine = parseLine(line)

                if (dialogLine) {
                    dialogLines << dialogLine
                }
            }
        }

        dialogLines
    }

    @Override
    DialogLine parseLine(String line) {
        String     style
        String     text
        DialogLine dialogLine = null

        //Tokenize the line
        String[] tokens = line.split(",")

        if (tokens.size() > 9) {
            style = tokens[3]

            //text itself mey contain comas, so lets concat all tokens beginning at 9 to one string
            text  = concatTextTokens( tokens[9..tokens.size()-1])

            dialogLine = new DialogLine(style, text)
        } else {
            //TODO: SSA parse exception
        }

        dialogLine
    }
}
