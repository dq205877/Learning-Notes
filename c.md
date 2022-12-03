安装：菜鸟教程

mingw32-make.exe复制一份重命名ｍake.exe

系统path加安装bin目录

验证：gcc -v       make -v   mingw32-make -v



版本

c89 ANSI C

c90 与c89格式做了修改，基本相同

c99 windows环境可能不兼容部分语法

c11

c18

K&R C  编写　c编程语言　书籍作者　STDC宏

POSIX便携操作系统接口



基础篇

宏　字面量，不是常量，也不是变量

const int i=0;

多个 c 代码的源码文件，编译

```
gcc test1.c test2.c -o main.out 
```

整型类型

| 类型           | 存储大小    | 值范围                                               |
| :------------- | :---------- | :--------------------------------------------------- |
| char           | 1 字节      | -128 到 127 或 0 到 255                              |
| unsigned char  | 1 字节      | 0 到 255                                             |
| signed char    | 1 字节      | -128 到 127                                          |
| int            | 2 或 4 字节 | -32,768 到 32,767 或 -2,147,483,648 到 2,147,483,647 |
| unsigned int   | 2 或 4 字节 | 0 到 65,535 或 0 到 4,294,967,295                    |
| short          | 2 字节      | -32,768 到 32,767                                    |
| unsigned short | 2 字节      | 0 到 65,535                                          |
| long           | 4 字节      | -2,147,483,648 到 2,147,483,647                      |
| unsigned long  | 4 字节      | 0 到 4,294,967,295                                   |

signed　有符号，unsigned　无符号

内存模型

数据结构



两类指针:

一、指向栈的，不需要free

二、指向堆的，一定要free

指针用法最好不要超过三层． 

![image-20210617194856651](source\static\images\cPointer.png)