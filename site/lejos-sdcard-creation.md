http://sourceforge.net/p/lejos/wiki/Installing%20leJOS/
http://sourceforge.net/p/lejos/wiki/Creating%20a%20bootable%20SD%20card%200.7.0/
```
diskutil list
sudo dd if=~/Development/leJOS_EV3_0.8.1-beta/sd500.img of=/dev/disk2 bs=1m
cd SD500
cp ~/Development/leJOS_EV3_0.8.1-beta/ejre-7u60-fcs-b19-linux-arm-sflt-headless-07_may_2014.tar.gz .
unzip ~/Development/leJOS_EV3_0.8.1-beta/lejosimage.zip
rm ._*
cd ..
diskutil umountDisk /dev/disk2

```
