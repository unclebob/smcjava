package smc.stripper;

import java.io.InputStream;
import java.io.OutputStream;
import java.io.IOException;

import smc.stripper.StripFSM;

class Stripper
{
    public static void main(String args[]) 
    {
        StripFSM s = new StripFSM();
        s.SetStreams( System.in, System.out);
        try
        {
            while( true )
            {
                int c = s.ReadChar();
                switch( c )
                {
                    case -1: System.exit(0); break; 
		    	    case '/': s.Slash(); break;
		    	    case '*': s.Star(); break;
		    	    case '\n': s.EOL(); break;
		    	    default: s.Other(); break;
		        }
            }
        }
        catch (Exception e )
        {  
            System.out.println();
            System.out.println(e);
            System.err.println(e); 
        }
    }
     
}
