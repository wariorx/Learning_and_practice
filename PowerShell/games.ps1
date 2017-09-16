Function games
{
    Param ([bool]$smite, [bool]$over, [bool]$league, [bool]$steam, [bool] $ts, [bool] $disc, [bool] $skype)

    if($smite -eq $true){Start-process "C:\ProgramData\Microsoft\Windows\Start Menu\Programs\Hi-Rez Studios\Smite"}

    if($league -eq $true){Start-Process "C:\Riot Games\League of Legends\LeagueClient.exe"}

    if($over -eq $true){Start-process "C:\Program Files (x86)\Overwatch\Overwatch Launcher.exe" }

    if($steam -eq $true){Start-process "C:\Program Files (x86)\Steam\Steam.exe"}

    #runs teamspeak if set to do so
	if($ts -eq $true){Start-process "C:\Users\Raúl\AppData\Local\TeamSpeak 3 Client\ts3client_win64.exe"} 
	
	#runs discord if set to do so
	if($disc -eq $true){Start-process "C:\Users\Raúl\AppData\Local\Discord\Update.exe"}
	
	#runs skype if set to do so
	if($skype -eq $true){Start-process "C:\Program Files (x86)\Skype\Phone\Skype.exe"}

}#end of game script