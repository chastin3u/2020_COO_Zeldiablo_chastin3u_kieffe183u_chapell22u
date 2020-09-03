package tests;

import org.junit.Test;

import code.jeu.Jeu;
import code.characters.*;
import code.jeu.*;

import static org.junit.Assert.*;

import java.io.File;

public class TestAttaque {
	
	/**
     * Methode qui retourne le fichier correspondant au path donnees (probleme de path avec les tests)
     * @param path
     * @return le Fichier correspondant
     */
    private File getFile(String path) {
        ClassLoader classLoader = getClass().getClassLoader();
        File file = new File(classLoader.getResource(path).getFile());
        return file;
    }
	
	@Test
	public void testAttaqueAdventurer() {
		File f = getFile("tests/map/level_1.txt");
		Advanturer j = new Advanturer("Bruno",10,0,0);
		Jeu jeu = new Jeu(f,j);
		jeu.getMap().getCharacters().add(new Monster("Robert",10,1,0));
		jeu.getMap().gererAttaque(j.getPosition().getX(), j.getPosition().getY(), j);
		assertEquals("Le monstre devrait avoir perdu des points de vie", 6, jeu.getMap().getCharacters().get(1).getVie());
	}
	
	@Test
	public void testAttaqueMage() {
		File f = getFile("tests/map/level_1.txt");
		Map m = new Map(f);
		Wizard j = new Wizard("Paul", 10, 0, 0, 10);
		Monster grr = new Monster("TANK", 10, 2, 0);
		j.setFacingView(2);
		m.getCharacters().add(j);
		m.getCharacters().add(grr);
		m.gererAttaque(j.getPosition().getX(), j.getPosition().getY(), j);
		assertEquals("Le monstre devrait avoir perdu des points de vies",6 ,m.getCharacters().get(1).getVie());
		assertEquals("Le mage doit avoir perdu du mana", 9,((Wizard)m.getCharacters().get(0)).getMana());
	}
	
	
}
