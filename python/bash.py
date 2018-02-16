#!/usr/bin/python
#coding=UTF-8 (脚本中有汉字需要添加此行)

# 第一个注释
print "welcome to python world"

# python2中 print是一个关键字 python3中 print是一个函数 必须使用 print(arg)
if True:
    print "True"
else:
    print "false"

# 变量定义和赋值
counter = 100 # 整型
miles = 1000.0 # 浮点
# 注释可能会在声明中表达或同一行之后
name = "caopeng" # 注释---解释声明

print counter
print miles
print name

# 多重赋值
a = b = c = 1
d,e,f = 1, 2, "ricky"
print a
print b
print c
print d
print e
print f

# 字符串的使用
str = "welcome to python world" # 字符串在python中本质上是一个字符序列seq
print str # 打印整个字符串
print str[0] # 打印字符串第一个字母
print str[2:5] # 打印第三到第五个字母
print str[2:] # 打印从第三个字母到末尾
print str * 2 # 字符串重复二次
print str + "test" # 字符串拼接

# 列表的使用
list = ['abcd', 786, 2.23, "ricky", 70.2]
tinylist = [123, "ricky"]

print list # 打印整个列表
print list[0] # 打印第一个元素
print list[1:3] # 打印第二和第三个元素
print list[2:] # 打印第三个元素到末尾
print tinylist * 2 # 打印二次
print list + tinylist # 拼接两个list

# 修改list中的元素
list[0] = "python"
print list

# 元组使用
# 元组是类似于列表中的序列数据类型 一个元组由数个逗号分隔的值组成 列表和元组之间的主要区别是 列表用方括号[] 列表的长度和元素值是可以改变的
# 而元组用圆括号() 不能被更新
tuple = ( 'abcd', 786 , 2.23, 'ricky', 70.2)
tinytuple = (123, 'ricky')

print tuple           # 打印整个元组
print tuple[0]         # 打印第一个元素
print tuple[1:3]       # 打印第2、3两个元素
print tuple[2:]        #
print tinytuple * 2     # 重复2遍
print tuple + tinytuple  # 拼接