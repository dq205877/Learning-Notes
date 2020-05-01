1、Observer



①Subject              Observer

regist                     do something

remove                  others 

notify

others

②concrete subject                                               concrete observer

regist   concrete  observer                                        do  concrete  thing             

remove  observer                                                       others

notify 

others

③concrete observer   regist  in  concrete subject

   if subject get message,notify all observer.

   if observer want leave, remove observer in the subject.

定义一个主题接口，一个观察者接口。具体的主题和观察者可以有很多。具体的主题提供三个方法，观察者可调用。



比如一个招聘中介，主题是招聘JAVA工程师，好多工程师是观察者。观察者可以把信息给中介说要注册到这个主题中，主题可提醒还有多少个岗位需求，也可以把观察者从主题中踢除。

注意：

1、像工程师找工作一样，最好还是工程师去找中介比较好，而中介找工程师有点数据量大。所以就目前来说工程师push给中介比较好一点。

2、存在工程师想把找工作的信息推给中介时，中介没有相应职位；还有是工程师自己另外找到了工作，中介并不即时了解工程师具体情况。

3、不能因为工程师先来中介找工作就先通知他工作帮找到了，而是有各种因素影响，不能靠这顺序办事。

4、在JAVA中java.util.Observable是类，不是接口。

Head First 设计模式（中文版）：

要点：

1、观察者模式定义了对象之间一对多的关系 。

2、主题（也就是可观察者）用一个共同的接口来更新观察者。

3、观察者和可观察者之间用松耦合方式结合（loosecoupling),可观察者不知道观察者的细节，只知道观察者实现了观察者接口。

4、使用此模式时，你可从被观察者处推（push)或拉(pull)数据（然而，推的方式被认为更“正确”）。

5、有多个观察者时，不可以依赖特定的通知次序。

6、JAVA有多种观察者模式的实现，包括了通用的java.util.Observable。

7、要注意java.util.Observable实现上所带来的一些问题。

8、如果有必要的话，可以实现自己的Observable,这并不难，不要害怕。

9、Swing大量使用观察者模式，许多GUI框架也是如此。

10、此模式也被应用在许多地方，例如：JavaBeans、RMI。





2、Decorator

Drink   abstract                  DrinkDecorator     extends Drink        abstract （use Type,not Methods)

String des;                           des();

des(des);                              Drink drink;

cost();                       

Juice

cost(JuiceCost);

milk

cost(MilkCost);

MrDingDrink extends  DrinkDecorator{

 	Drink drink;

​	MrDingDrink (Drink drink){

​		this.drink=drink;

​	}

​	des(){

​		drink.getdes()+MrDingDrink;//表明费用明细是如何来的

​	};

​	cost(){

​		drink.cost+100(MrDingCost)

​	}

}

解释：饮料是一个抽象类，饮料装饰类也是一个抽象类。MrDing牌饮料继承了饮料装饰类，也是饮料类的子类。因为有了装饰类，计算价格可以叠加了。类似于生活中的：装修：水、电、家具 、地板、墙面  装修公司：我来帮你精装，费用都会算好，还有就是我们自己也要收点装修费。精装可能不包含家具，第二次叫装修公司把家具也装上，就还要收一次装修费。不过这些都不是我们自己找人弄了。扩展是再装修不用变动原来装修具体费用，而是业务起来时追加就好了。但问题是会有很多种类出现，眼花缭乱。

Drink drink = new Juice();

drink=new MrDing(drink);

drink=new MrDing(drink);//the Cost:JuiceCost+100(MrDingCost)+100(MrDingCost) 经过MrDing两次加工。



Head First 设计模式（中文版）：

要点：

1、继承属于扩展形式之一，但不见得是达到弹性设计的最佳方式。

2、在我们的设计中，应该允许行为可以被扩展，而无须修改现有的代码。

3、组合和委托可用于在运行时动态地加上新的行为。

4、除了继承，装饰者模式也可以让我们扩展行为。

5、装饰者模式意味着一群装饰者类，这些类用来包装具体组件。

6、装饰者类反映出被装饰的组件类型（事实上，他们具有相同的类型，都经过接口或继承实现）。

7、装饰者可以在被装饰者的行为前面与/或后面加上自己的行为，甚至将被装饰者的行为整个取代掉，而达到特定的目的。

8、你可以用无数个装饰者包装一个组件。

9、装饰者一般对组件的客户是透明的，除非客户程序依赖于组件的具体类型。

10、装饰者会导致设计中出现许多小对象，如果过度使用，会让程序变得很复杂。



3、Proxy

Something Interface

Realthing   subject   <     thingProxy



Something Interface                 InvocationHandler(invoke) Interface 

Realthing          thingProxy  >     InvocationHandler impl

 Realthing  <  InvocationHandler impl

RMI 

(Client Object dosomthing Stub) lookup()

RMI registry(return Stub)      Skeleton  Server Object





Head First 设计模式（中文版）：

要点：

1、代理模式一个对象提供代表，以便控制客户对对象的访问，管理访问的方式有许多种。

2、远程代理管理客户和远程对象之间的交互。

3、虚拟代理控制访问实例化开销大的对象。

4、保护代理基于调用者控制对对象方法的访问。

5、代理模式有许多变体，例如：缓存代理、同步代理、防火墙代理和写入时复制代理。

6、代理在结构上类似装饰者，但是目的不同。

7、装饰者模式为对象加上行为，而代理则是控制访问。

8、JAVA内置的代理支持，可以根据需要建立动态代理，并将所有调用分配到所选的处理器。

9、就和其他的包装者（Wrapper)一样，代理会造成你的设计中类的数目增加。
static proxy
stable  1know real subject 2 has a pin to real subject
dynamic proxy
1must an interface
2implements InvocationHandler && not implements subject && constructor Object
3method. invoke(object,args) //object methods
4test


InvocationHandler handler = new dynamicProxy(subject) ;//初步代理对象
Subject s = (Subject) Proxy.newProxyInstance(handler.getClass().getClassLoader(),handler.getClass().getInterface (),handler) ；//最终代理对象



