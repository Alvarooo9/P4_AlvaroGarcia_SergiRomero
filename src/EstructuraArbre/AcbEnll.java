package EstructuraArbre;

import java.util.LinkedList;
import java.util.Queue;

public class AcbEnll<E extends Comparable<E>> implements Acb<E> {

    private class NodeA implements Cloneable {
        E inf;
        NodeA dret, esq;

        public NodeA(NodeA esq, NodeA dret, E inf) {
            this.esq = esq;
            this.dret = dret;
            this.inf = inf;
        }

        public NodeA(E inf) {
            this(null, null, inf);
        }

        private boolean membreRecursive(E element) {
            if (inf == null) return false;
            int cmp = element.compareTo(inf);
            if (cmp < 0) return esq != null && esq.membreRecursive(element);
            if (cmp > 0) return dret != null && dret.membreRecursive(element);
            return true;
        }

        private void inserirRecursive(E element) {
            int cmp = element.compareTo(inf);
            if (cmp < 0) {
                if (esq == null) esq = new NodeA(element);
                else esq.inserirRecursive(element);
            } else if (cmp > 0) {
                if (dret == null) dret = new NodeA(element);
                else dret.inserirRecursive(element);
            }
        }

        //mètode per ajudar a esborrar un node el qual té dos fills i que l'arbre segueixi quedant ordenat
        private E esborrarMinim(NodeA node) {
            if (node.esq == null) return node.inf;
            return esq.esborrarMinim(node.esq);
        }

        private NodeA esborrarRecursive(E element) throws ArbreException{
            int cmp = element.compareTo(inf);
            if (cmp < 0 && esq != null) {
                esq = esq.esborrarRecursive(element);
            } else if (cmp > 0 && dret != null) {
                dret = dret.esborrarRecursive(element);
            } else if (cmp == 0) {
                if (dret == null) return esq;
                if (esq == null) return dret;
                inf = dret.esborrarMinim(dret);
                dret = dret.esborrarRecursive(inf);
            }
            return this;
        }

        private void omplirInordre(Queue<E> cua) {
            if (esq != null) esq.omplirInordre(cua);
            cua.add(inf);
            if (dret != null) dret.omplirInordre(cua);
        }

        private void omplirReverseInordre(Queue<E> cua) {
            if (dret != null) dret.omplirReverseInordre(cua);
            cua.add(inf);
            if (esq != null) esq.omplirReverseInordre(cua);
        }

        private int cardinalitatRecursive() {
            int count = 1;
            if (esq != null) count += esq.cardinalitatRecursive();
            if (dret != null) count += dret.cardinalitatRecursive();
            return count;
        }

        @Override
        protected Object clone() throws CloneNotSupportedException {
            NodeA clonat = new NodeA(this.inf);
            if (this.esq != null) clonat.esq = (NodeA) this.esq.clone();
            if (this.dret != null) clonat.dret = (NodeA) this.dret.clone();
            return clonat;
        }

    }

    private NodeA arrel;

    public AcbEnll(NodeA arrel) {
        this.arrel = arrel;
    }

    public AcbEnll() {
        this.arrel = null;
    }

    @Override
    public E arrel() throws ArbreException {
        if (arrel == null) throw new ArbreException("L'arbre esta buit");
        return arrel.inf;
    }


    @Override
    public Acb<E> fillEsquerre() throws CloneNotSupportedException {
        AcbEnll<E> subarbre = new AcbEnll<>();
        if (arrel != null && arrel.esq != null) {
            subarbre.arrel = (NodeA) arrel.esq.clone();
        }
        return subarbre;
    }

    @Override
    public Acb<E> fillDret() throws CloneNotSupportedException {
        AcbEnll<E> subarbre = new AcbEnll<>();
        if (arrel != null && arrel.dret != null) {
            subarbre.arrel = (NodeA) arrel.dret.clone();
        }
        return subarbre;
    }

    @Override
    public boolean arbreBuit() {
        return arrel == null;
    }

    @Override
    public void buidar() {
        arrel = null;
    }

    @Override
    public void inserir(E element) throws ArbreException {
        if (arrel == null) {
            arrel = new NodeA(element);
        } else {
            arrel.inserirRecursive(element);
        }
    }

    @Override
    public void esborrar(E element) throws ArbreException {
        if (arrel == null) throw new ArbreException("Element no trobat a l'arbre.");
        arrel = arrel.esborrarRecursive(element);
    }

    @Override
    public boolean membre(E element) {
        return arrel != null && arrel.membreRecursive(element);
    }

    public Queue<E> getAscendentList() {
        Queue<E> cua = new LinkedList<>();
        if (arrel != null) arrel.omplirInordre(cua);
        return cua;
    }

    public Queue<E> getDescendentList() {
        Queue<E> cua = new LinkedList<>();
        if (arrel != null) arrel.omplirReverseInordre(cua);
        return cua;
    }

    public int cardinalitat() {
        if (arrel != null) {
            return arrel.cardinalitatRecursive();
        } else {
            return 0;
        }
    }
}
