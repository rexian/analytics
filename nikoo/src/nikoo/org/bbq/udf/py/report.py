#!/usr/bin/python
import sys
from datetime import datetime
for line in sys.stdin.readlines():
    boolVal = "false"
    line = line.strip()
    fields = line.split('\t')
    asset_end_dt = datetime.strptime(fields[0], "%m/%d/%Y")
    renewal_create_dt = datetime.strptime(fields[1], "%m/%d/%Y")
    if(asset_end_dt >= renewal_create_dt) :
        boolVal = "true"
    else :
        boolVal = "false"
    print asset_end_dt,"\t",renewal_create_dt,"\t",boolVal