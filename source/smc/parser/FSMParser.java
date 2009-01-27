package smc.parser;

import smc.parser.generated.SMParser;
import smc.parser.generated.ParseException;
import java.io.*;
import smc.builder.FSMBuilder;
import smc.parser.iface.SMParserInterface;

public class FSMParser implements SMParserInterface
{
    private FSMBuilder itsBuilder;
    private String     itsFSMGeneratorName;
    private String     itsFileName;
    private ParserErrorManager itsErrorManager = new ParserErrorManager();

    public FSMParser(FSMBuilder theBuilder, String theFileName)
    {
        itsBuilder = theBuilder;
        itsFileName = theFileName;
        itsFSMGeneratorName = "";
        itsBuilder.setErrorManager( itsErrorManager );
    }
	    		
    public void setFSMGenerator(String s)
        { itsFSMGeneratorName = s; }
    
    public String getFSMGeneratorName()
        { return itsFSMGeneratorName; }
 
    public void setFSMName(String s )
        { itsBuilder.setName( s ); }

    public void setContextName(String s)
        { itsBuilder.setContextName(s); }

    public void setException(String s)
        { itsBuilder.setException(s); }

    public void setInitialState(String s)
        { itsBuilder.setInitialState( s ); }

    public void setVersion(String s)
        { itsBuilder.setVersion( s ); }

    public void addPragma(String s)
        { itsBuilder.addPragma(s); }

    public void addSuperSubState(String theName, String theSuperState, int theLineNumber)
    {
        ParserSyntaxLocation l = new ParserSyntaxLocation(itsFileName, theLineNumber);
        itsBuilder.addSuperSubState(theName, theSuperState, l);
    }

    public void addSuperState(String theName, int theLineNumber)
    {
        ParserSyntaxLocation l = new ParserSyntaxLocation(itsFileName, theLineNumber);
        itsBuilder.addSuperState(theName, l);
    }

    public void addSubState(String theName, String theSuperState, int theLineNumber)
    {
        ParserSyntaxLocation l = new ParserSyntaxLocation(itsFileName, theLineNumber);
        itsBuilder.addSubState(theName, theSuperState, l);
    }

    public void addState(String theName, int theLineNumber)
    {
        ParserSyntaxLocation l = new ParserSyntaxLocation(itsFileName, theLineNumber);
        itsBuilder.addState(theName, l);
    }

    public void addTransition(String theEvent, String theNextState, int theLineNumber)
    {
        ParserSyntaxLocation l = new ParserSyntaxLocation(itsFileName, theLineNumber);
        itsBuilder.addTransition(theEvent, theNextState, l);
    }

    public void addInternalTransition(String theEvent, int theLineNumber)
    {
        ParserSyntaxLocation l = new ParserSyntaxLocation(itsFileName, theLineNumber);
        itsBuilder.addInternalTransition(theEvent, l);
    }

    public void addAction(String theAction, int theLineNumber)
        { itsBuilder.addAction(theAction); }

    public void addEntryAction(String theAction, int theLineNumber)
        { itsBuilder.addEntryAction(theAction); }

    public void addExitAction(String theAction, int theLineNumber)
        { itsBuilder.addExitAction(theAction); }

    public void syntaxError(int theLineNumber, String theMessage)
    {
        ParserSyntaxLocation l = new ParserSyntaxLocation(itsFileName, theLineNumber);
        itsErrorManager.error(l, theMessage);
    }

    public void processFSM()
    {}

    public boolean parse() 
    {
        boolean status = false;
        try
        {
            FileInputStream ifile = new FileInputStream(itsFileName);
            SMParser parser = new SMParser(ifile);
            try
            {
                parser.parseFSM(this);
                status = itsBuilder.build();
            }
            catch( ParseException pe )
            {
                printSyntaxError( pe );               
                System.out.println("Aborting due to syntax errors.");
            }
        }
        catch( FileNotFoundException fe)
        {
            System.out.println( "Could not open input file: " + itsFileName); 
        }
        return status;
    }

    private void printSyntaxError( ParseException pe )
    {
        int prevLine = pe.currentToken.beginLine;
        int errLine = pe.currentToken.next.beginLine;

        System.out.println("Syntax Error: " + itsFileName + " line " + errLine);

        if( prevLine != errLine )
            System.out.println("   Unknown field \"" + 
                            pe.currentToken.next.image + "\" in header" );
        else
            System.out.println("   Field \"" + pe.currentToken.image +
              "\" has invalid value \"" + pe.currentToken.next.image + "\"");
    }
}
