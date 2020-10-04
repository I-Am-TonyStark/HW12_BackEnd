package com.mamalimomen.base.controllers.guis;

import javax.swing.*;

public final class DialogProvider {
    private DialogProvider() {
    }

    public static synchronized <T> void createAndShowErrorDialog(T message, String title) {
        JOptionPane.showMessageDialog(null, message, title, JOptionPane.ERROR_MESSAGE);
    }

    public static synchronized <T> void createAndShowInformationDialog(T message, String title) {
        JOptionPane.showMessageDialog(null, message, title, JOptionPane.INFORMATION_MESSAGE);
    }

    public static synchronized <T> void createAndShowWarningDialog(T message, String title) {
        JOptionPane.showMessageDialog(null, message, title, JOptionPane.WARNING_MESSAGE);
    }

    public static synchronized <T> String createAndShowInputDialog(T message, String title) {
        return JOptionPane.showInputDialog(null, message, title, JOptionPane.QUESTION_MESSAGE);
    }
}
