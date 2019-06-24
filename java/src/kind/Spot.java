package kind;

public class Spot {
	String name;
	int emptyflag=0;
	int noresultflag=0;
	String empty="你要上天？";
	String noresult="你到底要去哪？";

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getEmptyflag() {
		return emptyflag;
	}

	public void setEmptyflag(int emptyflag) {
		this.emptyflag = emptyflag;
	}

	public int getNoresultflag() {
		return noresultflag;
	}

	public void setNoresultflag(int noresultflag) {
		this.noresultflag = noresultflag;
	}

	
}
