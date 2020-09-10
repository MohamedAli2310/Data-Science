package polling.treesheaps;
/**
 * Store one row of polling data 
 * @version April 5, 2020
 */
public class Candidate implements Comparable<Candidate> {
	
	private String lastName;
	private String fullName;
	private Double votePercentage;
	
	/**
	 * Constructs the candidate
	 * @param lastName
	 * @param fullName
	 * @param votePercentage
	 */
	public Candidate(String lastName, String fullName, Double votePercentage) {
		this.lastName = lastName;
		this.fullName = fullName;
		this.votePercentage = votePercentage;
	}
	
	/**
	 * Constructs the candidate with all arguments as Strings
	 * @param lastName
	 * @param fullName
	 * @param votePercentage
	 */
	public Candidate(String lastName, String fullName, String votePercentage) {
		this.lastName = lastName;
		this.fullName = fullName;
		this.votePercentage = Double.parseDouble(votePercentage);
	}
	
	/**
	 * Constructs the candidate with argument as an array of strings
	 * @param lastName
	 * @param fullName
	 * @param votePercentage
	 */
	public Candidate(String[] candidateData) {
		this(candidateData[0], candidateData[1], candidateData[2]);
	}

	/**
	 * @return the lastName
	 */
	public String getLastName() {
		return lastName;
	}

	/**
	 * @return the fullName
	 */
	public String getFullName() {
		return fullName;
	}

	/**
	 * @return the votePercentage
	 */
	public Double getVotePercentage() {
		return votePercentage;
	}
	
	/**
	 * implementing the Comparable interface so that polling data objects are put in order based on the candidate’s polling percentage.
	 */
	public int compareTo(Candidate o) {
		if(this.getVotePercentage().compareTo(o.getVotePercentage()) == 0) {
			// breaking the tie by taking into account the candidate's last name
			return this.getLastName().compareTo(o.getLastName());
		}
		else {
			return this.getVotePercentage().compareTo(o.getVotePercentage()); 
		}
		
	}
	
	/**
	 * return a string with the required format
	 */
	@Override
	public String toString() {
		return fullName + ":" + votePercentage;   
	}
}
