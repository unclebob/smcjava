//----------------------------------------------
//
// FSM:       StripFSM
// Context:   StripperContext
// Err Func:  FSMError
// Version:   Test version 1.0
// Generated: Tuesday 02/13/2007 at 23:36:09 CST
//
//----------------------------------------------


package smc.stripper;

// imports
import smc.stripper.StripperContext;

//----------------------------------------------
//
// class StripFSM
//    This is the Finite State Machine class
//
public class StripFSM extends StripperContext
{
  private State itsState;
  private static String itsVersion = "Test version 1.0";

  // instance variables for each state
  private static StartingSlash itsStartingSlashState;
  private static Outside itsOutsideState;
  private static StarAfterSlash itsStarAfterSlashState;
  private static SecondSlash itsSecondSlashState;
  private static StartingStar itsStartingStarState;

  // constructor
  public StripFSM()
  {
    itsStartingSlashState = new StartingSlash();
    itsOutsideState = new Outside();
    itsStarAfterSlashState = new StarAfterSlash();
    itsSecondSlashState = new SecondSlash();
    itsStartingStarState = new StartingStar();

    itsState = itsOutsideState;

    // Entry functions for: outside
  }

  // accessor functions

  public String getVersion()
  {
    return itsVersion;
  }

  public String getCurrentStateName()
  {
    return itsState.stateName();
  }

  // event functions - forward to the current State

  public void Star()
  {
    itsState.Star();
  }

  public void Slash()
  {
    itsState.Slash();
  }

  public void Other()
  {
    itsState.Other();
  }

  public void EOL()
  {
    itsState.EOL();
  }

//--------------------------------------------
//
// private class State
//    This is the base State class
//
  private abstract class State
  {
    public abstract String stateName();

    // default event functions

    public void Star()

    {
      FSMError( "Star", itsState.stateName());
    }

    public void Slash()

    {
      FSMError( "Slash", itsState.stateName());
    }

    public void Other()

    {
      FSMError( "Other", itsState.stateName());
    }

    public void EOL()

    {
      FSMError( "EOL", itsState.stateName());
    }

  }
  //--------------------------------------------
  //
  // class StartingSlash
  //    handles the startingSlash State and its events
  //
  private class StartingSlash extends State
  {
    public String stateName()
      { return "startingSlash"; }

    //
    // responds to Other event
    //
    public void Other()
    {

      PutSlash();
      PutChar();

      // change the state
      itsState = itsOutsideState;
    }

    //
    // responds to Star event
    //
    public void Star()
    {

      // change the state
      itsState = itsStarAfterSlashState;
    }

    //
    // responds to Slash event
    //
    public void Slash()
    {

      // change the state
      itsState = itsSecondSlashState;
    }

    //
    // responds to EOL event
    //
    public void EOL()
    {

      PutSlash();
      PutChar();

      // change the state
      itsState = itsOutsideState;
    }
  }

  //--------------------------------------------
  //
  // class Outside
  //    handles the outside State and its events
  //
  private class Outside extends State
  {
    public String stateName()
      { return "outside"; }

    //
    // responds to Slash event
    //
    public void Slash()
    {

      // change the state
      itsState = itsStartingSlashState;
    }

    //
    // responds to Star event
    //
    public void Star()
    {

      PutChar();
    }

    //
    // responds to Other event
    //
    public void Other()
    {

      PutChar();
    }

    //
    // responds to EOL event
    //
    public void EOL()
    {

      PutChar();
    }
  }

  //--------------------------------------------
  //
  // class StarAfterSlash
  //    handles the starAfterSlash State and its events
  //
  private class StarAfterSlash extends State
  {
    public String stateName()
      { return "starAfterSlash"; }

    //
    // responds to Star event
    //
    public void Star()
    {

      // change the state
      itsState = itsStartingStarState;
    }

    //
    // responds to EOL event
    //
    public void EOL()
    {
    }

    //
    // responds to Other event
    //
    public void Other()
    {
    }

    //
    // responds to Slash event
    //
    public void Slash()
    {
    }
  }

  //--------------------------------------------
  //
  // class SecondSlash
  //    handles the secondSlash State and its events
  //
  private class SecondSlash extends State
  {
    public String stateName()
      { return "secondSlash"; }

    //
    // responds to EOL event
    //
    public void EOL()
    {

      PutChar();

      // change the state
      itsState = itsOutsideState;
    }

    //
    // responds to Other event
    //
    public void Other()
    {
    }

    //
    // responds to Star event
    //
    public void Star()
    {
    }

    //
    // responds to Slash event
    //
    public void Slash()
    {
    }
  }

  //--------------------------------------------
  //
  // class StartingStar
  //    handles the startingStar State and its events
  //
  private class StartingStar extends State
  {
    public String stateName()
      { return "startingStar"; }

    //
    // responds to EOL event
    //
    public void EOL()
    {

      // change the state
      itsState = itsStarAfterSlashState;
    }

    //
    // responds to Other event
    //
    public void Other()
    {

      // change the state
      itsState = itsStarAfterSlashState;
    }

    //
    // responds to Slash event
    //
    public void Slash()
    {

      // change the state
      itsState = itsOutsideState;
    }

    //
    // responds to Star event
    //
    public void Star()
    {
    }
  }

}
