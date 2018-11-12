
AddText()

Func AddText()
	
	WinWaitActive("[CLASS:IEFrame]","",1)
	send("{TAB 2}")
	Sleep(2000)
	send("Automated Test Case{ENTER}{ENTER}")
	send("This test is to{ENTER}{ENTER}")
	send("Adds the form{ENTER}")
	send("Enter data entry{ENTER}")
	send("Verify Bullet points display as entered")
	sleep(1000)
	MouseClick("Left")
	sleep(1000)

EndFunc



