package shell.emulator;

import javax.swing.JFrame;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.JScrollPane;
import java.awt.BorderLayout;

public class ShellWindow extends JFrame {

    private final JTextArea outputArea;
    private final JTextField inputField;
    private static final int WINDOW_WIDTH = 800;
    private static final int WINDOW_HEIGHT = 600;
    private final Shell shell;
    private final OsInfo osInfo;

    public ShellWindow(Shell shell, StartupScriptResult scriptResult) {
        this.shell = shell;
        osInfo = new OsInfo();
        setTitle("Эмулятор - [" + osInfo.getUsername() + "@" + osInfo.getHostname() + "]");
        setSize(WINDOW_WIDTH,WINDOW_HEIGHT);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        outputArea = new JTextArea();
        inputField = new JTextField();
        outputArea.setEditable(false);

        for (String output : scriptResult.outputs()) {
            outputArea.append(output + "\n");
        }

        JScrollPane scrollPane = new JScrollPane(outputArea);

        setLayout(new BorderLayout());

        add(scrollPane, BorderLayout.CENTER);
        add(inputField, BorderLayout.SOUTH);

        inputField.addActionListener(event -> {
            String input = inputField.getText();
            outputArea.append("> " + input + "\n");
            try {
                CommandResult result = shell.execute(input);

                outputArea.append(result.output() + "\n");

                if (result.shouldExit()) {
                    dispose();
                }
            } catch (CommandParseException exception) {
                outputArea.append("Ошибка: " + exception.getMessage() + "\n");
            }

            inputField.setText("");
        });
    }
}
