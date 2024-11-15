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

    private boolean finalRecorregut(){
        return llistaDescendent.isEmpty();
    }

    private Alumnes_SEC segRecorregut(){
        return llistaDescendent.poll();
    }

    @Override
    public String toString() {
        String resultat = "";
        Queue<Alumnes_SEC> tempQueue = new LinkedList<>(llistaDescendent);
        while (!tempQueue.isEmpty()) {
            resultat += tempQueue.poll().toString() + "\n";
        }
        return resultat;
    }

    public void esborraAlumnesSenseMatricula(){
        //FER METODE
    }

    public void afegirAlumne(){
        //FER METODE
    }



}
