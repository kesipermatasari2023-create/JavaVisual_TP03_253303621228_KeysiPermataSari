
package com.unpri.charactermanager.view;

import javax.swing.*;
import java.awt.*;

import com.unpri.charactermanager.model.Character;
import com.unpri.charactermanager.model.CharacterManager;

public class MainWindow extends JFrame {

    // Batas level disimpan sebagai konstanta
    private static final int HIGH_LEVEL_THRESHOLD = 5;

    private CharacterManager manager;

    private JPanel cardsPanel;

    private JLabel countLabel;
    private JLabel averageLabel;

    private JButton addButton;

    public MainWindow(CharacterManager manager) {

        this.manager = manager;

        setTitle("Character Manager - Sprint #4");

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Panel utama
        JPanel mainPanel = new JPanel(new BorderLayout(15, 15));

        mainPanel.setBackground(AppTheme.BACKGROUND);

        mainPanel.setBorder(AppTheme.MAIN_BORDER);

        mainPanel.setPreferredSize(new Dimension(750, 600));

        // =========================
        // HEADER
        // =========================

        JPanel headerPanel = new JPanel();

        headerPanel.setLayout(
                new BoxLayout(headerPanel, BoxLayout.Y_AXIS)
        );

        headerPanel.setOpaque(false);

        JLabel titleLabel = new JLabel("CHARACTER MANAGER");

        titleLabel.setFont(AppTheme.TITLE_FONT);

        titleLabel.setForeground(AppTheme.TEXT);

        JLabel subtitleLabel = new JLabel(
                "Sprint #4 - Theming and Component Properties"
        );

        subtitleLabel.setFont(AppTheme.SMALL_FONT);

        subtitleLabel.setForeground(AppTheme.MUTED_TEXT);

        headerPanel.add(titleLabel);

        headerPanel.add(Box.createVerticalStrut(5));

        headerPanel.add(subtitleLabel);

        mainPanel.add(headerPanel, BorderLayout.NORTH);

        // =========================
        // DAFTAR KARAKTER
        // =========================

        cardsPanel = new JPanel();

        cardsPanel.setLayout(
                new BoxLayout(cardsPanel, BoxLayout.Y_AXIS)
        );

        cardsPanel.setBackground(AppTheme.SURFACE);

        cardsPanel.setBorder(AppTheme.PANEL_BORDER);

        JScrollPane scrollPane = new JScrollPane(cardsPanel);

        scrollPane.setBorder(AppTheme.NO_BORDER);

        scrollPane.getViewport().setBackground(AppTheme.SURFACE);

        mainPanel.add(scrollPane, BorderLayout.CENTER);

        // =========================
        // FOOTER DAN STATISTIK
        // =========================

        JPanel footerPanel = new JPanel();

        footerPanel.setLayout(
                new BoxLayout(footerPanel, BoxLayout.Y_AXIS)
        );

        footerPanel.setOpaque(false);

        JPanel statisticsPanel = new JPanel(
                new GridLayout(1, 2, 15, 0)
        );

        statisticsPanel.setOpaque(false);

        countLabel = new JLabel();

        averageLabel = new JLabel();

        countLabel.setFont(AppTheme.SECTION_FONT);

        averageLabel.setFont(AppTheme.SECTION_FONT);

        countLabel.setForeground(AppTheme.TEXT);

        averageLabel.setForeground(AppTheme.GOLD);

        statisticsPanel.add(countLabel);

        statisticsPanel.add(averageLabel);

        // Tombol tambah karakter
        addButton = new JButton("+ Tambah Karakter");

        addButton.setFont(AppTheme.BODY_FONT);

        addButton.setForeground(AppTheme.TEXT);

        addButton.setBackground(AppTheme.ACCENT);

        addButton.setFocusPainted(false);

        addButton.setBorder(AppTheme.CARD_BORDER);

        addButton.setAlignmentX(Component.LEFT_ALIGNMENT);

        addButton.addActionListener(e -> addNewCharacter());

        footerPanel.add(statisticsPanel);

        footerPanel.add(Box.createVerticalStrut(15));

        footerPanel.add(addButton);

        mainPanel.add(footerPanel, BorderLayout.SOUTH);

        add(mainPanel);

        // Menampilkan data pertama kali
        refreshDisplay();

        // Mengatur ukuran otomatis berdasarkan komponen
        pack();

        setLocationRelativeTo(null);
    }

