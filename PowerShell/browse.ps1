Function browse
{
    Param ([bool] $facebook, [bool] $youtube, [bool] $reddit, [bool] $uncc, [bool] $setmore, [bool] $canvas)

    $passed = $false #will be used to default an action

    if($facebook -eq $true){Start-Process chrome -ArgumentList "https://facebook.com"
                            $passed = $true}

    if($youtube -eq $true){Start-Process chrome -ArgumentList "https://youtube.com"
                            $passed = $true}

    if($reddit -eq $true){Start-Process chrome -ArgumentList "https://reddit.com/r/smite"
                            $passed = $true}

    if($uncc -eq $true){Start-Process chrome -ArgumentList "https://my.uncc.edu"
                        $passed = $true}

    if($setmore -eq $true){Start-Process chrome -ArgumentList "https://my.setmore.com"
                            $passed = $true}

    if($canvas -eq $true){Start-Process chrome -ArgumentList "https://uncc.instructure.com"
                            $passed = $true}

    if(!$passed){Start-Process chrome} #default action if no parameters are passed

} #end of browse script