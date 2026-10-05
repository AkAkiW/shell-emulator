package shell.emulator;

import javax.swing.SwingUtilities;
import java.io.IOException;
import java.util.List;

public class Main {

    private static final int REQUIRED_ARGS_COUNT = 2;

    public static void main(String[] args) {
        System.out.println("Количество аргументов: " + args.length);

        ShellConfig config = createConfig(args);

        System.out.println("VFS: " + config.vfsPath());
        System.out.println("Стартовый скрипт: " + config.scriptPath());

        Shell shell = new Shell();
        StartupScriptRunner runner = new StartupScriptRunner(shell);
        StartupScriptResult scriptResult;

        try {
            scriptResult = runner.run(config.scriptPath());
        } catch (IOException exception) {
            System.out.println(
                    "Ошибка запуска скрипта: " + exception.getMessage()
            );

            scriptResult = new StartupScriptResult(
                    List.of(
                            "Ошибка запуска скрипта: "
                                    + exception.getMessage()
                    )
            );
        }

        final StartupScriptResult finalScriptResult = scriptResult;

        SwingUtilities.invokeLater(() -> {
            ShellWindow window = new ShellWindow(shell, finalScriptResult);
            window.setVisible(true);
        });
    }

    private static ShellConfig createConfig(String[] args) {
        if (args.length < REQUIRED_ARGS_COUNT) {
            System.err.println(
                    "Использование: run.sh <путь_к_VFS> <путь_к_стартовому_скрипту>"
            );
            System.exit(1);
        }

        return new ShellConfig(args[0], args[1]);
    }
}