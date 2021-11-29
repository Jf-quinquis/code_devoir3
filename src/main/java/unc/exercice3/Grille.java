package unc.exercice3;

import java.io.IOException;

/**
 * Code pour un Sudoku.
 */
public interface Grille {
  char caractereVide = '@';
  char[][] grille = {
          {'7', '@', '2', '@', '5', '@', '6', '@', '@'},
          {'@', '@', '@', '@', '@', '3', '@', '@', '@'},
          {'1', '@', '@', '@', '@', '9', '5', '@', '@'},
          {'8', '@', '@', '@', '@', '@', '@', '9', '@'},
          {'@', '4', '3', '@', '@', '@', '7', '5', '@'},
          {'@', '9', '@', '@', '@', '@', '@', '@', '8'},
          {'@', '@', '9', '7', '@', '@', '@', '@', '5'},
          {'@', '@', '@', '2', '@', '@', '@', '@', '@'},
          {'@', '@', '7', '@', '4', '@', '2', '@', '3'}
  };
  static int grilleTaille = grille.length;

  /**
   * Retour de la taille de la grille.
   *
   * @return largeur/hauteur de la grille
   */
  static int getDimension(final char[][] grille) {
    return grille.length;
  }

  /**
   * Affecte une valeur dans la grille.
   *
   * @param ligne     position x dans la grille
   * @param colonne   position y dans la grille
   * @param caractere a mettre dans la case
   */
  static boolean setValue(final int ligne, final int colonne, final char caractere, final char[][] grille) throws IOException {
    return !valeurImpossibleException(ligne, colonne, caractere, grille)
            && !horsBornesException(ligne, colonne, grille)
            && !caractereInterditException(caractere, grille);
  }

  /**
   * class pour récupérer une valeur dans une case.
   *
   * @param ligne position x dans la grille
   * @return valeur dans la case ligne, colonne
   */
  static char getValue(final int ligne, final int colonne, char value, final char[][] grille) throws IOException {
    // Retour de la valeur de la case
    if (!testValeurLigne(ligne, value, grille)
            && !testValeurColonne(colonne, value, grille)
            && !testValeurZone(ligne, colonne, value, grille)
            && !caractereInterditException(value, grille)) {
      value = grille[ligne][colonne];
    } else {
      throw new IllegalArgumentException("Impossible de retourner la valeur dans une case.");
    }
    return value;
  }

  /**
   * Valeur impossible dans une case.
   */
  public static boolean valeurImpossibleException(final int ligne, final int colonne, final char caractere, final char[][] grille) throws IOException {
    try {
      final boolean test = !testValeurLigne(ligne, caractere, grille)
              && !testValeurColonne(colonne, caractere, grille)
              && !testValeurZone(ligne, colonne, caractere, grille)
              && !caractereInterditException(caractere, grille);
    } catch (Exception e) {
      throw new IllegalArgumentException("La valeur est déjà dans une dans la ligne, colonne ou zone.");
    } finally {
      return !testValeurLigne(ligne, caractere, grille)
              && !testValeurColonne(colonne, caractere, grille)
              && !testValeurZone(ligne, colonne, caractere, grille);
    }
  }

  /**
   * test d'une valeur dans une ligne.
   */
  private static boolean testValeurLigne(final int ligne, final char caractere, final char[][] grille) {
    for (int colonne = 0; colonne < getDimension(grille); colonne++) {
      if (grille[ligne][colonne] == caractere) {
        return true;
      }
    }
    return false;
  }

  /**
   * test d'une valeur dans une colonne.
   */
  private static boolean testValeurColonne(int colonne, char caractere, char[][] grille) {
    for (int ligne = 0; ligne < getDimension(grille); ligne++) {
      if (grille[ligne][colonne] == caractere) {
        return true;
      }
    }
    return false;
  }

  /**
   * test d'une valeur dans une zone.
   */
  private static boolean testValeurZone(int ligne, int colonne, char caractere, char[][] grille) {
    final int tailleZone = (int) Math.sqrt(getDimension(grille));
    final int bornexmin = (ligne / tailleZone) * tailleZone;
    final int borneymin = (colonne / tailleZone) * tailleZone;
    final int bornexmax = bornexmin + (tailleZone - 1);
    final int borneymax = borneymin + (tailleZone - 1);

    for (int i = bornexmin; i <= bornexmax; i++) {
      for (int j = borneymin; j <= borneymax; j++) {
        if (grille[i][j] == caractere) {
          return true;
        }
      }
    }
    return false;
  }

  /**
   * test pour savoir si la grille est remplie.
   */
  static boolean complete(char caractereVide, char[][] grille) {
    for (int ligne = 0; ligne < getDimension(grille); ligne++) {
      for (int colonne = 0; colonne < getDimension(grille); colonne++) {
        if (grille[ligne][colonne] == caractereVide) {
          return false;
        }
      }
    }
    return true;
  }

  /**
   * test si une valeur est dans la borne.
   *
   * @param ligne   valeur de la taille
   * @param colonne valeur de la taille
   * @param grille  valeur de la taille
   * @return la valeur de la borne si elle est correcte
   */
  static boolean horsBornesException(final int ligne, final int colonne, final char[][] grille) throws IOException {
    Boolean test = false;
    if (ligne > grille.length || colonne > grille.length) {
      test = true;
      throw new IllegalArgumentException("La ligne et/ou la colonne sont hors borne");
    } else {
      test = false;
    }
    return test;
  }

  /**
   * test si un caractère est autorisé.
   *
   * @param value le caractère
   * @return le caractère s'il est correcte
   */
  static boolean caractereInterditException(final char value, char[][] grille) throws IOException {
    boolean test = true;
    for (final char valeur : caracterePossible(getDimension(grille))) {
      if (valeur == value) {
        test = false;
        break;
      }
    }

    if (test) {
      throw new IllegalArgumentException("Ce caractère est interdit");
    }
    return test;
  }

  /**
   * Liste des caractères autorisés en fonction de la taille de la grille.
   * Caractere possible a mettre dans la grille
   * pour une grille 4x4 : 1..4
   * pour une grille 9x9 : 1..9
   * pour une grille 16x16: 0..9-a..f
   * pour une grille 25x25: 0..9-a..o
   *
   * @return la liste des caractères
   */
  static char[] caracterePossible(final int taille) {
    final char[] possible = {'1', '2', '3', '4', '5', '6', '7', '8', '9', '0',
      'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm', 'n', 'o'};

    char[] list = new char[taille];
    for (int i = 0; i < taille; i++) {
      list[i] = possible[i];
    }
    return list;
  }
}