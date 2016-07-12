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