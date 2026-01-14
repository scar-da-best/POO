package business.gardine;

public class Gardine {
	
	private String name;
	private String typ;
	private int kostenProQuadratmeter;
	
	public Gardine(String name, String typ, String kostenProQuadratmeter) {
		super();
		this.name = name;
		this.typ = typ;
		this.kostenProQuadratmeter = Integer.parseInt(kostenProQuadratmeter);
	}
	
	public String gibGardineZurueck(char trenner){
  		return this.getName() + trenner 
  			+ this.getTyp() + trenner
  		    + this.getKostenProQuadratmeter();
   	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getTyp() {
		return typ;
	}

	public void setTyp(String typ) {
		this.typ = typ;
	}

	public int getKostenProQuadratmeter() {
		return kostenProQuadratmeter;
	}

	public void setKostenProQuadratmeter(int kostenProQuadratmeter) {
		this.kostenProQuadratmeter = kostenProQuadratmeter;
	}
	
	
	
	

}
