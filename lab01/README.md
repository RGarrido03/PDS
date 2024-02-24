# Aula01 - Notas

Realizado por:
- Bruno Lopes (Nºmec 68264)
- Rúben Garrido (Nºmec 107927)

## Exercício 1
### Objetivo

Este exercício tem como objetivo, ao receber como argumento um ficheiro de texto que contém uma sopa de letras por resolver e um conjunto de palavras que se encontram nela. Resolver a sopa de letras e imprimir a mesma resolvida com a seguinte informação:
palavra           tamanho da palavra(lenght)          coordenadas(linha,coluna) da primeira letra       direção da palavra<br />


### Intruções de compilação e execução

Para compilar é necessário executar o comando na pasta lab01:
```
javac WSSolver.java Direction.java Utils.java WordSearch.java Word.java
```
Para executar basta executar o seguinte comando, passando como argumento o ficheiro pretendido:

Comando:
```
java WSSolver <filename>
```

## Exercício 2
### Objetivo
O objetivo deste exercício é gerar uma sopa de letras que contenha um conjunto de palavras que estão presentes num ficheiro de texto passado como argumento, bem como o número de linhas que a sopa de letras deverá de ter.
- wlist1.txt
- wlist2.txt
- wlist3.txt

### Instruções de compilação e execução
Para compilar é necessário ir à pasta lab01 e executar o seguinte comando:
```
javac WSGenerator.java Word.java WordSearch.java Utils.java
```
Para executar basta ter como argumentos obrigatórios o nome do ficheiro, também se pode passar como argumento o nome do ficheiro de texto em que queira guardar, mas caso não se faça irá ter como nome wordlist_result.txt. Para o ficheiro de texto que contém as palavras pode usar os seguintes:
- wl01.txt
- wl02.txt
- wl03.txt<br />

Comando:
```
java WSGenerator -i Outputs/wl01.txt -s 13

