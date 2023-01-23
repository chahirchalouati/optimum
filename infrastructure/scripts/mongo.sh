#!/usr/bin/env bash
for i in {1..5} ; do

curl http://localhost:9999/idp/idp/register/rest
   -F key1=value1
   -F key2=value2
   -F photo=@C:/Users/Chahir Chalouati/Downloads/avatar.jpg
done