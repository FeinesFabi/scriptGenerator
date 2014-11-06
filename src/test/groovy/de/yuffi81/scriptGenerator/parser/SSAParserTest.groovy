package de.yuffi81.scriptGenerator.parser

import de.yuffi81.scriptGenerator.model.DialogLine
import spock.lang.*

/**
 * Created by Benjamin on 06.11.2014.
 */
class SSAParserTest extends Specification {

    def "should parse a valid line of dialog" () {

        given:
            String[] lines = [""]
            String line = "Dialogue: 0,0:00:09.16,0:00:10.72,Tarkin,,0,0,0,,And they're heading for the finish line -"
            ISubtitleParser parser = new SSAParser()

        when:
            DialogLine dialogLine = parser.parseLine(line)

        then:
            dialogLine
            dialogLine.speaker == "Tarkin"
            dialogLine.line    == "And they're heading for the finish line -"
    }

    def "should pares a bunch of dialog lines" () {

        given:
            String[] lines = [
                               "Dialogue: 0,0:00:09.16,0:00:10.72,Tarkin,,0,0,0,,And they're heading for the finish line -",
                               "Dialogue: 0,0:00:10.84,0:00:13.30,Foo,,0,0,0,,The dust is too thick, I can't make out who it is!",
                               "Dialogue: 0,0:00:13.30,0:00:13.84,Default,,0,0,0,,Wait -"
                             ]


            ISubtitleParser parser = new SSAParser()

        when:
            List<DialogLine> dialogLines = parser.parseLines(lines)

        then:
            dialogLines

            dialogLines.size() == 3

            DialogLine line1 = dialogLines[0]
            DialogLine line2 = dialogLines[1]
            DialogLine line3 = dialogLines[2]

            line1.speaker == "Tarkin"
            line1.line    == "And they're heading for the finish line -"

            line2.speaker == "Foo"
            line2.line    == "The dust is too thick, I can't make out who it is!"

            line3.speaker == "Default"
            line3.line    == "Wait -"

    }
}
