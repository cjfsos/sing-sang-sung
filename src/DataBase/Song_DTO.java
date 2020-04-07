package DataBase;

public class Song_DTO {
	int no;
	String Stitle;
	String singer;
	String album;
	String genre;
	public int getNo() {
		return no;
	}
	public void setNo(int no) {
		this.no = no;
	}
	public String getStitle() {
		return Stitle;
	}
	public void setStitle(String stitle) {
		Stitle = stitle;
	}
	public String getSinger() {
		return singer;
	}
	public void setSinger(String singer) {
		this.singer = singer;
	}
	public String getAlbum() {
		return album;
	}
	public void setAlbum(String album) {
		this.album = album;
	}
	public String getGenre() {
		return genre;
	}
	public void setGenre(String genre) {
		this.genre = genre;
	}
	
	public String[] getArray() {
		String[] returnData = new String[5];
		String zero = Integer.toString(no);
		returnData[0]=zero;
		returnData[1]=this.Stitle;
		returnData[2]=this.singer;
		returnData[3]=this.album;
		returnData[4]=this.genre;		
		return returnData;
	}
}