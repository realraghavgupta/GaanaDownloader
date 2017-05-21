package in.pathri.gaana.utilities;

public class ProgressLogger {
	private String format;
	private long totalCount;
	private long currentCount;
	private double currentProgress;

	public ProgressLogger(String preText) {
		this.format = preText + " %d/%d(%.3f%%)";
	}

	public void resetProgress() {
		this.totalCount = 0;
		this.currentCount = 0;
		this.currentProgress = 0;
		System.out.println("");
	}

	public void setTotalCount(int totalCount) {
		this.totalCount = totalCount;
		this.currentCount = 0;
	}

	public ProgressLogger updateProgress(long updateCount) {
		this.currentCount = this.currentCount + updateCount;
		this.currentProgress = (double) (this.currentCount * 100) / this.totalCount;
		return this;
	}

	public void displayProgress() {
		System.out.print("\r");
		System.out.format(this.format, this.currentCount, this.totalCount, this.currentProgress);
		if(Long.compare(this.currentCount, this.totalCount) == 0){
			System.out.println("");
		}		
	}
}
