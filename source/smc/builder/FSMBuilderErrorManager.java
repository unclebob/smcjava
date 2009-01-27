package smc.builder;

//----------------------------------------------
// Name
//  FSMBuilderErrorManager
//
// Description
//  This class provides the interface that the FSMBuilder uses
//  to declare errors.  The parsing agent is expected to derive its own
//  implementation of this class.
//

public interface FSMBuilderErrorManager
{
	public abstract void error(SyntaxLocation loc, String s);
	public abstract void error(String s);
}
