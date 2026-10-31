
package com.unpri.charactermanager;

import javax.swing.SwingUtilities;

import com.unpri.charactermanager.model.Character;
import com.unpri.charactermanager.model.CharacterManager;
import com.unpri.charactermanager.model.CharacterType;
import com.unpri.charactermanager.view.MainWindow;

public class Main {

    public static void main(String[] args) {

        // Membuat CharacterManager
        CharacterManager manager = new CharacterManager();

        // Data karakter
        manager.addCharacter(
                new Character("Alucard", 10, CharacterType.WARRIOR)
        );

        manager.addCharacter(
                new Character("Miya", 8, CharacterType.ARCHER)
        );

        manager.addCharacter(
                new Character("Saber", 12, CharacterType.ASSASSIN)
        );

        manager.addCharacter(
                new Character("Layla", 3, CharacterType.ARCHER)
        );

        manager.addCharacter(
                new Character("Gusion", 14, CharacterType.MAGE)
        );

        manager.addCharacter(
                new Character("Fanny", 16, CharacterType.ASSASSIN)
        );

        manager.addCharacter(
                new Character("Tigreal", 4, CharacterType.WARRIOR)
        );

        // Menjalankan GUI pada Event Dispatch Thread
        SwingUtilities.invokeLater(() -> {

            MainWindow window = new MainWindow(manager);

            window.setVisible(true);

        });
    }
}
