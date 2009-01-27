package smc.parser;

import smc.builder.SyntaxLocation;
import smc.builder.FSMBuilderErrorManager;

import java.util.List;
import java.util.ArrayList;

//----------------------------------------------
// Name
//  ParserErrorManager
//
// Description
//  Error manager for the parser
//

public class ParserErrorManager implements FSMBuilderErrorManager
{
    private List errors;
    private boolean isOutputing;
    public ParserErrorManager()
    {
        isOutputing=true;
        errors = new ArrayList();
    }
    public ParserErrorManager(boolean isOutputing)
    {
        this.isOutputing=isOutputing;
        errors = new ArrayList();
    }
    public void error(SyntaxLocation loc, String s)
    {
        if( loc instanceof ParserSyntaxLocation )
	  {
	      ParserSyntaxLocation l = (ParserSyntaxLocation)loc;
          if(isOutputing)
	        System.out.println( l.toString() + s );
          errors.add(s);
	  }
    }
    public void error(String s)
    {
        if(isOutputing)
            System.out.println( s );
        errors.add(s);
    }
    public List getErrors()
    {
        return errors;
    }
}
