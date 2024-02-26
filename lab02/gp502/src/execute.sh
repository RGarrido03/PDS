#!/bin/bash

rm ./*.class

javac WSSolver.java
javac WSGenerator.java

java WSGenerator -i ../wlist0.txt -s 12 -o ../sopa0.txt
java WSSolver ../sopa0.txt > ../solu0.txt

java WSGenerator -i ../wlist1.txt -s 15 -o ../sopa1.txt
java WSSolver ../sopa1.txt > ../solu1.txt

java WSGenerator -i ../wlist2.txt -s 15 -o ../sopa2.txt
java WSSolver ../sopa2.txt > ../solu2.txt

java WSGenerator -i ../wlist3.txt -s 40 -o ../sopa3.txt
java WSSolver ../sopa3.txt > ../solu3.txt

echo "Done!"