package EstructuraArbre;

//DONA ERROR PERQUÈ QUEDA IMPLMENTAR ELS ALTRES MÈTODES DE LA INTERFÍCIE ACB
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
}
