package shell.emulator;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

public class StartupScriptRunner {

    private final Shell shell;

    public StartupScriptRunner(Shell shell) {
        this.shell = shell;
    }

    public StartupScriptResult run(String scriptPath) throws IOException {
        List<String> outputs = new ArrayList<>();

        for (String line : Files.readAllLines(Path.of(scriptPath))) {
            outputs.add("> " + line);

            try {
                CommandResult result = shell.execute(line);
                outputs.add(result.output());
            } catch (CommandParseException exception) {
                outputs.add("Ошибка: " + exception.getMessage());
            }
        }

        return new StartupScriptResult(outputs);
    }
}