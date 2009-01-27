all : clean stripper

clean :
	rm -f *.o stripfsm.h stripfsm.cpp stripper

stripfsm.cpp : stripfsm.sm
	java -cp ../../../build/smc.jar smc.Smc stripfsm.sm

stripper.o : stContext.h stripfsm.o stripfsm.h stripper.cpp

stripper : stripper.o stripfsm.o
	g++ -o stripper stripper.o stripfsm.o

