#!/bin/bash

mkdir out >/dev/null 2>&1
echo "Google Protocol Buffer Compiler: GPBC"

list_compile=""
count=0
for protofile in `dir -d *.proto` ; do
  list_compile="$list_compile $protofile "
  count=$((count+1))
done

echo "[GPBC] Compiling $count proto files..."
#echo $list_compile
protoc $list_compile --java_out=out/ --proto_path=.

echo "[GPBC] Compilation DONE"

