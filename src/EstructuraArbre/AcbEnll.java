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

        @Override
        protected NodeA clone() throws CloneNotSupportedException {
            NodeA cloned = (NodeA) super.clone();
            if (this.esq != null) {
                cloned.esq = this.esq.clone();
            }
            if (this.dret != null) {
                cloned.dret = this.dret.clone();
            }
            return cloned;
        }
    }

    NodeA arrel;

    public AcbEnll(NodeA arrel) {
        this.arrel = arrel;
    }

    public AcbEnll() {
        this.arrel = null;
    }

    @Override
    public void inserir(E element) throws ArbreException {
        if (this.arrel == null) {
            this.arrel = new NodeA(element);
        } else {
            inserirRecursive(arrel, element);
        }
    }

    private void inserirRecursive(NodeA node, E element) throws ArbreException {
        int cmp = element.compareTo(node.inf);
        if (cmp == 0) {
            throw new ArithmeticException("L'element ja existeix a l'arbre");
        } else if (cmp < 0) {
            if (node.esq == null) {
                node.esq = new NodeA(element);
            } else {
                inserirRecursive(node.esq, element);
            }
        } else {
            if (node.dret == null) {
                node.dret = new NodeA(element);
            } else {
                inserirRecursive(node.dret, element);
            }
        }
    }

    @Override
    public void esborrar(E element) throws ArbreException {
        arrel = esborrarRecursive(arrel, element);
    }

    private NodeA esborrarRecursive(NodeA node, E element) throws ArbreException {
        if (node == null) {
            throw new ArbreException("Element no trobat a l'arbre");
        }

        int cmp = element.compareTo(node.inf);

        if (cmp < 0) {
            node.esq = esborrarRecursive(node.esq, element);
        } else if (cmp > 0) {
            node.dret = esborrarRecursive(node.dret, element);
        } else {
            if (node.esq == null) return node.dret;
            if (node.dret == null) return node.esq;
            NodeA minNode = esborrarMinim(node.dret);
            node.inf = minNode.inf;
            node.dret = esborrarRecursive(node.dret, minNode.inf);
        }
        return node;
    }

    //mètode per ajudar a esborrar un node el qual té dos fills i que l'arbre segueixi quedant ordenat
    private NodeA esborrarMinim(NodeA node) {
        while (node.esq != null) {
            node = node.esq;
        }
        return node;
    }

    @Override
    public boolean membre(E element) {
        return membreRecursive(arrel, element);
    }

    private boolean membreRecursive(NodeA node, E element) {
        if (node == null) return false;
        int cmp = element.compareTo(node.inf);
        if (cmp == 0) return true;
        if (cmp < 0) {
            return membreRecursive(node.esq, element);
        } else {
            return membreRecursive(node.dret, element);
        }
    }

    @Override
    public E arrel() throws ArbreException {
        if (arrel == null) throw new ArbreException("L'arbre esta buit");
        return arrel.inf;
    }

    @Override
    public Acb<E> fillDret() throws CloneNotSupportedException {
        if (arrel == null || arrel.dret == null) return new AcbEnll<>();
        return new AcbEnll<>(arrel.dret.clone());
    }

    @Override
    public Acb<E> fillEsquerre() throws CloneNotSupportedException {
        if (arrel == null || arrel.esq == null) return new AcbEnll<>();
        return new AcbEnll<>(arrel.esq.clone());
    }

    @Override
    public boolean arbreBuit() {
        if (arrel == null) return true;
        return false;
    }

    @Override
    public void buidar() {
        arrel = null;
    }

    public Queue<E> getAscendentList() {
        Queue<E> cua = new LinkedList<>();
        omplirInOrdre(arrel, cua);
        return cua;
    }

    private void omplirInOrdre(NodeA node, Queue<E> cua) {
        if (node != null) {
            omplirInOrdre(node.esq, cua);
            cua.add(node.inf);
            omplirInOrdre(node.dret, cua);
        }
    }

    public Queue<E> getDescendentList() {
        Queue<E> cua = new LinkedList<>();
        omplirReverseInOrdre(arrel, cua);
        return cua;
    }

    private void omplirReverseInOrdre(NodeA node, Queue<E> cua) {
        if (node != null) {
            omplirReverseInOrdre(node.dret, cua);
            cua.add(node.inf);
            omplirReverseInOrdre(node.esq, cua);
        }
    }

    public int cardinalitat() {
        return cardinalitatRecursive(arrel);
    }

    private int cardinalitatRecursive(NodeA node) {
        if (node == null) return 0;
        return 1 + cardinalitatRecursive(node.esq) + cardinalitatRecursive(node.dret);
    }
}
