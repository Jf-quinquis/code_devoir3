package unc.exercice3;

import static org.junit.jupiter.api.Assertions.*;
import java.io.IOException;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class GrilleTest {
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

  @Test
  void testGetDimension(){
    assertEquals(9,Grille.getDimension(grille));
  }

  @Test
  void testSetValue() throws IOException {
    assertEquals(true,Grille.setValue(0,4,'9',grille));
    Assertions.assertThrows(ArrayIndexOutOfBoundsException.class,() -> {Grille.setValue(12,15,'Z',grille);});
  }

  @Test
  void testGetValue() throws IOException {
    assertEquals(false,Grille.setValue(0,0,'3',grille));
    Assertions.assertThrows(ArrayIndexOutOfBoundsException.class,() -> {Grille.getValue(12,15,'Z',grille);});
  }

  @Test
  void testHorsBornesException() throws IOException {
    assertEquals(false, Grille.horsBornesException(5, 4, grille));
    Assertions.assertThrows(IllegalArgumentException.class,() -> {Grille.horsBornesException(12, 4, grille);});

  }

  @Test
  void testCaractereInterditException() throws IOException {
    assertEquals(false,Grille.caractereInterditException('3',grille));
    Assertions.assertThrows(IllegalArgumentException.class,() -> {Grille.caractereInterditException('Z',grille);});
  }

  @Test
  void testValeurImpossibleException() throws IOException {
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

  @Test
  void testComplete() {
    assertEquals(false, Grille.complete('@', grille ));
    assertEquals(true, Grille.complete( '@', grillePleine ));
  }

  @Test
  void testCaracterePossible(){
    char test[] = {'1', '2', '3', '4', '5', '6', '7', '8', '9'};
    assertArrayEquals(test, Grille.caracterePossible(9));
  }
}