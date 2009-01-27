package smc.generator;

import smc.fsmrep.*;
import java.io.IOException;
import java.util.*;

public abstract class FSMGenerator
{
    private StateMap itsStateMap;
    private String itsFilePrefix;
    private String itsFileName;
    private String itsDirectory;
 
    public FSMGenerator()
    {
        itsFilePrefix = "";
        itsFileName = "";
    }
    
    public void FSMInit( StateMap map, String fileName, String directory )
    {
        itsStateMap = map;
        itsFileName = fileName;
        itsDirectory = directory;
        itsFilePrefix = getFilePrefix( itsFileName );
    }

    public abstract void initialize() throws IOException;
    public abstract void generate() throws Exception;
    public abstract Vector getGeneratedFileNames();

    public String getInputFileName()
        { return itsFileName; } 
    public String getFilePrefix()
        { return itsFilePrefix; }
    public String getDirectory()
        { return itsDirectory; }
    public StateMap getStateMap()
        { return itsStateMap; }

    public List getConcreteStates()
    {
        List concreteStates = new ArrayList();

        Vector states = getStateMap().getOrderedStates();
        Iterator si = states.iterator();
        while( si.hasNext() )
        {
            State s = (State)si.next();
            if( s instanceof ConcreteState )
            {
                ConcreteState cs = (ConcreteState)s;
                concreteStates.add( cs );                       
            }
        }  
        return concreteStates;      
    }

    // generate a hierarchy for the given state
    // the order of states in the hierarchy is from the super state
    // to the substate with the last element being the state s.
    public void getStateHierarchy( Vector hierarchy, State s )
    {
        if( s instanceof SubState )
        {
            SubState ss = (SubState)s;
            getStateHierarchy( hierarchy, ss.getSuperState() );
        }
        hierarchy.addElement(s);
    }

    // generate a hierarchy for each state s1 snd s2 such that no member of the
    // hierarchy of one state is a member of the heirarchy of the other - the 
    // shared ancestors are not present in the vector hierarchy, only the 
    // unshared ancestors of s1 are in the vector h1, and the unshared ancestors
    // of s2 are in the vector h2
    //
    public void getUnsharedHierarchy( Vector h1, Vector h2, State s1, State s2 )
    {
        getStateHierarchy( h1, s1 ); 
        getStateHierarchy( h2, s2 );
        
        // Now prune the hierarchy from the top down, eliminating the
        // super states which are common to the hierarchy
        // Do not prune the last state since it is s1
        while (h1.size() > 1 && h2.size() > 1 &&
    	       h1.firstElement() == h2.firstElement())
        {
    		h1.remove(0);
    		h2.remove(0);
        }
    }

    // Strips the path and the file extension from the file name
    // and returns just the prefix. eg: c:/smc/draw.sm would return
    // draw.
    //
    private String getFilePrefix(String theFileName)
    {
      String retval = new String(theFileName);
      int pos;

      // strip off leading directory path if any

      while ((pos = retval.indexOf('/')) >= 0)
        retval = retval.substring(pos+1); 
      while ((pos = retval.indexOf('\\')) >= 0)
	    retval = retval.substring(pos+1); 
      while ((pos = retval.indexOf(':')) >= 0)
		retval = retval.substring(pos+1); 

      // Strip off the suffix.
      int dotPos = retval.indexOf(".");
      if (dotPos >= 0)
      {
		retval = retval.substring(0,dotPos);
      }
      return retval;
    }
}

