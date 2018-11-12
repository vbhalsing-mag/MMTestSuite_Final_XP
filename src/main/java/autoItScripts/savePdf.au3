	
Example()

Func Example()

	;Sleep(18000)	
	;send("{TAB 9}") 
	;send("{ENTER}")
	Sleep(2000)
	WinWaitActive("[CLASS:IEFrame]","",10)
	ControlClick("[CLASS:IEFrame]","","AVL_AVView26")
	Sleep(2000)
	send("{CTRLDOWN}")
	send("{SHIFTDOWN}")
	send("{s down}")
	WinWaitActive("Save As")
	send("{CTRLUP}")
	send("{SHIFTUP}")
	send("{s up}")
	Sleep(3000)
	ControlFocus("Save As","","Edit1")
	Sleep(2000)
	ControlSend("Save As","","Edit1","C:\savePDF\verifyPDF.pdf");
	Sleep(2000)
	ControlClick("Save As","&Save","Button1")
	Sleep(3000)
	if WinExists("Confirm Save As")then
		ControlClick("Confirm Save As","&Yes","Button1")
	else 
		send("{TAB}")
	EndIf
	Sleep(5000)
	send("{TAB 20}") 
	send("{ENTER}")
	; Wait 10 seconds for the Run dialogue window to appear.
EndFunc   ;==>Example