    // =========================
    // MEMBUAT KARTU KARAKTER
    // =========================

    private JPanel createCharacterCard(Character character) {

        JPanel card = new JPanel(new BorderLayout(10, 5));

        card.setMaximumSize(
                new Dimension(Integer.MAX_VALUE, 80)
        );

        card.setPreferredSize(
                new Dimension(650, 80)
        );

        // Properti tambahan 1:
        // Tooltip memberikan informasi detail karakter
        card.setToolTipText(
                "Nama: " + character.getName()
                + " | Level: " + character.getLevel()
                + " | Role: " + character.getType()
        );

        // Properti tambahan 2:
        // Border disesuaikan dengan role karakter
        card.setBorder(
                AppTheme.borderFor(character.getType())
        );

        card.setOpaque(true);

        card.setBackground(
                AppTheme.colorFor(character.getType())
        );

        // Label nama
        JLabel nameLabel = new JLabel(character.getName());

        nameLabel.setFont(
                AppTheme.fontForLevel(character.getLevel())
        );

        nameLabel.setForeground(
                AppTheme.textColorForLevel(character.getLevel())
        );

        // Label role
        JLabel typeLabel = new JLabel(
                character.getType().toString()
        );

        typeLabel.setFont(AppTheme.SMALL_FONT);

        typeLabel.setForeground(AppTheme.TEXT);

        // Label level
        JLabel levelLabel = new JLabel(
                "LEVEL " + character.getLevel()
        );

        // Mini-Challenge:
        // Level >= 5 menggunakan tulisan emas dan tebal
        if (character.getLevel() >= HIGH_LEVEL_THRESHOLD) {

            levelLabel.setFont(AppTheme.GOLD_FONT);

            levelLabel.setForeground(AppTheme.GOLD);

        } else {

            levelLabel.setFont(AppTheme.BODY_FONT);

            levelLabel.setForeground(AppTheme.TEXT);
        }

        // Panel informasi
        JPanel informationPanel = new JPanel();

        informationPanel.setLayout(
                new BoxLayout(informationPanel, BoxLayout.Y_AXIS)
        );

        informationPanel.setOpaque(false);

        informationPanel.add(nameLabel);

        informationPanel.add(Box.createVerticalStrut(5));

        informationPanel.add(typeLabel);

        card.add(informationPanel, BorderLayout.WEST);

        card.add(levelLabel, BorderLayout.EAST);

        return card;
    }

    // =========================
    // REFRESH TAMPILAN
    // =========================

    private void refreshDisplay() {

        cardsPanel.removeAll();

        // Mengambil data karakter secara dinamis
        for (Character character : manager.getAllCharacters()) {

            JPanel card = createCharacterCard(character);

            cardsPanel.add(card);

            cardsPanel.add(Box.createVerticalStrut(10));
        }

        // Memperbarui jumlah karakter
        countLabel.setText(
                "Total Karakter: " + manager.getTotalCharacters()
        );

        // Memperbarui rata-rata level
        averageLabel.setText(
                String.format(
                        java.util.Locale.US,
                        "Rata-rata Level: %.2f",
                        manager.getAverageLevel()
                )
        );

        cardsPanel.revalidate();

        cardsPanel.repaint();
    }

    // =========================
    // TAMBAH KARAKTER
    // =========================

    private void addNewCharacter() {

        Character newCharacter = new Character(
                "Nova",
                9,
                com.unpri.charactermanager.model.CharacterType.MAGE
        );

        manager.addCharacter(newCharacter);

        refreshDisplay();

        addButton.setEnabled(false);

        addButton.setText("Karakter Berhasil Ditambahkan");

        JOptionPane.showMessageDialog(
                this,
                "Karakter Nova berhasil ditambahkan!",
                "Berhasil",
                JOptionPane.INFORMATION_MESSAGE
        );
    }
}
