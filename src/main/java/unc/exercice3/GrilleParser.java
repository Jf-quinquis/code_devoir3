package unc.exercice3;

import java.io.EOFException;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.nio.charset.StandardCharsets;

/** Class pour parser une grille au format Texte. */
public class GrilleParser {
  /** Récupération des données. */
  public static Grille parse(InputStream in, Grille grille) throws IOException {
    Reader reader = new InputStreamReader(in, StandardCharsets.UTF_8);
    int dimension = Grille.getDimension(Grille.grille);
    char[] buffer = new char[dimension];
    for (int ligne = 0; ligne < dimension; ligne++) {
      int lus = reader.read(buffer);
      if (lus != dimension) {
        throw new EOFException("format incorrect");
      }
      for (int colonne = 0; colonne < dimension; colonne++) {
        Grille.grille[ligne][colonne] = Grille.setValue(ligne, colonne, buffer[colonne]);
      }
      lus = reader.read(new char[1]);
      if (lus != 1) {
        throw new EOFException("pas de fin de ligne ? ligne=" + ligne);
      }
    }
    reader.close();
    return grille;
  }

  public static void parse(File f, Grille grille) throws IOException {
    parse(new FileInputStream(f), grille);
  }

}
