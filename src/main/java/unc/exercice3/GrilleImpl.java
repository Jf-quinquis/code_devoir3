package unc.exercice3;

import java.io.IOException;

/** Class pour remplir une grille de Sudoku. */
public class GrilleImpl {
  /** Affichage de la grille résolue. */
  public static void main(String[] args) throws IOException {

    char[][] grille = Template.grille9;
    implementerGrille(grille);
    if (Grille.complete('@', grille)) {
      System.out.println("Grille résolue ! :)");
    } else {
      System.out.println("Grille no résolue ! :(");
    }

    afficherGrille(grille);

  }

  /** Méthode pour afficher la grille. */
  private static void afficherGrille(char[][] grille) {
    for (int ligne = 0; ligne < grille.length; ligne++) {
      for (int colonne = 0; colonne < grille.length; colonne++) {
        System.out.print(grille[ligne][colonne] + "|");
      }
      System.out.println();
    }
  }

  /** Méthode pour résoudre la grille. */
  private static boolean implementerGrille(char[][] grille) throws IOException {
    for (int ligne = 0; ligne < grille.length; ligne++) {
      for (int colonne = 0; colonne < grille.length; colonne++) {
        if (grille[ligne][colonne] == '@') {
          for (final char caractere : Grille.caracterePossible(grille.length)) {
            if (Grille.valeurImpossibleException(ligne, colonne, caractere, grille)) {
              grille[ligne][colonne] = caractere;

              if (implementerGrille(grille)) {
                return true;
              } else {
                grille[ligne][colonne] = '@';
              }
            }
          }
          return false;
        }
      }
    }
    return true;
  }
}