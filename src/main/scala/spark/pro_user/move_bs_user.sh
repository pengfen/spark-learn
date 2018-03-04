#!/bin/bash

#获取当前日期 如20160903
date_now=`date +%Y%m%d`

# 1. 先备份数据 bs_user.log.bak_20180304
cp -a /home/ricky/data/spark/basic/bs_user.log /home/ricky/data/spark/basic/bs_user.log.bak_${date_now}

# 2. 移到数据至data/pro/user
mv /home/ricky/data/spark/basic/bs_user.log /home/ricky/data/pro/user