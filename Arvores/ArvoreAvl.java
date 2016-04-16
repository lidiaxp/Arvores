package Arvores;

public class ArvoreAvl {

    No raiz;                //onde fica armazenado a arvore
    String print = "";      //para iniciar a impressao da arvore

    class No {              //classe para cada no da arvore
        No esquerda;        //os nos da arvore e seu valor
        No direita;
        No pai;
        int valor;

        public No(int valor, No pai) {         //para quando criar o no ela ter pai e um valor
            this.valor = valor;
            this.pai = pai;
        }
    }

    public void inserir(int valor) {            //metodo para inserir os nos
        int x = 0;                              //x mostra se ja colocou ou nao o no na arvore
        if (raiz == null) {                     //se a raiz tiver vazia, o no inserido sera a raiz
            raiz = new No(valor, null);
        } else {                                //caso contrario
            No aux = raiz;                      //armazena-se a raiz em uma variavel auxiliar
            while (x == 0) {                    //checa se o no ja foi inserido
                if (valor > aux.valor) {     //se o no for maior que o ultimo elemento inserido, ele olha o elemento da direita
                    if (aux.direita == null) {  //se o elemento da direita nao tiver ninguem
                        No n = new No(valor, aux);
                        aux.direita = n;           //insere o elemento na direita
                        quemBalancear(n);          //balanceia o no
                        x = 1;                     //avisa que o no foi inserido
                    } else {                      //se o da direita nao estiver vazio, ele manda ele checar tudo isso de novo
                        aux = aux.direita;        //so que levando como referencia o no da direita do no anterior
                    }
                } else {                   //se o no for menor que o ultimo elemento inserido, ele olha o elemento da esquerda
                    if (aux.esquerda == null) {      //se o elemento da esquerda nao tiver ninguem
                        No n = new No(valor, aux);
                        aux.esquerda = n;            //insere o elemento na esquerda
                        quemBalancear(n);            //balanceia o no
                        x = 1;                       //avisa que o no foi inserido
                    } else {                         //se o da esquerda nao estiver vazio, ele manda ele checar tudo isso de novo
                        aux = aux.esquerda;          //so que levando como referencia o no da esquerda do no anterior
                    }
                }
            }
        }
    }

    public void quemBalancear(No n) {     //no para saber quem balancear
        if (n.pai != null) {             //o no inserido nunca estara balanceado, seu avo ou bisavo que estarao e aki
            if (n.pai.pai != null) {     //checa se eles estao nulos ou nao
                 //e aki para ver se eles estao desbalanceados ou nao
                if (alturaEsquerda(n.pai.pai) - alturaDireita(n.pai.pai) > 1 || alturaDireita(n.pai.pai) - alturaEsquerda(n.pai.pai) > 1) {
                    balancear(n.pai.pai);  //caso o avo esteja desbalanceado, o mesmo vai ser balanceado
                }
            }
        }
        if (n.pai != null) {            //o no inserido nunca estara balanceado, seu avo ou bisavo que estarao e aki
            if (n.pai.pai != null) {         //checa se eles estao nulos ou nao
                if (n.pai.pai.pai != null) {     
                    //e aki para ver se eles estao desbalanceados ou nao
                    if (alturaEsquerda(n.pai.pai.pai) - alturaDireita(n.pai.pai.pai) > 1 || alturaDireita(n.pai.pai.pai) - alturaEsquerda(n.pai.pai.pai) > 1) {
                        balancear(n.pai.pai.pai);    //caso o bisavo esteja desbalanceado, o mesmo vai ser balanceado
                    }
                }
            }
        }
    }

    public int alturaEsquerda(No n) { //metodo para checar a altura esquerda dos nos
        int alturaEsquerda = 0;
        if (n.esquerda != null) {        //se nao tiver alguem na esquerda soma um
            n = n.esquerda;
            alturaEsquerda++;
            if (n.esquerda != null ) {     //a partir da primeira esquerda ele comeca a checar tanto na direita
                n = n.esquerda;            //quanto na esquerda pois os dois contaram como altura do no a esquerda
                alturaEsquerda++;          //ele so checa ate o terceiro filho, pois nos metodos que eh chamado
                if(n.esquerda != null || n.direita != null){    //so eh necessario ate o terceiro, pois provavelmente o
                    alturaEsquerda++;              //proximo seria nulo
                }
            }else if(n.direita != null){
                n = n.direita;
                alturaEsquerda++;
                if(n.esquerda != null || n.direita != null){
                    alturaEsquerda++;
                }
            }
        }
        return alturaEsquerda;
    }

