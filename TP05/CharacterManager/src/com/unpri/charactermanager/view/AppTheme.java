
package com.unpri.charactermanager.view;

import java.awt.Color;
import java.awt.Font;

import javax.swing.BorderFactory;
import javax.swing.border.Border;

import com.unpri.charactermanager.model.CharacterType;

public class AppTheme {

    // =========================
    // WARNA UTAMA APLIKASI
    // =========================

    public static final Color BACKGROUND =
            new Color(25, 27, 40);

    public static final Color SURFACE =
            new Color(38, 41, 59);

    public static final Color CARD_BACKGROUND =
            new Color(48, 51, 72);

    public static final Color TEXT =
            new Color(245, 245, 250);

    public static final Color MUTED_TEXT =
            new Color(175, 180, 200);

    public static final Color GOLD =
            new Color(255, 205, 80);

    public static final Color ACCENT =
            new Color(125, 105, 235);

    public static final Color BORDER_COLOR =
            new Color(75, 78, 110);

    // =========================
    // FONT APLIKASI
    // =========================

    public static final Font TITLE_FONT =
            new Font(Font.SANS_SERIF, Font.BOLD, 25);

    public static final Font SECTION_FONT =
            new Font(Font.SANS_SERIF, Font.BOLD, 17);

    public static final Font BODY_FONT =
            new Font(Font.SANS_SERIF, Font.PLAIN, 14);

    public static final Font SMALL_FONT =
            new Font(Font.SANS_SERIF, Font.PLAIN, 12);

    public static final Font GOLD_FONT =
            BODY_FONT.deriveFont(Font.BOLD, 15f);

    // =========================
    // BORDER APLIKASI
    // =========================

    public static final Border PANEL_BORDER =
            BorderFactory.createCompoundBorder(
                    BorderFactory.createLineBorder(BORDER_COLOR, 1),
                    BorderFactory.createEmptyBorder(15, 15, 15, 15)
            );

    public static final Border CARD_BORDER =
            BorderFactory.createEmptyBorder(12, 15, 12, 15);

    public static final Border MAIN_BORDER =
            BorderFactory.createEmptyBorder(20, 20, 20, 20);

    public static final Border NO_BORDER =
            BorderFactory.createEmptyBorder();

    // =========================
    // WARNA BERDASARKAN ROLE
    // =========================

    public static Color colorFor(CharacterType type) {

        switch (type) {

            case WARRIOR:
                return new Color(150, 55, 65);

            case MAGE:
                return new Color(85, 70, 165);

            case ARCHER:
                return new Color(40, 125, 90);

            case ASSASSIN:
                return new Color(110, 65, 135);

            case HEALER:
                return new Color(45, 115, 155);

            default:
                return CARD_BACKGROUND;
        }
    }

    // =========================
    // BORDER BERDASARKAN ROLE
    // =========================

    public static Border borderFor(CharacterType type) {

        Color roleColor = colorFor(type);

        return BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(roleColor.brighter(), 2),
                CARD_BORDER
        );
    }

    // =========================
    // FONT BERDASARKAN LEVEL
    // =========================

    public static Font fontForLevel(int level) {

        if (level >= 5) {
            return GOLD_FONT;
        }

        return BODY_FONT;
    }

    // =========================
    // WARNA TEKS BERDASARKAN LEVEL
    // =========================

    public static Color textColorForLevel(int level) {

        if (level >= 5) {
            return GOLD;
        }

        return TEXT;
    }
}
