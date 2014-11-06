package de.yuffi81.scriptGenerator.parser

import de.yuffi81.scriptGenerator.model.DialogLine

interface ISubtitleParser {

    /**
     * parses array of lines into List of dialog lines
     * @param lines to parse
     * @return list of parsed dialog lines
     */
    List<DialogLine>    parseLines  (String[] lines)

    /**
     * parses single line into dialog line
     * @param line to parse
     * @return parsed dialog line
     */
    DialogLine          parseLine   (String   line)
}
