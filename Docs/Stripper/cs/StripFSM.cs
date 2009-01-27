//----------------------------------------------
//
// FSM:       StripFSM
// Context:   StripperContext
// Err Func:  FSMError
// Version:   Test version 1.0
// Generated: Thursday 07/29/2004 at 13:58:46 CDT
//
//----------------------------------------------


namespace smc
{
	//----------------------------------------------
	//
	// class StripFSM
	//    This is the Finite State Machine class
	//
	public class StripFSM : StripperContext
	{
		private State itsState;
		private static string itsVersion = "Test version 1.0";

		// instance variables for each state
		private StartingSlash itsStartingSlashState;
		private Outside itsOutsideState;
		private StarAfterSlash itsStarAfterSlashState;
		private SecondSlash itsSecondSlashState;
		private StartingStar itsStartingStarState;

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
			OutsideIn();
		}

		// accessor functions

		public string GetVersion()
		{
			return itsVersion;
		}
		public string GetCurrentStateName()
		{
			return itsState.StateName();
		}
		public State GetCurrentState()
		{
			return itsState;
		}
		public State GetItsStartingSlashState()
		{
			return itsStartingSlashState;
		}
		public State GetItsOutsideState()
		{
			return itsOutsideState;
		}
		public State GetItsStarAfterSlashState()
		{
			return itsStarAfterSlashState;
		}
		public State GetItsSecondSlashState()
		{
			return itsSecondSlashState;
		}
		public State GetItsStartingStarState()
		{
			return itsStartingStarState;
		}

		// Mutator functions

		public void SetState(State value)
		{
			itsState = value;
		}
		// event functions - forward to the current State

		public void Star()
		{
			itsState.Star(this);
		}

		public void Slash()
		{
			itsState.Slash(this);
		}

		public void Other()
		{
			itsState.Other(this);
		}

