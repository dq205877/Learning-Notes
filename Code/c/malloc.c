#include <stdio.h>
#include <malloc.h>
void f(int *q){//q是p的一个拷贝，或副本
    //*p =200; //error
   // q = 200;//error
  //**q = 200;//error 不能对整型值取*
  *q =200;
  //free(q);//error
} 
int main(void)
{
   /* 我的第一个 C 程序 */
   int i = 5;//4个字节，静态分配
   int *p = (int *) malloc(4);
   /* 1 引入头文件
       2 malloc 只有一个形参，形参是整型
       3 4分配4个字节
       4 malloc只返回第一个字节的地址。（强制转换表示到底占几个字节，int四个）
       5 p变量占4字节，指向也4个 p静态 ，malloc动态 80%以上都是内存问题
 */
  *p=10;
  f(p);
  printf("%d\n",*p);
   free(p);//p本身未释放，p指向的malloc释放，函数结束由操作系统释放
   
   return 0;
}