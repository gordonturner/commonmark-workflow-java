OSX Creating USB Install - OSX
==============================

- Reference
http://www.techrepublic.com/blog/apple-in-the-enterprise/how-to-create-a-bootable-usb-to-install-os-x/


Create OSX USB Install El Capitan (10.11)
----------------------------------------

- Run Disk Utilty
- Select the USB device (not a partition of the device) in the left pane

- If on 10.11 or later:
  - Click `Partition` tab, ensure there is a single partition
  - Set Name to `Untitled`, Format to `OS X Extended (Journaled)`
  - Click `Apply` button
  - Confirm `Partition Map` is set to `GUID Partition Map`
  
- If on 10.10 or earler, confirm `Partition Map` is set to `GUID Partition Map`
  - Click `Partition` tab
  - In `Partition Layout`, select `1 Partition` in the
  - Click `Options` button, select `GUID Partition Table`, click OK
  - Change Name to `Untitled`
  - Change Format type to `Mac OS Extended (Journaled)`
  - Click Apply, then Partition

- Once partitioning is complete, click `Done`
- Close Disk Utility

- For El Capitan, default Application location:

```
sudo /Applications/Install\ OS\ X\ El\ Capitan.app/Contents/Resources/createinstallmedia \
--volume /Volumes/Untitled \
--applicationpath /Applications/Install\ OS\ X\ El\ Capitan.app \
--nointeraction
```

- For El Capitan, after Application moved and renamed:

```
sudo /Users/gturner/Documents/Disk\ Images/Apple\ OS\ X/10.11\ El\ Capitan/Install\ OS\ X\ El\ Capitan.app/Contents/Resources/createinstallmedia \
--volume /Volumes/Untitled \
--applicationpath /Users/gturner/Documents/Disk\ Images/Apple\ OS\ X/10.11\ El\ Capitan/Install\ OS\ X\ El\ Capitan.app \
--nointeraction
```

- NOTE: createinstallmedia takes about 35 min for usb 2 device


Create OSX USB Install Yosemite (10.10)
---------------------------------------

- Run Disk Utilty
- Select the USB device (not a partition of the device) in the left pane
- Click `Partition` tab
- In `Partition Layout`, select `1 Partition` in the
- Click `Options` button, select `GUID Partition Table`, click OK
- Change Name to `Untitled`
- Change Format type to `Mac OS Extended (Journaled)`
- Click Apply, then Partition
- Close Disk Utility

- For Yosemite, default Application location:

```
sudo /Applications/Install\ OS\ X\ Yosemite.app/Contents/Resources/createinstallmedia \
--volume /Volumes/Untitled \
--applicationpath /Applications/Install\ OS\ X\ Yosemite.app \
--nointeraction
```

- For Yosemite, after Application moved and renamed:

```
sudo /Users/gturner/Documents/Disk\ Images/Apple\ OS\ X/10.10\ -\ Install\ OS\ X\ Yosemite.app/Contents/Resources/createinstallmedia \
--volume /Volumes/Untitled \
--applicationpath /Users/gturner/Documents/Disk\ Images/Apple\ OS\ X/10.10\ -\ Install\ OS\ X\ Yosemite.app \
--nointeraction
```

- NOTE: createinstallmedia takes about 35 min for usb 2 device


Create OSX USB Install Mavericks (10.9)
---------------------------------------

- Run Disk Utilty
- Select the USB device (not a partition of the device) in the left pane
- Click `Partition` tab
- In `Partition Layout`, select `1 Partition` in the
- Click `Options` button, select `GUID Partition Table`, click OK
- Change Name to `Untitled`
- Change Format type to `Mac OS Extended (Journaled)`
- Click Apply, then Partition
- Close Disk Utility

- For Mavericks, default Application location:

```
sudo /Applications/Install\ OS\ X\ Mavericks.app/Contents/Resources/createinstallmedia \
--volume /Volumes/Untitled \
--applicationpath /Applications/Install\ OS\ X\ Mavericks.app \
--nointeraction
```

- For Mavericks, after Application moved and renamed:

```
sudo /Users/gturner/Documents/Disk\ Images/Apple\ OS\ X/10.9\ -\ Install\ OS\ X\ Mavericks.app/Contents/Resources/createinstallmedia \
--volume /Volumes/Untitled \
--applicationpath /Users/gturner/Documents/Disk\ Images/Apple\ OS\ X/10.9\ -\ Install\ OS\ X\ Mavericks.app \
--nointeraction
```


Create OSX USB Install Mountain Lion (10.8)
-------------------------------------------

- Run Disk Utilty
- Select the USB device (not a partition of the device) in the left pane
- Click `Partition` tab
- In `Partition Layout`, select `1 Partition` in the
- Click `Options` button, select `GUID Partition Table`, click OK
- Change Name to `Untitled 1`
- Change Format type to `Mac OS Extended (Journaled)`
- Click Apply, then Partition
- Close Disk Utility

- Execute `Configure USB`
- Run Disk Utility
- Select the USB device (not a partition of the device) in the left pane
- Click `Restore` tab
- Set the `Destination` to the USB device (not a partition of the device)
- Set the `Source` to the disk image (dmg) file, eg `InstallESD.dmg` or `10.8 - Mac OS X Mountain Lion InstallESD.dmg`
- Click `Restore`

