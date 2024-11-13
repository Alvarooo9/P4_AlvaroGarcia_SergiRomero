package Alumnes;

public class Alumnes_SEC implements Comparable<Alumnes_SEC> {

    class Node {
        Node next;
        Assignatura info;

        public Node(Assignatura info) {
            this.next = null;
            this.info = info;
        }

        public Node(String nom) {
            this.info = new Assignatura(nom);
            this.next = null;
        }
    }

    private Node cap; //guarda el nom de l’alumne i la nota mitjana calculada en funció de les assignatures que hi ha a la seqüència enllaçada

    public Alumnes_SEC(String nom) {
        this.cap = new Node(nom);
    }

    public void addAssignatura(Assignatura nova) {
        Node actual = cap;
        Node anterior = null;

        while (actual.next != null) {
            anterior = actual;
            actual = actual.next;
            if (actual.info.equals(nova)) {
                actual.info = nova;
                calcularNotaMitjana();
                return;
            }
        }
        actual.next = new Node(nova);
        calcularNotaMitjana();
    }

    private void calcularNotaMitjana() {
        int sumaPonderada = 0;
        int totalCredits = 0;
        Node actual = cap.next;

        while (actual != null) {
            int punts = actual.info.getPunts();
            int credits = actual.info.getCredits();
            sumaPonderada += punts * credits;
            totalCredits += credits;
            actual = actual.next;
        }
        if (totalCredits > 0) {
            cap.info.setNota((double) sumaPonderada / totalCredits);
        } else {
            cap.info.setNota(0);
        }
    }

    public boolean hiHa(int punts) {
        Node actual = cap.next;
        while (actual != null) {
            if (actual.info.getPunts() == punts) {
                return true;
            }
            actual = actual.next;
        }
        return false;
    }

    public int compareTo(Alumnes_SEC other) {
        double diff = this.cap.info.getNota() - other.cap.info.getNota();

        if (diff < 0) {
            return -1;
        } else if (diff > 0) {
            return 1;
        } else {
            return 0;
        }
    }

    @Override
    public String toString() {
        return "Alumne: " + cap.info.toString() + ", Nota Mitjana: " + cap.info.getNota();
    }


}
