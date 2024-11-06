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
        if (nota >= EXCELLENT && mHonor==true){
            mHonor = true;
        }
        this.nom = nom;
        this.credits = credits;
        this.nota = nota;
        this.mHonor = mHonor;

    }





}
