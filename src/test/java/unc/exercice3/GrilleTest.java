package unc.exercice3;

import junit.framework.TestCase;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

public class GrilleTest extends TestCase {
  static char grille[][] = {
          {'@', '@', '@', '@', '9', '@', '@', '@', '@'},
          {'7', '@', '@', '1', '@', '@', '3', '@', '@'},
          {'@', '8', '4', '3', '@', '7', '@', '5', '1'},
          {'1', '3', '@', '6', '@', '@', '5', '9', '1'},
          {'2', '@', '9', '5', '@', '8', '7', '@', '3'},
          {'@', '7', '6', '@', '@', '2', '@', '4', '8'},
          {'9', '6', '@', '2', '@', '1', '8', '7', '@'},
          {'@', '@', '1', '@', '@', '9', '@', '@', '6'},
          {'@', '@', '@', '@', '6', '@', '@', '@', '@'}
  };
  static char grillePleine[][] = {
          {'1', '1', '1', '1', '9', '1', '1', '1', '1'},
          {'7', '1', '1', '1', '1', '1', '3', '1', '1'},
          {'1', '8', '4', '3', '1', '7', '1', '5', '1'},
          {'1', '3', '1', '6', '1', '1', '5', '9', '1'},
          {'2', '1', '9', '5', '1', '8', '7', '1', '3'},
          {'1', '7', '6', '1', '1', '2', '1', '4', '8'},
          {'9', '6', '1', '2', '1', '1', '8', '7', '1'},
          {'1', '1', '1', '1', '1', '9', '1', '1', '6'},
          {'1', '1', '1', '1', '6', '1', '1', '1', '1'}
  };

  public void testGetDimension(){
    assertEquals(9,Grille.getDimension(grille));
  }

  public void testSetValue() throws IOException {
  }

  public void testHorsBornesException() throws IOException {
    assertEquals(false, Grille.horsBornesException(9, 4, grille));
  }

  public void testCaractereInterditException() throws IOException {
    assertEquals(false,Grille.caractereInterditException('3',grille));
  }

  public void testValeurImpossibleException() throws IOException {
    // test avec un 9 en position 1,1 casse libre acceptant la valeur
    assertEquals(true,Grille.valeurImpossibleException(0,0,'5',grille));
    // test avec un 9 en position 0,1 casse libre mais la ligne contient déjà un 9 renvoie une erreur
    assertEquals(false, Grille.valeurImpossibleException(0,1,'9',grille));
    // test avec un 9 en position 2,0 casse libre mais la colonne contient déjà un 9 renvoie une erreur
    assertEquals(false, Grille.valeurImpossibleException(2,0,'9',grille));
    // test avec un 9 en position 5,6 casse libre mais la zone contient déjà un 9 renvoie une erreur
    assertEquals(false, Grille.valeurImpossibleException(0,1,'9',grille));
    // test avec un 9 en position 2,1 casse non libre, ligne, ciolonne et zone ne contiennt pas de 9 renvoie une erreur
    assertEquals(false, Grille.valeurImpossibleException(0,1,'9',grille));
  }

  public void testComplete() {
    assertEquals(false, Grille.complete('@', grille ));
    assertEquals(true, Grille.complete( '@', grillePleine ));
  }

  public void testCaracterePossible(){
    char test[] = {'1', '2', '3', '4', '5', '6', '7', '8', '9'};
    assertArrayEquals(test, Grille.caracterePossible(9));
  }

}