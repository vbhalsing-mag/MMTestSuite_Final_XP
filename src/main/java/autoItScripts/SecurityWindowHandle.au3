	Example()

	Func Example()
	
		Sleep(5000)
		if WinExists("[TITLE:Security Warning]")then
			ControlClick("[TITLE:Security Warning]","","Button2")
		else 
			Sleep(2000)
		endif
		
	EndFunc