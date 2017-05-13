# Gaana Downloader

[![Join the chat at https://gitter.im/PathriK/GaanaDownloader](https://badges.gitter.im/PathriK/GaanaDownloader.svg)](https://gitter.im/PathriK/GaanaDownloader?utm_source=badge&utm_medium=badge&utm_campaign=pr-badge&utm_content=badge) [![Build Status](https://travis-ci.org/PathriK/GaanaDownloader.svg?branch=master)](https://travis-ci.org/PathriK/GaanaDownloader) [![Build status](https://ci.appveyor.com/api/projects/status/32jy46yxmtve2yav?svg=true)](https://ci.appveyor.com/project/PathriK/gaanadownloader)


A Java tool to download and convert songs to MP3 from Gaana in Bulk

<pre>
<b>Note:</b>
    Primary aim of this tool is to do bulk downloads, but individual downloads will also be supported in future. 
    For list of search options supported and to suggest new features, kindly take a look at Issue <a href="https://github.com/PathriK/GaanaDownloader/issues/2">#2</a>
</pre>

## Gaana:

[Gaana](http://gaana.com/) is a music streaming free service. There are also mobile Apps that allow users to listen to music for free in their mobiles. 

The mobile App features unlimited download of songs for a small amount of fee. The catch here is that the downloaded songs can be listed through the Gaana App only. 

Using this utility, songs can be downloaded in bulk and converted to audio files with proper tags
Also there is an option to organize the songs into folders named under their Album names

## Usage:

1. Download the latest GaanaDownloader zip file from the below link
2. Extract the zip file
3. Run the GaanaDownloader.bat(Windows) or GaanaDownloader.sh(Linux)
4. A console is opened. Proceed as indicated in the displayed console
5. After everything the downloaded & converted songs should be in the folder 'Downloaded_Songs/converted'
6. Enjoy the songs!

**Note1** : Internet connected is required for this utility to get the song details from the Gaana Server.

**Note2** : If there is any error during the process, please share the log files with me. The log files can be found in the same directory as that of the bat/sh file. It will be under the extension 'log'. If there was no error during conversion, the log file can be deleted. 
	
## Downloads:

Releases:

https://github.com/PathriK/GaanaDownloader/releases

Latest Snapshot Version (v1.4):

https://ci.appveyor.com/api/projects/PathriK/GaanaDownloader/artifacts/binaries/GaanaDownloader-1.4-SNAPSHOT.zip

## Contact:
Gmail: pathrikumark@gmail.com

Gitter: https://gitter.im/PathriK/GaanaDownloader

## CHANGELOG:
v1.3(13 May 2017):
- Fixes #8: Some bugs croppeed up with previous fix. All good now
- Fixes #11: Was an issue where Excel incorrectly interprets the TrackIds. Fixed by adding "'" before the trackids
- Closes #15: This is a valid error as the same error comes up in the Gaana App/Web too. Contrary to the error message it is showing, I believe the tracks have not been made available yet

v1.2(21 Mar 2017):
- Fixed #8 : No buffer error when downloading all songs
- Neater command output

v1.1(24 Jan 2017):
- Fixed #4 : Works in Linux now
- Fixed #5 : Users can now select the Song download quality
- Fixed #6 : Now the tool can convert songs that has been downloaded using external download managers

v1.0:
- Initial Release
