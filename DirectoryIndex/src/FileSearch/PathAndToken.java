package FileSearch;

public class PathAndToken implements Comparable<PathAndToken> {
	String directory, firstToken, secondToken;

	public PathAndToken() {
		directory = null;
		firstToken = null;
		secondToken = null;
	}

	public PathAndToken(String directory, String token) {
		super();
		this.directory = directory;
		this.firstToken = token;
	}

	public String getDirectory() {
		return directory;
	}

	public void setDirectory(String directory) {
		this.directory = directory;
	}

	public String getFirstToken() {
		return firstToken;
	}

	public void setFirstToken(String firstToken) {
		this.firstToken = firstToken;
	}

	public String getSecondToken() {
		return secondToken;
	}

	public void setSecondToken(String secondToken) {
		this.secondToken = secondToken;
	}

	@Override
	public int compareTo(PathAndToken comparePathAndToken) {
		// TODO Auto-generated method stub
		String compareToken = comparePathAndToken.getFirstToken();
		return this.getFirstToken().compareTo(compareToken);
	}

}
