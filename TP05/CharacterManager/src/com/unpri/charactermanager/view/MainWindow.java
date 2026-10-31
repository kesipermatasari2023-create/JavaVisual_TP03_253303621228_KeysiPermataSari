package com.unpri.charactermanager.view;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

import com.unpri.charactermanager.model.Character;
import com.unpri.charactermanager.model.CharacterManager;
import com.unpri.charactermanager.model.CharacterType;

public class MainWindow extends JFrame {

    private CharacterManager manager;

    private JPanel cardsPanel;

    private JLabel countLabel;
    private JLabel averageLabel;

    private JButton addButton;

    public MainWindow(CharacterManager manager) {

        this.manager = manager;

        setTitle("Character Manager - Sprint #3");

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        setMinimumSize(new Dimension(650, 500));

        // Panel utama
        JPanel mainPanel = new JPanel(new BorderLayout(15, 15));

        mainPanel.setBackground(new Color(25, 27, 40));

        mainPanel.setBorder(
            new EmptyBorder(20, 20, 20, 20)
        );

        // =========================
        // HEADER
        // =========================

        JPanel headerPanel = new JPanel(new BorderLayout());

        headerPanel.setOpaque(false);

        JLabel titleLabel = new JLabel("CHARACTER MANAGER");

        titleLabel.setFont(
            new Font("Arial", Font.BOLD, 25)
        );

        titleLabel.setForeground(Color.WHITE);

        JLabel subtitleLabel = new JLabel(
            "Manage your favorite game characters"
        );

        subtitleLabel.setFont(
            new Font("Arial", Font.PLAIN, 13)
        );

        subtitleLabel.setForeground(
            new Color(180, 185, 205)
        );

        JPanel titlePanel = new JPanel();

        titlePanel.setLayout(
            new BoxLayout(titlePanel, BoxLayout.Y_AXIS)
        );

        titlePanel.setOpaque(false);

        titlePanel.add(titleLabel);
        titlePanel.add(Box.createVerticalStrut(5));
        titlePanel.add(subtitleLabel);

        headerPanel.add(titlePanel, BorderLayout.WEST);

        mainPanel.add(headerPanel, BorderLayout.NORTH);

        // =========================
        // DAFTAR KARAKTER
        // =========================

        cardsPanel = new JPanel();

        cardsPanel.setLayout(
            new BoxLayout(cardsPanel, BoxLayout.Y_AXIS)
        );

        cardsPanel.setBackground(
            new Color(35, 38, 55)
        );

        cardsPanel.setBorder(
            new EmptyBorder(15, 15, 15, 15)
        );

        JScrollPane scrollPane = new JScrollPane(cardsPanel);

        scrollPane.setBorder(null);

        scrollPane.getViewport().setBackground(
            new Color(35, 38, 55)
        );

        mainPanel.add(scrollPane, BorderLayout.CENTER);

        // =========================
        // FOOTER / STATISTIK
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

        countLabel.setFont(
            new Font("Arial", Font.BOLD, 14)
        );

        averageLabel.setFont(
            new Font("Arial", Font.BOLD, 14)
        );

        countLabel.setForeground(Color.WHITE);

        averageLabel.setForeground(Color.WHITE);

        statisticsPanel.add(countLabel);
        statisticsPanel.add(averageLabel);

        // Tombol tambah karakter
        addButton = new JButton("+ Tambah Karakter");

        addButton.setFont(
            new Font("Arial", Font.BOLD, 14)
        );

        addButton.setForeground(Color.WHITE);

        addButton.setBackground(
            new Color(100, 90, 220)
        );

        addButton.setFocusPainted(false);

        addButton.setBorder(
            new EmptyBorder(12, 20, 12, 20)
        );

        addButton.setAlignmentX(Component.LEFT_ALIGNMENT);

        addButton.addActionListener(e -> addNewCharacter());

        footerPanel.add(statisticsPanel);

        footerPanel.add(Box.createVerticalStrut(15));

        footerPanel.add(addButton);

        mainPanel.add(footerPanel, BorderLayout.SOUTH);

        add(mainPanel);

        // Menampilkan data pertama kali
        refreshDisplay();

        // Menyesuaikan ukuran jendela
        pack();

        setSize(750, 600);

        setLocationRelativeTo(null);
    }

    // =========================
    // MEMBUAT KARTU KARAKTER
    // =========================

    private JPanel createCharacterCard(Character character) {

        JPanel card = new JPanel(new BorderLayout(10, 5));

        card.setMaximumSize(
            new Dimension(Integer.MAX_VALUE, 75)
        );

        card.setPreferredSize(
            new Dimension(500, 75)
        );

        card.setBorder(
            new EmptyBorder(12, 15, 12, 15)
        );

        card.setOpaque(true);

        card.setBackground(getColorByType(character.getType()));

        JLabel nameLabel = new JLabel(character.getName());

        nameLabel.setFont(
            new Font("Arial", Font.BOLD, 16)
        );

        nameLabel.setForeground(Color.WHITE);

        JLabel levelLabel = new JLabel(
            "Level " + character.getLevel()
        );

        levelLabel.setFont(
            new Font("Arial", Font.BOLD, 14)
        );

        levelLabel.setForeground(Color.WHITE);

        JLabel typeLabel = new JLabel(
            character.getType().toString()
        );

        typeLabel.setFont(
            new Font("Arial", Font.PLAIN, 12)
        );

        typeLabel.setForeground(
            new Color(240, 240, 240)
        );

        JPanel informationPanel = new JPanel();

        informationPanel.setLayout(
            new BoxLayout(informationPanel, BoxLayout.Y_AXIS)
        );

        informationPanel.setOpaque(false);

        informationPanel.add(nameLabel);

        informationPanel.add(typeLabel);

        card.add(informationPanel, BorderLayout.WEST);

        card.add(levelLabel, BorderLayout.EAST);

        return card;
    }

    // =========================
    // WARNA BERDASARKAN TIPE
    // =========================

    private Color getColorByType(CharacterType type) {

        switch (type) {

            case WARRIOR:
                return new Color(170, 65, 65);

            case MAGE:
                return new Color(90, 75, 175);

            case ARCHER:
                return new Color(45, 135, 100);

            case ASSASSIN:
                return new Color(110, 75, 130);

            case HEALER:
                return new Color(50, 125, 160);

            default:
                return new Color(80, 80, 90);
        }
    }

    // =========================
    // UPDATE TAMPILAN
    // =========================

    private void refreshDisplay() {

        cardsPanel.removeAll();

        // Mengambil data secara dinamis
        for (Character character : manager.getAllCharacters()) {

            JPanel card = createCharacterCard(character);

            cardsPanel.add(card);

            cardsPanel.add(
                Box.createVerticalStrut(10)
            );
        }

        // Memperbarui statistik
        countLabel.setText(
            "Total Karakter: " + manager.getTotalCharacters()
        );

        averageLabel.setText(
            String.format(
                java.util.Locale.US,
                "Rata-rata Level: %.2f",
                manager.getAverageLevel()
            )
        );

        // Memperbarui tampilan
        cardsPanel.revalidate();

        cardsPanel.repaint();
    }

    // =========================
    // MENAMBAHKAN KARAKTER BARU
    // =========================

    private void addNewCharacter() {

        Character newCharacter = new Character(
            "Nova",
            9,
            CharacterType.HEALER
        );

        manager.addCharacter(newCharacter);

        // Memperbarui daftar dan statistik
        refreshDisplay();

        // Mencegah penambahan berulang
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