package Arvores;

public class ArvoreRN {

    class No {

        No esquerda;
        No direita;
        No pai;
        int valor;
        boolean cor;

        public No(int valor, No pai, boolean cor) {
            this.valor = valor;
            this.pai = pai;
            this.cor = cor; //vermelho == true && preto == false 
        }
    }

    No raiz;
    String print = "";

    public void inserir(int valor) {
        int x = 0;
        No n = new No(valor, null, true);
        if (raiz == null) {
            raiz = new No(valor, null, false);
        } else {
            No aux = raiz;
            if (n.valor > raiz.valor) {
                if (raiz.direita == null) {
                    n.pai = raiz;
                    raiz.direita = n;
                }
            } else {
                if (raiz.esquerda == null) {
                    n.pai = raiz;
                    raiz.esquerda = n;
                }
            }
        }
        if (raiz.esquerda != null) {
            if (raiz.esquerda.esquerda != null || raiz.esquerda.direita != null || raiz.direita.esquerda != null || raiz.direita.direita != null) {
                if (alturaEsquerda(n.pai.pai) - alturaDireita(n.pai.pai) > 1 || alturaDireita(n.pai.pai) - alturaEsquerda(n.pai.pai) > 1) {
                    balancear(n.pai.pai);
                }
                balancearCores(n.pai.pai, n);
            }
        }
        if (raiz.direita != null) {
            if (raiz.direita.esquerda != null || raiz.direita.direita != null) {
                if (alturaEsquerda(n.pai.pai) - alturaDireita(n.pai.pai) > 1 || alturaDireita(n.pai.pai) - alturaEsquerda(n.pai.pai) > 1) {
                    balancear(n.pai.pai);
                }
                balancearCores(n.pai.pai, n);
            }
        }
    }

    public void balancearCores(No n, No filho) {
        if (filho.pai.cor) {
            if (alturaEsquerda(n.pai) > alturaDireita(n.pai) && alturaEsquerda(n) > alturaDireita(n)) {
                filho.pai.cor = false;
                n.cor = true;
            } else if (alturaEsquerda(n.pai) < alturaDireita(n.pai) && alturaEsquerda(n) < alturaDireita(n)) {
                filho.pai.cor = false;
                n.cor = true;
            } else if (alturaEsquerda(n.pai) > alturaDireita(n.pai) && alturaEsquerda(n) < alturaDireita(n)) {
                filho.cor = false;
                n.cor = true;
            } else if (alturaEsquerda(n.pai) < alturaDireita(n.pai) && alturaEsquerda(n) > alturaDireita(n)) {
                filho.cor = false;
                n.cor = true;
            }
            balancear(n);
        }
        if (filho.pai.pai.esquerda == filho.pai.pai) {
            if (n.direita.cor) {
                n.direita.cor = false;
                n.esquerda.cor = false;
            }
        } else {
            if (n.esquerda.cor) {
                n.direita.cor = false;
                n.esquerda.cor = false;
            }
        }
        if (n.pai.cor && n.cor) {
            balancearCores(n.pai.pai, n);
        }
        raiz.cor = false;
    }

    public int alturaEsquerda(No n) {
        int alturaEsquerda = 0;
        while (n.esquerda != null) {
            alturaEsquerda++;
        }
        return alturaEsquerda;
    }

    public int alturaDireita(No n) {
        int alturaDireita = 0;
        while (n.direita != null) {
            alturaDireita++;
        }
        return alturaDireita;
    }

    public void balancear(No n) {
        if (alturaEsquerda(n.pai) > alturaDireita(n.pai) && alturaEsquerda(n) > alturaDireita(n)) {
            rotacaoDireita(n);
        } else if (alturaEsquerda(n.pai) < alturaDireita(n.pai) && alturaEsquerda(n) < alturaDireita(n)) {
            rotacaoEsquerda(n);
        } else if (alturaEsquerda(n.pai) > alturaDireita(n.pai) && alturaEsquerda(n) < alturaDireita(n)) {
            rotacaoDuplaDireita(n);
        } else if (alturaEsquerda(n.pai) < alturaDireita(n.pai) && alturaEsquerda(n) > alturaDireita(n)) {
            rotacaoDuplaEsquerda(n);
        }
    }

    public void rotacaoEsquerda(No n) {
        if (n.pai.esquerda == n) {
            No aux = n.pai.esquerda;
            No aux2 = n.pai.esquerda.direita.esquerda;
            n.pai.esquerda = n.pai.esquerda.direita;
            n.pai.esquerda.esquerda = aux;
            n.pai.esquerda.esquerda.direita = aux2;
        } else {
            No aux = n.pai.direita;
            No aux2 = n.pai.direita.direita.esquerda;
            n.pai.direita = n.pai.direita.direita;
            n.pai.direita.esquerda = aux;
            n.pai.direita.esquerda.direita = aux2;
        }
    }

    public void rotacaoDireita(No n) {
        if (n.pai.esquerda == n) {
            No aux = n.pai.esquerda;
            No aux2 = n.pai.esquerda.esquerda.direita;
            n.pai.esquerda = n.pai.esquerda.esquerda;
            n.pai.esquerda.direita = aux;
            n.pai.esquerda.direita.esquerda = aux2;
        } else {
            No aux = n.pai.direita;
            No aux2 = n.pai.direita.esquerda.direita;
            n.pai.direita = n.pai.direita.esquerda;
            n.pai.direita.direita = aux;
            n.pai.direita.direita.esquerda = aux2;
        }
    }

    public void rotacaoDuplaEsquerda(No n) {
        rotacaoDireita(n.direita);
        rotacaoEsquerda(n);
    }

    public void rotacaoDuplaDireita(No n) {
        rotacaoEsquerda(n.esquerda);
        rotacaoDireita(n);
    }

    public String printar() {
        if (raiz == null) {
            return "";
        } else {
            No n = this.raiz;
            print = print + Integer.toString(n.valor) + " - " + n.cor + " ";
            if (n.esquerda != null) {
                print = print + "(" + printaraux(n.esquerda) + " - " + n.esquerda.cor + ")";
            }
            if (n.direita != null) {
                print = print + "(" + printaraux(n.direita) + " - " + n.direita.cor + ")";
            }
            String pr = print;
            print = " ";
            return pr;
        }
    }

    private String printaraux(No n) {
        print = Integer.toString(n.valor) + " ";
        if (n.esquerda != null) {
            print = print + "( " + printaraux(n.esquerda) + " - " + n.esquerda.cor + ")";
        }
        if (n.direita != null) {
            print = print + "(" + printaraux(n.direita) + " - " + n.direita.cor + ")";
        }
        String pr = print;
        print = " ";
        return pr;
    }
}
