package unc.exercice3;

import java.io.EOFException;
import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.nio.charset.StandardCharsets;

/**
 * Class pour récupérer une grille d'un fichier.
 */
public class GrilleParser {

  /**
   * Méthode pour récupérer une grille d'un fichier.
   */
  public static char[][] parse(int dimension, String fichier) throws IOException {
    Reader fichierParse = new FileReader(fichier, StandardCharsets.UTF_8);
    char[] buffer = new char[dimension];
    char[][] grille = new char[dimension][dimension];
    for (int ligne = 0; ligne < dimension; ligne++) {
      int lus = fichierParse.read(buffer);
      if (lus != dimension) {
        throw new EOFException("format incorrect");
      }
      for (int colonne = 0; colonne < dimension; colonne++) {
        grille[ligne][colonne] = buffer[colonne];
      }
      lus = fichierParse.read(new char[1]);
      if (lus != 1) {
        throw new EOFException("pas de fin de ligne ? ligne=" + ligne);
      }
    }
    fichierParse.close();
    return grille;
  }
}