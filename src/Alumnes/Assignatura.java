package Alumnes;

public class Assignatura {


    public static final int EXCELLENT = 9;
    public static final int NOTABLE = 7;
    public static final int APROVAT = 5;

    private final String nom;
    private final int credits;
    private double nota;
    private final boolean mHonor;

    public Assignatura(String nom, int credits, double nota, boolean mHonor) {

        if (credits < 0 || nota < 0) throw new IllegalArgumentException();
        this.nom = nom;
        this.credits = credits;
        this.nota = nota;
        this.mHonor = (nota >= EXCELLENT) && mHonor;
    }

    public Assignatura(String nom) {
        this.nom = nom;
        this.credits = 0;
        this.nota = 0.0;
        this.mHonor = false;
    }

    public void setNota(double nota) {
        if (nota < 0) {
            throw new IllegalArgumentException("La nota no pot ser negativa.");
        }
        this.nota = nota;
    }

    public double getNota() {
        return this.nota;
    }

    public int getPunts() {
        if (nota >= EXCELLENT) {
            return mHonor ? 4 : 3;
        } else if (nota >= NOTABLE) {
            return 2;
        } else if (nota >= APROVAT) {
            return 1;
        } else {
            return 0;
        }
    }

    public int getCredits() {
        return this.credits;
    }

    @Override
    public String toString() {
        return "Assignatura: " + nom + ", Nota mitjana: " + nota;
    }

    @Override
    public boolean equals(Object other) {
        if (this == other) return true;
        if (other == null || getClass() != other.getClass()) return false;
        Assignatura that = (Assignatura) other;
        return nom.equals(that.nom);
    }
}
