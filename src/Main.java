import Alumnes.Alumnes_SEC;
import EstructuraArbre.ArbreException;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws ArbreException {
        Beca beca = new Beca();
        Scanner sc = new Scanner(System.in);
        while (true) {
            System.out.print("Que vols fer? ");
            System.out.println("1. Afegir un nou alumne.");
            System.out.println("2. Esborrar un alumne a partir del seu nom.");
            System.out.println("3. Mostrar tots els alumnes en ordre descendent.");
            System.out.println("4. Esborrar alumnes sense matrícula d’honor.");
            System.out.println("5. Sortir del programa");

            int accio = Integer.parseInt(sc.nextLine());

            switch (accio) {
                case 1:
                    beca.afegirAlumne();
                    break;
                case 2:
                    System.out.print("Nom de l'alumne a esborrar: ");
                    String nom = sc.nextLine();
                    try {
                        beca.arbreACB.esborrar(new Alumnes_SEC(nom));
                    } catch (ArbreException e) {
                        System.out.println("No s'ha pogut esborrar l'alumne: " + e.getMessage());
                    }
                    beca.llistaDescendent = beca.arbreACB.getDescendentList();
                    break;
                case 3:
                    System.out.println(beca.toString());
                    break;
                case 4:
                    beca.esborraAlumnesSenseMatricula();
                    break;
                case 5:
                    System.out.println("Sortint del programa...");
                    return;
                default:
                    System.out.println("Opció no vàlida!");
                    break;
            }
        }
    }
}