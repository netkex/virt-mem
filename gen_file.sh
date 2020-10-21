#! /bin/bash
n=$1
m=$2
chartflag=$3
q=$4
echo "$n $m $chartflag" > rnd.txt
for ((i = 1; i < q; i++))
do
	x=$(( RANDOM%$n + 1))
	echo -n "$x ">>rnd.txt
done
