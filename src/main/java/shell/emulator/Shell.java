package shell.emulator;

import java.util.List;

public class Shell {

    private final CommandParser parser;
    private final CommandExecutor executor;

    public Shell() {
        parser = new CommandParser();
        executor = new CommandExecutor();
    }

    public CommandResult execute(String input) {
        List<String> parts = parser.parse(input);

        if (parts.isEmpty()) {
            return new CommandResult("", false);
        }

        String name = parts.get(0);
        List<String> arguments = parts.subList(1, parts.size());

        Command command = new Command(name, arguments);

        return executor.execute(command);
    }
}