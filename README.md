#EARDENTIFICATION

We implemented an app that uses the imprint of the ear on the touchscreen as input to unlock the phone. To generate the imprint the raw capacitance values of the touchscreen are needed, therefore the kernel of the device has to be manipulated. The project is based on:

- [RainCheck](https://ubicomplab.cs.washington.edu/raincheck/)

To build our own kernel, we followed this approach:

- [Pete's Block - Compile and run your own android](http://pete.akeo.ie/2013/10/compiling-and-running-your-own-android.html)

The offical but not so helpful tutorial can be found here:

- [Building Kernels](http://source.android.com/source/building-kernels.html)

If you don't want to build your own kernel and coincidentally have a Nexus 5 with kernel version XXX you can jump ahead to "Booting the kernel" using the boot.img that is provided in this repo.

## What you need

- Nexus 5
- Computer running Linux (Ubuntu)
- [USB OTG Cable](http://www.makeuseof.com/tag/how-to-connect-a-usb-android-keyboard/) (for debugging)

### Preparing the device

- Installing a custom recovery and root the phone:
	[Nexus 5 Root tutorial](http://www.ibtimes.co.uk/how-root-android-6-0-marshmallow-build-mra58k-nexus-5-6-7-9-nexus-player-1522653)
- Go to the "About phone" and write down your kernel version
- Go to recovery and create a backup of your rooted boot.img
- Transfer the boot.img to your computer

###Preparing the computer

1. Install the following tools:
	- adb and fastboot (can be installed with apt-get but are also included in android studio)
	- arm-eabi gcc compiler
		1. clone this repo to *usr/local/share*:
		```
		git clone https://android.googlesource.com/platform/prebuilts/gcc/linux-x86/arm/arm-eabi-4.6
		```
		2. export path
		```
		export PATH=$PATH:/usr/local/share/arm-eabi-4.6/bin
		```
		3. check
		```
		arm-eabi-gcc --version
		```
	- mkbootimg
		```
		git clone https://android.googlesource.com/platform/system/core bootimg-tools
		cd bootimg-tools/libmincrypt/
		gcc -c *.c -I../include
		ar rcs libmincrypt.a *.o
		cd ../mkbootimg
		gcc mkbootimg.c -o mkbootimg -I../include ../libmincrypt/libmincrypt.a
		cp mkbootimg /usr/local/bin/
		```
	- unmkbootimg
		```
		cd /usr/src/android/bootimg-tools/mkbootimg/
		wget https://raw.github.com/pbatard/bootimg-tools/master/mkbootimg/unmkbootimg.c
		gcc -o unmkbootimg unmkbootimg.c
		cp unmkbootimg /usr/local/bin/
		```

### Downloading the kernel

1. Clone the kernel and switch to the right branch/commit:
	- The Nexus 5 dev device name is **hammerhead**, it's kernel is located in the **msm** repo:
	`git clone https://android.googlesource.com/kernel/msm.git`

Although you will download quite some data, the folder will empty, that's because you need to switch to the right branch first.

#### Finding the right commit
The kernel version of your phone will look something like 3.10.40-g88882d2, what you care about is the last hash of that string after g: 3.10.40-g**88882d2**

With that hash you can checkout the branch for your device like this:
`git checkout -b my_current_kernel_branch 88882d2`


### Manipulating the kernel

What we are interested in is the touch_synaptics_ds5.c which is located in *drivers/input/touchscreen*. We simply replace it by the version we can find here: [touch_synaptics_ds5.c](https://raw.githubusercontent.com/isaaczinda/RainCheck/master/Kernel/touch_synaptics_ds5.c)

If you are interested what this is actually doing, you can check out this site:
[Getting the Phone's Capacitance Values](https://ubicomplab.cs.washington.edu/raincheck/capacitance-values.html)


### Compiling

Now that we manipulated the kernel it's time to compile it:

1. Set cross-compile variables:
```
export ARCH=arm
export SUBARCH=arm
export CROSS_COMPILE=arm-eabi-
```
2. Initialize default config
```
make hammerhead_defconfig
make menuconfig
```
3. Compile
```
make
```

### Creating the boot.img

Now that we have our compile kernel zImage file, it's time to create a complete boot.img. Therefore we first need to split our backup boot.img that we saved to the computer in the beginning into it's zImage and ramdisk with unmkbootimg and zip together the ramdisk with our zImage kernel file using mkbootimg

```
unmkbootimg -i boot.img
```

Take the command that appears when running unmkbootimg and replace the name of the kernel with the name of our kernel like:

```
mkbootimg --base 0 --pagesize 2048 --kernel_offset 0x80208000 --ramdisk_offset 0x82200000 --second_offset 0x81100000 --tags_offset 0x80200100 --cmdline 'console=ttyHSL0,115200,n8 androidboot.hardware=flo user_debug=31 msm_rtb.filter=0x3F ehci-hcd.park=3' --kernel YOUR_KERNEL_NAME --ramdisk ramdisk.cpio.gz -o new_boot.img
```

Congrats, you created your own android boot.img!

## Booting the kernel

Assuming your device is connected over USB, debug mode is on and permissions are given.

1. reboot into bootloader:

```
	adb reboot bootloader
```

2. boot with new boot.img

```
	fastboot boot new_boot.img
```

3. get device ip in network

```
	adb shell ip -f inet addr show wlan0
```

-> inet __10.0.1.8__/24 brd 10.0.1.255 scope global wlan0

4. run adb over port

```
	adb tcpip 5555
```

5. connect over wifi

	adb connect __10.0.1.8__:5555

You can now disconnect your device from the computer, connect a mouse over the OTB cable and use the device with the mouse while debugging.

## How the app works