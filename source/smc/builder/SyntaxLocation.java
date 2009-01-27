package smc.builder;

//--------------------------------------------
// Name
//  SyntaxLocation
//
// Description
//  This class is used to represent the location of a syntactic element
//  of the state machine being built.
//
//  The FSMBuilder has no notion of what the syntax really is,
//  but it does have to associate semantic elements with their
//  syntactic locations.  Specifically for the purpose of reporting the
//  syntactic location of a semantic error.  Thus it provides an abstract
//  base class that the parsing agent can derive from.
//

public interface SyntaxLocation
{
	public abstract String toString();
}







