package smc.parser;

//--------------------------------------------
// Parser Syntax Location Class
//  This class is used to represent the location of a syntactic element
//  of the state machine being built.
//

import smc.builder.SyntaxLocation;

public class ParserSyntaxLocation implements SyntaxLocation
{
    private String itsFileName;
    private int itsLineNumber;

    public ParserSyntaxLocation(String theFileName, int theLineNumber)
    {
        itsFileName = theFileName;
        itsLineNumber = theLineNumber;
    }

    public String toString()
    {
        String s =  itsFileName + " line " + itsLineNumber + ":";
        return s;
    }
}

