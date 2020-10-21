#! /bin/bash
n=$1
m=$2
chartflag=$3
q=$4

bash gen_file.sh $n $m $chartflag $q
gradle run --args="rnd.txt"