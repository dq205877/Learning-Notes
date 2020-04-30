##### 1、tools(VM10+CentOs6.8)

https://download3.vmware.com/software/wkst/file/VMware-workstation-full-10.0.2-1744117.exe

VM10key:https://zhidao.baidu.com/question/607052953.html?qbl=relate_question_7&word=vmware12%D0%ED%BF%C9%D6%A4%C3%DC%D4%BF

http://archive.kernel.org/centos-vault/6.8/isos/x86_64/CentOS-6.8-x86_64-bin-DVD1.iso

##### 2、net settings

linux net work：
NAT
子网：192.168.XX.0
网关：VM8和linux统一 192.168.XX.1

###### ①网卡修改:

```shell
vi /etc/sysconfig/network-scripts/ifcfg-eth0 
```

update or append

ONBOOT=yes
NM_CONTROLLED=yes
BOOTPROTO=static         
IPADDR=192.168.3.128
GATEWAY=192.168.3.1
BROADCAST=192.168.3.255

static：IP不变了

###### ②

192.168.3.128 》hostname 用本地域名访问IP
windows  C/windows/system32/drivers/etc/hosts 
centos  

```shell
vi /etc/hosts
```

append 192.168.3.128 hostname

###### ③net work:

```shell
service NetworkManager stop
/etc/init.d/network restart
chkconfig NetworkManager off
vi /etc/resolv.conf  
```

​                      append       nameserver 192.168.3.128

```shell
service network restart
ping www.baidu.com 
```

   finished0.0

###### stop firewalls:

service iptables stop
chkconfig iptables off         //关闭自启
service iptables status

time syn:
yum -y install ntp ntpdate
date -s 07/04/20
date -s 18:20:20
date 0618141620.30  （MMDDhhmmYYYY.ss）
ntpdate cn.pool.ntp.org
hwclock --systohc


tools:secureCRT  control centos   up and down: yum -y install lrzsz

DNS1:114.114.114.114   电信
DNS2:8.8.8.8  google



###### SecureCRT

plugins:    yum install lrzsz

 cd /tem, mouse move windows file,Zmodem...   transtation files



###### JDK install:

exe:rpm

rar:tar.gz

 rpm -ivh jdk-filename

/usr         the default install folder

vi /etc/profile    envir

append

export JAVA_HOME=/user/java/jdk1.8..-amd

export CLASSPATH=$JAVA_HOME$\lib:$CLASSPATH

export PATH=$JAVA_HOME$\bin:$PATH



source /etc/profile        --refresh file

java -version             --check java env



###### group

totally clone

main others 

1、rom cutdown

2、ip/uuid/hostname mapping change or delete                will genera restart

/etc/udev/rule.d/70-persistent-net.rules  delete  if remain will reserve the rule in the up

3、mapping

window/linux linux/linux



###### Docker install:

uname -r       --up 3.10

yum update     --refresh core

yum install docker      --install docker

systemctl start docker  -- start docker

docker -v      --check docker version

systemctl enable docker  -- boot start







##### usually cmd:

```shell
pwd
```

  --show current directory

```
uname -r     --core version
ps aux|grep tomcat

ssh root@192.160.3.111

df -lh

ll

ls

cd ..
```

改名：mv oldname newname
复制：cp -a oldfile newfile
显示编辑：vi file
ps -aux
ps -aux | grep tomcat

vi:
按ESC键 跳到命令模式，然后：
:w 保存文件但不退出vi
:w file 将修改另外保存到file中，不退出vi
:w! 强制保存，不推出vi
:wq 保存文件并退出vi
:wq! 强制保存文件，并退出vi
q: 不保存文件，退出vi
:q! 不保存文件，强制退出vi
:e! 放弃所有修改，从上次保存文件开始再编辑

解压：
tar –xvf file.tar  解压 tar包
tar -xzvf file.tar.gz 解压tar.gz
tar -xjvf file.tar.bz2   解压 tar.bz2
tar –xZvf file.tar.Z   解压tar.Z
unrar e file.rar 解压rar
unzip file.zip 解压zip

查看日志
tail -f catalina.out

/home/tomcat/bin/shutdown.sh

cd /home/tomcat/bin  && ./startup.sh

kill -9 [PID] 通过PID杀死某个进程

find . -type f -size +100M   查找当前目录下文件大小

nslookup www.sohu.com



mkdir dir1 创建一个叫做 'dir1' 的目录' 
mkdir dir1 dir2 同时创建两个目录 
mkdir -p /tmp/dir1/dir2 创建一个目录树 
rm -f file1 删除一个叫做 'file1' 的文件' 
rmdir dir1 删除一个叫做 'dir1' 的目录' 
rm -rf dir1 删除一个叫做 'dir1' 的目录并同时删除其内容 
rm -rf dir1 dir2 同时删除两个目录及它们的内容 
mv dir1 new_dir 重命名/移动 一个目录 
cp file1 file2 复制一个文件 