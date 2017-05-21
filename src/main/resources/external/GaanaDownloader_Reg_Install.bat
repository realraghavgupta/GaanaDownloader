@echo off
echo Installing Gaana Downloader to Registry.....
REG ADD "HKCU\Software\Google\Chrome\NativeMessagingHosts\in.pathri.gaana.gaana_downloader" /ve /t REG_SZ /d "%~dp0manifest.json" /f
pause