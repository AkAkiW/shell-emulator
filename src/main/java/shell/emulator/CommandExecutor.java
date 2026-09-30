package shell.emulator;

public class CommandExecutor {

    public CommandResult execute(Command command) {
        return switch (command.name()) {
            case "ls" -> executeLs(command);
            case "cd" -> executeCd(command);
            case "exit" -> executeExit();
            default -> new CommandResult(
                    "Неизвестная команда: " + command.name(),
                    false
            );
        };
    }

    private CommandResult executeLs(Command command){
        String output = "ls\nАргументы: " + command.arguments();
        return new CommandResult(output, false);
    }

    private CommandResult executeCd(Command command){
        String output = "cd\nАргументы: " + command.arguments();
        return new CommandResult(output, false);
    }

    private CommandResult executeExit(){
        return new CommandResult("Выход из эмулятора.", true);
    }
}
