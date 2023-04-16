import java.util.Scanner;
import java.lang.Math;
import java.util.Random;

public class Main {

    public static int count( char my_char, char[] tableau ) {
        int nombre_occurence = 0 ;
        for( int index = 0 ; index < tableau.length ; index++ ) {
            if( tableau[ index ] == my_char ) {
                nombre_occurence++ ;
            }
        }
        return nombre_occurence ;
    }

    public static char[] tirage_aleatoire( char[] symbols ) {
        Random r = new Random();

        // Création du tableau
        char[] tableau = new char[3];

        // Tirage aléatoire d'un caractère pour chaque case
        for( int index = 0 ; index < 3 ; index++ ) {
            tableau[index] = symbols[ r.nextInt( symbols.length ) ];
        }

        // Retour du tirage
        return tableau ;
    }

    // Fonction renvoyant true si <str> se trouve dans l'array <arr>
    public static boolean array_contains( String str, String[] arr ) {
        for( String x : arr ) {
            if( str.equals(x) ) {
                return true ;
            }
        }
        return false ;
    }

    public static void main( String[] args ) {    

        // Préparation de jeu
        Scanner scan = new Scanner( System.in );
        final int prix_partie = 1 ;
        final String[] commandes_sortie = { "quit", "exit", "stop" };
        char[] symbols = { '&', '#', '~', '?', '*', '$', '@', ']', '^', '/', '%', '+', '!', '=', '[' };
        int credit = 10 ;

        // Partie jeu
        while( true ) {
            System.out.println("Il vous reste " + credit + " crd");

            // Condition de partie perdue
            if( credit < prix_partie ) {
                System.out.println("Game Over");
                break;
            }

            // Ecoute de l'entrée utilisateur
            System.out.println(
                String.format(
                    "Tapez [Entrer] pour jouer (%d crds) ou exit/stop/quit pour quitter", prix_partie
                )
            );
            String saisie = scan.nextLine();

            // Condition d'arrêt de jeu
            // if( saisie.equals("exit") || saisie.equals("stop") || saisie.equals("quit") ) {
            if( array_contains( saisie, commandes_sortie ) ) {
                break;
            }

            // Début de la nouvelle partie
            credit -= prix_partie ;
            char[] tirage = tirage_aleatoire( symbols );

            // Affichage du tirage
            System.out.print("Tirage : ");
            for( char symbol : tirage ) {
                System.out.print( symbol );
            }
            System.out.println("");

            // Vérification des gains
            int count_first_symbol = count( tirage[0], tirage ) ;
            if( count_first_symbol == 3 ) {
                System.out.println("3 Symboles : +5 credits !");
                credit += 5 ;
            }
            else if( count_first_symbol == 2 || count( tirage[1], tirage ) == 2 ) {
                System.out.println("2 Symboles : +2 credits !");
                credit += 2 ;
            }
            else {
                System.out.println("Aucun gain, dommage !");
            }

        }

        // Fin de partie
        System.out.println("Fin de partie, Score : " + credit);

    }

}