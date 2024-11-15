import Alumnes.Alumnes_SEC;
import Alumnes.Assignatura;
import EstructuraArbre.Acb;
import EstructuraArbre.AcbEnll;
import EstructuraArbre.ArbreException;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class Beca {
    private static Scanner scanner = new Scanner(System.in);
    private AcbEnll<Alumnes_SEC> arbreACB;
    private Queue<Alumnes_SEC> llistaDescendent;

    public Beca() throws ArbreException {
        arbreACB = new AcbEnll<>();
        inicialitzarExemples();
        llistaDescendent = arbreACB.getDescendentList();
    }

    // Mètode per inicialitzar exemples d'alumnes
    private void inicialitzarExemples() throws ArbreException {
        arbreACB.inserir(exempleRosa());
        arbreACB.inserir(exempleEnric());
        arbreACB.inserir(exempleRandom("Martí"));
        arbreACB.inserir(exempleRandom("Martina"));
        arbreACB.inserir(exempleRandom("Carles"));
    }

    private Alumnes_SEC exempleRosa() {
        Alumnes_SEC rosa = new Alumnes_SEC("Rosa");
        rosa.addAssignatura(new Assignatura("Fonaments de la Programació", 6, 7, false));
        rosa.addAssignatura(new Assignatura("Programació Orientada a l’objecte", 6, 5, false));
        rosa.addAssignatura(new Assignatura("Estructura de Dades i Algorismes", 4, 9, false));
        rosa.addAssignatura(new Assignatura("Programació Avançada", 4, 5, false));
        return rosa;
    }

    private Alumnes_SEC exempleEnric() {
        Alumnes_SEC enric = new Alumnes_SEC("Enric");
        enric.addAssignatura(new Assignatura("Fonaments de la Programació", 6, 8, false));
        enric.addAssignatura(new Assignatura("Programació Orientada a l’objecte", 6, 6, false));
        enric.addAssignatura(new Assignatura("Estructura de Dades i Algorismes", 4, 9, true));
        enric.addAssignatura(new Assignatura("Programació Avançada", 4, 3, false));
        return enric;
    }

    // Alumne amb nota i assignatures aleatòries
    private Alumnes_SEC exempleRandom(String nom) {
        Alumnes_SEC randomAlumne = new Alumnes_SEC(nom);
        randomAlumne.addAssignatura(new Assignatura("Fonaments de la Programació", 6, Math.random() * 10, false));
        randomAlumne.addAssignatura(new Assignatura("Programació Orientada a l’objecte", 6, Math.random() * 10, false));
        randomAlumne.addAssignatura(new Assignatura("Estructura de Dades i Algorismes", 4, Math.random() * 10, Math.random() > 0.9));
        randomAlumne.addAssignatura(new Assignatura("Programació Avançada", 4, Math.random() * 10, false));
        return randomAlumne;
    }

    private boolean finalRecorregut() {
        return llistaDescendent.isEmpty();
    }

    private Alumnes_SEC segRecorregut() {
        return llistaDescendent.poll();
    }

    @Override
    public String toString() {
        String resultat = "";
        Queue<Alumnes_SEC> llista = new LinkedList<>(llistaDescendent);
        while (!llista.isEmpty()) {
            resultat += llista.poll().toString() + "\n";
        }
        return resultat;
    }

    public void esborraAlumnesSenseMatricula() {
        Queue<Alumnes_SEC> llista = new LinkedList<>(llistaDescendent);
        for (Alumnes_SEC alumne : llista) {
            if (!alumne.hiHa(4)) {
                try {
                    arbreACB.esborrar(alumne);
                } catch (ArbreException e) {
                    System.out.println("No s'ha pogut esborrar l'alumne");
                }
            }
        }
        llistaDescendent = arbreACB.getDescendentList();
    }

    public void afegirAlumne() {
        System.out.print("Indica el nom de l'alumne: ");
        String nom = scanner.nextLine();
        Alumnes_SEC nouAlumne = new Alumnes_SEC(nom);

        while (true) {
            System.out.print("Nom de l'assignatura (o 'fi' si ja les ha afegit totes): ");
            String nomAssignatura = scanner.nextLine();
            if (nomAssignatura.equalsIgnoreCase("fi")) {
                break;
            }
            System.out.print("Crèdits de l'assignatura: ");
            int credits = Integer.parseInt(scanner.nextLine());
            System.out.print("Nota de l'assignatura: ");
            double nota = Double.parseDouble(scanner.nextLine());
            System.out.print("Matrícula d'honor (true/false): ");
            boolean mHonor = Boolean.parseBoolean(scanner.nextLine());

            nouAlumne.addAssignatura(new Assignatura(nomAssignatura, credits, nota, mHonor));
        }
        try {
            arbreACB.inserir(nouAlumne);
            llistaDescendent = arbreACB.getDescendentList();
        } catch (ArbreException e) {
            System.out.println("No s'ha pogut afegir l'alumne: " + e.getMessage());
        }
    }


}