    public int alturaDireita(No n) {     //metodo para checar a altura direita dos nos
        int alturaDireita = 0;
        if (n.direita != null) {
            n = n.direita;
            alturaDireita++;
            if (n.esquerda != null ) {    //a partir da primeira esquerda ele comeca a checar tanto na direita
                n = n.esquerda;           //ele so checa ate o terceiro filho, pois nos metodos que eh chamado
                alturaDireita++;          //ele so checa ate o terceiro filho, pois nos metodos que eh chamado
                if(n.esquerda != null || n.direita != null){    //so eh necessario ate o terceiro, pois provavelmente o
                    alturaDireita++;              //proximo seria nulo
                }
            }else if(n.direita != null){
                n = n.direita;
                alturaDireita++;
                if(n.esquerda != null || n.direita != null){
                    alturaDireita++;
                }
            }
        }
        return alturaDireita;
    }

    public void balancear(No n) {  //diferencia qual tipo de balanceamento o no vai ter de acordo com as regras de balanceamento
        if (alturaEsquerda(n) > alturaDireita(n) && alturaEsquerda(n.esquerda) > alturaDireita(n.esquerda)) {
            rotacaoDireita(n);
        } else if (alturaEsquerda(n) < alturaDireita(n) && alturaEsquerda(n.direita) < alturaDireita(n.direita)) {
            rotacaoEsquerda(n);
        } else if (alturaEsquerda(n) > alturaDireita(n) && alturaEsquerda(n.esquerda) < alturaDireita(n.esquerda)) {
            rotacaoDuplaDireita(n);
        } else if (alturaEsquerda(n) < alturaDireita(n) && alturaEsquerda(n.direita) > alturaDireita(n.direita)) {
            rotacaoDuplaEsquerda(n);
        }
    }

    public void rotacaoEsquerda(No n) {      //faz a rotaçao para esquerda do no
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

    public void rotacaoDireita(No n) {         //faz a rotaçao para direita do no
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

    public void rotacaoDuplaEsquerda(No n) {          //faz a rotaçao dupla esquerda do no
        rotacaoDireita(n.direita);
        rotacaoEsquerda(n);
    }

    public void rotacaoDuplaDireita(No n) {             //faz a rotaçao dupla direita do no
        rotacaoEsquerda(n.esquerda);
        rotacaoDireita(n);
    }

    public String printar() {       //metodo para printar a arvore
        if (raiz == null) {
            return "";                  //se nao tiver arvore, nao printa nada
        } else {
            No n = this.raiz;                //n recebe raiz
            print = print + Integer.toString(n.valor) + " ";   //e printa o valor dela
            if (n.esquerda != null) {         //se tiver algo na esquerda
                print = print + "(" + printaraux(n.esquerda) + ")";  //printa todos os valores de la dentro do primeiro parenteses
            }
            if (n.direita != null) {          //se tiver algo na direita
                print = print + "(" + printaraux(n.direita) + ")";   //printa todos os valores de la dentro do segundo parenteses
            }
            String pr = print;        // guarda tudo o que salvo durante esse loop numa variavel auxiliar
            print = " ";              //zera a variavel print para poder imprimir novas arvores
            return pr;                //e retorna a string que contem a arvore
        }
    }

    private String printaraux(No n) {             //metodo para auxiliar o print da arvore
        print = Integer.toString(n.valor) + " ";   //transforma o valor do no em uma string
        if (n.esquerda != null) {               //se tiver algo na esquerda
            print = print + "( " + printaraux(n.esquerda) + ")";  //printa todos os valores de la dentro do primeiro parenteses
        }
        if (n.direita != null) {      //se tiver algo na direita
            print = print + "(" + printaraux(n.direita) + ")"; //printa todos os valores de la dentro do segundo parenteses
        }
        String pr = print;        // guarda tudo o que salvo durante esse loop numa variavel auxiliar
        print = " ";              //zera a variavel print para poder imprimir novas arvores
        return pr;                //e retorna a string que contem a arvore
    }
    /* a ideia desses dois metodos e printar a raiz e dois parenteses exemplo:8(4)(10), fora do parentese 
    esta a raiz, o primeiro parentese é o no da esquerda e o segundo o no da direita, caso os nos da direita 
    ou esquerda tenham filhos vao seguir a mesma logica exemplo:8(4(3)(5))(10(9)(11)), 3 é filho da esquerda de quatro
    e 5 é filho da direita de 4.*/
}
