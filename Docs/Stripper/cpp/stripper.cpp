// $Id: stripper.cpp,v 1.1.1.1 2001/07/17 22:59:22 micah Exp $
//
// This program implements a C++ comment stripper
// using the State Map Parser.
//
#include <iostream>
#include <stdlib.h>
#include "stripFSM.h"

using namespace std;

int main()
{
	smc::StripFSM myStripper;
	myStripper.SetStreams(cin,cout);

	while (cin)
		switch(myStripper.ReadChar())
		{
		  case EOF: exit(0); break;
			case '/': myStripper.Slash(); break;
			case '*': myStripper.Star(); break;
			case '\n': myStripper.EOL(); break;
			default: myStripper.Other(); break;
		}
}