		public void EOL()
		{
			itsState.EOL(this);
		}

	}
	//--------------------------------------------
	//
	// public class State
	//    This is the base State class
	//
	public abstract class State
	{
		public abstract string StateName();

		// default event functions

		public virtual void Star(StripFSM name)
		{
			name.FSMError( "Star", name.GetCurrentState());
		}
		public virtual void Slash(StripFSM name)
		{
			name.FSMError( "Slash", name.GetCurrentState());
		}
		public virtual void Other(StripFSM name)
		{
			name.FSMError( "Other", name.GetCurrentState());
		}
		public virtual void EOL(StripFSM name)
		{
			name.FSMError( "EOL", name.GetCurrentState());
		}
	}
	//--------------------------------------------
	//
	// class StartingSlash
	//    handles the startingSlash State and its events
	//
	public class StartingSlash : State
	{
		public override string StateName()
		{ return "startingSlash"; }

		//
		// responds to Star event
		//
		public override void Star(StripFSM name)
		{

			// change the state
			name.SetState(name.GetItsStarAfterSlashState());

			// Exit functions for: startingSlash
			name.StartingSlashOut();
			// Entry functions for: inComment
			name.InCommentIn();
			// Entry functions for: starAfterSlash
			name.StarAfterSlashIn();  }

		//
		// responds to EOL event
		//
		public override void EOL(StripFSM name)
		{
			name.PutSlash();
			name.PutChar();

			// change the state
			name.SetState(name.GetItsOutsideState());

			// Exit functions for: startingSlash
			name.StartingSlashOut();
			// Entry functions for: outside
			name.OutsideIn();  }

		//
		// responds to Slash event
		//
		public override void Slash(StripFSM name)
		{

			// change the state
			name.SetState(name.GetItsSecondSlashState());

			// Exit functions for: startingSlash
			name.StartingSlashOut();
			// Entry functions for: inComment
			name.InCommentIn();
			// Entry functions for: secondSlash
			name.SecondSlashIn();  }

		//
		// responds to Other event
		//
		public override void Other(StripFSM name)
		{
			name.PutSlash();
			name.PutChar();

			// change the state
			name.SetState(name.GetItsOutsideState());

			// Exit functions for: startingSlash
			name.StartingSlashOut();
			// Entry functions for: outside
			name.OutsideIn();  }
	}

	//--------------------------------------------
	//
	// class Outside
	//    handles the outside State and its events
	//
	public class Outside : State
	{
		public override string StateName()
		{ return "outside"; }

		//
		// responds to Slash event
		//
		public override void Slash(StripFSM name)
		{

			// change the state
			name.SetState(name.GetItsStartingSlashState());

			// Exit functions for: outside
			name.OutsideOut();
			// Entry functions for: startingSlash
			name.StartingSlashIn();  }

		//
		// responds to Star event
		//
		public override void Star(StripFSM name)
		{
			name.PutChar();
		}

		//
		// responds to Other event
		//
		public override void Other(StripFSM name)
		{
			name.PutChar();
		}

		//
		// responds to EOL event
		//
		public override void EOL(StripFSM name)
		{
			name.PutChar();
		}
	}

	//--------------------------------------------
	//
	// class StarAfterSlash
	//    handles the starAfterSlash State and its events
	//
	public class StarAfterSlash : State
	{
		public override string StateName()
		{ return "starAfterSlash"; }

		//
		// responds to Star event
		//
		public override void Star(StripFSM name)
		{

			// change the state
			name.SetState(name.GetItsStartingStarState());

			// Exit functions for: starAfterSlash
			name.StarAfterSlashOut();
			// Entry functions for: startingStar
			name.StartingStarIn();  }

		//
		// responds to EOL event
		//
		public override void EOL(StripFSM name)
		{
		}

		//
		// responds to Slash event
		//
		public override void Slash(StripFSM name)
		{
		}

		//
		// responds to Other event
		//
		public override void Other(StripFSM name)
		{
		}
	}

	//--------------------------------------------
	//
	// class SecondSlash
	//    handles the secondSlash State and its events
	//
	public class SecondSlash : State
	{
		public override string StateName()
		{ return "secondSlash"; }

		//
		// responds to EOL event
		//
		public override void EOL(StripFSM name)
		{
			name.PutChar();

			// change the state
			name.SetState(name.GetItsOutsideState());

			// Exit functions for: secondSlash
			name.SecondSlashOut();
			// Exit functions for: inComment
			name.InCommentOut();
			// Entry functions for: outside
			name.OutsideIn();  }

		//
		// responds to Star event
		//
		public override void Star(StripFSM name)
		{
		}

		//
		// responds to Slash event
		//
		public override void Slash(StripFSM name)
		{
		}

		//
		// responds to Other event
		//
		public override void Other(StripFSM name)
		{
		}
	}

	//--------------------------------------------
	//
	// class StartingStar
	//    handles the startingStar State and its events
	//
	public class StartingStar : State
	{
		public override string StateName()
		{ return "startingStar"; }

		//
		// responds to Other event
		//
		public override void Other(StripFSM name)
		{

			// change the state
			name.SetState(name.GetItsStarAfterSlashState());

			// Exit functions for: startingStar
			name.StartingStarOut();
			// Entry functions for: starAfterSlash
			name.StarAfterSlashIn();  }

		//
		// responds to EOL event
		//
		public override void EOL(StripFSM name)
		{

			// change the state
			name.SetState(name.GetItsStarAfterSlashState());

			// Exit functions for: startingStar
			name.StartingStarOut();
			// Entry functions for: starAfterSlash
			name.StarAfterSlashIn();  }

		//
		// responds to Slash event
		//
		public override void Slash(StripFSM name)
		{

			// change the state
			name.SetState(name.GetItsOutsideState());

			// Exit functions for: startingStar
			name.StartingStarOut();
			// Exit functions for: inComment
			name.InCommentOut();
			// Entry functions for: outside
			name.OutsideIn();  }

		//
		// responds to Star event
		//
		public override void Star(StripFSM name)
		{
		}
	}


}
