1、tools(VM10+CentOs6.8)

https://download3.vmware.com/software/wkst/file/VMware-workstation-full-10.0.2-1744117.exe

VM10key:https://zhidao.baidu.com/question/607052953.html?qbl=relate_question_7&word=vmware12%D0%ED%BF%C9%D6%A4%C3%DC%D4%BF

http://archive.kernel.org/centos-vault/6.8/isos/x86_64/CentOS-6.8-x86_64-bin-DVD1.iso

2、net settings

linux net work：
NAT
子网：192.168.XX.0
网关：VM8和linux统一 192.168.XX.1

①网卡修改:
vi /etc/sysconfig/network-scripts/ifcfg-eth0 

update or append

ONBOOT=yes
NM_CONTROLLED=yes
BOOTPROTO=static         
IPADDR=192.168.3.128
GATEWAY=192.168.3.1
BROADCAST=192.168.3.255

static：IP不变了

②
192.168.3.128 》hostname 用本地域名访问IP
windows  C/windows/system32/drivers/etc/hosts 
centos  vi /etc/hosts
append 192.168.3.128 hostname

③net work:
service NetworkManager stop
/etc/init.d/network restart
chkconfig NetworkManager off
vi /etc/resolv.conf                        append       nameserver 192.168.3.128
service network restart
ping www.baidu.com    finished0.0


stop firewalls:
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

