package propublica.datadesign;

class PropublicaDataTable{
	
	private double falsePWhite;
	private double falsePBlack;
	private double falseFWhite;
	private double falseFBlack;
	
	//constructor
	public PropublicaDataTable(double HighNoReoffendWhite, double HighNoReoffendBlack, double LowRecidivateWhite, double LowRecidivateBlack)
	{
		falsePWhite = HighNoReoffendWhite;
		falsePBlack = HighNoReoffendBlack;
		falseFWhite = LowRecidivateWhite;
		falseFBlack = LowRecidivateBlack;
	}
	
	//getters
	public double getHighNoReoffendWhite() {return falsePWhite;}
	public double getHighNoReoffendBlack() {return falsePBlack;}
	public double getLowRecidivateWhite() {return falseFWhite;}
	public double getLowRecidivateBlack() {return falseFBlack;}
	
	public String getPercentage(double decimal_input)
	{
		double percent = 100 * (double) Math.round(decimal_input * 1000) / 1000;
		return "" + percent + "%";
	}
	
	public String toString()
	{
		String myTable = "" +
				"                                                ┌─────────────────────┬──────────────────────────┐\n" +
				"                                                │        White        │     African-American     │\n" +
				"┌───────────────────────────────────────────────┼─────────────────────┼──────────────────────────┤\n" +
				"│    Didn't Re-Offend, but Labeled High Risk    │        " + getPercentage(this.getHighNoReoffendWhite()) + "        │           " + getPercentage(this.getHighNoReoffendBlack()) + "          │\n" +
				"├───────────────────────────────────────────────┼─────────────────────┼──────────────────────────┤\n" +
				"│    Did    Re-Offend, but Labeled Low  Risk    │        " + getPercentage(this.getLowRecidivateWhite()) + "        │           " + getPercentage(this.getLowRecidivateBlack()) + "          │\n" +
				"└───────────────────────────────────────────────┴─────────────────────┴──────────────────────────┘\n" ;
		return myTable;
	}
	
}


