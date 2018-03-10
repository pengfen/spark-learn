#!/bin/bash

# 1. 获取今天的日期
# date+'%Y-%m-%d'或者date -d "now" +%Y-%m-%d

# 2. 获取昨天的日期
# date -d "yesterday" +%Y-%m-%d或者date -d "1 days ago" +%Y-%m-%d

# 3. 获取前天的日期
# date -d "1 days ago" +%Y-%m-%d

# 4. 获取具体日期的前几天
# date -d"15 day ago 2017-04-16" +%Y-%m-%d

#获取当前日期 如20160903
date_now=`date +%Y%m%d`

# 1. 先备份数据 bs_user.log.bak_20180304
cp -a /home/ricky/data/spark/basic/bs_user_${date_now}.log /home/ricky/data/spark/basic/bs_user._${date_now}.log.bak

# 2. 移到数据至data/pro/user
mv /home/ricky/data/spark/basic/bs_user_${date_now}.log /home/ricky/data/pro/user