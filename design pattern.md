1、Observer

①Subject              Observer

regist                     do something

remove

notify

②concrete subject                                               concrete observer

regist   concrete  thing                                        do  concrete  thing             

remove  observer method

notify observer

③concrete observer   regist  in  concrete subject

   if subject get message,notify all observer.

   if observer want leave, remove observer in the subject.

定义一个主题接口，一个观察者接口。具体的主题和观察者可以有很多。具体的主题提供三个方法，观察者可调用。



比如一个招聘中介，主题是招聘JAVA工程师，好多工程师是观察者。观察者可以把信息给中介说要注册到这个主题中，主题可提醒还有多少个岗位需求，也可以把观察者从主题中踢除。

