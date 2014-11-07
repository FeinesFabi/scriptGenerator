package de.yuffi81.scriptGenerator.parser

import de.yuffi81.scriptGenerator.model.DialogLine


class SSAParser implements ISubtitleParser {

    private def lineParser = { String line, List<DialogLine> dialogLines ->
                                if (line.startsWith("Dialogue")){
                                    DialogLine dialogLine = parseLine(line)

                                    if (dialogLine) {
                                        dialogLines << dialogLine
                                    }
                                }
                             }

    @Override
    List<DialogLine> parseLines(String[] lines) {
        List<DialogLine> dialogLines = []

        lines.each {
            lineParser.call(it, dialogLines)
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
            text  = {it.join(",")} .call (tokens[9..tokens.size()-1])

            dialogLine = new DialogLine(style, text)
        } else {
            //TODO: SSA parse exception
        }

        dialogLine
    }

    @Override
    List<DialogLine> parseFile(File file) {
        List<DialogLine> dialogLines = []

        if (file.exists()){
            file.eachLine {
                lineParser.call( it, dialogLines)
            }
        }

        dialogLines
    }

    @Override
    List<DialogLine> parseFile(String filePath) {
        File file = new File (filePath)
        parseFile (file)
    }
}
