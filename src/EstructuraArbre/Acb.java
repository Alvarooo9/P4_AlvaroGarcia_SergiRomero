package EstructuraArbre;

public interface Acb<E extends Comparable<E>> {

    // Insereix un element a l’arbre. Si l’element ja existeix, llança una excepció ArbreException.
    void inserir(E element) throws ArbreException;

    // Esborra un element de l’arbre. Llança una excepció si l’arbre és buit o si l’element no es troba a l’arbre.
    void esborrar(E element) throws ArbreException;

    // true si l’element està a l’arbre, fals en cas contrari
    boolean membre(E element);

    // Retorna una còpia del subarbre esquerre. En cas que l’arbre estigui buit o si no té fill esquerre retorna un arbre buit.
    Acb<E> fillEsquerre()throws CloneNotSupportedException;

    // retorna una còpia del subarbre dret en cas que l’arbre estigui buit o si no té fill dret retorna un arbre buit.
    Acb<E> fillDret() throws CloneNotSupportedException;

    // Retorna true si l’arbre és buit.
    boolean arbreBuit();

    // Neteja l’arbre deixant-lo buit.
    void buidar();
}
