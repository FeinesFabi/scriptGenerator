package de.yuffi81.scriptGenerator.model

/**
 * Represents a line of dialog
 */
class DialogLine {
    final String speaker
    final String line

    public  DialogLine (String speaker, String line) {
        this.speaker = speaker
        this.line    = line
    }
}
