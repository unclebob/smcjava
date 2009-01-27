using System;

namespace smc
{
	public class StripperContext
	{
		public void FSMError(String s , object o){}

		public void OutsideIn() 
		{
			Console.WriteLine("OutsideIn");
		}
		public void StartingSlashOut()
		{
			Console.WriteLine("StartingSlashOut");
		}
		public void StartingSlashIn() 
		{
			Console.WriteLine("StartingSlashIn");
		}
		public void StartingStarIn() 
		{
			Console.WriteLine("StartingStarIn");
		}
		public void StartingStarOut()
		{
			Console.WriteLine("StartingStarOut");
		}
		public void InCommentIn() 
		{
			Console.WriteLine("InCommentIn");
		}
		public void InCommentOut() 
		{
			Console.WriteLine("InCommentOut");
		}
		public void PutSlash()
		{
			Console.WriteLine("PutSlash");
		}
		public void PutChar()
		{
			Console.WriteLine("PutChar");
		}
		public void StarAfterSlashIn()
		{
			Console.WriteLine("StarAfterSlashIn");
		}
		public void StarAfterSlashOut() 
		{
			Console.WriteLine("StarAfterSlashOut");
		}
		public void SecondSlashIn()
		{
			Console.WriteLine("SecondSlashIn");
		}
		public void SecondSlashOut() 
		{
			Console.WriteLine("SecondSlashOut");
		}
		public void OutsideOut() 
		{
			Console.WriteLine("OutsideOut");
		}
		
		public static void Main()
		{
			StripFSM fsm = new StripFSM();
			fsm.Star();
		}
		
	}
}
