package smc.generator.csharp.CSharpCodeGenerators;

import smc.generator.csharp.SMCSharpGenerator;
import smc.fsmrep.State;

public abstract class CSharpCodeGenerator
{
    public abstract String generateCode(SMCSharpGenerator gen);

    public  String printSeparator( int level )
    {
        if( level == 0 )
            return  "//----------------------------------------------\n";
        else
            return  "//--------------------------------------------\n";
    }
    public String createMethodName( State s )
    {
        StringBuffer buff = new StringBuffer( s.getName() );
        if( buff.length() > 0 )
            buff.setCharAt(0, Character.toUpperCase( buff.charAt(0) ));
        return( buff.toString() );
    }
    public String createMethodName( String event )
    {
        StringBuffer buff = new StringBuffer( event );
        if( buff.length() > 0 )
            buff.setCharAt(0, Character.toUpperCase( buff.charAt(0) ));
        return( buff.toString() );
    }
}
