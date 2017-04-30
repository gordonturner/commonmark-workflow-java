OSX
===

App Store
---------

- 10.11 El Capitan
- Turn on App Store debug menu, quit App Store and run:

```
defaults write com.apple.appstore ShowDebugMenu -bool true
```

- Launch App Store, Debug Menu now available


- Use to reveal download location, hard link to app and save:

```
ln /path/to/package_from_mac_app_store_name_here.pkg /path/to/name_you_want_to_save_package_as_here.pkg
```

- Reference:

https://derflounder.wordpress.com/2013/08/22/downloading-apples-server-app-installer-package/


- Grab 'raw' El Capitan pkg from the Mac App Store:

```
cd ~/Desktop
wget http://osxapps.itunes.apple.com/apple-assets-us-std-000001/Purple3/v4/74/d2/82/74d28291-9db9-7ae2-305d-9b8b3f5fd463/ftk3252456602304584541.pkg
mkdir osxapps_local
cd osxapps_local
sudo mkdir -p ./apple-assets-us-std-000001/Purple3/v4/74/d2/82/74d28291-9db9-7ae2-305d-9b8b3f5fd463/
sudo mv ~/Desktop/ftk3252456602304584541.pkg ./apple-assets-us-std-000001/Purple3/v4/74/d2/82/74d28291-9db9-7ae2-305d-9b8b3f5fd463/
sudo python -m SimpleHTTPServer 80
```

- 10.11.1

```
http://osxapps.itunes.apple.com/apple-assets-us-std-000001/Purple6/v4/00/09/cf/0009cf9b-1b5b-462d-87f2-2443c397ba55/ugi4737957828788231600.pkg
sudo mkdir -p ./apple-assets-us-std-000001/Purple6/v4/00/09/cf/0009cf9b-1b5b-462d-87f2-2443c397ba55/
```

- 10.11.2
```
http://osxapps.itunes.apple.com/apple-assets-us-std-000001/Purple2/v4/c1/18/fc/c118fc97-bb54-41e4-6f57-3c50539caa98/ipl1193488517185310884.pkg
sudo mkdir -p ./apple-assets-us-std-000001/Purple2/v4/c1/18/fc/c118fc97-bb54-41e4-6f57-3c50539caa98/
```

- Close Mac App Store
- Edit hosts and add: `127.0.0.1       osxapps.itunes.apple.com`
- Flush the cache: `sudo killall -HUP mDNSResponder`
- Launch the App Store

- Reference:

https://7labs.heypub.com/tips-tricks/el-capitan-direct-download.html
https://gist.github.com/rahul286/2fc41942c7ed4039893f



DNS Cache Flush 10.12.0 (Sierra)
---------------------

```
sudo killall -HUP mDNSResponder
```


DNS Cache Flush 10.10
---------------------

```
sudo discoveryutil mdnsflushcache
```


dd CD ROM
---------

- Determine with disk to copy:

```
sudo diskutil list
```

- Create image:

```
dd if=/dev/disk1 of=DiskImage.iso
```


Add Markdown files to Spotlight
-------------------------------

- Run:

```
find . -name "*.md" -exec mdimport {} \;
```

Command Line Notification
-------------------------

- Install:

```
sudo gem install terminal-notifier
```

- Example call:

```
echo 'Piped Message Data!' | terminal-notifier -sound default
terminal-notifier -title '💰' -message 'Check your Apple stock!' -open 'http://finance.yahoo.com/q?s=AAPL'
terminal-notifier -group 'address-book-sync' -title 'Address Book Sync' -subtitle 'Finished' -message 'Imported 42 contacts.' -activate 'com.apple.AddressBook'
```

- Reference:
https://github.com/alloy/terminal-notifier


Lookup Windows Computer Name
----------------------------

- Lookup SMB name for ip address:

```
smbutil status 10.0.40.58
```


Apache
------

- Apache status:

```
httpd -v
httpd -V
```

- To start and stop apache:

```
sudo apachectl start
sudo apachectl stop
```

- Document directory:

```
/Library/WebServer/Documents/
```

- Enable php, edit http.conf and uncomment:

```
sudo vi /etc/apache2/httpd.conf
```
```
LoadModule php5_module libexec/apache2/libphp5.so
```

- Restart:

```
sudo apachectl restart
```

- Log files:

```
/var/log/apache2/access_log	
/var/log/apache2/error_log
```

- Note: For wordpress, had to change from localhost to 127.0.0.1


Screenshot
----------

```
screencapture -C ~/Desktop/screen.png
```


System profiling
----------------

```
birch:~ gturner$ system_profiler SPHardwareDataType
Hardware:

    Hardware Overview:

      Model Name: Mac mini
      Model Identifier: Macmini2,1
      Processor Name: Intel Core 2 Duo
      Processor Speed: 2 GHz
      Number of Processors: 1
      Total Number of Cores: 2
      L2 Cache: 4 MB
      Memory: 2 GB
      Bus Speed: 667 MHz
      Boot ROM Version: MM21.009A.B00
      SMC Version (system): 1.19f2
      Serial Number (system): YM8310MQYL2
      Hardware UUID: 00000000-0000-1000-8000-0016CBACA88C

birch:~ gturner$ sysctl -n machdep.cpu.brand_string
Intel(R) Core(TM)2 CPU         T7200  @ 2.00GHz
```


DTrace
------

- Run:

```
sudo iosnoop
```


OSX Problems
------------

- Screensharing doesn't work
- Kill `NetAuthAgent`


Unzip Issue
-----------

- Unzip fails, use `tar zvxf`

```
ICF2025001:2016-06-07-chrtang@gmail.com Email Issue 34870$ unzip 2016-06-07-CONFIRM-ALDO_PREFERENCE_CENTER_PROD.csv.zip 
Archive:  2016-06-07-CONFIRM-ALDO_PREFERENCE_CENTER_PROD.csv.zip
   skipping: 2016-06-07-CONFIRM-ALDO_PREFERENCE_CENTER_PROD.csv  need PK compat. v4.5 (can do v2.1)
```

```
tar xzvf 2016-06-07-CONFIRM-ALDO_PREFERENCE_CENTER_PROD.csv.zip 
x 2016-06-07-CONFIRM-ALDO_PREFERENCE_CENTER_PROD.csv
```
