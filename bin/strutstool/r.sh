#! /usr/bin/env bash

echo "Remember to run this with the project name as the (first and only) parameter e.g. ./r.sh test"

#./strutstool.sh new project test com.ypg

cd $1

#../strutstool.sh scaffold Statement date:date userId1:string userId2:string branchNo:string pointsReceived:long pointsGiven:long referenceNo:string ipAddress:string
../strutstool.sh scaffold Movie id:long date:date title:string genre:string url:string catalogNo:string upc:string
