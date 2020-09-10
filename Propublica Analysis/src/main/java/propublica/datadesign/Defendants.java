package propublica.datadesign;

import java.util.ArrayList;

/**
 * Setting up the Defendants class with the necessary constructors, instance
 * variables, methods to read and analyze the data from the csv file
 * 
 * @version February 19, 2020
 */

public class Defendants {

	// creating enums for suitable data
	public static enum sex {
		MALE, FEMALE
	};

	public static enum scoreText {
		LOW, HIGH, MEDIUM
	};

	public static enum cChargeDegree {
		M, F
	};

	public static enum race {
		AFRICAN_AMERICAN, ASIAN, HISPANIC, NATIVE_AMERICAN, OTHER, CAUCASIAN
	};

	// declaration of instance variables
	private String cChargeDesc; // type o0f initial charge
	private boolean twoYearRecid; // status of defendant after two years (re-offend/didn't re-offend)
	private int decileScore; // risk score
	private String rChargeDesc; // type of charge after re-offending
	private String rChargeDegree; // degree of charge after re-offending
	sex Sex; // defendant's sex
	race Race; // defendant's race
	cChargeDegree Cdegree; // degree of initial charge (misdemeanor/felony)
	scoreText Score; // Risk score text
	private int whiteHighNo = 0; // white defendant of high risk and didn't re-offend
	private int whiteLowYes = 0; // white defendant of low risk and re-offended
	private int blackHighNo = 0; // black defendant of high risk and didn't re-offend
	private int blackLowYes = 0; // black defendant of low risk and re-offended
	private int whiteNo = 0; // white defendant who didn't re-offend
	private int whiteYes = 0; // white defendant who re-offended
	private int blackNo = 0; // black defendant who didn't re-offend
	private int blackYes = 0; // black defendant who re-offended

	// constructor that takes the row attributes with their default values that we
	// can deal with
	public Defendants(sex inputSex, race inputRace, cChargeDegree inputCChargeDegree, String inputCChargeDesc,
			int inputDecileScore, scoreText inputScore, boolean inputTwoYearRecid, String inputRChargeDesc,
			String inputRChargeDegree) {

		twoYearRecid = inputTwoYearRecid;
		Race = inputRace;
		Score = inputScore;
		Sex = inputSex;
		cChargeDesc = inputCChargeDesc;
		decileScore = inputDecileScore;
		rChargeDesc = inputRChargeDesc;
		Cdegree = inputCChargeDegree;
		rChargeDegree = inputRChargeDegree;

	}

	// constructor that takes the row attributes as an array of strings
	public Defendants(String[] defendantsData) {

		this(defendantsData[0], defendantsData[1], defendantsData[2], defendantsData[3], defendantsData[4],
				defendantsData[5], defendantsData[6], defendantsData[7], defendantsData[8]);

	}

	// constructor that takes the row attributes as strings
	public Defendants(String inputSex, String inputRace, String inputCChargeDegree, String inputCChargeDesc,
			String inputDecileScore, String inputScore, String inputTwoYearRecid, String inputRChargeDesc,
			String inputRChargeDegree) {

		rChargeDesc = inputRChargeDesc;
		cChargeDesc = inputCChargeDesc;
		rChargeDegree = inputRChargeDegree;

		// if-conditions to convert input data (strings) to other types that we can deal
		// with
		if (inputRace.equals("African-American")) {
			Race = race.AFRICAN_AMERICAN;
		} else if (inputRace.equals("Hispanic")) {
			Race = race.HISPANIC;
		} else if (inputRace.equals("Asian")) {
			Race = race.ASIAN;
		} else if (inputRace.equals("Native American")) {
			Race = race.NATIVE_AMERICAN;
		} else if (inputRace.equals("Other")) {
			Race = race.OTHER;
		} else if (inputRace.equals("Caucasian")) {
			Race = race.CAUCASIAN;
		} else {
			// exception: throwing an error if input is invalid
			throw new IllegalArgumentException("Race is invalid: " + inputRace);
		}
		if (inputTwoYearRecid.equals("1")) {
			twoYearRecid = true;
		} else if (inputTwoYearRecid.equals("0")) {
			twoYearRecid = false;
		} else {
			// exception: throwing an error if input is invalid
			throw new IllegalArgumentException("Two Year Recid is invalid: " + inputTwoYearRecid);
		}
		if (inputScore.equals("Low")) {
			Score = scoreText.LOW;
		} else if (inputScore.equals("Medium")) {
			Score = scoreText.MEDIUM;
		} else if (inputScore.equals("High")) {
			Score = scoreText.HIGH;
		} else {
			// exception: throwing an error if input is invalid
			throw new IllegalArgumentException("Score is invalid: " + inputScore);
		}
		if (inputSex.equals("Male")) {
			Sex = sex.MALE;
		} else if (inputSex.equals("Female")) {
			Sex = sex.FEMALE;
		} else {
			// exception: throwing an error if input is invalid
			throw new IllegalArgumentException("Sex is invalid: " + inputSex);
		}

		// convert input decile scores (strings) to integers
		decileScore = Integer.parseInt(inputDecileScore);
		if (decileScore > 10 || decileScore < 0) {
			// exception: throwing an error if input is invalid
			throw new IllegalArgumentException("Decile Score is invalid: " + inputDecileScore);
		}

		if (inputCChargeDegree.equals("M")) {
			Cdegree = cChargeDegree.M;
		} else if (inputCChargeDegree.equals("F")) {
			Cdegree = cChargeDegree.F;
		} else {
			// exception: throwing an error if input is invalid
			throw new IllegalArgumentException("C Charge Degree: " + inputCChargeDegree);
		}

	}

