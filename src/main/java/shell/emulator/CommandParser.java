package shell.emulator;

import java.util.ArrayList;
import java.util.List;

public class CommandParser {

    private static final char SINGLE_QUOTE = '\'';
    private static final char DOUBLE_QUOTE = '"';
    private static final char SPACE = ' ';

    public List<String> parse(String input) {
        List<String> arguments = new ArrayList<>();
        StringBuilder currentArgument = new StringBuilder();

        boolean insideSingleQuotes = false;
        boolean insideDoubleQuotes = false;

        for (char character : input.toCharArray()) {
            if (character == SINGLE_QUOTE && !insideDoubleQuotes) {
                insideSingleQuotes = !insideSingleQuotes;
            } else if (character == DOUBLE_QUOTE && !insideSingleQuotes) {
                insideDoubleQuotes = !insideDoubleQuotes;
            } else if (character == SPACE
                    && !insideSingleQuotes
                    && !insideDoubleQuotes) {
                addArgument(currentArgument, arguments);
            } else {
                currentArgument.append(character);
            }
        }

        if (insideSingleQuotes || insideDoubleQuotes){
            throw new CommandParseException("Незакрытая кавычка");
        }

        addArgument(currentArgument, arguments);

        return arguments;
    }

    private void addArgument(
            StringBuilder currentArgument,
            List<String> arguments
    ) {
        if (!currentArgument.isEmpty()) {
            arguments.add(currentArgument.toString());
            currentArgument.setLength(0);
        }
    }
}