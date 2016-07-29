#EARDENTIFICATION

We implemented an app that uses the imprint of the ear on the touchscreen as input to unlock the phone. To generate the imprint the raw capacitance values of the touchscreen are needed, therefore the kernel of the device has to be manipulated. The project is based on:

- [RainCheck](https://ubicomplab.cs.washington.edu/raincheck/)

To build our own kernel, we followed this approach:

- [Pete's Block - Compile and run your own android](http://pete.akeo.ie/2013/10/compiling-and-running-your-own-android.html)

The offical but not so helpful tutorial can be found here:

- [Building Kernels](http://source.android.com/source/building-kernels.html)

## What you need

- Nexus 5
- Computer running Linux (Ubuntu)
- [USB OTG Cable](http://www.makeuseof.com/tag/how-to-connect-a-usb-android-keyboard/) (for debugging)

## Building the kernel

###Preparing the device

- Installing a custom recovery and root the phone
	[Nexus 5 Root tutorial](http://www.ibtimes.co.uk/how-root-android-6-0-marshmallow-build-mra58k-nexus-5-6-7-9-nexus-player-1522653)
- Go to recovery and create a backup of your rooted boot.img
- Transfer the boot.img to your computer

###Preparing the computer




## Booting the kernel

1. reboot into bootloader:

	adb reboot bootloader

2. boot with new boot.img

	fastboot boot new_boot.img

3. get device ip in network

	adb shell ip -f inet addr show wlan0

	-> inet __10.0.1.8__/24 brd 10.0.1.255 scope global wlan0

4. run adb over port

	adb tcpip 5555

5. connect over wifi

	adb connect __10.0.1.8__:5555

## How the app works