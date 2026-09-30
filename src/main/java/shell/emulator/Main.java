package shell.emulator;

import javax.swing.SwingUtilities;

public class Main {

    public static void main(String[] args){
        SwingUtilities.invokeLater(() -> {
            ShellWindow window = new ShellWindow();
            window.setVisible(true);
        });
    }
}