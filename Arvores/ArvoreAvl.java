package Arvores;

public class ArvoreAvl {

    No raiz;
    String print = "";

    class No {

        No esquerda;
        No direita;
        No pai;
        int valor;

        public No(int valor, No pai) {
            this.valor = valor;
            this.pai = pai;
        }
    }

    public void inserir(int valor) {
        int x = 0;
        if (raiz == null) {
            raiz = new No(valor, null);
        } else {
            No aux = raiz;
            while (x == 0) {
                if (valor > aux.valor) {
                    if (aux.direita == null) {
                        No n = new No(valor, aux);
                        aux.direita = n;
                        quemBalancear(n);
                        x = 1;
                    } else {
                        aux = aux.direita;
                    }
                } else {
                    if (aux.esquerda == null) {
                        No n = new No(valor, aux);
                        aux.esquerda = n;
                        quemBalancear(n);
                        x = 1;
                    } else {
                        aux = aux.esquerda;
                    }
                }
            }
        }
    }

    public void quemBalancear(No n) {
        if (n.pai != null) {
            if (n.pai.pai != null) {
                if (n.pai.pai.pai != null) {
                    if (alturaEsquerda(n.pai.pai.pai) - alturaDireita(n.pai.pai.pai) >= 1 || alturaDireita(n.pai.pai.pai) - alturaEsquerda(n.pai.pai.pai) >= 1) {
                        balancear(n.pai.pai);
                    }
                }
            }
        }
    }

    public int alturaEsquerda(No n) {
        int alturaEsquerda = 0;
        while (n.esquerda != null) {
            n = n.esquerda;
            alturaEsquerda++;
        }
        return alturaEsquerda;
    }

    public int alturaDireita(No n) {
        int alturaDireita = 0;
        while (n.direita != null) {
            n = n.direita;
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

    public void rotacaoEsquerda(No n) {  // rotação esquerda no nó n
        if (n.pai.esquerda == n) {   //checa se o nó n é igual ao nó pai na esquerda. se sim:
            No aux = n.pai.esquerda;   // atribui o nó pai da esquerda a um nó auxiliar
            No aux2 = n.pai.esquerda.direita.esquerda;  //atribui o 
            n.pai.esquerda = n.pai.esquerda.direita;
            n.pai.esquerda.esquerda = aux;
            n.pai.esquerda.esquerda.direita = aux2;
        } else {  //se não:
            No aux = n.pai.direita;  //atrubui o nó pai da direita a um nó auxiliar
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
            print = print + Integer.toString(n.valor) + " ";
            if (n.esquerda != null) {
                print = print + "(" + printaraux(n.esquerda) + ")";
            }
            if (n.direita != null) {
                print = print + "(" + printaraux(n.direita) + ")";
            }
            String pr = print;
            print = " ";
            return pr;
        }
    }

    private String printaraux(No n) {
        print = Integer.toString(n.valor) + " ";
        if (n.esquerda != null) {
            print = print + "( " + printaraux(n.esquerda) + ")";
        }
        if (n.direita != null) {
            print = print + "(" + printaraux(n.direita) + ")";
        }
        String pr = print;
        print = " ";
        return pr;
    }
}
