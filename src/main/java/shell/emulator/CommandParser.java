package shell.emulator;

import java.util.ArrayList;
import java.util.List;

public class CommandParser {

    private static final char NO_QUOTE = '\0';
    private static final char SINGLE_QUOTE = '\'';
    private static final char DOUBLE_QUOTE = '"';
    private static final char SPACE = ' ';

    public List<String> parse(String input) {
        List<String> arguments = new ArrayList<>();
        StringBuilder current = new StringBuilder();
        char quote = NO_QUOTE;

        for (char character : input.toCharArray()) {
            char newQuote = updateQuote(character, quote);

            if (newQuote != quote) {
                quote = newQuote;
            } else if (character == SPACE && quote == NO_QUOTE) {
                addArgument(current, arguments);
            } else {
                current.append(character);
            }
        }

        if (quote != NO_QUOTE) {
            throw new CommandParseException("Незакрытая кавычка");
        }

        addArgument(current, arguments);
        return arguments;
    }

    private char updateQuote(char character, char quote) {
        boolean isQuote = character == SINGLE_QUOTE
                || character == DOUBLE_QUOTE;

        if (!isQuote) {
            return quote;
        }
        if (quote == NO_QUOTE) {
            return character;
        }
        return quote == character ? NO_QUOTE : quote;
    }

    private void addArgument(
            StringBuilder current,
            List<String> arguments
    ) {
        if (!current.isEmpty()) {
            arguments.add(current.toString());
            current.setLength(0);
        }
    }
}