	@Override
	public String toString() {
		return "Defendants [cChargeDesc=" + cChargeDesc + ", twoYearRecid=" + twoYearRecid + ", decileScore="
				+ decileScore + ", rChargeDesc=" + rChargeDesc + ", rChargeDegree=" + rChargeDegree + ", Sex=" + Sex
				+ ", Race=" + Race + ", Cdegree=" + Cdegree + ", Score=" + Score + "]";
	}

	/**
	 * analyze the defendants data and classify them as one of four cases as in the
	 * Propublica table
	 */

	public void analysis() {

		// check if the defendant is white
		if (Race == race.CAUCASIAN) {
			// check if the defendant didn't re-offend
			if (!twoYearRecid) {
				whiteNo = 1;
				// check if the defendant is high risk
				if (Score == scoreText.HIGH || Score == scoreText.MEDIUM) {
					whiteHighNo = 1;
				}
			}
			// check if the defendant re-offended
			else {
				whiteYes = 1;
				// check if the defendant is low risk
				if (Score == scoreText.LOW) {
					whiteLowYes = 1;
				}
			}
		}
		// check if the defendant is black
		if (Race == race.AFRICAN_AMERICAN) {
			// check if the defendant didn't re-offend
			if (!twoYearRecid) {
				blackNo = 1;
				// check if the defendant is high risk
				if (Score == scoreText.HIGH || Score == scoreText.MEDIUM) {
					blackHighNo = 1;
				}
			}
			// check if the defendant re-offended
			else {
				blackYes = 1;
				// check if the defendant is low risk
				if (Score == scoreText.LOW) {
					blackLowYes = 1;
				}
			}
		}

	}
	
	/**
	 * checked for the charges that shouldn't count as recidivism and changed the value of twoYearRecid to false
	 */
	public void furtherAnalysis() {
		if (rChargeDesc.equals("Driving Under The Influence") || rChargeDesc.equals("Driving License Suspended")
				|| rChargeDesc.equals("Prowling/Loitering") || rChargeDesc.equals("DOC/Fighting/Threatening Words")
				|| rChargeDesc.equals("Susp Drivers Lic 1st Offense") || rChargeDesc.equals("Operating W/O Valid License")
				|| rChargeDesc.equals("Resist/Obstruct W/O Violence") || rChargeDesc.equals("Fail Register Vehicle")
				|| rChargeDesc.equals("Extradition/Defendants") || rChargeDesc.equals("Enter City Park/ Prohibited Hrs")
				|| rChargeDesc.equals("Stalking (Aggravated)")) {
			
			twoYearRecid = false;			
		}
	}

	/**
	 * @return the cChargeDesc (type of initial charge)
	 */
	public String getcChargeDesc() {
		return cChargeDesc;
	}

	/**
	 * @return the twoYearRecid (status of defendant after two years
	 *         (re-offend/didn't re-offend))
	 */
	public boolean isTwoYearRecid() {
		return twoYearRecid;
	}

	/**
	 * @return the decileScore (risk score)
	 */
	public int getDecileScore() {
		return decileScore;
	}

	/**
	 * @return the rChargeDesc (type of charge after re-offending)
	 */
	public String getrChargeDesc() {
		return rChargeDesc;
	}

	/**
	 * @return the rChargeDegree (degree of charge after re-offending)
	 */
	public String getrChargeDegree() {
		return rChargeDegree;
	}

	/**
	 * @return the sex
	 */
	public sex getSex() {
		return Sex;
	}

	/**
	 * @return the race
	 */
	public race getRace() {
		return Race;
	}

	/**
	 * @return the cdegree (initial charge degree)
	 */
	public cChargeDegree getCdegree() {
		return Cdegree;
	}

	/**
	 * @return the risk score as text
	 */
	public scoreText getScore() {
		return Score;
	}

	/**
	 * @return the whiteHighNo (white defendant of high risk and didn't re-offend)
	 */
	public int getWhiteHighNo() {
		return whiteHighNo;
	}

	/**
	 * @return the whiteLowYes (white defendant of low risk and re-offended)
	 */
	public int getWhiteLowYes() {
		return whiteLowYes;
	}

	/**
	 * @return the blackHighNo (black defendant of high risk and didn't re-offend)
	 */
	public int getBlackHighNo() {
		return blackHighNo;
	}

	/**
	 * @return the blackLowYes (black defendant of low risk and re-offended)
	 */
	public int getBlackLowYes() {
		return blackLowYes;
	}

	/**
	 * @return the whiteNo (white defendant who didn't re-offend)
	 */
	public int getWhiteNo() {
		return whiteNo;
	}

	/**
	 * @return the whiteYes (white defendant who re-offended)
	 */
	public int getWhiteYes() {
		return whiteYes;
	}

	/**
	 * @return the blackNo (black defendant who didn't re-offend)
	 */
	public int getBlackNo() {
		return blackNo;
	}

	/**
	 * @return the blackYes (black defendant who re-offended)
	 */
	public int getBlackYes() {
		return blackYes;
	}

}
