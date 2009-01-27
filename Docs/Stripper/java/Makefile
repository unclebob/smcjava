all : clean stripper

clean :
	rm -rf classes StripFSM.java 

StripFSM.java : stripfsm.sm
	java -cp ../../../build/smc.jar smc.Smc stripfsm.sm

stripper : StripFSM.java
	mkdir classes
	javac -d classes *.java
