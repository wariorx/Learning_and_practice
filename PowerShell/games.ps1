Function games
{
    Param ([bool]$smite, [bool]$over, [bool]$league, [bool]$steam, [bool] $ts, [bool] $disc, [bool] $skype)

    if($smite -eq $true){Start-process "C:\SmiteAddress\smite.exe"}

    if($league -eq $true){Start-Process "C:\LoLAddress\lol.exe"}

    if($over -eq $true){Start-process "C:\OverwatchAddress\overwatch.exe" }

    if($steam -eq $true){Start-process "C:\SteamAddress\Steam.exe"}

    #runs teamspeak if set to do so
	if($ts -eq $true){Start-process "C:\ts3Client\ts3client_win64.exe"} 
	
	#runs discord if set to do so
	if($disc -eq $true){Start-process "C:\DiscordAddress\Update.exe"}
	
	#runs skype if set to do so
	if($skype -eq $true){Start-process "C:\SkypeAddress\Skype.exe"}

}#end of game script
