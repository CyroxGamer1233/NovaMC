package com.novamc.launcher;

import com.novamc.common.BuildInfo;
import com.novamc.common.NovaConfig;
import com.novamc.updater.UpdateService;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.util.concurrent.atomic.AtomicBoolean;

public final class LauncherMain {
    private static final Color BG = new Color(15, 17, 22);
    private static final Color CARD = new Color(25, 28, 35);
    private static final Color TEXT = new Color(238, 240, 245);
    private static final Color MUTED = new Color(160, 166, 178);
    private static final Color ACCENT = new Color(96, 125, 255);

    public static void main(String[] args) {
        SwingUtilities.invokeLater(LauncherMain::create);
    }

    private static void create() {
        JFrame frame = new JFrame("NovaMC Client");
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.setMinimumSize(new Dimension(1000, 620));
        frame.setSize(1100, 680);
        frame.setLocationRelativeTo(null);

        JPanel root = new JPanel(new BorderLayout(18, 18));
        root.setBackground(BG);
        root.setBorder(new EmptyBorder(22, 22, 22, 22));

        JLabel brand = label("NovaMC", 28, Font.BOLD, TEXT);
        JLabel sub = label("Original Minecraft client launcher • " + BuildInfo.VERSION, 13, Font.PLAIN, MUTED);
        JPanel header = new JPanel(new BorderLayout());
        header.setOpaque(false);
        JPanel title = new JPanel();
        title.setOpaque(false);
        title.setLayout(new BoxLayout(title, BoxLayout.Y_AXIS));
        title.add(brand);
        title.add(Box.createVerticalStrut(4));
        title.add(sub);
        header.add(title, BorderLayout.WEST);

        JButton settings = button("Settings");
        header.add(settings, BorderLayout.EAST);
        root.add(header, BorderLayout.NORTH);

        JPanel center = new JPanel(new GridLayout(1, 2, 18, 0));
        center.setOpaque(false);

        JPanel profile = card();
        profile.setLayout(new BoxLayout(profile, BoxLayout.Y_AXIS));
        profile.add(label("PLAYER PROFILE", 12, Font.BOLD, MUTED));
        profile.add(Box.createVerticalStrut(20));
        JLabel username = label("Local Developer", 24, Font.BOLD, TEXT);
        profile.add(username);
        profile.add(Box.createVerticalStrut(6));
        profile.add(label("Offline / Local Profile", 13, Font.PLAIN, MUTED));
        profile.add(Box.createVerticalGlue());
        profile.add(label("Version", 12, Font.BOLD, MUTED));
        JComboBox<String> version = new JComboBox<>(new String[]{"NovaMC 1.21.x", "Custom Installation"});
        profile.add(version);
        profile.add(Box.createVerticalStrut(12));
        profile.add(label("RAM", 12, Font.BOLD, MUTED));
        JComboBox<String> ram = new JComboBox<>(new String[]{"2048 MB", "3072 MB", "4096 MB"});
        profile.add(ram);
        profile.add(Box.createVerticalStrut(18));
        JButton play = button("PLAY");
        play.setAlignmentX(Component.LEFT_ALIGNMENT);
        profile.add(play);

        JPanel news = card();
        news.setLayout(new BoxLayout(news, BoxLayout.Y_AXIS));
        news.add(label("NOVA NEWS", 12, Font.BOLD, MUTED));
        news.add(Box.createVerticalStrut(18));
        news.add(label("Phase 1 launcher is ready", 20, Font.BOLD, TEXT));
        news.add(Box.createVerticalStrut(8));
        news.add(label("<html>Build the launcher on GitHub Actions, then package it<br>as a Windows application with jpackage.</html>", 14, Font.PLAIN, MUTED));
        news.add(Box.createVerticalStrut(22));
        news.add(label("SYSTEM", 12, Font.BOLD, MUTED));
        news.add(Box.createVerticalStrut(8));
        news.add(label("Java: " + System.getProperty("java.version"), 13, Font.PLAIN, TEXT));
        news.add(Box.createVerticalStrut(6));
        news.add(label(new UpdateService().status(), 13, Font.PLAIN, TEXT));
        news.add(Box.createVerticalGlue());
        JLabel status = label("Ready", 13, Font.BOLD, ACCENT);
        news.add(status);

        center.add(profile);
        center.add(news);
        root.add(center, BorderLayout.CENTER);

        play.addActionListener(e -> {
            play.setEnabled(false);
            status.setText("Launch pipeline ready — Minecraft runtime integration is Phase 2.");
            JOptionPane.showMessageDialog(frame,
                "NovaMC Phase 1 launcher is running.\n\nMinecraft installation/authentication is intentionally not bundled in this phase.",
                "NovaMC", JOptionPane.INFORMATION_MESSAGE);
            play.setEnabled(true);
        });

        settings.addActionListener(e -> JOptionPane.showMessageDialog(frame,
            "Launcher settings will be expanded in later phases.", "Settings", JOptionPane.INFORMATION_MESSAGE));

        frame.setContentPane(root);
        frame.setVisible(true);
    }

    private static JPanel card() {
        JPanel p = new JPanel();
        p.setBackground(CARD);
        p.setBorder(new EmptyBorder(24, 24, 24, 24));
        return p;
    }

    private static JLabel label(String text, int size, int style, Color color) {
        JLabel l = new JLabel(text);
        l.setFont(new Font("SansSerif", style, size));
        l.setForeground(color);
        return l;
    }

    private static JButton button(String text) {
        JButton b = new JButton(text);
        b.setFont(new Font("SansSerif", Font.BOLD, 14));
        b.setForeground(Color.WHITE);
        b.setBackground(ACCENT);
        b.setFocusPainted(false);
        b.setBorder(BorderFactory.createEmptyBorder(12, 22, 12, 22));
        return b;
    }
}
