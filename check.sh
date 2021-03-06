#! /bin/bash

./gradlew -q jar
pth_tst=""
pth_ans=""
num_of_tests=10
for ((tst = 1; tst <= num_of_tests; tst++))
do
    if (("$tst" < 10))
    then
      pth_tst="data/mainTests/0$tst.in"
      pth_ans="data/mainTests/0$tst.out"
    else
      pth_tst="data/mainTests/$tst.in"
      pth_ans="data/mainTests/$tst.out"
    fi

    java -jar build/libs/virtual_memory.jar $pth_tst > cur.txt
    diff "cur.txt" "$pth_ans" > diff.txt
    if [ -s diff.txt ]
      then
        echo "$tst test: FAIL"
        exit 202
      else
        echo "$tst test: OK"
    fi
done

rm -f diff.txt cur.txt
./gradlew clean

echo "tests passed"